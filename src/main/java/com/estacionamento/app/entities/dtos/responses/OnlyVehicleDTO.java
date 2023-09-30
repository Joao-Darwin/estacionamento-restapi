package com.estacionamento.app.entities.dtos.responses;

import com.estacionamento.app.entities.enums.VehiclesType;

public record OnlyVehicleDTO (Long id, String model, String plate, VehiclesType type) {
}
