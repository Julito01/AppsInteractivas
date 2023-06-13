package com.interactive.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDriverDTO {
    private Integer id;
    private String plate;
    private List<DriverDTO> drivers;
}