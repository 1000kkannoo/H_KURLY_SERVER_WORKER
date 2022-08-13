package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Handler;


import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.ErrorDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Exception.DuplicateManagerException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.CONFLICT;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(value = {DuplicateManagerException.class})
    @ResponseBody
    protected ErrorDto badRequest(RuntimeException ex, WebRequest request) {
        return new ErrorDto(CONFLICT.value(), ex.getMessage());
    }
}