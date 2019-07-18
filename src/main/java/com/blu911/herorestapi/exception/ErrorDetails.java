package com.blu911.herorestapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
}
