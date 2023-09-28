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


    public Vehicle updateVehicle(Long idVehicle, Vehicle vehicleUpdated) {
        try {
            Vehicle vehicleToUpdate = vehicleRepository.findById(idVehicle).get();

            updateVehicleDatas(vehicleToUpdate, vehicleUpdated);

            vehicleRepository.save(vehicleToUpdate);
            return vehicleToUpdate;
        } catch (NoSuchElementException exception) {
            throw new NotFoundException(String.format("Vehicle to update not finded. Id: %d", idVehicle));
        } catch (DataIntegrityViolationException exception) {
            throw new NotSaveException("Vehicle does not save, vehicle already exists");
        }
    }

    private void updateVehicleDatas(Vehicle vehicleToUpdate, Vehicle vehicleUpdated) {
        vehicleToUpdate.setBrand(vehicleUpdated.getBrand());
        vehicleToUpdate.setModel(vehicleUpdated.getModel());
        vehicleToUpdate.setColor(vehicleUpdated.getColor());
        vehicleToUpdate.setPlate(vehicleUpdated.getPlate());
        vehicleToUpdate.setType(vehicleUpdated.getType());
        vehicleToUpdate.setCompany(vehicleUpdated.getCompany());
    }

    public void deleteVehicle(Long idVehicle) {
        try {
            vehicleRepository.deleteById(idVehicle);
        } catch (NoSuchElementException exception) {
            throw new NotFoundException(String.format("Vehicle to remove not finded. Id: %d", idVehicle));
        }
    }
}
