package com.estacionamento.app.entities;

import com.estacionamento.app.entities.enums.VehiclesType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String name;
    @Setter
    @Column(unique = true, nullable = false)
    private String cnpj;
    @Setter
    @Column(nullable = false)
    private String address;
    @Setter
    @Column(nullable = false)
    private String phone;
    @Setter
    @Column(nullable = false)
    private int spacesForCars;
    @Setter
    @Column(nullable = false)
    private int spacesForMotorcycles;

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Vehicle> vehicles = new ArrayList<>();

    public boolean verifySpacesCarIsFull() {
        int numberCarOnSpaces = 0;

        if(vehicles.isEmpty()) {
            return false;
        }

        for(Vehicle vehicle : vehicles) {
            if(vehicle.getType() == VehiclesType.CAR) {
                numberCarOnSpaces += 1;
            }
        }

        return numberCarOnSpaces == spacesForCars;
    }

    public boolean verifySpacesMotorcyclesIsFull() {
        int numberMotorcyclesOnSpaces = 0;

        if(vehicles.isEmpty()) {
            return false;
        }

        for(Vehicle vehicle : vehicles) {
            if(vehicle.getType() == VehiclesType.MOTORCYCLE) {
                numberMotorcyclesOnSpaces += 1;
            }
        }

        return numberMotorcyclesOnSpaces == spacesForMotorcycles;
    }

    public int getAllSpaces() {
        return spacesForCars + spacesForMotorcycles;
    }

    public int getOccupiedSpaces() {
        return vehicles.size();
    }
}
