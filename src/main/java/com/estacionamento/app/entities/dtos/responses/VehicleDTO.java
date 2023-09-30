package com.estacionamento.app.entities.dtos.responses;

import com.estacionamento.app.entities.enums.VehiclesType;

public record VehicleDTO(Long id, String model, String plate, VehiclesType type, CompanyDTO company) {

}
