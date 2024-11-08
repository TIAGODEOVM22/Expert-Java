package models.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationException extends StandardError {

    @Getter
    @JsonProperty
    private List<FieldError> errors; // Inicializa a lista

    public List<StandardError> addError( final String fieldName,final String message) {
        var validationException = new ValidationException();
        validationException.errors.add(new FieldError(fieldName, message));
        return List.of();
    }

    @Getter
    public static class FieldError {

        private final String fieldName;
        private final String message;

        public FieldError(String fieldName, String message) {
            this.fieldName = fieldName;
            this.message = message;
        }


    }
}
