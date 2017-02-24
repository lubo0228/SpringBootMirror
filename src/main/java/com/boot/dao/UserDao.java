package com.boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.pojo.User;

public interface UserDao extends JpaRepository<User, Long>{

	public List<User> findByLoginNameAndLoginPassword(String loginName, String loginPassword);
}
