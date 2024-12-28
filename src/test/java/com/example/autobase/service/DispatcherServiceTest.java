package com.example.autobase.service;
import com.example.autobase.model.Driver;
import com.example.autobase.model.Request;
import com.example.autobase.model.Vehicle;
import com.example.autobase.repository.DriverRepository;
import com.example.autobase.repository.RequestRepository;
import com.example.autobase.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class DispatcherServiceTest {
    @Mock
    private DriverRepository driverRepository;
    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private RequestRepository requestRepository;
    @InjectMocks
    private DispatcherService dispatcherService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testAssignRequest_Success() {
        Request request = new Request();
        request.setCargoType("general");
        request.setWeight(100);
        Driver driver = new Driver();
        driver.setExperience(3);
        Vehicle vehicle = new Vehicle();
        vehicle.setCapacity(200);
        vehicle.setAvailable(true);
        when(driverRepository.findAll()).thenReturn(List.of(driver));
        when(vehicleRepository.findAll()).thenReturn(List.of(vehicle));
        dispatcherService.assignRequest(request);
        assertFalse(vehicle.isAvailable());
        verify(driverRepository, times(1)).save(driver);
        verify(vehicleRepository, times(1)).save(vehicle);
        verify(requestRepository, times(1)).save(request);
    }
    @Test
    public void testAssignRequest_NoSuitableDriverOrVehicle() {
        Request request = new Request();
        request.setCargoType("hazardous");
        request.setWeight(300);
        when(driverRepository.findAll()).thenReturn(List.of());
        when(vehicleRepository.findAll()).thenReturn(List.of());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            dispatcherService.assignRequest(request);
        });
        assertEquals("No suitable driver or vehicle available.", exception.getMessage());
    }
    @Test
    public void testRequestRepair_VehicleFound() {
        Long vehicleId = 1L;
        Vehicle vehicle = new Vehicle();
        vehicle.setNeedsRepair(false);
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));
        dispatcherService.requestRepair(vehicleId);
        assertTrue(vehicle.isNeedsRepair());
        verify(vehicleRepository, times(1)).save(vehicle);
    }
    @Test
    public void testRequestRepair_VehicleNotFound() {
        Long vehicleId = 1L;
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            dispatcherService.requestRepair(vehicleId);
        });
        assertEquals("Vehicle not found.", exception.getMessage());
    }
    @Test
    public void testCompleteTrip_Success() {
        Long driverId = 1L;
        Long vehicleId = 1L;
        boolean vehicleCondition = true;
        Driver driver = new Driver();
        Vehicle vehicle = new Vehicle();
        vehicle.setAvailable(false);
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver));
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));
        dispatcherService.completeTrip(driverId, vehicleId, vehicleCondition);
        assertTrue(vehicle.isAvailable());
        assertFalse(vehicle.isNeedsRepair());
        verify(driverRepository, times(1)).save(driver);
        verify(vehicleRepository, times(1)).save(vehicle);
    }
    @Test
    public void testCompleteTrip_DriverOrVehicleNotFound() {
        Long driverId = 1L;
        Long vehicleId = 1L;
        when(driverRepository.findById(driverId)).thenReturn(Optional.empty());
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            dispatcherService.completeTrip(driverId, vehicleId, true);
        });
        assertEquals("Driver or Vehicle not found.", exception.getMessage());
    }
}