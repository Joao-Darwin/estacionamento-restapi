package com.estacionamento.app.services;

import com.estacionamento.app.entities.User;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

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

    public User updateUser(Long id, User userUpdated) {
        try {
            User userToUpdate = userRepository.findById(id).get();

            updateDataUser(userToUpdate, userUpdated);

            return userRepository.save(userToUpdate);
        } catch (NoSuchElementException exception) {
            throw new NotFoundException(String.format("User does not update. Id: %d", id));
        }
    }

    private void updateDataUser(User userToUpdate, User userUpdated) {
        userToUpdate.setName(userUpdated.getName());
        userToUpdate.setEmail(userUpdated.getEmail());
        userToUpdate.setPassword(userUpdated.getPassword());
        userToUpdate.setRole(userUpdated.getRole());
    }

    public void removeUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (NoSuchElementException exception) {
            throw new NotFoundException(String.format("User does not remove, not find. Id: %d", id));
        }
    }
}
