package be.vdab.school.exceptions;

import java.io.Serial;

public class RepositoryException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public RepositoryException(Exception cause) {
        super(cause);
    }
}
