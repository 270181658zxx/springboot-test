package com.longmao.girl.service;

import com.longmao.girl.GirlException.GirlException;
import com.longmao.girl.domain.Girl;
import com.longmao.girl.enums.ResultEnum;
import com.longmao.girl.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;


    @Transactional
    public  void  insertTow(){
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(18);
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setCupSize("B");
        girlB.setAge(19);
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) throws Exception {
        Girl girl = girlRepository.findOne(id);
        Integer age = girl.getAge();
        if ( age < 10){
            //返回 "还在上小学" code=100
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if(age > 10 && age < 16){
            //返回 "还在上初中" code=101
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
        //如果>16 ,加钱
    }

    /**
     * 通过id查一个女生的信息
     * @param id
     * @return
     */
    public Girl findOne(Integer id){
        return girlRepository.findOne(id);
    }
}
