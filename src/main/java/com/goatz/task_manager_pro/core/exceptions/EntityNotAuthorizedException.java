package com.goatz.task_manager_pro.core.exceptions;

/**
 * Exception thrown when an entity operation is not authorized for the current user.
 * Adds a "NotAuthorized" code suffix to the provided error code.
 */
public class EntityNotAuthorizedException extends EntityGenericException {
    private static final String DEFAULT_CODE = "NotAuthorized";

    /**
     * Constructs a new EntityNotAuthorizedException with a code and message.
     * @param code The error code prefix
     * @param message The error message
     */
    public EntityNotAuthorizedException(String code, String message) {
        super(code + DEFAULT_CODE, message);
    }
}
