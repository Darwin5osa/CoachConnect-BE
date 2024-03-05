package com.digitalhouse.CoachConnectBE.controller.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
@Slf4j
public class HealthController {
    @GetMapping()
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("All ok");
    }
}
