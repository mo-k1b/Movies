package com.example.movies.controller;

import org.springframework.boot.availability.Availability;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    private final ApplicationEventPublisher eventPublisher;

    public HealthController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Movies API");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/readiness")
    public ResponseEntity<Map<String, String>> readiness() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "READY");
        response.put("service", "Movies API");
        return ResponseEntity.ok(response);
    }

    // These endpoints are for demonstration and should be secured in production
    @GetMapping("/make-live")
    public String makeLive() {
        AvailabilityChangeEvent.publish(eventPublisher, this, LivenessState.CORRECT);
        return "Liveness state set to CORRECT";
    }

    @GetMapping("/make-not-live")
    public String makeNotLive() {
        AvailabilityChangeEvent.publish(eventPublisher, this, LivenessState.BROKEN);
        return "Liveness state set to BROKEN";
    }
}
