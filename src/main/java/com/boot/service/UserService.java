package com.boot.service;

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
	
	public User login(String loginName, String loginPassword) {
		return userDao.findByLoginNameAndLoginPassword(loginName, loginPassword);
	}

	public User loginPage(User user) {
		return userDao.findByLoginNameAndLoginPassword(user.getLoginName(), user.getLoginPassword());
	}

	public User findByUsername(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	
}
