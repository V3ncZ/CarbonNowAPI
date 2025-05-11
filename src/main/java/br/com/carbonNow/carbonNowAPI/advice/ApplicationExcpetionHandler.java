package br.com.carbonNow.carbonNowAPI.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExcpetionHandler {

    //Metodo chamado quando ocorre um erro de validação atraves da anotação @Valid
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handle(MethodArgumentNotValidException erro) {
        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = erro.getBindingResult().getFieldErrors();

        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return errors;
    }

    //Metodo chamado quando ocorre um erro de integridade de dados
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handle(DataIntegrityViolationException erro) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Conflito de dados: " + erro.getMostSpecificCause().getMessage());
        return errors;
    }
}
