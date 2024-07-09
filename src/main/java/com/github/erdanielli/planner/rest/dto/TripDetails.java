package com.github.erdanielli.planner.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.erdanielli.planner.domain.Trip;

import java.util.UUID;

public record TripDetails(UUID id,
                          @JsonProperty("is_confirmed") boolean confirmed,
                          @JsonUnwrapped CreateTripRequest request) {

    public static TripDetails from(Trip trip) {
        var id = trip.id();
        var confirmed = !(trip instanceof Trip.UnconfirmedTrip);
        var request = CreateTripRequest.from(trip);
        return new TripDetails(id, confirmed, request);
    }
}
