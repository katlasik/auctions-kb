package pl.sda.auctions.model;

import jdk.jfr.Description;

public enum Status {
    CREATED("Nieaktywna"),
    OPENED("Aktywna"),
    CLOSED("Zamknięta");

    public final String label;

    private Status(String label) {
        this.label = label;
    }
}
