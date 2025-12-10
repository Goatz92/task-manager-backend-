package com.goatz.task_manager_pro.core.exceptions;

/**
 * Exception thrown when an invalid argument is provided for an entity operation.
 * Adds an "InvalidArgument" code suffix to the provided error code.
 */
public class EntityInvalidArgumentException extends EntityGenericException {
    private static final String DEFAULT_CODE = "InvalidArgument";

    /**
     * Constructs a new EntityInvalidArgumentException with a code and message.
     * @param code The error code prefix
     * @param message The error message
     */
    public EntityInvalidArgumentException(String code, String message) {
        super(code + DEFAULT_CODE, message);
    }
}
