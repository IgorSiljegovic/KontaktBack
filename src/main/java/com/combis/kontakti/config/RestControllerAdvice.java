package com.combis.kontakti.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<?> handleBusinessException(BusinessException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
        List<ConstraintMessage> messages = new ArrayList();

        Map<String, List<ConstraintViolation>> mapaPoruka = ex.getConstraintViolations()
            .stream()
            .collect(Collectors.groupingBy(
                p -> p.getRootBeanClass()
                    .getSimpleName()
                    .concat(".")
                    .concat(p.getPropertyPath().toString())
            ));

        for (Map.Entry<String, List<ConstraintViolation>> entry : mapaPoruka.entrySet()) {
            String error = "";
            String[] opis = entry.getKey().split("\\.");
            for (ConstraintViolation violation : entry.getValue()) {
                error = error.concat(violation.getMessage()).concat(" ");
            }
            ConstraintMessage msg = new ConstraintMessage(opis[0], opis[1], error);
            messages.add(msg);
        }

        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }
}
