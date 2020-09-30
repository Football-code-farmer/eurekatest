package com.eurekatest.commoninfo.exception;


import com.eurekatest.commoninfo.utils.ServerResponse;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * description:
 * 统一异常处理类
 *
 * @author hongjw
 * @create 2020-09-10 17:05
 */
@ControllerAdvice
@RestControllerAdvice
public class ExceptionAdvice {
    /**
     * @param ex
     * @return
     * @author hongjw
     * 拦截捕捉自定义异常 ConstraintViolationException.class
     **/

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ServerResponse ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        ex.printStackTrace();
        StringBuffer stringBuffer = new StringBuffer();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            stringBuffer.append(cvl.getMessageTemplate() + ",");

        }
        ex.printStackTrace();
        return ServerResponse
                .ServerResponseInvalidParameters(stringBuffer.toString().substring(0, stringBuffer.length() - 1));
    }

    /**
     * 校验错误拦截处理
     *
     * @param ex 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServerResponse validationBodyException(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        StringBuffer stringBuffer = new StringBuffer();
        BindingResult result = ex.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                FieldError fieldError = (FieldError) error;
                stringBuffer.append(fieldError.getField() + ":" + fieldError.getDefaultMessage() + ",");
            }

        }
        return ServerResponse
                .ServerResponseInvalidParameters(stringBuffer.toString().substring(0, stringBuffer.length() - 1));
    }

    /**
     * 校验错误拦截处理
     *
     * @param ex 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(BindException.class)
    public ServerResponse validationBodyException(BindException ex) {
        ex.printStackTrace();
        StringBuffer stringBuffer = new StringBuffer();
        BindingResult result = ex.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                FieldError fieldError = (FieldError) error;
                stringBuffer.append(fieldError.getField() + ":" + fieldError.getDefaultMessage() + ",");
            }

        }
        return ServerResponse
                .ServerResponseInvalidParameters(stringBuffer.toString().substring(0, stringBuffer.length() - 1));
    }

    /**
     * 参数类型转换错误
     *
     * @param ex 错误
     * @return 错误信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public ServerResponse parameterTypeException(HttpMessageConversionException ex) {
        ex.printStackTrace();
        return ServerResponse.ServerResponseInvalidParameters("类型转换错误");

    }

    /**
     * 参数异常捕捉
     *
     * @param ex
     * @return
     * @author hongjw
     */
    @ExceptionHandler(value = ParamException.class)
    public ServerResponse errorParamExceptionHandle(ParamException ex) {
        ex.printStackTrace();
        return ServerResponse.ServerResponseInvalidParameters(ex.getMessage());
    }

    /**
     * 业务异常捕捉
     *
     * @param ex
     * @return
     * @author hongjw
     */
    @ExceptionHandler(value = ServiceException.class)
    public ServerResponse errorServiceExceptionHandle(ServiceException ex) {
        ex.printStackTrace();
        Integer errorflag = ex.getErrorflag();
        if (null != errorflag) {
            return ServerResponse.createByErrorCodeMessage(errorflag, ex.getMessage());
        }
        return ServerResponse.createByErrorMessage(ex.getMessage());
    }

    /**
     * 异常捕捉
     *
     * @param ex
     * @return
     * @author hongjw
     */
    @ExceptionHandler(value = Exception.class)
    public ServerResponse errorExceptionHandle(Exception ex) {
        ex.printStackTrace();
        return ServerResponse.createByError();
    }
}
