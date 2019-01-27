package com.simple.shop.core.domain.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
public class Response {

    private Status status;
    private Error error;
    private Object data;

    public static Response ok(Object data) {
        return new Response(Status.OK, null, data);
    }

    public static Response fail(String message) {
        return new Response(Status.ERROR, new Error(message), null);
    }

    public static Response from(Runnable execution) {
        try {
            execution.run();
            return Response.ok(null);
        } catch (Exception e) {
            log.warn("Error occurred while processing request.");
            log.error(e.getMessage(), e);
            return Response.fail(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
        }
    }

    public enum Status {
        OK, ERROR
    }

    @Data
    @AllArgsConstructor
    private static class Error {
        private String message;
    }
}
