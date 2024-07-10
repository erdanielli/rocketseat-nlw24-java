package com.github.erdanielli.planner.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConfirmParticipantRequest(@NotBlank String name,
                                        @NotNull @Email String email) {
}
