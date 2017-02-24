package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.pojo.User;
import com.boot.service.UserService;


@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
    @ResponseBody
	public String login(String loginName, String loginPassword){
		List<User> list = userService.login(loginName , loginPassword);
		return null == list && list.isEmpty()?"Error":"Success";
	}
	
	@RequestMapping("/")  
    @ResponseBody  
    String home() {  
      return "Hello World!";  
    }  

}
