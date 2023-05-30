package com.interactive.demo.dtos;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CarDTO {
    private String plate;
    
}
