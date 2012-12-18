package com.github.erdanielli.ezmod;

import java.util.*;

public class ModuleB extends AbstractModule {

    @Override
    protected void configure() {
        export(Map.class, new HashMap());
    }

}
