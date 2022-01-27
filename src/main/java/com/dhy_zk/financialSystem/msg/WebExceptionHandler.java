package com.dhy_zk.financialSystem.msg;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

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

    @ResponseStatus()
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public AjaxResponse exception(DataIntegrityViolationException e) {
        String msg=null;
        if(e.getMessage().contains("doesn't have a default value"))
        {
            msg="必填字段没填完";
        }
        else
        {
            msg="数据库操作出现异常,请重新尝试";
        }
        return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,msg);
    }


}
