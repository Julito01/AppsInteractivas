package com.interactive.demo.repository;

import com.interactive.demo.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository <Driver, Integer> {
}
