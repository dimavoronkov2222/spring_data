package com.example.autobase.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
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
    @OneToMany(mappedBy = "vehicle")
    private List<Request> requests;
    private boolean assigned = false;
    public Vehicle() {
    }
    public Vehicle(String model, int capacity, boolean available, boolean needsRepair, boolean assigned) {
        this.model = model;
        this.capacity = capacity;
        this.available = available;
        this.needsRepair = needsRepair;
        this.assigned = assigned;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public boolean isNeedsRepair() {
        return needsRepair;
    }
    public void setNeedsRepair(boolean needsRepair) {
        this.needsRepair = needsRepair;
    }
    public List<Request> getRequests() {
        return requests;
    }
    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
    public boolean isAssigned() {
        return assigned;
    }
    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", available=" + available +
                ", needsRepair=" + needsRepair +
                ", assigned=" + assigned +
                '}';
    }
}