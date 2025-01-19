package com.example.autobase.repository;
import com.example.autobase.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findAvailableVehicle(int weight);
}