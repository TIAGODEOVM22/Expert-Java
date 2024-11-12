package models.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@SuperBuilder
public abstract class StandardError {

    private LocalDateTime timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError() {

    }

}
