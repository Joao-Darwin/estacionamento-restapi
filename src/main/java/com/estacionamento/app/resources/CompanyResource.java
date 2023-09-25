package com.estacionamento.app.resources;

import com.estacionamento.app.entities.Company;
import com.estacionamento.app.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyResource {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
        try {
            company = companyService.saveCompany(company);
            return ResponseEntity.status(HttpStatus.CREATED).body(company);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        try {
            List<Company> allCompanies = companyService.findAll();
            return ResponseEntity.status(HttpStatus.FOUND).body(allCompanies);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();        }
    }
}
