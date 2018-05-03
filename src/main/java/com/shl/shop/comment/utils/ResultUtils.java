package com.shl.shop.comment.utils;

import com.shl.shop.comment.Result.Result;
import com.shl.shop.comment.enums.ResultEnum;

public class ResultUtils {

    public static Result wrapResult(ResultEnum resultEnum, Object data){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getmsg());
        result.setData(data);
        return result;
    }
}
