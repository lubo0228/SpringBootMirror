package com.boot.pojo.activiti;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="user")
public class User {    
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name="login_name")
    private  String loginName;

//    @NotBlank
    @Column(name="login_password")
    private  String loginPassword;

//    @NotBlank
    @Column(name="role_name")
    private String roleName;
}