package com.interactive.demo.services;


import com.interactive.demo.model.Driver;
import com.interactive.demo.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository repository) {
        this.driverRepository = repository;
    }

    // Create
    public ResponseEntity add(Driver driver) {

        try{
            driverRepository.save(driver);
            return ResponseEntity.status(CREATED).build();
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }

    }
    // Read
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    // Update
    public ResponseEntity update(Integer id,Driver driver) {
        try {
            Driver driverTemporaly = driverRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Driver not found"));
            driverTemporaly.setName(driver.getName());
            driverRepository.save(driverTemporaly);
            return ResponseEntity.status(OK).build();
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete

    public ResponseEntity delete(Integer id){
        try {
            driverRepository.deleteById(id);
            return ResponseEntity.status(OK).build();
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }


}
