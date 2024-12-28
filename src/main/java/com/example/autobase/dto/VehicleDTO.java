package com.example.autobase.dto;
import lombok.Data;
@Data
public class VehicleDTO {
    private Long id;
    private String model;
    private int capacity;
    private boolean available;
    private boolean needsRepair;
}