package com.interactive.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    public Driver(String name, String lastName, Car car) {
        this.name = name;
        this.lastName = lastName;
        this.car = car;
    }
}