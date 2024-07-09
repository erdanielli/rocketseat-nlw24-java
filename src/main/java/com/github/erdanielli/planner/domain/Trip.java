package com.github.erdanielli.planner.domain;

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
    }

}
