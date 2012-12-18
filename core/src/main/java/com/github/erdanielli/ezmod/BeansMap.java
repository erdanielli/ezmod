package com.github.erdanielli.ezmod;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

class BeansMap implements Beans {

    private LinkedHashMap<Class,Object> beansByType = new LinkedHashMap<Class, Object>();

    private LinkedHashSet<Bean> beans = new LinkedHashSet<Bean>();

    public <T, I extends T> void add(Class<T> type, I impl) {
        beansByType.put(type, impl);
        beans.add(new Bean(type, impl));
    }

    @SuppressWarnings("unchecked")
    public <T> T getBeanOfType(Class<T> type) {
        return (T) beansByType.get(type);
    }

    public Collection<Bean> all() {
        return beans;
    }
}
