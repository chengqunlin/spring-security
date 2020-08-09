package com.example.springsecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@EnableWebSecurity //开启WebSecurity模式
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
       // web.ignoring().antMatchers("/");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 匹配许可，首页所有人可以访问
                // 匹配访问规则，需要vip1/2/3权限
                .antMatchers("/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
        http.formLogin();
        http.logout().logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("cyan").password("{pbkdf2}"+new Pbkdf2PasswordEncoder().encode("cyan123")).roles("vip2","vip3")
                .and()
                .withUser("admin").password("{pbkdf2}"+new Pbkdf2PasswordEncoder().encode("admin123")).roles("vip1","vip2","vip3")
                .and()
                .withUser("youke").password("{pbkdf2}"+new Pbkdf2PasswordEncoder().encode("youke123")).roles("vip1","vip2");
    }
}