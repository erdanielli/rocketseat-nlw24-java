package com.github.erdanielli.planner.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateTripRequest(@NotBlank String destination,
                                @JsonProperty("starts_at") @NotNull LocalDate startsAt,
                                @JsonProperty("ends_at") @NotNull LocalDate endsAt) {
}
