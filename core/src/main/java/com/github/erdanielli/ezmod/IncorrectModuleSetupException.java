package com.github.erdanielli.ezmod;

public class IncorrectModuleSetupException extends RuntimeException {

    public static IncorrectModuleSetupException unexpectedFailure(String moduleName, Exception e) {
        return new IncorrectModuleSetupException("Could not instantiate module " + moduleName + ". Cause: " + e.getMessage());
    }

    public static IncorrectModuleSetupException classNotFound(String moduleName) {
        return new IncorrectModuleSetupException("Could not find module " + moduleName);
    }

    public static IncorrectModuleSetupException typeMismatch(Class<?> moduleImplType, Class<?> moduleBaseType) {
        return new IncorrectModuleSetupException("Module " + moduleImplType.getName() + " does not implements/extends " + moduleBaseType.getName());
    }

    private IncorrectModuleSetupException(String message) {
        super(message);
    }

}
