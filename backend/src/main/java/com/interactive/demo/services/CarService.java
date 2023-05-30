package com.interactive.demo.services;

import com.interactive.demo.model.Car;
import com.interactive.demo.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;




import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class CarService {
    // TODO: CRUD methods

    private final CarRepository carRepository;
    private final ModelMapper mm = new ModelMapper();




    @Autowired
    public CarService(CarRepository repository) {
        this.carRepository = repository;
    }

    // Create
    public ResponseEntity add(Car car) {

        try{
            carRepository.save(car);
            return ResponseEntity.status(CREATED).build();
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }

    }
    // Read
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    // Update
    public ResponseEntity update(Integer id,Car car) {
        try {
            Car carTemporaly = carRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Car not found"));
            carTemporaly.setPlate(car.getPlate());
            carRepository.save(carTemporaly);
            return ResponseEntity.status(OK).build();
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete

    public ResponseEntity deleteCar(Integer id){
        try {
            carRepository.deleteById(id);
            return ResponseEntity.status(OK).build();
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
