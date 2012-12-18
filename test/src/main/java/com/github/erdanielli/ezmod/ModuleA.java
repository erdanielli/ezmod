package com.github.erdanielli.ezmod;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModuleA extends AbstractModule {

    @Override
    protected void configure() {
        export(List.class, new ArrayList());

        // ArrayList was replaced
        export(List.class, new LinkedList());
    }

}
