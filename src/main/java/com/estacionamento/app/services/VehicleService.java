package com.estacionamento.app.services;

import com.estacionamento.app.entities.Vehicle;
import com.estacionamento.app.exceptions.NotFoundException;
import com.estacionamento.app.exceptions.NotSaveException;
import com.estacionamento.app.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle saveVehicle(Vehicle vehicle) {
        try {
            vehicleRepository.save(vehicle);
            return vehicle;
        } catch (DataIntegrityViolationException exception) {
            throw new NotSaveException("Vehicle does not save, vehicle already exists");
        }
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }
}
