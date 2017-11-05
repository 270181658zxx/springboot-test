package com.longmao.girl.controller;


import com.longmao.girl.domain.Result;
import com.longmao.girl.repository.GirlRepository;
import com.longmao.girl.domain.Girl;
import com.longmao.girl.service.GirlService;
import com.longmao.girl.utils.ResultUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {
    //spring自带的日志处理
    private final static org.slf4j.Logger logger =
            LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女生列表
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList() {
        logger.info("girlList()");
        return girlRepository.findAll();
}
    /**
     * 添加一个女生
     */
    //单个属性换成对象
    /*public Girl girlAdd(@RequestParam("cupSize") String cupSize,
                        @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setCupSize(cupSize);
        girl.setAge(age);*/
    @PostMapping(value = "/girls")       //验证结果返回到对象BindingResult
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {//@Valid表示要在验证的对象

        if (bindingResult.hasErrors()){ //验证输出提示message
      /*  Result result = new Result();
        result.setCode(0);
        result.setMsg(bindingResult.getFieldError().getDefaultMessage());
        return result;*/
           // return  null;//切面 object.toString 抛出异常 “未知错误”
       return ResultUtil.error(0,bindingResult.getFieldError().getDefaultMessage());
        }
       /* girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());*/

      /*  Result result = new Result();
        result.setCode(1);
        result.setMsg("成功");
        result.setDate(girlRepository.save(girl));*/
        return  ResultUtil.success(girlRepository.save(girl));
    }
    //查询一个女生
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlRepository.findOne(id);
    }
    //更新
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);

       return girlRepository.save(girl);
    }

    //删除
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlRepository.delete(id);
    }

    //通过年龄来查询
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }

    @PostMapping(value = "/girls/two")
    public void girlTwo(){ girlService.insertTow();
    }

    @GetMapping(value = "girls/getAge/{id}")
    public void getAge(@PathVariable("id")Integer id) throws Exception {
         girlService.getAge(id);

    }
   }

