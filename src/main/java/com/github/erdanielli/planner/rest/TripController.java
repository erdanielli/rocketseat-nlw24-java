package com.github.erdanielli.planner.rest;

import com.github.erdanielli.planner.rest.dto.CreateTripRequest;
import com.github.erdanielli.planner.rest.dto.CreateTripResponse;
import com.github.erdanielli.planner.rest.dto.TripDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController {
    private final Map<UUID, CreateTripRequest> fakeTrips = new HashMap<>();

    @PostMapping
    public CreateTripResponse createTrip(@RequestBody CreateTripRequest request) {
        var id = UUID.randomUUID();
        fakeTrips.put(id, request);
        return new CreateTripResponse(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<TripDetails> tripDetails(@PathVariable UUID id) {
        return Optional.ofNullable(fakeTrips.get(id))
                .map(req -> ResponseEntity.ok(new TripDetails(id, false, req)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
