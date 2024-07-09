package com.github.erdanielli.planner.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CreateTripRequest(@NotBlank String destination,
                                @JsonProperty("starts_at") @NotNull LocalDate startsAt,
                                @JsonProperty("ends_at") @NotNull LocalDate endsAt,
                                @JsonProperty("emails_to_invite") List<@NotNull @Email String> emailsToInvite,
                                @JsonProperty("owner_email") @NotNull @Email String ownerEmail,
                                @JsonProperty("owner_name") @NotBlank String ownerName) {
}
