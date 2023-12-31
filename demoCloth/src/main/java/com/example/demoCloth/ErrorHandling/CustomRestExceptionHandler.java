package com.example.demoCloth.ErrorHandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Handler that overrides the responses given in case of Error using ApiErrorMessage.
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    //$400

    /**
     * Handles the error when an argument is not valid.
     * @param ex Exception raised when an argument is not valid.
     * @param headers Headers of the API call.
     * @param status Http Status of the response.
     * @param request Web Request.
     * @return the custom response as ResponseEntity<Object>
     */
    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        super.handleMethodArgumentNotValid(ex,headers,status,request);

        log.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, apiErrorMessage, headers, apiErrorMessage.getStatus(), request);
    }

    /**
     * Handles error message when the parameter passed is not one of the expected in the call.
     * @param ex Exception raised when the parameter passed is not one of the expected in the call.
     * @param headers Headers of the API call.
     * @param status Http Status of the response.
     * @param request Web Request.
     * @return the custom response as ResponseEntity<Object>
     */
    @Override
    @Nullable
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.info(ex.getClass().getName());
        //
        final String error = ex.getParameterName() + " parameter is missing";
        final ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }


    // 405
    /**
     * Handles the error when the Http method is not supported.
     * @param ex Exception raised when an argument is not valid.
     * @param headers Headers of the API call.
     * @param status Http Status of the response.
     * @param request Web Request.
     * @return the custom response as ResponseEntity<Object>
     */
    @Override
    @Nullable
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request)  {
        log.info(ex.getClass().getName());
        //
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        if(Objects.nonNull(ex.getSupportedHttpMethods())) {
            ex.getSupportedHttpMethods().forEach(t -> builder.append(t).append(" "));
        }

        final ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), builder.toString());
        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }


    // 500

    /**
     * Handles the errors not handled by other methods
     * @param ex Exception raised
     * @param request Web Request.
     * @return the custom response as ResponseEntity<Object>
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        log.info(ex.getClass().getName());
        log.error("error", ex);
        String message = ex.getLocalizedMessage();
        if(ex.getClass().getName().equals(DateTimeParseException.class.getName())){
            message = "Date Format is not correct, should follow format yyyy-MM-dd-HH.mm.ss";
        }
        if(ex.getClass().getName().equals(NumberFormatException.class.getName())){
            message = "The identifier passed is wrong, it should be a number";
        }
        //
        final ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, message, "error occurred");
        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }
}
