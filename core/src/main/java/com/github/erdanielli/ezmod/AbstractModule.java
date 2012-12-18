package com.github.erdanielli.ezmod;

import lombok.Setter;

public abstract class AbstractModule {

    @Setter
    protected Beans beans;

    protected abstract void configure();

    protected <T,I extends T> void export(Class<T> type, I implementation) {
        beans.add(type, implementation);
    }

}
