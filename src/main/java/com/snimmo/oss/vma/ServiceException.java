package com.snimmo.oss.vma;

public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String format, Object... objects) {
        super(String.format(format, objects));
    }

}