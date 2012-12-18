package com.github.erdanielli.ezmod;

import java.util.List;

class InjectorBean {

    private final Beans beans;

    public InjectorBean() {
        this(new ModuleLoader());
    }

    public InjectorBean(ModuleLoader moduleLoader) {
        this.beans = new BeansMap();

        List<AbstractModule> modules = moduleLoader.loadModulesInOrder();
        for (AbstractModule module : modules) {
            module.setBeans(beans);
            module.configure();
        }
    }

    public <T> T getBean(Class<T> type) {
        return beans.getBeanOfType(type);
    }

}
