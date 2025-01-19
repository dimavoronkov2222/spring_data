package com.example.autobase.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class TripLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long requestId;
    private Long vehicleId;
    private Long driverId;
    private String tripStatus;
    private String tripDate;
    private String note;
    public TripLog() {}
    public TripLog(Long requestId, Long vehicleId, Long driverId, String tripStatus, String tripDate, String note) {
        this.requestId = requestId;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.tripStatus = tripStatus;
        this.tripDate = tripDate;
        this.note = note;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getRequestId() {
        return requestId;
    }
    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
    public Long getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
    public Long getDriverId() {
        return driverId;
    }
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
    public String getTripStatus() {
        return tripStatus;
    }
    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }
    public String getTripDate() {
        return tripDate;
    }
    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
}