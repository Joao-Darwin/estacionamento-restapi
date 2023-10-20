package com.estacionamento.app.resources.companies;

import com.estacionamento.app.entities.Company;
import com.estacionamento.app.entities.dtos.responses.DataCompanyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ICompanyResource {

    ResponseEntity<?> saveCompany(Company company);

    ResponseEntity<List<DataCompanyDTO>> findAll();

    ResponseEntity<?> findVehiclesByCompany(Long id);

    ResponseEntity<?> updateCompany(Long idCompany, @RequestBody Company companyUpdated);

    ResponseEntity<?> deleteCompany(Long id);
}
