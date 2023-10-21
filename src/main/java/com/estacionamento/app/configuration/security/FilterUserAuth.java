package com.estacionamento.app.configuration.security;

import com.estacionamento.app.entities.User;
import com.estacionamento.app.entities.dtos.responses.UserDTO;
import com.estacionamento.app.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterUserAuth extends OncePerRequestFilter {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String POST_METHOD_REQUEST = "POST";
    private static final String PATH_API_DOCUMENTATION = "/swagger-ui";
    private static final String PATH_USER_RESOURCE = "/users";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String servletPathRequest = request.getServletPath();
        String methodRequest = request.getMethod();

        if(isServletPathRequestWithoutFilterAuthorization(servletPathRequest, methodRequest)) {
            filterChain.doFilter(request, response);
        } else {
            UserDTO userCredentials = getCredentialsUser(request);
            if(verifyCredentialsUser(userCredentials)) {
                filterChain.doFilter(request, response);
            } else {
                response.sendError(401, "User dont authorization");
            }
        }
    }

    private boolean isServletPathRequestWithoutFilterAuthorization(String servletPathRequest, String methodRequest) {
        if(servletPathRequest.startsWith(PATH_API_DOCUMENTATION)) {
            return true;
        }

        return servletPathRequest.startsWith(PATH_USER_RESOURCE) && methodRequest.equals(POST_METHOD_REQUEST);
    }

    private UserDTO getCredentialsUser(HttpServletRequest request) {
        String authorization = request.getHeader(HEADER_AUTHORIZATION);

        String authEncoder = authorization.replaceAll("^Basic\\h", "");
        String authDecoder = new String(Base64.getDecoder().decode(authEncoder));

        String[] credentials = authDecoder.split(":");

        String email = credentials[0];
        String password = credentials[1];

        return new UserDTO(email, password);
    }

    private boolean verifyCredentialsUser(UserDTO user) {
        return userHasEmailValid(user.email()) && userHasPasswordCurrent(user);
    }

    private boolean userHasEmailValid(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    private boolean userHasPasswordCurrent(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.email());
        return encoder.matches(userDTO.password(), user.getPassword());
    }
}