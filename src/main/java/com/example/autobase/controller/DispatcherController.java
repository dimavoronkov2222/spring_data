package com.example.autobase.controller;
import com.example.autobase.dto.RequestDTO;
import com.example.autobase.mapper.RequestMapper;
import com.example.autobase.model.Request;
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
            Request request = requestMapper.toEntity(requestDTO);
            dispatcherService.assignRequest(request);
            return ResponseEntity.ok("Request assigned successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/complete")
    public ResponseEntity<String> completeTrip(@RequestParam Long tripId, @RequestParam boolean successful, @RequestParam String note) {
        try {
            dispatcherService.completeTrip(tripId, successful, note);
            return ResponseEntity.ok("Trip completed successfully.");
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
}
