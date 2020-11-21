package com.tblx.api.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BusgpsExceptionHandler {
    
    public static ResponseEntity handleException(Exception exception){
        ResponseEntity response;
        if(isCustomException((exception))) response = createCustomErrorResponse((BusgpsException) exception);
        else response = createInternalServerErrorResponse(exception.getMessage());
        return response;
    }

    private static boolean isCustomException(Exception exception){
        return exception instanceof BusgpsException;
    }

    private static ResponseEntity createCustomErrorResponse(BusgpsException exception){
        return new ResponseEntity(exception.getErrorMessage(), exception.getStatusCode());
    }

    private static ResponseEntity createInternalServerErrorResponse(String exceptionMessage){
        return new ResponseEntity(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
