package com.github.erdanielli.planner.domain.repository;

import com.github.erdanielli.planner.domain.Participant;
import com.github.erdanielli.planner.domain.Trip;
import com.github.erdanielli.planner.domain.TripDuration;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public final class TripRepositoryFakeAdapter implements TripRepository {
    private final Map<UUID, Trip> tripById = new HashMap<>();

    @Override
    public Trip.UnconfirmedTrip createNew(String destination,
                                          TripDuration duration,
                                          Participant owner,
                                          List<String> invitations) {
        var trip = new Trip.UnconfirmedTrip(UUID.randomUUID(), destination, duration, owner, invitations);
        tripById.put(trip.id(), trip);
        return trip;
    }

    @Override
    public Optional<Trip> findById(UUID id) {
        return Optional.ofNullable(tripById.get(id));
    }
}
