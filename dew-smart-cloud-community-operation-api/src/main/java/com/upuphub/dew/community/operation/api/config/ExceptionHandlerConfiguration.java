package com.upuphub.dew.community.operation.api.config;
import com.upuphub.dew.community.operation.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.operation.api.exception.SourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ValidationException;
import java.util.stream.Collectors;

/**
 * 服务器的统一异常处理器，所有服务器在Controller抛出的异常都会在这里处理
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/7/25 22:01
 */

@Slf4j
@RestController
@RestControllerAdvice
public class ExceptionHandlerConfiguration implements ErrorController {
    private static final String ERROR_PATH = "/error";

    /**
     * 400异常
     * NoHandlerFoundException 需要Servlet API支持
     * 客户端请求的语法错误，服务器无法理解
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ServiceResponseMessage handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        log.error("不能正确读取JSON数据", e);
        return ServiceResponseMessage.createByFailCodeMessage(HttpStatus.BAD_REQUEST.value(),"不能正确读取JSON数据");
    }


    /**
     * 处理 @RequestBody方法验证失败
     * @param e 异常信息
     * @return 方法提示
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ServiceResponseMessage handleValidationException(MethodArgumentNotValidException e) {
        log.error("方法参数验证失败", e);
        String error = e.getBindingResult().getAllErrors().stream().map(
                    objectError -> ((FieldError)objectError).getField() + ((FieldError)objectError).getDefaultMessage())
                .collect(Collectors.joining("/"));
        return ServiceResponseMessage.createByFailCodeMessage(HttpStatus.BAD_REQUEST.value(),error);
    }

    /**
     * 处理不带任何注解的参数绑定校验异常
     * @param e 异常信息
     * @return 提示返回
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ServiceResponseMessage handleBingException(BindException e) {
        String errorMsg = e.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> ((FieldError)objectError).getField() + ((FieldError)objectError).getDefaultMessage())
                .collect(Collectors.joining("/"));
        return ServiceResponseMessage.createByFailCodeMessage(HttpStatus.BAD_REQUEST.value(),errorMsg);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ServiceResponseMessage handleValidationException(ValidationException e) {
        log.error("参数验证失败", e);
        return ServiceResponseMessage.createByFailCodeMessage(HttpStatus.BAD_REQUEST.value(),"参数校验失败");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SourceNotFoundException.class)
    public ServiceResponseMessage handlerNotFoundException(NoHandlerFoundException e) {
        log.error("请求的资源不存在", e);
        return ServiceResponseMessage.createByFailCodeMessage(HttpStatus.NOT_FOUND.value(),"请求的资源不存在");
    }

    /**
     * 405异常
     * 需要Servlet API支持
     * 客户端请求中的方法被禁止
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ServiceResponseMessage handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        log.error("请求方法类型不支持", e);
        return ServiceResponseMessage.createByFailCodeMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), "请求方法类型不支持");
    }


    /**
     * 415 异常
     * 需要Servlet API支持
     * 服务器无法处理请求附带的媒体格式
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ServiceResponseMessage handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("内容类型不支持", e);
        return ServiceResponseMessage.createByFailCodeMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "内容类型不支持");
    }


    /**
     * 500 异常
     * 最大的异常处理
     * 服务器内部错误，无法完成请求
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ServiceResponseMessage handleException(Exception e) {
        log.error("网络服务器异常", e);
        return ServiceResponseMessage.createByFailCodeMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @Override
    public String getErrorPath() {
      return ERROR_PATH;
    }

    /**
     * 重写404错误
     * @return 页面找不到的JSON返回信息
     */
    @RequestMapping(value = ERROR_PATH,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ServiceResponseMessage handleError() {
        return ServiceResponseMessage.createByFailCodeMessage(HttpStatus.NOT_FOUND.value(),"请求的API或页面不存在");
    }
}
