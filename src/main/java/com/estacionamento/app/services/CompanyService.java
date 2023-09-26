package com.estacionamento.app.services;

import com.estacionamento.app.entities.Company;
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

    public void deleteCompany(Long idCompany) {
        try {
            companyRepository.deleteById(idCompany);
        } catch (NoSuchElementException exception) {
            throw new NotFoundException(String.format("Company to remove not finded. Id: %d", idCompany));
        }
    }
}
