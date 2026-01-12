package model;

import lombok.NonNull;

public class Message {
    private final String message;

    public Message(@NonNull final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
