package com.shpark.woosso.api.user.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String userId;      //사용자 아이디

    private String userPassword;    //사용자 비밀번호

    private String userName;    //사용자 이름

    private String farmName;    //농장이름

    private String testNo;  //검정번호

    private String role;


}
