package com.interactive.demo.services;

import com.interactive.demo.dtos.CarDTO;
import com.interactive.demo.exception.ApiRequestException;
import com.interactive.demo.model.Car;
import com.interactive.demo.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public CarService(CarRepository repository, ModelMapper modelMapper) {

        this.carRepository = repository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<CarDTO> add(CarDTO carDTO) {
        Car car = this.modelMapper.map(carDTO, Car.class);
        Car createdCar = this.carRepository.save(car);
        return ResponseEntity.status(CREATED).body(this.modelMapper.map(createdCar, CarDTO.class));
    }

    public ResponseEntity<List<CarDTO>> getAll() {
        List<CarDTO> carDTOS = new ArrayList<>();

        for (Car car : this.carRepository.findAll()) {
            carDTOS.add(this.modelMapper.map(car, CarDTO.class));
        }

        return ResponseEntity.ok(carDTOS);
    }

    public ResponseEntity<CarDTO> update(Integer carId, CarDTO carDTO) {
        Car car = carRepository.findById(carId)
                .orElseThrow(
                        () -> new ApiRequestException(
                                String.format("Auto con id [%s] no encontrado.", carId),
                                NOT_FOUND
                        ));

        car.setPlate(carDTO.getPlate());
        Car updatedCar = carRepository.save(car);

        return ResponseEntity.ok(this.modelMapper.map(updatedCar, CarDTO.class));
    }

    public ResponseEntity<Map<String, Object>> deleteCar(Integer carId) {
        Car car = this.carRepository.findById(carId)
                .orElseThrow(
                        () -> new ApiRequestException(
                                String.format("Auto con id [%s] no encontrado.", carId),
                                NOT_FOUND
                        )
                );

        this.carRepository.delete(car);

        Map<String, Object> response = new HashMap<>();
        response.put("result", true);
        response.put("message", String.format("Auto con id [%s] eliminado con éxito.", carId));

        return ResponseEntity.ok(response);
    }
}
