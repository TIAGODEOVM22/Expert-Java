package models.exceptions;

import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
@SuperBuilder

public class ValidationException extends StandardError {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationException(List<FieldMessage> errors) {
        super();
        this.errors = errors;
    }

    public ValidationException(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public ValidationException() {
        super();
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
