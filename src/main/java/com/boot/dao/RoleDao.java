package com.boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.pojo.Role;

public interface RoleDao extends JpaRepository<Role, Long>{

	public List<Role> findByUserId(long userId);

}
