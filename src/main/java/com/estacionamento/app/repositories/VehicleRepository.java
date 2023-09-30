package com.estacionamento.app.repositories;

import com.estacionamento.app.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "SELECT * FROM VEHICLE WHERE PLATE = :plate", nativeQuery = true)
    Vehicle findByPlate(@Param("plate") String plate);
}
