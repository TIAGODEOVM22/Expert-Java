package models.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
public class ValidationException extends StandardError implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty
    private List<FieldError> errors = new ArrayList<>();

    public void addError(String fieldName, String message) {
        errors.add(new FieldError(fieldName, message));
    }


    @Getter
    static class FieldError {
        private String fieldName;
        private String message;

        public FieldError() {
        }
        public FieldError(String fieldName, String message) {
            this.fieldName = fieldName;
            this.message = message;
        }
    }
}