package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomGlobalExceptionHandler {

        /**
         * Catch si le vehicule est non existant.
         *
         * @param e type d'exception à catcher
         * @return objet de type ResponseEntity
         */
        @ExceptionHandler(VehiculeNotFoundException.class)
        public ResponseEntity<Object> handleMethodSkillNotFoundException(VehiculeNotFoundException e) {
            ApiError apiError = ApiError.of(e.getMessage(), "Skill");

            return ResponseEntity.status(404).body(apiError);
        }

        /**
         * Catch si en cas d'erreur de mapping.
         *
         * @param e type d'exception à catcher
         * @return objet de type ResponseEntity
         */
        @ExceptionHandler(CustomMappingException.class)
        public ResponseEntity<Object> handleMethodCustomMappingException(CustomMappingException e) {
            ApiError apiError = ApiError.of(e.getMessage(), "Skill");

            return ResponseEntity.badRequest().body(apiError);
        }

    }
