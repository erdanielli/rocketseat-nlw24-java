package com.github.erdanielli.planner.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record InviteParticipantRequest(@NotNull @Email String email) {
}
