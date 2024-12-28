package com.example.autobase.dto;
import lombok.Data;
@Data
public class DriverDTO {
    private Long id;
    private String name;
    private int experience;
    private double earnings;
    private int totalCargo;
}