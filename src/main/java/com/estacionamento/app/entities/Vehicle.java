package com.estacionamento.app.entities;

import com.estacionamento.app.entities.enums.VehiclesType;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false, unique = true)
    private String plate;
    @Column(nullable = false)
    private VehiclesType type;

    @ManyToOne
    private Company company;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, String color, String plate, VehiclesType type, Company company) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.plate = plate;
        this.type = type;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public VehiclesType getType() {
        return type;
    }

    public void setType(VehiclesType type) {
        this.type = type;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
