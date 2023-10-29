package com.estacionamento.app.resources.companies;

import com.estacionamento.app.entities.Company;
import com.estacionamento.app.entities.dtos.responses.DataCompanyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Company Resources", description = "Resources to companies")
public interface ICompanyResource {

    @Operation(summary = "Save a company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Company created success"),
            @ApiResponse(responseCode = "401", description = "User don't authorization"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<?> saveCompany(@RequestBody Company company);

    @Operation(summary = "Find all companies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "User don't authorization"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<List<DataCompanyDTO>> findAll();

    @Operation(summary = "Find vehicles by Company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Company don't found"),
            @ApiResponse(responseCode = "401", description = "User don't authorization"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<?> findVehiclesByCompany(@Parameter(description = "Company's Id") Long id);

    @Operation(summary = "Update company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company updated!"),
            @ApiResponse(responseCode = "304", description = "Company don't modified"),
            @ApiResponse(responseCode = "404", description = "Company don't found"),
            @ApiResponse(responseCode = "401", description = "User don't authorization"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<?> updateCompany(@Parameter(description = "Company's Id") Long idCompany, @RequestBody Company companyUpdated);

    @Operation(summary = "Remove company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company removed"),
            @ApiResponse(responseCode = "404", description = "Company don't found"),
            @ApiResponse(responseCode = "401", description = "User don't authorization"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<?> deleteCompany(@Parameter(description = "Company's Id") Long id);
}
