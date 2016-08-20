package com.kbhit.orangebox.trading.controllers.mapping;

interface DomainConverter<S, T> {

    T convertFrom(S source);

    Class<S> getFromClass();

    Class<T> getToClass();

}
