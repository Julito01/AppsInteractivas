package com.interactive.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.interactive.demo.services.CarService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String plate;

    @OneToMany(mappedBy = "car")
    private List<Driver> driversList;

    /*
    // Configuracion para relacion de muchos a muchos
    @JsonIgnore
    @ManyToMany
    @JoinTable( name = "auto", JoinColumn(name = "id_a"), InverseJoinColumns = @JoinColumn(name = "id_b"))
    private List<Driver> driverList;
    */
}
