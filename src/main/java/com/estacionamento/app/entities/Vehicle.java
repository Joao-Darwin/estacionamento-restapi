package com.estacionamento.app.entities;

import com.estacionamento.app.entities.enums.VehiclesType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Entity
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String brand;
    @Setter
    @Column(nullable = false)
    private String model;
    @Setter
    @Column(nullable = false)
    private String color;
    @Setter
    @Column(nullable = false, unique = true)
    private String plate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-3")
    private Instant entryDate = Instant.now();
    @Setter
    @Column(nullable = false)
    private VehiclesType type;

    @Setter
    @ManyToOne
    private Company company;
}
