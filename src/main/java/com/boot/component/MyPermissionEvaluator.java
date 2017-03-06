package com.boot.component;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.boot.pojo.User;
import com.boot.service.RoleService;
import com.boot.service.UserService;

@Component
public class MyPermissionEvaluator implements PermissionEvaluator {
	@Autowired
	private UserService loginService;
	@Autowired
	private RoleService roleService;
	@Override
   	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
           String username = authentication.getName();
           User login = loginService.findByUsername(username);
           return true;//roleService.authorized(login.getId(), targetDomainObject.toString(), permission.toString());
   	}
    @Override
   	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
	    // not supported
    	return false;
   	}
   }
