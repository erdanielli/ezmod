package com.github.erdanielli.ezmod;

import java.util.List;

class InjectorBean {

    private final Beans beans;

    public InjectorBean() {
        this(new ModuleLoader("META-INF/services/", AbstractModule.class));
    }

    public InjectorBean(ModuleLoader moduleLoader) {
        this.beans = new BeansMap();

        List<AbstractModule> modules = moduleLoader.loadModules();
        for (AbstractModule module : modules) {
            module.setBeans(beans);
            module.configure();
        }
    }

    public <T> T getBean(Class<T> type) {
        return beans.getBeanOfType(type);
    }

}
