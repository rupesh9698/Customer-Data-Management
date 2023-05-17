package com.dm.customer.exception;

import java.util.List;

public class MyRestApiResponse implements RestApiResponse {
    private final String message;
    private final List<String> errors;

    public MyRestApiResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
