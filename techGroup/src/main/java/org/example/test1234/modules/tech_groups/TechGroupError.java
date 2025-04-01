package org.example.test1234.modules.tech_groups;

import lombok.Getter;
import org.example.test1234.appError.ErrorMessage;

@Getter
public enum TechGroupError implements ErrorMessage {

    TECH_GROUP_ALREADY_EXIST(1000, "Tech group already exist"),
    TECH_GROUP_NOT_FOUND(1001, "Tech group not found");

    private final int code;
    private final String message;

    TechGroupError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return this.code + " " + this.message;
    }
}