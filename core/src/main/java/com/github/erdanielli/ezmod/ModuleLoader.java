package com.github.erdanielli.ezmod;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

class ModuleLoader {

    private final String path;

    private final Class moduleType;

    private final ClassLoader classLoader;

    ModuleLoader(String path, Class moduleType) {
        this(path, moduleType, Thread.currentThread().getContextClassLoader());
    }

    ModuleLoader(String path, Class moduleType, ClassLoader classLoader) {
        this.path = path;
        this.moduleType = moduleType;
        this.classLoader = classLoader;
    }

    public List<AbstractModule> loadModules() {
        return findAllImplementations();
    }

    @SuppressWarnings("unchecked")
    private List<AbstractModule> findAllImplementations() {
        ArrayList<AbstractModule> implementations = new ArrayList<AbstractModule>();

        List<String> strings = findAllStrings(moduleType.getName());
        for (String className : strings) {
            implementations.add(createInstance(className));
        }

        return implementations;
    }

    private List<String> findAllStrings(String uri) {
        try {
            String fulluri = path + uri;
            List<String> strings = new ArrayList<String>();
            Enumeration<URL> resources = classLoader.getResources(fulluri);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String string = readContents(url);
                strings.add(string);
            }
            return strings;
        } catch (IOException e) {
            throw IncorrectModuleSetupException.unexpectedFailure(uri, e);
        }
    }

    private String readContents(URL resource) throws IOException {
        InputStream in = resource.openStream();
        BufferedInputStream reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedInputStream(in);
            int b = reader.read();
            while (b != -1) {
                sb.append((char) b);
                b = reader.read();
            }

            return sb.toString().trim();
        } finally {
            try {
                in.close();
                if (reader != null) reader.close();
            } catch (Exception ignored) {
            }
        }
    }

    @SuppressWarnings("unchecked")
    private AbstractModule createInstance(String className) {
        Class<?> subType = null;
        try {
            subType = classLoader.loadClass(className);
            return (AbstractModule) subType.asSubclass(moduleType).newInstance();
        } catch (ClassNotFoundException e) {
            throw IncorrectModuleSetupException.classNotFound(className);
        } catch (ClassCastException e) {
            throw IncorrectModuleSetupException.typeMismatch(subType, moduleType);
        } catch (Exception e) {
            throw IncorrectModuleSetupException.unexpectedFailure(className, e);
        }
    }

}


