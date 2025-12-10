package com.goatz.task_manager_pro.core.exceptions;

/**
 * Exception thrown when an entity already exists in the system.
 * Adds an "AlreadyExists" code suffix to the provided error code.
 */
public class EntityAlreadyExistsException extends EntityGenericException {
    private static final String DEFAULT_CODE = "AlreadyExists";

    /**
     * Constructs a new EntityAlreadyExistsException with a code and message.
     * @param code The error code prefix
     * @param message The error message
     */
    public EntityAlreadyExistsException(String code, String message) {
        super(code + DEFAULT_CODE, message);
    }
}
