package com.interactive.demo.services;

import com.interactive.demo.dtos.DriverCarDTO;
import com.interactive.demo.dtos.DriverDTO;
import com.interactive.demo.exception.ApiRequestException;
import com.interactive.demo.model.Car;
import com.interactive.demo.model.Driver;
import com.interactive.demo.repository.CarRepository;
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
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public DriverService(DriverRepository driverRepository, CarRepository carRepository, ModelMapper modelMapper) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<DriverDTO> add(DriverDTO driverDTO) {
        Driver driverFound = this.driverRepository.getByFullName(driverDTO.getName(), driverDTO.getLastName());

                if (driverFound != null)
                    throw new ApiRequestException(
                            "Ese piloto ya existe!",
                            CONFLICT
                    );

        Car car = this.carRepository.findById(driverDTO.getCarId()).orElseThrow(
                () -> new ApiRequestException(
                        String.format("No se encontró el auto con id %s", driverDTO.getCarId()),
                        NOT_FOUND
                )
        );

        Driver driver = new Driver(
                driverDTO.getName(),
                driverDTO.getLastName(),
                car
        );

        Driver savedDriver = this.driverRepository.save(driver);

        car.getDrivers().add(savedDriver);
        this.carRepository.save(car);

        return ResponseEntity.status(CREATED).body(this.modelMapper.map(savedDriver, DriverDTO.class));
    }

    public ResponseEntity<List<DriverCarDTO>> getDriversWithCar() {
        List<DriverCarDTO> driverCarDTOS = new ArrayList<>();

        for (Driver driver : this.driverRepository.findAll()) {
            DriverCarDTO driverCarDTO = this.modelMapper.map(driver, DriverCarDTO.class);

            if (driverCarDTO.getCar() != null)
                driverCarDTOS.add(driverCarDTO);
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