package com.estacionamento.app.services;

import com.estacionamento.app.entities.Vehicle;
import com.estacionamento.app.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle saveVehicle(Vehicle vehicle) throws Exception{
        vehicleRepository.save(vehicle);
        return vehicle;
    }
}
