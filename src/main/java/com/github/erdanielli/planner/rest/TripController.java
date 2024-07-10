package com.github.erdanielli.planner.rest;

import com.github.erdanielli.planner.domain.Participant;
import com.github.erdanielli.planner.domain.TripDuration;
import com.github.erdanielli.planner.domain.repository.TripRepository;
import com.github.erdanielli.planner.rest.dto.CreateTripRequest;
import com.github.erdanielli.planner.rest.dto.CreateTripResponse;
import com.github.erdanielli.planner.rest.dto.TripDetails;
import com.github.erdanielli.planner.rest.dto.UpdateTripRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/trips")
public record TripController(TripRepository tripRepository) {

    @PostMapping
    public CreateTripResponse createTrip(@RequestBody @Valid CreateTripRequest request) {
        var trip = tripRepository.createNew(request.destination(),
                new TripDuration(request.startsAt(), request.endsAt()),
                new Participant(request.ownerName(), request.ownerEmail()),
                request.emailsToInvite());
        return new CreateTripResponse(trip.id());
    }

    @PutMapping("{id}")
    public ResponseEntity<TripDetails> updateTrip(@PathVariable UUID id,
                                                  @RequestBody @Valid UpdateTripRequest request) {
        return tripRepository.update(id, request.destination(), new TripDuration(request.startsAt(), request.endsAt()))
                .map(trip -> ResponseEntity.ok(TripDetails.from(trip)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<TripDetails> tripDetails(@PathVariable UUID id) {
        return tripRepository.findById(id)
                .map(trip -> ResponseEntity.ok(TripDetails.from(trip)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
