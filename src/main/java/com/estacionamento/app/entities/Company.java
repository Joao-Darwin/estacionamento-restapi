package com.estacionamento.app.entities;

import com.estacionamento.app.entities.enums.VehiclesType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String cnpj;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private int spacesForCars;
    @Column(nullable = false)
    private int spacesForMotorcycles;

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Vehicle> vehicles = new ArrayList<>();

    public Company() {
    }

    public Company(String name, String cnpj, String address, String phone, int spacesForCars, int spacesForMotorcycles) {
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
        this.phone = phone;
        this.spacesForCars = spacesForCars;
        this.spacesForMotorcycles = spacesForMotorcycles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSpacesForCars() {
        return spacesForCars;
    }

    public void setSpacesForCars(int spacesForCars) {
        this.spacesForCars = spacesForCars;
    }

    public int getSpacesForMotorcycles() {
        return spacesForMotorcycles;
    }

    public void setSpacesForMotorcycles(int spacesForMotorcycles) {
        this.spacesForMotorcycles = spacesForMotorcycles;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

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
