package com.shpark.woosso.api.user.service;

import com.shpark.woosso.api.user.dto.UserDto;

public interface UserService {

    public boolean UserJoin(UserDto userDto) throws Exception;
}
