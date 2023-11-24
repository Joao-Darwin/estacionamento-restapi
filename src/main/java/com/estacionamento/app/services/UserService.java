package com.estacionamento.app.services;

import com.estacionamento.app.entities.User;
import com.estacionamento.app.entities.dtos.requests.UserPassword;
import com.estacionamento.app.entities.dtos.requests.UserUpdateInfo;
import com.estacionamento.app.exceptions.NotFoundException;
import com.estacionamento.app.exceptions.NotSaveException;
import com.estacionamento.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User saveUser(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            user = userRepository.save(user);
            return user;
        } catch (DataIntegrityViolationException exception) {
            throw new NotSaveException("User does not save, User already exists");
        }
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, UserUpdateInfo userUpdated) {
        try {
            User userToUpdate = userRepository.findById(id).get();

            updateDataUser(userToUpdate, userUpdated);

            return userRepository.save(userToUpdate);
        } catch (NoSuchElementException exception) {
            throw new NotFoundException(String.format("User does not update. Id: %d", id));
        }
    }

    private void updateDataUser(User userToUpdate, UserUpdateInfo userUpdated) {
        userToUpdate.setName(userUpdated.name());
        userToUpdate.setEmail(userUpdated.email());
    }

    public User changePasswordUser(Long id, UserPassword userWithNewPassword) {
        try {
            User userToChangePassword = userRepository.findById(id).get();

            userToChangePassword.setPassword(encoder.encode(userWithNewPassword.password()));

            return userRepository.save(userToChangePassword);
        } catch (NoSuchElementException exception) {
            throw new NotFoundException(String.format("User does not remove, not find. Id: %d", id));
        }
    }

    public void removeUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (NoSuchElementException exception) {
            throw new NotFoundException(String.format("User does not remove, not find. Id: %d", id));
        }
    }
}
