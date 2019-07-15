package cn.zdxh.shirodemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "cn.zdxh.shirodemo.mapper")
@EnableSwagger2 //开启Swagger2
public class ShirodemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShirodemoApplication.class, args);
    }

}
