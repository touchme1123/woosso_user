package com.shpark.woosso.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private String userId;      //사용자 아이디

    private String userPassword;    //사용자 비밀번호

    private String userName;    //사용자 이름

    private String farmName;    //농장이름

    private String testNo;  //검정번호

    private String role;
}
