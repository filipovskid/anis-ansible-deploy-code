package com.filipovski.drboson.drboson.web.controllers.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.filipovski.drboson.drboson.service.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    ObjectMapper mapper;

    public RestResponseEntityExceptionHandler() {
        super();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        ObjectNode responseBody = createBindExceptionResponse(result);
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
                                                         WebRequest request) {
        BindingResult result = ex.getBindingResult();
        ObjectNode responseBody = createBindExceptionResponse(result);
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ UsernameAlreadyExistsException.class })
    public ResponseEntity<Object> handleUsernameAlreadyExist(UsernameAlreadyExistsException ex, WebRequest request) {
        ObjectNode responseBody = createUsernameExistsExceptionResponse(ex, request);
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    private ObjectNode createBindExceptionResponse(BindingResult result) {
        List<ObjectNode> responseObjects = result.getAllErrors().stream().map(e -> {
            ObjectNode node = mapper.createObjectNode();
            if (e instanceof FieldError) {
                return node.put("field", ((FieldError) e).getField())
                        .put("message", e.getDefaultMessage());
            } else {
                return node.put("object", e.getObjectName())
                        .put("message", e.getDefaultMessage());
            }
        }).collect(Collectors.toList());

        ObjectNode response = mapper.createObjectNode();
        response.putArray("message").addAll(responseObjects);
        response.put("error", "Invalid" + result.getObjectName());

        return response;
    }

    private ObjectNode createUsernameExistsExceptionResponse(UsernameAlreadyExistsException ex, WebRequest request) {
        ObjectNode response = mapper.createObjectNode();
        response.put("message", ex.getMessage());
        response.put("error", "UsernameAlreadyExists");

        return response;
    }
}
