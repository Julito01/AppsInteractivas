package com.interactive.demo.repository;

import com.interactive.demo.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository <Driver, Integer> {
    @Query(value = "SELECT d FROM Driver d WHERE d.name = :name AND d.lastName = :lastName")
    Driver getByFullName(@Param("name") String name, @Param("lastName") String lastName);
}
