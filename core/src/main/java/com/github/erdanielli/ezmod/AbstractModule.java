package com.github.erdanielli.ezmod;

import lombok.Setter;

public abstract class AbstractModule {

    private String name;

    @Setter
    protected Beans beans;

    protected <T, I extends T> void export(Class<T> type, I implementation) {
        beans.add(type, implementation);
    }

    protected String name() {
        return getClass().getPackage().getName();
    }

    void postConstruct() {
        this.name = name();
    }

    protected abstract void configure();

}
