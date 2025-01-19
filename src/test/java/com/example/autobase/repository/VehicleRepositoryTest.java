package com.example.autobase.repository;
import com.example.autobase.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class VehicleRepositoryTest {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Test
    public void testSaveAndFindVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setModel("Truck");
        vehicle.setCapacity(500);
        vehicle.setAvailable(true);
        vehicle.setNeedsRepair(false);
        vehicle = vehicleRepository.save(vehicle);
        Optional<Vehicle> foundVehicle = vehicleRepository.findById(vehicle.getId());
        assertTrue(foundVehicle.isPresent());
        assertEquals(vehicle.getModel(), foundVehicle.get().getModel());
        assertEquals(vehicle.getCapacity(), foundVehicle.get().getCapacity());
        assertEquals(vehicle.isAvailable(), foundVehicle.get().isAvailable());
        assertEquals(vehicle.isNeedsRepair(), foundVehicle.get().isNeedsRepair());
    }
}