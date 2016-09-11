package com.kbhit.orangebox.trading.controllers.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResourceResponseBuilder<T> {

    private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.OK;

    private T resource;

    private HttpStatus statusCode;

    private ResourceResponseBuilder() {
        statusCode = DEFAULT_HTTP_STATUS;
    }

    public ResponseEntity<T> build() {
        return new ResponseEntity<>(resource, statusCode);
    }

    public ResourceResponseBuilder<T> withResource(T resource) {
        this.resource = resource;
        return this;
    }

    public static <T> ResourceResponseBuilder<T> aResourceResponse(
            @SuppressWarnings("unused") Class<T> payloadClazz) {
        return new ResourceResponseBuilder<>();
    }

}
