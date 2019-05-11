package com.boot.dao.activiti;

import com.boot.pojo.activiti.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userDao-activiti")
public interface UserDao extends JpaRepository<User, Long> {

    User findByLoginNameAndLoginPassword(String loginName, String loginPassword);

    User findByLoginName(String loginName);
}
