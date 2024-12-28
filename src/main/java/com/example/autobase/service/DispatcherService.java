package com.example.autobase.service;
import com.example.autobase.model.Driver;
import com.example.autobase.model.Request;
import com.example.autobase.model.Vehicle;
import com.example.autobase.repository.DriverRepository;
import com.example.autobase.repository.RequestRepository;
import com.example.autobase.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
@Service
public class DispatcherService {
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final RequestRepository requestRepository;
    private static final Logger logger = Logger.getLogger(DispatcherService.class.getName());
    public DispatcherService(DriverRepository driverRepository, VehicleRepository vehicleRepository, RequestRepository requestRepository) {
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.requestRepository = requestRepository;
        try {
            FileHandler fileHandler = new FileHandler("trips.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void assignRequest(Request request) {
        Optional<Driver> driver = driverRepository.findAll().stream()
                .filter(d -> d.getExperience() >= requiredExperience(request.getCargoType()))
                .findFirst();
        Optional<Vehicle> vehicle = vehicleRepository.findAll().stream()
                .filter(v -> v.isAvailable() && v.getCapacity() >= request.getWeight())
                .findFirst();
        if (driver.isPresent() && vehicle.isPresent()) {
            vehicle.get().setAvailable(false);
            driver.get().setEarnings(driver.get().getEarnings() + calculateEarnings(request));
            driverRepository.save(driver.get());
            vehicleRepository.save(vehicle.get());
            requestRepository.save(request);
            logTrip(driver.get(), vehicle.get(), request);
        } else {
            throw new RuntimeException("No suitable driver or vehicle available.");
        }
    }
    private int requiredExperience(String cargoType) {
        switch (cargoType) {
            case "hazardous":
                return 5;
            case "fragile":
                return 3;
            default:
                return 1;
        }
    }
    public double calculateEarnings(Request request) {
        return request.getWeight() * 2.0;
    }
    private void logTrip(Driver driver, Vehicle vehicle, Request request) {
        String logMessage = String.format("Driver: %s, Vehicle: %s, Request: %s, Destination: %s, Weight: %d, CargoType: %s",
                driver.getName(), vehicle.getModel(), request.getId(), request.getDestination(), request.getWeight(), request.getCargoType());
        logger.info(logMessage);
    }
    public void requestRepair(Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        if (vehicle.isPresent()) {
            vehicle.get().setNeedsRepair(true);
            vehicleRepository.save(vehicle.get());
        } else {
            throw new RuntimeException("Vehicle not found.");
        }
    }
    public String getDriverStatistics(Long driverId) {
        Optional<Driver> driverOptional = driverRepository.findById(driverId);
        if (driverOptional.isPresent()) {
            Driver driver = driverOptional.get();
            String stats = String.format("Driver ID: %d\nName: %s\nExperience: %d years\nEarnings: %.2f\nTotal Cargo: %d",
                    driver.getId(), driver.getName(), driver.getExperience(), driver.getEarnings(), driver.getTotalCargo());
            return stats;
        } else {
            return "Driver not found.";
        }
    }
    public String getDestinationStatistics(String destination) {
        List<Request> requests = requestRepository.findAll().stream()
                .filter(request -> request.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());
        if (requests.isEmpty()) {
            return "No requests found for the specified destination.";
        }
        int totalCargo = requests.stream().mapToInt(Request::getWeight).sum();
        String stats = String.format("Destination: %s\nTotal Requests: %d\nTotal Cargo: %d",
                destination, requests.size(), totalCargo);
        return stats;
    }
    public String getTopEarningDriver() {
        List<Driver> drivers = driverRepository.findAll();
        if (drivers.isEmpty()) {
            return "No drivers found.";
        }
        Driver topEarningDriver = drivers.stream()
                .max((d1, d2) -> Double.compare(d1.getEarnings(), d2.getEarnings()))
                .orElse(null);
        if (topEarningDriver == null) {
            return "No top-earning driver found.";
        }
        String stats = String.format("Top Earning Driver\nID: %d\nName: %s\nEarnings: %.2f",
                topEarningDriver.getId(), topEarningDriver.getName(), topEarningDriver.getEarnings());
        return stats;
    }
    public void completeTrip(Long driverId, Long vehicleId, boolean vehicleCondition) {
        Optional<Driver> driver = driverRepository.findById(driverId);
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        if (driver.isPresent() && vehicle.isPresent()) {
            vehicle.get().setAvailable(true);
            vehicle.get().setNeedsRepair(!vehicleCondition);
            driverRepository.save(driver.get());
            vehicleRepository.save(vehicle.get());
        } else {
            throw new RuntimeException("Driver or Vehicle not found.");
        }
    }
}