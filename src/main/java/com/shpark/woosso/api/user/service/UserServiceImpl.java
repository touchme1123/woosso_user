package com.shpark.woosso.api.user.service;

import com.shpark.woosso.api.user.domain.User;
import com.shpark.woosso.api.user.dto.UserDto;
import com.shpark.woosso.api.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean UserJoin(UserDto userDto) {
        log.info("UserServiceImpl.UserJoin Start");
        String userId = userDto.getUserId();
        String userPassword = userDto.getUserPassword();

        Boolean isExist = userRepository.existsByUserId(userId);

        if (isExist) {
            log.debug("User already exists");
            log.info("UserServiceImpl.UserJoin End");
            return false;
        }

        User user = new User();

        user.setUserId(userId);
        user.setUserPassword(bCryptPasswordEncoder.encode(userPassword));
        user.setRole("ROLE_ADMIN");

        userRepository.save(user);
        log.info("UserServiceImpl.UserJoin End");
        return true;
    }
}
