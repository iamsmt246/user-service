package com.realtimeStockPortfolio.userService.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class ErrorDetails {
    Date timestamp;
    String message;
    String details;

    public ErrorDetails(Date timestamp, String message, String details){
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
