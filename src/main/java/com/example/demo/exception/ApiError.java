package com.example.demo.exception;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ApiError {

    private String message;
    private String microService;

    /**
     * Constructeur de ApiError.
     * Utile pour la désérialisation.
     */
    public ApiError() {
    }

    private ApiError(String message, String microService) {
        this.message = message;
        this.microService = microService;
    }

    /**
     * Constructeur statique d'ApiError.
     *
     * @param message Message d'erreur
     */
    public static ApiError of(String message, String microService) {
        return new ApiError(message, microService);
    }

    /**
     * Accesseur pour message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Accesseur pour le nom du micro service.
     *
     * @return microService
     */
    public String getMicroService() {
        return microService;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
