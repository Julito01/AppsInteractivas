package com.interactive.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDriverDTO {
    private String name;
    private String lastName;
    private Integer carId;
}
