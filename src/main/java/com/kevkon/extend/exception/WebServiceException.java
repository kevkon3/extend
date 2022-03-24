package com.kevkon.extend.exception;

/**
 * Custom exception class for handling exceptions related to web service calls.
 * Should be used for handling web service errors that contain an HTTP status code, such as 400 or 500 level errors.
 */
public class WebServiceException extends RuntimeException {

    private int statusCode;

    /**
     * Constructor for WebServiceException
     * @param message Error message associated with the web service error
     * @param statusCode HTTP Status code associated with the web service error
     */
    public WebServiceException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    /**
     * Get the HTTP status code associated with the web service error
     * @return HTTP status code
     */
    public int getStatusCode() {
        return statusCode;
    }
}
