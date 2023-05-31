package com.interactive.demo.services;

import com.interactive.demo.dtos.DriverCarDTO;
import com.interactive.demo.dtos.DriverDTO;
import com.interactive.demo.exception.ApiRequestException;
import com.interactive.demo.model.Driver;
import com.interactive.demo.repository.DriverRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@Service
public class DriverService {
    private final DriverRepository driverRepository;
    private final ModelMapper modelMapper;

    public DriverService(DriverRepository repository, ModelMapper modelMapper) {
        this.driverRepository = repository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<DriverDTO> add(DriverDTO driverDTO) {
        Driver driver = this.modelMapper.map(driverDTO, Driver.class);
        Driver createdDriver = this.driverRepository.save(driver);

        return ResponseEntity.status(CREATED).body(this.modelMapper.map(createdDriver, DriverDTO.class));
    }

    public ResponseEntity<List<DriverCarDTO>> getDriversWithCar() {
        List<DriverCarDTO> driverCarDTOS = new ArrayList<>();

        for (Driver driver : this.driverRepository.findAll()) {
            driverCarDTOS.add(this.modelMapper.map(driver, DriverCarDTO.class));
        }

        return ResponseEntity.ok(driverCarDTOS);
    }

    public ResponseEntity<List<DriverDTO>> getAll() {
        List<DriverDTO> driverDTOS = new ArrayList<>();

        for (Driver driver : this.driverRepository.findAll()) {
            driverDTOS.add(this.modelMapper.map(driver, DriverDTO.class));
        }

        return ResponseEntity.ok(driverDTOS);
    }

    public ResponseEntity<DriverDTO> update(Integer driverId, DriverDTO driverDTO) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(
                        () -> new ApiRequestException(
                                String.format("Piloto con id [%s] no encontrado", driverId),
                                NOT_FOUND
                        ));

        driver.setName(driverDTO.getName());
        Driver updatedDriver = driverRepository.save(driver);

        return ResponseEntity.ok(this.modelMapper.map(updatedDriver, DriverDTO.class));
    }

    public ResponseEntity<Map<String, Object>> delete(Integer driverId) {
        Driver driver = this.driverRepository.findById(driverId)
                .orElseThrow(
                        () -> new ApiRequestException(
                                String.format("Piloto con id [%s] no encontrado", driverId),
                                NOT_FOUND
                        )
                );

        this.driverRepository.delete(driver);

        Map<String, Object> response = new HashMap<>();
        response.put("result", true);
        response.put("message", "Piloto con id [%s] eliminado");

        return ResponseEntity.ok(response);
    }
}