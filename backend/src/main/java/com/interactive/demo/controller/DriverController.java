package com.interactive.demo.controller;

import com.interactive.demo.dtos.DriverCarDTO;
import com.interactive.demo.dtos.DriverDTO;
import com.interactive.demo.services.DriverService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/drivers")
@CrossOrigin(origins = "*")
public class DriverController {
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<DriverDTO> add(@RequestBody final @NotNull DriverDTO driver) {
        return this.driverService.add(driver);
    }

    @GetMapping
    public ResponseEntity<List<DriverDTO>> getAll() {
        return this.driverService.getAll();
    }

    @GetMapping("cars")
    public ResponseEntity<List<DriverCarDTO>> getDriversCars() {
        return this.driverService.getDriversWithCar();
    }

    @PutMapping("{driverId}")
    public ResponseEntity<DriverDTO> update(@PathVariable(value = "driverId") final @NotNull Integer id, @RequestBody final @NotNull DriverDTO driver) {
        return this.driverService.update(id, driver);
    }

    @DeleteMapping("{driverId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable(value = "driverId") Integer id) {
        return this.driverService.delete(id);
    }
}
