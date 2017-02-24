package com.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.dao.UserDao;
import com.boot.pojo.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public List<User> login(String loginName, String loginPassword) {
		return userDao.findByLoginNameAndLoginPassword(loginName, loginPassword);
	}

	
}
