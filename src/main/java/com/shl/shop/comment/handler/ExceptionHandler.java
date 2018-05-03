package com.shl.shop.comment.handler;

import com.shl.shop.comment.Result.Result;
import com.shl.shop.comment.enums.ResultEnum;
import com.shl.shop.comment.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Result handlerException(Exception e){
        log.warn("{}",e);
        return ResultUtils.wrapResult(ResultEnum.FAIL,"发生错误，错误信息：" + e.getMessage());
    }
}
