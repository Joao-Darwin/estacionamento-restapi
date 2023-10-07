package com.estacionamento.app.resources;

import com.estacionamento.app.entities.User;
import com.estacionamento.app.entities.dtos.responses.ErrorResponse;
import com.estacionamento.app.exceptions.NotFoundException;
import com.estacionamento.app.exceptions.NotSaveException;
import com.estacionamento.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        try {
            user = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (NotSaveException exception) {
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(errorResponse);
        } catch (Exception exception) {
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<User> users = userService.findAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception exception) {
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userUpdated) {
        try {
            User updatedUser = userService.updateUser(id, userUpdated);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } catch (NotFoundException exception) {
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception exception) {
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUser(@PathVariable Long id) {
        try {
            userService.removeUser(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundException exception) {
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception exception) {
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
