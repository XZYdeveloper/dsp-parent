package com.xzy.dspweb;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.controller","com.service","com.entity"})
@MapperScan(basePackages = "com.mapper")
@ServletComponentScan(basePackages = {"com.controller","com.service","com.entity"})
public class DspWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DspWebApplication.class, args);
    }


    /**
     * @Description : druid注入
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
