package com.longmao.girl.utils;

import com.longmao.girl.domain.Result;

public class ResultUtil {
    public static Result success(Object object){//静态方法 Object类型
        Result result = new Result();
        result.setCode(1);
        result.setMsg("成功");
        result.setDate(object);//object 类型定义由 切面 aspect开始
        return result;
    }
    //成功时Object也有可能为null
    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code,String msg){//Integer String 类型
        Result result = new Result();
         result.setCode(code);
         result.setMsg(msg);
         return result;
    }
}
