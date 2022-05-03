package com.lamine.InventoryManagement.exception;

import lombok.Getter;

import java.util.List;

public class EntityInvalidException extends  RuntimeException {

    @Getter
    ErrorCode errorCode;
    @Getter
    List<String> errors ;

    public EntityInvalidException(String message) {
        super(message);
    }

    public EntityInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityInvalidException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public EntityInvalidException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public EntityInvalidException(String message, ErrorCode errorCode, List<String> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
    }
}
