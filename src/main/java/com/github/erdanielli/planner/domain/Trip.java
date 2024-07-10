package com.github.erdanielli.planner.domain;

import com.github.erdanielli.planner.domain.Invitation.UnconfirmedInvitation;

import java.util.List;
import java.util.UUID;

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
                            .toList());
        }
    }

    record ConfirmedTrip(UUID id,
                         String destination,
                         TripDuration duration,
                         Participant owner,
                         List<? extends Invitation> invitations) implements Trip {

        public ConfirmedTrip confirm(Participant participant) {
            var v2 = invitations.stream().map(i -> {
                if (i instanceof UnconfirmedInvitation unc && unc.email().equalsIgnoreCase(participant.email())) {
                    return unc.confirm(participant.name());
                }
                return i;
            }).toList();
            return new ConfirmedTrip(id, destination, duration, owner, v2);
        }
    }

}
