package com.github.erdanielli.ezmod;

public class NoSuchImplementationException extends RuntimeException {

    public NoSuchImplementationException(Class<?> type) {
        super("No implementation found for " + type.getName());
    }

}
