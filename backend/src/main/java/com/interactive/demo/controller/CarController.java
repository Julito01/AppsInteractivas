package com.interactive.demo.controller;


import com.interactive.demo.model.Car;
import com.interactive.demo.services.CarService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping ("/car")
@CrossOrigin(origins="*")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("")
    public ResponseEntity add(@RequestBody final @NotNull Car car) {
        return carService.add(car);
    }


    @GetMapping("")
    public List<Car> getAll(){
        return carService.getAll();
    }

    @PostMapping("/{id}/update")
    public ResponseEntity update (@PathVariable final @NotNull Integer id, @RequestBody final @NotNull  Car car){
        return carService.update(id,car);
    }

    @PostMapping ("/{id}/delete")
    public ResponseEntity delete( @PathVariable Integer id){
        return carService.deleteCar(id);
    }


}
