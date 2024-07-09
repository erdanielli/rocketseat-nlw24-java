package com.github.erdanielli.planner.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.UUID;

public record TripDetails(UUID id,
                          @JsonProperty("is_confirmed") boolean confirmed,
                          @JsonUnwrapped CreateTripRequest request) {
}
