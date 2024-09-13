package models.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder /*a classe que esta extendendo -> ValidationException <- vai ter esses atributos tambem */
/*class generates attributes to capture an ex*/
public class StandardError {

    private LocalDateTime timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
