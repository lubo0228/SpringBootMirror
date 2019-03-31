//package com.boot.component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import com.boot.pojo.User;
//import com.boot.service.UserService;
//
//@Component
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        User user = userService.findByUsername(username);
//
//        if(null == user){
//        	throw new UsernameNotFoundException("用户不存在");
//        }
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//	    authorities.add(new SimpleGrantedAuthority("ROLE"));
//
//        return new org.springframework.security.core.userdetails.User(
//                username, user.getLoginPassword(),
//                true,//是否可用
//                true,//是否过期
//                true,//证书不过期为true
//                true,//账户未锁定为true
//                authorities);
//    }
//}