package models.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

@SuperBuilder
public class ValidationError extends StandardError {

    public ValidationError(long l, int value, String validationError, String erroNaValidaçãoDosCampos, String requestURI) {
    }

    @Override
    public List<FieldMessage> getErrors() {
        return List.of();
    }

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    private List<FieldError> errors;


    public void addError(final String fieldName, final String message) {
        this.errors.add(new FieldError(fieldName, message));
    }

    @Getter
    private static class FieldError {
        private String fieldName;
        private String message;

        public FieldError(String fieldName, String message) {
        }
    }

}