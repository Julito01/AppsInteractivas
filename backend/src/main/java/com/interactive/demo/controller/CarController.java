package com.interactive.demo.controller;


import com.interactive.demo.dtos.CarDTO;
import com.interactive.demo.services.CarService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/cars")
@CrossOrigin(origins = "*")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<CarDTO> add(@RequestBody final @NotNull CarDTO car) {
        return carService.add(car);
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAll() {
        return carService.getAll();
    }

    @PutMapping("{carId}")
    public ResponseEntity<CarDTO> update(@PathVariable(value = "carId") final @NotNull Integer id, @RequestBody final @NotNull CarDTO car) {
        return carService.update(id, car);
    }

    @DeleteMapping("{carId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable(value = "carId") final @NotNull Integer id) {
        return carService.deleteCar(id);
    }
}
