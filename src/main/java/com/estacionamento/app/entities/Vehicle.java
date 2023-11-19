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
@Table(name = "vehicles", uniqueConstraints = @UniqueConstraint(columnNames = {"plate", "entrydate"}))
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
    @Column(nullable = false)
    private String plate;

    @Setter
    private boolean leave = false;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-3")
    private Instant entryDate = Instant.now();

    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-3")
    private Instant departureDate = null;

    @Setter
    @Column(nullable = false)
    private VehiclesType type;

    @Setter
    @ManyToOne
    private Company company;
}
