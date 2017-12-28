package com.cemgunduz.payconiq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by cem on 27/12/17.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockNotFoundException extends RuntimeException{
    public StockNotFoundException(String message) {
        super(message);
    }
}
