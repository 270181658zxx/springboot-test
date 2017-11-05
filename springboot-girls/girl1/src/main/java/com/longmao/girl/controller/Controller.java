package com.longmao.girl.controller;

import com.longmao.girl.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@org.springframework.stereotype.Controller
//@ResponseBody
@RestController
@RequestMapping("/say")
public class Controller {
//Value注入
   /* @Value("${cupSize}")
    private String cupSize;

    @Value("${age}")
    private Integer age;

    @Value("${content}")
    private String content;*/

   @Autowired
   private GirlProperties girlProperties;
   //不推荐使用不写请求方法
//   @RequestMapping(value ="/hello",method = RequestMethod.GET)
    @GetMapping(value = "/hello")
    public String say(@RequestParam(value = "id" ,
           required = false,defaultValue = "0") Integer myId){
       return "id:" +myId;
      /* return content;*/
     /* return girlProperties.getCupSize();*/
    }
}
