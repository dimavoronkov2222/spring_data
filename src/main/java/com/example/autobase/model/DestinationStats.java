package com.example.autobase.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class DestinationStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private int totalWeight;
    private int tripsCount;
    public DestinationStats() {}
    public DestinationStats(String destination, int totalWeight, int tripsCount) {
        this.destination = destination;
        this.totalWeight = totalWeight;
        this.tripsCount = tripsCount;
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
    public int getTotalWeight() {
        return totalWeight;
    }
    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }
    public int getTripsCount() {
        return tripsCount;
    }
    public void setTripsCount(int tripsCount) {
        this.tripsCount = tripsCount;
    }
}