package com.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import com.boot.component.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private MyAuthenticationProvider authenticationProvider;//自定义验证
    @Autowired
    private MyUserDetailsService userDetailsService;
    /*@Autowired
    private MyPersistentTokenRepository tokenRepository;
    @Autowired
    private MyAccessDecisionManager accessDecisionManager;
    */
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
            	//.tokenRepository(tokenRepository)
                //.rememberMeServices(rememberMeServices())
                .rememberMeParameter("remember-me").key("key")
                .tokenValiditySeconds(86400)
            	.and()
            .logout()
                .permitAll();
    }

   
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
       // auth.authenticationProvider(authenticationProvider);
    }

    
    /* 内存用户 */
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }*/
    
   /* @Bean
    public RememberMeServices rememberMeServices() {
        // Key must be equal to rememberMe().key()
        PersistentTokenBasedRememberMeServices rememberMeServices =
                new PersistentTokenBasedRememberMeServices("key", userDetailsService, tokenRepository);
        rememberMeServices.setCookieName("remember-me");
        rememberMeServices.setParameter("remember-me");
        rememberMeServices.setTokenValiditySeconds(864000);
        return rememberMeServices;
    }*/


}