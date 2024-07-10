package com.github.erdanielli.planner.rest;

import com.github.erdanielli.planner.domain.Participant;
import com.github.erdanielli.planner.domain.repository.TripRepository;
import com.github.erdanielli.planner.rest.dto.ConfirmParticipantRequest;
import com.github.erdanielli.planner.rest.dto.TripDetails;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/participants")
public record ParticipantController(TripRepository tripRepository) {

    @PostMapping("{tripId}/confirm")
    public ResponseEntity<TripDetails> confirmParticipant(@PathVariable UUID tripId,
                                                          @RequestBody @Valid ConfirmParticipantRequest request) {
        return tripRepository.confirmParticipant(tripId, new Participant(request.name(), request.email()))
                .map(trip -> ResponseEntity.ok(TripDetails.from(trip)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
