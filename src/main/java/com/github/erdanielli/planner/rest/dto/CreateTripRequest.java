package com.github.erdanielli.planner.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record CreateTripRequest(String destination,
                                @JsonProperty("starts_at") LocalDate startsAt,
                                @JsonProperty("ends_at") LocalDate endsAt,
                                @JsonProperty("emails_to_invite") List<String> emailsToInvite,
                                @JsonProperty("owner_email") String ownerEmail,
                                @JsonProperty("owner_name") String ownerName) {
}
