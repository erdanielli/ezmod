package com.github.erdanielli.ezmod;

import java.util.List;

class InjectorBean {

    private final Beans beans;

    final List<AbstractModule> modules;

    public InjectorBean() {
        this(new ModuleLoader("META-INF/services/", AbstractModule.class));
    }

    public InjectorBean(ModuleLoader moduleLoader) {
        modules = moduleLoader.loadModules();
        beans = new BeansMap();

        for (AbstractModule module : modules) {
            module.postConstruct();
            module.setBeans(beans);
            module.configure();
        }
    }

    public <T> T getBean(Class<T> type) {
        return beans.getBeanOfType(type);
    }

}
