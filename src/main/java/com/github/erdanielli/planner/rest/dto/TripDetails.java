package com.github.erdanielli.planner.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.erdanielli.planner.domain.Trip;
import com.github.erdanielli.planner.domain.Trip.ConfirmedTrip;
import com.github.erdanielli.planner.domain.Trip.UnconfirmedTrip;

import java.util.List;
import java.util.UUID;

public record TripDetails(UUID id,
                          @JsonProperty("is_confirmed") boolean confirmed,
                          @JsonUnwrapped CreateTripRequest request,
                          List<InvitationDetails> participants) {

    public static TripDetails from(Trip trip) {
        var id = trip.id();
        var request = CreateTripRequest.from(trip);
        return switch (trip) {
            case UnconfirmedTrip ignored -> new TripDetails(id, false, request, null);
            case ConfirmedTrip conf -> new TripDetails(id, true, request,
                    conf.invitations().stream().map(InvitationDetails::from).toList());
        };
    }
}
