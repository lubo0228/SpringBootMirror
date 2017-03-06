package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	 @RequestMapping("/loginsecurity")
     public String login() {
        return "login";
     }

	
	 @RequestMapping("/haha")
     public String haha() {
         return "haha";
     }

     @RequestMapping("/hehe")
     public String hehe() {
         return "hehe";
     }
}
