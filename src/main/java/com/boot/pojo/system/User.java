package com.boot.pojo.system;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="user")
public class User {    
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank
    @Column(name="login_name")
    private  String loginName;

	@NotBlank
    @Column(name="login_password")
    private  String loginPassword;

	@NotBlank
    @Column(name="role_name")
    private String roleName;
}