package com.marketapp.beta.Advice;

import com.marketapp.beta.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    public Map<String, String> handleItemException(ItemNotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error_message", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderItemNotFoundException.class)
    public Map<String, String> handleOrderItemException(OrderItemNotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error_message", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PendingOrderNotFoundException.class)
    public Map<String, String> handlePendingOrderException(PendingOrderNotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error_message", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    public Map<String, String> handleOrderException(OrderNotFoundException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error_message", e.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ItemAlreadyExistsException.class)
    public Map<String, String> handleItemException(ItemAlreadyExistsException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error_message", e.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidBarcodeException.class)
    public Map<String, String> handleInvalidBarcodeException(InvalidBarcodeException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error_message", e.getMessage());
        return errorMap;
    }
}
