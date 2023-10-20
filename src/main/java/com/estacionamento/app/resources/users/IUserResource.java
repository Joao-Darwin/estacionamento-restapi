package com.estacionamento.app.resources.users;

import com.estacionamento.app.entities.User;
import org.springframework.http.ResponseEntity;

public interface IUserResource {

    ResponseEntity<?> save(User user);

    ResponseEntity<?> findAll();

    ResponseEntity<?> updateUser(Long id, User userUpdated);

    ResponseEntity<?> removeUser(Long id);
}
