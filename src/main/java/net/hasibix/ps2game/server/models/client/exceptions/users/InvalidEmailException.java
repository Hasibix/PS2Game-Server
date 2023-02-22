package net.hasibix.ps2game.server.models.client.exceptions.users;

public class InvalidEmailException extends Exception {
    public InvalidEmailException(String errorMessage) {
        super(errorMessage);
    }
}
