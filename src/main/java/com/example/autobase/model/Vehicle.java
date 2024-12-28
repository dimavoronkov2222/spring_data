package com.example.autobase.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private int capacity;
    private boolean available = true;
    private boolean needsRepair = false;
}