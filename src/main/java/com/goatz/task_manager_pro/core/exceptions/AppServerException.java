package com.goatz.task_manager_pro.core.exceptions;

/**
 * Exception thrown for generic server-side errors in the application.
 */
public class AppServerException extends EntityGenericException {

    /**
     * Constructs a new AppServerException with a code and message.
     * @param code The error code
     * @param message The error message
     */
    public AppServerException(String code, String message) {
        super(code, message);
    }
}