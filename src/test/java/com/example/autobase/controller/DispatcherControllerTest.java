package com.example.autobase.controller;
import com.example.autobase.dto.RequestDTO;
import com.example.autobase.mapper.RequestMapper;
import com.example.autobase.model.Request;
import com.example.autobase.service.DispatcherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class DispatcherControllerTest {
    @Mock
    private DispatcherService dispatcherService;
    @Mock
    private RequestMapper requestMapper;
    @InjectMocks
    private DispatcherController dispatcherController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testAssignRequest_Success() {
        RequestDTO requestDTO = new RequestDTO();
        Request request = new Request();
        when(requestMapper.toEntity(requestDTO)).thenReturn(request);
        doNothing().when(dispatcherService).assignRequest(request);
        ResponseEntity<String> response = dispatcherController.assignRequest(requestDTO);
        assertEquals(ResponseEntity.ok("Request assigned successfully."), response);
    }
    @Test
    public void testAssignRequest_Failure() {
        RequestDTO requestDTO = new RequestDTO();
        Request request = new Request();
        when(requestMapper.toEntity(requestDTO)).thenReturn(request);
        doThrow(new RuntimeException("Error")).when(dispatcherService).assignRequest(request);
        ResponseEntity<String> response = dispatcherController.assignRequest(requestDTO);
        assertEquals(ResponseEntity.badRequest().body("Error"), response);
    }
    @Test
    public void testRequestRepair_Success() {
        Long vehicleId = 1L;
        doNothing().when(dispatcherService).requestRepair(vehicleId);
        ResponseEntity<String> response = dispatcherController.requestRepair(vehicleId);
        assertEquals(ResponseEntity.ok("Repair request submitted successfully."), response);
    }
    @Test
    public void testRequestRepair_Failure() {
        Long vehicleId = 1L;
        doThrow(new RuntimeException("Error")).when(dispatcherService).requestRepair(vehicleId);
        ResponseEntity<String> response = dispatcherController.requestRepair(vehicleId);
        assertEquals(ResponseEntity.badRequest().body("Error"), response);
    }
    @Test
    public void testCompleteTrip_Success() {
        Long driverId = 1L;
        Long vehicleId = 1L;
        boolean vehicleCondition = true;
        doNothing().when(dispatcherService).completeTrip(driverId, vehicleId, vehicleCondition);
        ResponseEntity<String> response = dispatcherController.completeTrip(driverId, vehicleId, vehicleCondition);
        assertEquals(ResponseEntity.ok("Trip completed successfully."), response);
    }
    @Test
    public void testCompleteTrip_Failure() {
        Long driverId = 1L;
        Long vehicleId = 1L;
        boolean vehicleCondition = true;
        doThrow(new RuntimeException("Error")).when(dispatcherService).completeTrip(driverId, vehicleId, vehicleCondition);
        ResponseEntity<String> response = dispatcherController.completeTrip(driverId, vehicleId, vehicleCondition);
        assertEquals(ResponseEntity.badRequest().body("Error"), response);
    }
}