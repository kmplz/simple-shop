package com.simple.shop.core.exception;

public class ServiceException extends RuntimeException {

    ServiceException(String message) {
        super(message);
    }

    public static class LoginNotFoundException extends ServiceException {
        public LoginNotFoundException(String login) {
            super("Login " + login + " not found");
        }
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

    public static class CategoryAlreadyExistException extends ServiceException {
        public CategoryAlreadyExistException(String name) {
            super("Category " + name + " already exists");
        }
    }

    public static class CategoryDoesNotExistException extends ServiceException {
        public CategoryDoesNotExistException(Integer id) {
            super("Category " + id + " does not exist");
        }
    }

    public static class BrandAlreadyExistException extends ServiceException {
        public BrandAlreadyExistException(String name) {
            super("Brand " + name + " already exists");
        }
    }

    public static class BrandDoesNotExistException extends ServiceException {
        public BrandDoesNotExistException(Integer id) {
            super("Brand " + id + " does not exist");
        }
    }

    public static class ProductAlreadyExistException extends ServiceException {
        public ProductAlreadyExistException(String name) {
            super("Product " + name + " already exists");
        }
    }
}
