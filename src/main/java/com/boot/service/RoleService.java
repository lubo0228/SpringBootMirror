package com.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.RoleDao;
import com.boot.pojo.Role;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public List<Role> getRoles(long userId) {
		return roleDao.findByUserId(userId);
	}

}
