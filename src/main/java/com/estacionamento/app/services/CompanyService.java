package com.estacionamento.app.services;

import com.estacionamento.app.entities.Company;
import com.estacionamento.app.entities.Vehicle;
import com.estacionamento.app.exceptions.NotFoundException;
import com.estacionamento.app.exceptions.NotSaveException;
import com.estacionamento.app.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public List<Vehicle> findAllVehiclesByCompany(Long idCompany) {
        try {
            Company company = companyRepository.findById(idCompany).get();

            return company.getVehicles();
        } catch (NoSuchElementException exception) {
            throw new NotFoundException(String.format("Company not finded. Id: %d", idCompany));
        }
    }

    public Company updateCompany(Long idCompany, Company companyUpdated) {
        try {
            Company companyToUpdate = companyRepository.findById(idCompany).get();

            updateCompanyData(companyUpdated, companyToUpdate);

            companyRepository.save(companyToUpdate);
            return companyToUpdate;
        } catch(NoSuchElementException exception) {
            throw new NotFoundException(String.format("Company to update not finded. Id: %d", idCompany));
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
            throw new NotFoundException(String.format("Company to remove not finded. Id: %d", idCompany));
        }
    }
}
