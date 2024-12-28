package com.example.autobase.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int experience;
    private double earnings;
    private int totalCargo;
}