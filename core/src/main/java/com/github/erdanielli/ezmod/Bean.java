package com.github.erdanielli.ezmod;

class Bean {

    final Class<?> type;

    final Object implementation;

    public Bean(Class<?> type, Object implementation) {
        this.type = type;
        this.implementation = implementation;
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return type == ((Bean) o).type;
    }
}
