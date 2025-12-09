package com.goatz.task_manager_pro.exceptions;

public class AppServerException extends EntityGenericException {

    public AppServerException(String code, String message) {
        super(code, message);
    }
}