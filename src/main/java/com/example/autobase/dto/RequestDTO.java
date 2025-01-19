package com.example.autobase.dto;
import lombok.Data;
@Data
public class RequestDTO {
    private Long id;
    private String destination;
    private int weight;
    private String cargoType;
    private boolean completed;
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}