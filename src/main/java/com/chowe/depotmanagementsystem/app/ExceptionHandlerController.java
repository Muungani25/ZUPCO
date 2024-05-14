package com.chowe.depotmanagementsystem.app;


import com.chowe.depotmanagementsystem.service.exceptions.Error;
import com.chowe.depotmanagementsystem.service.exceptions.ResourceAlreadyExists;
import com.chowe.depotmanagementsystem.service.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice(annotations ={ RestController.class})
@Slf4j
public class ExceptionHandlerController {


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Error resourceNotFound(ResourceNotFoundException e) {
        log.info("Validation error: {}", e.getMessage());
        return Error.of(4, e.getMessage());
    }
    @ExceptionHandler(ResourceAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Error resourceNotFound(ResourceAlreadyExists e) {
        log.info("Validation error: {}", e.getMessage());
        return Error.of(4, e.getMessage());
    }
}
