package com.example.demoCloth.ErrorHandling;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


/**
 * Class to customize error message.
 */
@Getter
@Setter
public class ApiErrorMessage {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private List<String> errors;

    public ApiErrorMessage() {
        timestamp = LocalDateTime.now();
    }

    public ApiErrorMessage(final HttpStatus status, final String message, final List<String> errors) {
        this();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiErrorMessage(final HttpStatus status, final String message, final String error) {
        this();
        this.status = status;
        this.message = message;
        errors = Collections.singletonList(error);
    }
}
