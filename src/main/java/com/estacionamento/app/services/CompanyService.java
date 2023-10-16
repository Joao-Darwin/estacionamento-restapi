package com.estacionamento.app.services;

import com.estacionamento.app.entities.Company;
import com.estacionamento.app.entities.Vehicle;
import com.estacionamento.app.entities.dtos.responses.DataCompanyDTO;
import com.estacionamento.app.entities.dtos.responses.OnlyVehicleDTO;
import com.estacionamento.app.exceptions.NotFoundException;
import com.estacionamento.app.exceptions.NotSaveException;
import com.estacionamento.app.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company saveCompany(Company company) {
        try {
            company = companyRepository.save(company);
            return company;
        } catch (DataIntegrityViolationException exception) {
            throw new NotSaveException("Company does not save, company already exists");
        }
    }

    public List<DataCompanyDTO> findAll() {
        List<Company> companies = companyRepository.findAll();
        List<DataCompanyDTO> companiesDTOS = new ArrayList<>();

        convertListCompanyToDataCompanyDTO(companies, companiesDTOS);

        return companiesDTOS;
    }

    private void convertListCompanyToDataCompanyDTO(List<Company> companies, List<DataCompanyDTO> companiesDTOS) {
        for (Company company : companies) {
            DataCompanyDTO companyDTO = new DataCompanyDTO(company.getId(), company.getName(), company.getAllSpaces(), company.getOccupiedSpaces());
            companiesDTOS.add(companyDTO);
        }
    }


    public List<OnlyVehicleDTO> findAllVehiclesByCompany(Long idCompany) {
        try {
            Company company = companyRepository.findById(idCompany).get();
            List<Vehicle> allVehicles = company.getVehicles();
            List<OnlyVehicleDTO> allVehiclesDTO = new ArrayList<>();

            generatedAllVehiclesDTO(allVehicles, allVehiclesDTO);

            return allVehiclesDTO;
        } catch (NoSuchElementException exception) {
            throw new NotFoundException(String.format("Company not found. Id: %d", idCompany));
        }
    }

    private void generatedAllVehiclesDTO(List<Vehicle> allVehicles, List<OnlyVehicleDTO> allVehiclesDTO) {
        for (Vehicle vehicle : allVehicles) {
            OnlyVehicleDTO vehicleDTO = new OnlyVehicleDTO(vehicle.getId(), vehicle.getModel(), vehicle.getPlate(), vehicle.getType());

            allVehiclesDTO.add(vehicleDTO);
        }
    }

    public Company updateCompany(Long idCompany, Company companyUpdated) {
        try {
            Company companyToUpdate = companyRepository.findById(idCompany).get();

            updateCompanyData(companyUpdated, companyToUpdate);

            companyRepository.save(companyToUpdate);
            return companyToUpdate;
        } catch (NoSuchElementException exception) {
            throw new NotFoundException(String.format("Company to update not found. Id: %d", idCompany));
        }
    }

    private void updateCompanyData(Company companyUpdated, Company companyToUpdate) {
        companyToUpdate.setName(companyUpdated.getName());
        companyToUpdate.setCnpj(companyUpdated.getCnpj());
        companyToUpdate.setAddress(companyUpdated.getAddress());
        companyToUpdate.setPhone(companyUpdated.getPhone());
        companyToUpdate.setSpacesForCars(companyUpdated.getSpacesForCars());
        companyToUpdate.setSpacesForMotorcycles(companyUpdated.getSpacesForMotorcycles());
    }

    public void deleteCompany(Long idCompany) {
        try {
            companyRepository.deleteById(idCompany);
        } catch (NoSuchElementException exception) {
            throw new NotFoundException(String.format("Company to remove not found. Id: %d", idCompany));
        }
    }
}
