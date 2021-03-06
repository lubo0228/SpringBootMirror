//package com.boot.controller;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.boot.pojo.system.User;
//import com.boot.service.UserService;
//
//
//@Controller
//public class UserController {
//
//	@Value("${application.message}")
//    private String message;
//
//	@Autowired
//	private UserService userService;
//
//	@RequestMapping("/{viewName}")
//	public ModelAndView toPage(@PathVariable("viewName")String viewName) {
//	      return new ModelAndView(viewName);
//	}
//	@RequestMapping("/login2")
//    @ResponseBody
//	public String login(String loginName, String loginPassword){
//		return null == userService.login(loginName , loginPassword)?"Error":"Success";
//	}
//
//	@RequestMapping("/")
//    @ResponseBody
//    public String home() {
//      return "Hello World!";
//    }
//
//	@RequestMapping(value="/login",method=RequestMethod.POST)
//	public String loginPage(User user){
//		return null == userService.loginPage(user)?"haha":"hehe";
//	}
//
//	@RequestMapping("/helloHtml")
//    public String hello(ModelMap model) {
//        model.put("hello", new Date());
//        return "helloHtml";
//	}
//
//	@RequestMapping("/hello")
//    public ModelAndView welcome(ModelMap model) {
//        model.put("time", new Date());
//        model.put("message", this.message);
//        return new ModelAndView("hello");
//	}
//
//}
