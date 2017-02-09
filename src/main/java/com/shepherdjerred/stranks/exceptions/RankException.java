package com.shepherdjerred.stranks.exceptions;

public class RankException extends Exception {

    private final String playerMessage;

    public RankException(String message) {
        playerMessage = null;
    }

    public RankException(String message, String playerMessage){
        super(message);
        this.playerMessage = playerMessage;
    }

    public String getPlayerMessage() {
        return playerMessage;
    }

}
