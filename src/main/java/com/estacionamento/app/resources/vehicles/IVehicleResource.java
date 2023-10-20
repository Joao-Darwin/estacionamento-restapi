package com.estacionamento.app.resources.vehicles;

import com.estacionamento.app.entities.Vehicle;
import com.estacionamento.app.entities.dtos.responses.VehicleDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IVehicleResource {

    ResponseEntity<?> saveVehicle(Vehicle vehicle);

    ResponseEntity<List<VehicleDTO>> findAll();

    ResponseEntity<?> findVehicleById(Long idVehicle);

    ResponseEntity<?> findVehicleByPlate(String plate);

    ResponseEntity<?> updateVehicle(Long id, Vehicle vehicleUpdated);

    ResponseEntity<?> deleteVehicle(Long id);
}
