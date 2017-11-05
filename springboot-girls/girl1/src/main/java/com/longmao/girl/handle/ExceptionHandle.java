package com.longmao.girl.handle;

import com.longmao.girl.GirlException.GirlException;
import com.longmao.girl.domain.Result;
import com.longmao.girl.utils.ResultUtil;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice//全局异常统一处理
public class ExceptionHandle {
      public final static org.slf4j.Logger logger =
              LoggerFactory.getLogger(ExceptionHandle.class);

        //处理的类型 Exception.class
        @ExceptionHandler(value = Exception.class)
        //ajax Json
        @ResponseBody
        public Result handle(Exception e) {
            if (e instanceof GirlException) {
                GirlException girlException = (GirlException) e;
                return ResultUtil.error(girlException.getCode(), girlException.getMessage());
            } else {
                logger.error("[系统异常] {}",e);
                return ResultUtil.error(-1, "未知错误");//Result的包装结构输出
            }
        }
}
