package com.estacionamento.app.services;

import com.estacionamento.app.entities.Company;
import com.estacionamento.app.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company saveCompany(Company company) throws Exception{
        return companyRepository.save(company);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
