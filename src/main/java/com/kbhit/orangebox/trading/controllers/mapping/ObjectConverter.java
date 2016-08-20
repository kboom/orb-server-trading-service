package com.kbhit.orangebox.trading.controllers.mapping;

import com.kbhit.orangebox.trading.domain.Bid;

import java.util.HashMap;
import java.util.Map;

public class ObjectConverter {

    private Map<ConversionKey, DomainConverter> converters = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T, E> T toTransfer(E entity, Class<T> toClazz) {
        DomainConverter converter = converters.get(conversionKey(entity.getClass(), toClazz));
        return (T) converter.convertFrom(entity);
    }

    @SuppressWarnings("unchecked")
    public <T, E> E toDomain(T transfer, Class<Bid> toClazz) {
        DomainConverter converter = converters.get(conversionKey(transfer.getClass(), toClazz));
        return (E) converter.convertFrom(transfer);
    }

    public void addConverter(DomainConverter<?, ?> domainConverter) {
        converters.put(conversionKey(domainConverter.getFromClass(), domainConverter.getToClass()), domainConverter);
    }

    private static ConversionKey conversionKey(Class<?> fromClazz, Class<?> toClazz) {
        return new ConversionKey(fromClazz, toClazz);
    }

    private static class ConversionKey {

        private Class<?> fromClazz;

        private Class<?> toClazz;

        ConversionKey(Class<?> fromClazz, Class<?> toClazz) {
            this.fromClazz = fromClazz;
            this.toClazz = toClazz;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ConversionKey that = (ConversionKey) o;

            if (fromClazz != null ? !fromClazz.equals(that.fromClazz) : that.fromClazz != null) return false;
            return toClazz != null ? toClazz.equals(that.toClazz) : that.toClazz == null;

        }

        @Override
        public int hashCode() {
            int result = fromClazz != null ? fromClazz.hashCode() : 0;
            result = 31 * result + (toClazz != null ? toClazz.hashCode() : 0);
            return result;
        }

    }
}
