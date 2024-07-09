package com.github.erdanielli.planner.rest;

import com.github.erdanielli.planner.rest.dto.CreateTripRequest;
import com.github.erdanielli.planner.rest.dto.CreateTripResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
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

}
