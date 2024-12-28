package com.example.autobase.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private int weight;
    private String cargoType;
}