package com.boot.service;

import com.boot.dao.system.UserDao;
import com.boot.mapper.system.UserMapper;
import com.boot.pojo.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = {java.lang.Exception.class})
public class UserService {

    @Resource
    private UserDao userDao;
    @Resource(name = "userDao-activiti")
    private com.boot.dao.activiti.UserDao userDaoActiviti;
    @Autowired
    private UserMapper userMapper;

    public User login(String loginName, String loginPassword) {
        return userDao.findByLoginNameAndLoginPassword(loginName, loginPassword);
    }

    public User loginPage(User user) {
        return userDao.findByLoginNameAndLoginPassword(user.getLoginName(), user.getLoginPassword());
    }

    public User findByUsername(String loginName) {
        return userDao.findByLoginName(loginName);
    }

    public void testJta() {
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

    public void saveActivitUser() {
        List<com.boot.pojo.activiti.User> users = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            com.boot.pojo.activiti.User activiti = new com.boot.pojo.activiti.User();
            activiti.setLoginName(UUID.randomUUID().toString().substring(0, 4));
            users.add(activiti);
            if (users.size() >= 1000) {
                userDaoActiviti.saveAll(users);
                users.clear();
            }
        }

    }

    public User testNull(Long id) {
        User system = new User();
        system.setId(id);
        return userMapper.find(system);
    }
}
