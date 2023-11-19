package com.estacionamento.app.resources.vehicles.impl;

import com.estacionamento.app.entities.Vehicle;
import com.estacionamento.app.entities.dtos.responses.AllDataVehicle;
import com.estacionamento.app.entities.dtos.responses.ErrorResponse;
import com.estacionamento.app.entities.dtos.responses.OnlyVehicleDTO;
import com.estacionamento.app.entities.dtos.responses.VehicleDTO;
import com.estacionamento.app.exceptions.NotFoundException;
import com.estacionamento.app.exceptions.NotSaveException;
import com.estacionamento.app.resources.vehicles.IVehicleResource;
import com.estacionamento.app.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vehicles")
public class VehicleResource implements IVehicleResource {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<?> saveVehicle(@RequestBody Vehicle vehicle) {
        try {
            OnlyVehicleDTO vehicleDTO = vehicleService.saveVehicle(vehicle);
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicleDTO);
        } catch (NotSaveException exception) {
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> findAll() {
        try {
            List<VehicleDTO> vehicles = vehicleService.findAll();
            return ResponseEntity.status(HttpStatus.FOUND).body(vehicles);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findVehicleById(@PathVariable("id") Long idVehicle) {
        try {
            AllDataVehicle vehicleDTO = vehicleService.findVehicleById(idVehicle);

            return ResponseEntity.status(HttpStatus.OK).body(vehicleDTO);
        } catch (NotFoundException exception) {
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(errorResponse);
        }
    }

    @GetMapping(value = "/findByPlate")
    public ResponseEntity<?> findVehicleByPlate(@RequestParam("plate") String plate) {
        try {
            AllDataVehicle allDataVehicle = vehicleService.findVehicleByPlate(plate);

            return ResponseEntity.status(HttpStatus.FOUND).body(allDataVehicle);
        } catch (NotFoundException exception) {
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(errorResponse);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleUpdated) {
        try {
            OnlyVehicleDTO vehicleDTO = vehicleService.updateVehicle(id, vehicleUpdated);
            return ResponseEntity.status(HttpStatus.OK).body(vehicleDTO);
        } catch (NotFoundException | NotSaveException exception) {
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(errorResponse);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        try {
            vehicleService.deleteVehicle(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundException exception) {
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(errorResponse);
        }
    }
}
