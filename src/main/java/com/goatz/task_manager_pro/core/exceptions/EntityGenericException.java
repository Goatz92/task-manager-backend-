package com.goatz.task_manager_pro.core.exceptions;

/**
 * Base exception for all entity-related errors in the application.
 * Stores an error code and message for detailed error handling.
 */
public class EntityGenericException extends Exception {
    /** Error code for the exception. */
    private final String code;

    /**
     * Constructs a new EntityGenericException with a code and message.
     * @param code The error code
     * @param message The error message
     */
    public EntityGenericException(String code, String message) {
        super(message);
        this.code = code;
    }
}