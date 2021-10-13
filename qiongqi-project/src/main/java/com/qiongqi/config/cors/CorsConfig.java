package com.qiongqi.config.cors;

import com.qiongqi.config.Filtter.CustomCorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @description: 跨域处理
 * @author: ※狗尾巴草
 * @date: 2020-12-16 23:53
 **/
@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean customCorsfilterRegistrationBean() {
        FilterRegistrationBean<CustomCorsFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new CustomCorsFilter());
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
