package com.estacionamento.app.services;

import com.estacionamento.app.entities.Vehicle;
import com.estacionamento.app.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle saveVehicle(Vehicle vehicle) throws Exception{
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }
}
