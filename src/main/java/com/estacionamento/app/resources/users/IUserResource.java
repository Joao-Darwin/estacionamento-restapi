package com.estacionamento.app.resources.users;

import com.estacionamento.app.entities.User;
import com.estacionamento.app.entities.dtos.requests.UserChangePassword;
import com.estacionamento.app.entities.dtos.requests.UserUpdateInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Users", description = "Users Resources")
public interface IUserResource {

    @Operation(summary = "Create user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created!"),
            @ApiResponse(responseCode = "501", description = "Not implemented", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<?> save(@RequestBody User user);

    @Operation(summary = "Find all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success!"),
            @ApiResponse(responseCode = "401", description = "Don't Authorization", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<?> findAll();

    @Operation(summary = "Update User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success!"),
            @ApiResponse(responseCode = "404", description = "User don't found"),
            @ApiResponse(responseCode = "401", description = "Don't Authorization", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<?> updateUser(@Parameter(description = "user id") Long id, @RequestBody UserUpdateInfo userUpdated);

    @Operation(summary = "Update password User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password changed!"),
            @ApiResponse(responseCode = "404", description = "User don't found"),
            @ApiResponse(responseCode = "401", description = "Don't Authorization", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<?> changePasswordUser(@Parameter(description = "user id") Long id, @RequestBody UserChangePassword userChangePassword);

    @Operation(summary = "Remove User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success!"),
            @ApiResponse(responseCode = "404", description = "User don't found"),
            @ApiResponse(responseCode = "401", description = "Don't Authorization", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<?> removeUser(@Parameter(description = "user id") Long id);
}
