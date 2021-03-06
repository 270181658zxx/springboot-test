package com.longmao.girl.properties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//获取前缀为girl的注解 从lym文件中
@ConfigurationProperties(prefix ="girl")
public class GirlProperties {
    private String cupSize;
    private  Integer age;

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
