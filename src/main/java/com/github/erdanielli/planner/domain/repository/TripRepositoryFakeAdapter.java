package com.github.erdanielli.planner.domain.repository;

import com.github.erdanielli.planner.domain.Participant;
import com.github.erdanielli.planner.domain.Trip;
import com.github.erdanielli.planner.domain.Trip.ConfirmedTrip;
import com.github.erdanielli.planner.domain.Trip.UnconfirmedTrip;
import com.github.erdanielli.planner.domain.TripDuration;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public record TripRepositoryFakeAdapter(Map<UUID, Trip> tripById) implements TripRepository {

    @Override
    public UnconfirmedTrip createNew(String destination,
                                     TripDuration duration,
                                     Participant owner,
                                     List<String> invitations) {
        var trip = new UnconfirmedTrip(UUID.randomUUID(), destination, duration, owner, invitations);
        tripById.put(trip.id(), trip);
        return trip;
    }

    @Override
    public Optional<Trip> findById(UUID id) {
        return Optional.ofNullable(tripById.get(id));
    }

    @Override
    public Optional<UnconfirmedTrip> update(UUID id, String destination, TripDuration duration) {
        var updatedTrip = tripById.computeIfPresent(id, (_id, trip) -> {
            if (trip instanceof UnconfirmedTrip unc) {
                return new UnconfirmedTrip(unc.id(), destination, duration, unc.owner(), unc.invitations());
            }
            return null;
        });
        return Optional.ofNullable((UnconfirmedTrip) updatedTrip);
    }

    @Override
    public Optional<ConfirmedTrip> confirm(UUID id) {
        var updatedTrip = tripById.computeIfPresent(id, (_id, trip) -> {
            if (trip instanceof UnconfirmedTrip unc) {
                return unc.confirm();
            }
            return null;
        });
        return Optional.ofNullable((ConfirmedTrip) updatedTrip);
    }
}
