package com.boot.service;

import com.boot.dao.system.UserDao;
import com.boot.pojo.system.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = { java.lang.Exception.class })
public class UserService {

	@Resource
	private UserDao userDao;
	@Resource(name = "userDao-activiti")
	private com.boot.dao.activiti.UserDao userDaoActiviti;
	
	public User login(String loginName, String loginPassword) {
		return userDao.findByLoginNameAndLoginPassword(loginName, loginPassword);
	}

	public User loginPage(User user) {
		return userDao.findByLoginNameAndLoginPassword(user.getLoginName(), user.getLoginPassword());
	}

	public User findByUsername(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	public void testJta(){
		User system = new User();
		system.setLoginName("system");
		system.setLoginPassword("system");
		system.setRoleName("system");
		userDao.save(system);

		int i = 1 / 0;

		com.boot.pojo.activiti.User activiti = new com.boot.pojo.activiti.User();
		activiti.setLoginName("activiti");
		activiti.setLoginPassword("activiti");
		activiti.setRoleName("activiti");
		userDaoActiviti.save(activiti);
	}
}
