package com.github.erdanielli.planner.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.erdanielli.planner.domain.Trip;
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
    public static CreateTripRequest from(Trip trip) {
        var destination = trip.destination();
        var startsAt = trip.duration().starts();
        var endsAt = trip.duration().ends();
        var ownerEmail = trip.owner().email();
        var ownerName = trip.owner().name();
        List<String> emailsToInvite = null;
        if (trip instanceof Trip.UnconfirmedTrip unc) {
            emailsToInvite = unc.invitations();
        }
        return new CreateTripRequest(destination, startsAt, endsAt, emailsToInvite, ownerEmail, ownerName);
    }
}
