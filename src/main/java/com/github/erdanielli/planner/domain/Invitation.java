package com.github.erdanielli.planner.domain;

public sealed interface Invitation {

    String email();

    record UnconfirmedInvitation(String email) implements Invitation {

        public ConfirmedInvitation confirm(String name) {
            return new ConfirmedInvitation(name, email);
        }
    }

    record ConfirmedInvitation(String name, String email) implements Invitation {
    }

}
