package com.dhy_zk.financialSystem.msg;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author zdh
 */
@ControllerAdvice
public class WebExceptionHandler {

    @ResponseStatus()
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public AjaxResponse customerException(CustomException e) {
        return AjaxResponse.error(e);
    }

    @ResponseStatus()
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResponse exception(Exception e) {
        return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,e.getMessage());
    }


}
