package org.example.test1234.appError;

import lombok.Getter;

@Getter
public class AppException extends Exception {
    private final ErrorMessage errorMessage;

    public AppException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

}
