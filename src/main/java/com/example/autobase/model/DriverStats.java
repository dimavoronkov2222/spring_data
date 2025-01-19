package com.example.autobase.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class DriverStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long driverId;
    private int tripsCompleted;
    private int totalWeight;
    private double totalEarnings;
    public DriverStats() {}
    public DriverStats(Long driverId, int tripsCompleted, int totalWeight, double totalEarnings) {
        this.driverId = driverId;
        this.tripsCompleted = tripsCompleted;
        this.totalWeight = totalWeight;
        this.totalEarnings = totalEarnings;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getDriverId() {
        return driverId;
    }
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
    public int getTripsCompleted() {
        return tripsCompleted;
    }
    public void setTripsCompleted(int tripsCompleted) {
        this.tripsCompleted = tripsCompleted;
    }
    public int getTotalWeight() {
        return totalWeight;
    }
    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }
    public double getTotalEarnings() {
        return totalEarnings;
    }
    public void setTotalEarnings(double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }
}