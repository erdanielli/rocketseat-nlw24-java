package com.github.erdanielli.planner.domain;

public sealed interface Invitation {

    String email();

    record UnconfirmedInvitation(String email) implements Invitation {
    }

    record ConfirmedInvitation(String name, String email) implements Invitation {
    }

}
