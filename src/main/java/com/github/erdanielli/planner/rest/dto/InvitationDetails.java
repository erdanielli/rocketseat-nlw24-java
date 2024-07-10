package com.github.erdanielli.planner.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.erdanielli.planner.domain.Invitation;
import com.github.erdanielli.planner.domain.Invitation.UnconfirmedInvitation;

public record InvitationDetails(String name,
                                String email,
                                @JsonProperty("is_confirmed") boolean confirmed) {

    public static InvitationDetails from(Invitation invitation) {
        return switch (invitation) {
            case UnconfirmedInvitation unc -> new InvitationDetails(null, unc.email(), false);
            case Invitation.ConfirmedInvitation conf -> new InvitationDetails(conf.name(), conf.email(), true);
        };
    }
}
