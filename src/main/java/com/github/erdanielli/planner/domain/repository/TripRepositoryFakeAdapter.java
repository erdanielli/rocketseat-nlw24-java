package com.github.erdanielli.planner.domain.repository;

import com.github.erdanielli.planner.domain.Invitation;
import com.github.erdanielli.planner.domain.Invitation.UnconfirmedInvitation;
import com.github.erdanielli.planner.domain.Participant;
import com.github.erdanielli.planner.domain.Trip;
import com.github.erdanielli.planner.domain.Trip.ConfirmedTrip;
import com.github.erdanielli.planner.domain.Trip.UnconfirmedTrip;
import com.github.erdanielli.planner.domain.TripDuration;

import java.util.*;

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

    @Override
    public Optional<ConfirmedTrip> confirmParticipant(UUID tripId, Participant participant) {
        var updatedTrip = tripById.computeIfPresent(tripId, (_id, trip) -> {
            if (trip instanceof ConfirmedTrip conf) {
                return conf.confirm(participant);
            }
            return null;
        });
        return Optional.ofNullable((ConfirmedTrip) updatedTrip);
    }

    @Override
    public Optional<Trip> invite(UUID id, String email) {
        var updatedTrip = tripById.computeIfPresent(id, (_id, trip) -> switch (trip) {
            case UnconfirmedTrip unc -> invite(unc, email);
            case ConfirmedTrip conf -> invite(conf, email);
        });
        return Optional.ofNullable(updatedTrip);
    }

    private Trip invite(UnconfirmedTrip unc, String email) {
        if (unc.invitations().contains(email)) return unc;
        var v2 = new ArrayList<>(unc.invitations());
        v2.add(email);
        return new UnconfirmedTrip(unc.id(), unc.destination(), unc.duration(), unc.owner(), v2);
    }

    private Trip invite(ConfirmedTrip conf, String email) {
        if (conf.invitations().stream().anyMatch(i -> i.email().contains(email))) return conf;
        List<Invitation> v2 = new ArrayList<>(conf.invitations());
        v2.add(new UnconfirmedInvitation(email));
        return new ConfirmedTrip(conf.id(), conf.destination(), conf.duration(), conf.owner(), v2);
    }
}
