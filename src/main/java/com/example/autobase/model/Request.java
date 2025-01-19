package com.example.autobase.model;
import jakarta.persistence.*;
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private int weight;
    private String cargoType;
    private boolean completed;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    public Request() {
    }
    public Request(String destination, int weight, String cargoType, boolean completed) {
        this.destination = destination;
        this.weight = weight;
        this.cargoType = cargoType;
        this.completed = completed;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public String getCargoType() {
        return cargoType;
    }
    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", weight=" + weight +
                ", cargoType='" + cargoType + '\'' +
                ", completed=" + completed +
                ", vehicle=" + vehicle +
                '}';
    }
}
