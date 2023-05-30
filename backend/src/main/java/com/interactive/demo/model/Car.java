package com.interactive.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.interactive.demo.services.CarService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private int id;
    private String plate;

    @OneToMany(mappedBy = "car" )
    @JsonIgnore
    private List<Driver> driversList;

    public Car(String plate) {
        this.plate = plate;
    }

    public Car() {

    }
    /*
    // Configuracion para relacion de muchos a muchos
    @JsonIgnore
    @ManyToMany
    @JoinTable( name = "auto", JoinColumn(name = "id_a"), InverseJoinColumns = @JoinColumn(name = "id_b"))
    private List<Driver> driverList;

     */



}
