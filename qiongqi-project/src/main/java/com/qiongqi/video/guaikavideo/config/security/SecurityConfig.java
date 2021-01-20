package com.qiongqi.video.guaikavideo.config.security;

import com.qiongqi.video.guaikavideo.config.Filtter.JWTAuthorizationFilter;
import com.qiongqi.video.guaikavideo.config.Filtter.ValidateCaptchaFilter;
import com.qiongqi.video.guaikavideo.config.security.handler.MyAuthenticationEntryPoint;
import com.qiongqi.video.guaikavideo.config.security.handler.MyAuthenticationFailureHandler;
import com.qiongqi.video.guaikavideo.config.security.handler.MyAuthenticationSuccessHandler;
import com.qiongqi.video.guaikavideo.config.security.handler.MyLogoutSuccessHandler;
import com.qiongqi.video.guaikavideo.config.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description: SecurityConfig
 * @author: ※狗尾巴草
 * @date: 2020-12-15 15:19
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyAuthenticationSuccessHandler successHandler;// 认证成功处理器

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;// 认证失败处理器

    @Autowired
    private MyLogoutSuccessHandler logoutSuccessHandler; //退出成功处理器

//    @Autowired
//    private MyAccessDeniedHandler accessDeniedHandler; // 登录异常捕获

    @Autowired
    private MyAuthenticationEntryPoint authenticationEntryPoint; //匿名用户访问无权限资源时的异常

    @Autowired
    private ValidateCaptchaFilter validateCaptchaFilter;

    //security的鉴权排除的url列表
    private static final String[] excludedAuthPages = {
            "/qiongqi/project/auth/login",
            "/qiongqi/project/auth/logout",
    };
//    private static final String[] HadRoleUrl={
//            "/user/**",
//            "/**/user/**",
//            "/common/upload/**",
//            "/**/publish/**",
//            "/**/delete/**"
//    };

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new UserDetailsServiceImpl();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置认证方式
        auth.userDetailsService(userDetailsService());
    }

    @Override
    public void configure(WebSecurity web) {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
//                .antMatchers(excludedAuthPages).permitAll()  //无需进行权限过滤的请求路径
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("admin")
//                .antMatchers(HadRoleUrl).hasAnyRole("user","admin")
//                .anyRequest().permitAll()
//            .and()
//                .httpBasic()
           .and()
                .formLogin()
                .loginPage("/project/auth/login")
                .permitAll()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                //登出
           .and()
                .logout()
                .logoutUrl("/project/auth/logout")
//                .permitAll(false)
//                .permitAll()//允许所有用户
                .logoutSuccessHandler(logoutSuccessHandler)//登出成功处理逻辑
                .deleteCookies("JSESSIONID")//登出之后删除cookie

            .and()
                .exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler)//异常捕获
                .authenticationEntryPoint(authenticationEntryPoint) //匿名用户访问无权限资源时的异常

//            .and()
//                .csrf().disable().csrf()
             .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // 不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(validateCaptchaFilter, UsernamePasswordAuthenticationFilter.class);// 验证码拦截器
//        http.addFilterAfter(new ParamValidateFilter(),ValidateCaptchaFilter.class);

    }
}
