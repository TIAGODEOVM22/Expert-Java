package models.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@SuperBuilder /*a classe que esta extendendo -> ValidationException <- vai ter esses atributos tambem */
/*class generates attributes to capture an ex*/
public class StandardError {

    private LocalDateTime timeStamp;
    private Integer status;
    private String errors;
    private String message;
    private String path;

    public StandardError(Long timestamp, Integer status, String error, String message, String path) {
    }

    public StandardError() {

    }
}
