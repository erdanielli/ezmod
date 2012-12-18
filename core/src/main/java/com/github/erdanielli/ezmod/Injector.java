package com.github.erdanielli.ezmod;

public class Injector {

    private static final InjectorBean INJECTOR_BEAN = new InjectorBean();

    public static void create() {
        // resolve static context
    }

    public static <T> T getInstance(Class<T> type) {
        return getInstance(type, false);
    }

    public static <T> T getInstance(Class<T> type, boolean allowNull) {
        T bean = INJECTOR_BEAN.getBean(type);
        if (bean == null && !allowNull) {
            throw new NoSuchImplementationException(type);
        }
        return bean;
    }

    private Injector() {
    }

}
