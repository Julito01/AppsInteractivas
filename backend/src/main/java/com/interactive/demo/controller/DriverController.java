package com.interactive.demo.controller;

import com.interactive.demo.model.Car;
import com.interactive.demo.model.Driver;
import com.interactive.demo.services.DriverService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/driver")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping("")
    public ResponseEntity add(@RequestBody final @NotNull Driver driver) {
        return driverService.add(driver);
    }


    @GetMapping("")
    public List<Driver> getAll(){
        return driverService.getAll();
    }

    @PostMapping("/{id}/update")
    public ResponseEntity update (@PathVariable final @NotNull Integer id, @RequestBody final @NotNull  Driver driver){
        return driverService.update(id,driver);
    }

    @PostMapping ("/{id}/delete")
    public ResponseEntity delete( @PathVariable Integer id){
        return driverService.delete(id);
    }
}
