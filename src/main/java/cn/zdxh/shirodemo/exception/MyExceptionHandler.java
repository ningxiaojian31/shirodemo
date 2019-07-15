package cn.zdxh.shirodemo.exception;

import cn.zdxh.shirodemo.utils.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常管理
 */
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * 没有权限的异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public Result<String> authorizationException(AuthorizationException e){
        return Result.error("没有操作权限");
    }

    /**
     * 其他的一些未知名异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<String> exception(Exception e){
        return Result.error(e.getMessage());
    }
}
