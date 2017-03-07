package com.boot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.boot.component.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*@Autowired
	private DataSource dataSource;*/
	
	@Autowired
	private MyUserDetailsService detailsService; 
	/*
	 * 配置如何通过拦截器保护请求
	 * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
           .csrf().disable()
           .authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/loginsecurity")
                .permitAll()
                .and()	
            .rememberMe()
                .tokenValiditySeconds(86400)
                .key("user")
            	.and()
            .logout()
                .logoutSuccessUrl("/hehe.html")
                .permitAll();
    }

    /* 配置Filter链  */
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
    }
	/* 配置user-detail服务 */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	//auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select login_name , login_password from user where username=?")
    	//												.authoritiesByUsernameQuery("select login_name ,role_name from user where username=?");	
    	auth.userDetailsService(detailsService);
    }

    
    /* 内存用户 */
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
	*/


}