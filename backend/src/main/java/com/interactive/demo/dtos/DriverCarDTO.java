package com.interactive.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverCarDTO {
    private Integer id;
    private String name;
    private String lastName;
    private CarDTO car;
}
