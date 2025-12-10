package com.goatz.task_manager_pro.core.exceptions;

/**
 * Exception thrown when an entity is not found in the system.
 * Adds a "NotFound" code suffix to the provided error code.
 */
public class EntityNotFoundException extends EntityGenericException {
    private static final String DEFAULT_CODE = "NotFound";

    /**
     * Constructs a new EntityNotFoundException with a code and message.
     * @param code The error code prefix
     * @param message The error message
     */
    public EntityNotFoundException(String code, String message) {
        super(code + DEFAULT_CODE, message);
    }
}
