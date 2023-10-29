package com.estacionamento.app.resources.vehicles;

import com.estacionamento.app.entities.Vehicle;
import com.estacionamento.app.entities.dtos.responses.VehicleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Vehicle", description = "Resources to vehicles")
public interface IVehicleResource {

    @Operation(summary = "Save a vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehicle created success"),
            @ApiResponse(responseCode = "401", description = "User don't authorization"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<?> saveVehicle(@RequestBody Vehicle vehicle);

    @Operation(summary = "Find all vehicles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "User don't authorization"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<List<VehicleDTO>> findAll();

    @Operation(summary = "Find vehicles by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Vehicle don't found"),
            @ApiResponse(responseCode = "401", description = "User don't authorization"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<?> findVehicleById(@Parameter(description = "Vehicle's Id") Long idVehicle);

    @Operation(summary = "Find vehicles by plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Vehicle don't found"),
            @ApiResponse(responseCode = "401", description = "User don't authorization"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<?> findVehicleByPlate(@Parameter(description = "Vehicle's Plate") String plate);

    @Operation(summary = "Update vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle updated!"),
            @ApiResponse(responseCode = "304", description = "Vehicle don't modified"),
            @ApiResponse(responseCode = "404", description = "Vehicle don't found"),
            @ApiResponse(responseCode = "401", description = "User don't authorization"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<?> updateVehicle(@Parameter(description = "Vehicle's Id") Long id, @RequestBody Vehicle vehicleUpdated);

    @Operation(summary = "Remove vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle removed"),
            @ApiResponse(responseCode = "404", description = "Vehicle don't found"),
            @ApiResponse(responseCode = "401", description = "User don't authorization"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<?> deleteVehicle(@Parameter(description = "Vehicle's Id") Long id);
}
