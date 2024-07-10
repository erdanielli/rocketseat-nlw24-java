package com.github.erdanielli.planner.domain.notification;

import com.github.erdanielli.planner.domain.Invitation;
import com.github.erdanielli.planner.domain.Participant;
import com.github.erdanielli.planner.domain.Trip;
import com.github.erdanielli.planner.domain.Trip.ConfirmedTrip;
import com.github.erdanielli.planner.domain.Trip.UnconfirmedTrip;
import com.github.erdanielli.planner.domain.TripDuration;
import com.github.erdanielli.planner.domain.repository.TripRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record TripRepositoryWithNotifications(TripRepository delegate) implements TripRepository {

    @Override
    public Optional<ConfirmedTrip> confirm(UUID id) {
        return delegate.confirm(id)
                .map(this::triggerConfirmationEmailToParticipants);
    }

    private ConfirmedTrip triggerConfirmationEmailToParticipants(ConfirmedTrip trip) {
        for (Invitation invitation : trip.invitations()) {
            System.out.printf("[CONVITE_ENVIADO] trip=%s, email=%s%n", trip.id(), invitation.email());
        }
        return trip;
    }

    // delegate simples, sem notificar

    @Override
    public UnconfirmedTrip createNew(String destination,
                                     TripDuration duration,
                                     Participant owner,
                                     List<String> invitations) {
        return delegate.createNew(destination, duration, owner, invitations);
    }

    @Override
    public Optional<Trip> findById(UUID id) {
        return delegate.findById(id);
    }

    @Override
    public Optional<UnconfirmedTrip> update(UUID id, String destination, TripDuration duration) {
        return delegate.update(id, destination, duration);
    }
}
