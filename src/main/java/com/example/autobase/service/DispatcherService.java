package com.example.autobase.service;
import com.example.autobase.model.Request;
import com.example.autobase.model.Vehicle;
import com.example.autobase.repository.RequestRepository;
import com.example.autobase.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class DispatcherService {
    private final RequestRepository requestRepository;
    private final VehicleRepository vehicleRepository;
    public DispatcherService(RequestRepository requestRepository, VehicleRepository vehicleRepository) {
        this.requestRepository = requestRepository;
        this.vehicleRepository = vehicleRepository;
    }
    public void assignRequest(Request request) {
        Optional<Vehicle> availableVehicle = vehicleRepository.findAvailableVehicle(request.getWeight());
        if (availableVehicle.isPresent()) {
            Vehicle vehicle = availableVehicle.get();
            request.setVehicle(vehicle);
            vehicle.setAssigned(true);
            request.setCompleted(false);
            requestRepository.save(request);
        } else {
            throw new RuntimeException("No available vehicle for this request.");
        }
    }
    public void completeTrip(Long tripId, boolean successful, String note) {
        Optional<Request> requestOpt = requestRepository.findById(tripId);
        if (requestOpt.isPresent()) {
            Request request = requestOpt.get();
            if (successful) {
                request.setCompleted(true);
                Vehicle vehicle = request.getVehicle();
                vehicle.setAssigned(false);
                vehicleRepository.save(vehicle);
            }
            requestRepository.save(request);
        } else {
            throw new RuntimeException("Request not found.");
        }
    }
    public void requestRepair(Long vehicleId) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicleId);
        if (vehicleOpt.isPresent()) {
            Vehicle vehicle = vehicleOpt.get();
            vehicle.setNeedsRepair(true);
            vehicleRepository.save(vehicle);
        } else {
            throw new RuntimeException("Vehicle not found.");
        }
    }
}