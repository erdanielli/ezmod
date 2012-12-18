package com.github.erdanielli.ezmod;

import java.util.Collection;

public interface Beans {

    <T,I extends T> void add(Class<T> type, I impl);

    <T> T getBeanOfType(Class<T> type);

    Collection<Bean> all();

}
