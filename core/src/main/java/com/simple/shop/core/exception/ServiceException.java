package com.simple.shop.core.exception;

public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public static class LoginExistsException extends ServiceException {
        public LoginExistsException(String message) {
            super(message);
        }
    }

    public static class RoleDoesNotExistException extends ServiceException {
        public RoleDoesNotExistException(String message) {
            super(message);
        }
    }
}
