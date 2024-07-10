package com.github.erdanielli.planner.domain;

import com.github.erdanielli.planner.domain.Invitation.UnconfirmedInvitation;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public sealed interface Trip {

    UUID id();

    String destination();

    TripDuration duration();

    Participant owner();

    record UnconfirmedTrip(UUID id,
                           String destination,
                           TripDuration duration,
                           Participant owner,
                           List<String> invitations) implements Trip {

        public ConfirmedTrip confirm() {
            return new ConfirmedTrip(id, destination, duration, owner,
                    invitations.stream()
                            .map(UnconfirmedInvitation::new)
                            .collect(Collectors.toList()));
        }
    }

    record ConfirmedTrip(UUID id,
                         String destination,
                         TripDuration duration,
                         Participant owner,
                         List<Invitation> invitations) implements Trip {
    }

}
