package com.github.erdanielli.planner.domain.repository;

import com.github.erdanielli.planner.domain.Participant;
import com.github.erdanielli.planner.domain.Trip;
import com.github.erdanielli.planner.domain.TripDuration;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TripRepository {

    Trip.UnconfirmedTrip createNew(String destination,
                                   TripDuration duration,
                                   Participant owner,
                                   List<String> invitations);

    Optional<Trip> findById(UUID id);

}
