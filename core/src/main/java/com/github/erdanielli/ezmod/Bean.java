package com.github.erdanielli.ezmod;

public class Bean {

    private final Class<?> type;

    private final Object implementation;

    public Bean(Class<?> type, Object implementation) {
        this.type = type;
        this.implementation = implementation;
    }

    public Class<?> getType() {
        return type;
    }

    public Object getImplementation() {
        return implementation;
    }
}
