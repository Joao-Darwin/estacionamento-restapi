package com.estacionamento.app.entities.dtos.responses;

import com.estacionamento.app.entities.enums.VehiclesType;

import java.time.Instant;

public record AllDataVehicle (Long id, String brand, String model, String color, String plate, Instant entryDate, VehiclesType type, CompanyDTO company) {
}
