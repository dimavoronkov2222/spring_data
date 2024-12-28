package com.example.autobase.controller;
import com.example.autobase.dto.RequestDTO;
import com.example.autobase.mapper.RequestMapper;
import com.example.autobase.service.DispatcherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/dispatcher")
public class DispatcherController {
    private final DispatcherService dispatcherService;
    private final RequestMapper requestMapper;
    public DispatcherController(DispatcherService dispatcherService, RequestMapper requestMapper) {
        this.dispatcherService = dispatcherService;
        this.requestMapper = requestMapper;
    }
    @PostMapping("/assign")
    public ResponseEntity<String> assignRequest(@RequestBody RequestDTO requestDTO) {
        try {
            dispatcherService.assignRequest(requestMapper.toEntity(requestDTO));
            return ResponseEntity.ok("Request assigned successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/repair")
    public ResponseEntity<String> requestRepair(@RequestParam Long vehicleId) {
        try {
            dispatcherService.requestRepair(vehicleId);
            return ResponseEntity.ok("Repair request submitted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/statistics/driver/{driverId}")
    public ResponseEntity<String> getDriverStatistics(@PathVariable Long driverId) {
        try {
            String stats = dispatcherService.getDriverStatistics(driverId);
            return ResponseEntity.ok(stats);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/statistics/destination/{destination}")
    public ResponseEntity<String> getDestinationStatistics(@PathVariable String destination) {
        try {
            String stats = dispatcherService.getDestinationStatistics(destination);
            return ResponseEntity.ok(stats);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/statistics/top-earning-driver")
    public ResponseEntity<String> getTopEarningDriver() {
        try {
            String stats = dispatcherService.getTopEarningDriver();
            return ResponseEntity.ok(stats);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/complete")
    public ResponseEntity<String> completeTrip(@RequestParam Long driverId, @RequestParam Long vehicleId, @RequestParam boolean vehicleCondition) {
        try {
            dispatcherService.completeTrip(driverId, vehicleId, vehicleCondition);
            return ResponseEntity.ok("Trip completed successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}