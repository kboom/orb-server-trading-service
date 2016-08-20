package com.kbhit.orangebox.trading.controllers.mapping;

abstract class AbstractDomainConverter<S, T> implements DomainConverter<S, T> {

    private Class<S> sourceClass;

    private Class<T> targetClass;

    public AbstractDomainConverter(Class<S> sourceClass, Class<T> targetClass) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

    @Override
    public Class<S> getFromClass() {
        return sourceClass;
    }

    @Override
    public Class<T> getToClass() {
        return targetClass;
    }

}
