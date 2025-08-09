package com.shpark.woosso.api.user.controller;

import com.shpark.woosso.api.user.dto.UserDto;
import com.shpark.woosso.api.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
@Log4j2
public class UserController {

    private final UserService userService;

    @ResponseBody
    @PostMapping(value = "/join")
    public List<Map<?, ?>> woossoJoin(UserDto userDto) {
        log.info("UserController.woossoJoin Start");
        boolean result = false;

        try {
            result = userService.UserJoin(userDto);
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }

        if(!result) {
            return List.of(Map.of("RESULT", "FAIL"));
        }

        log.info("UserController.woossoJoin End");
        return List.of(Map.of("RESULT", "SUCCESS"));
    }

/*    @PostMapping(value = "/login")
    public List<Map<?, ?>> woossoLogin(@RequestBody Map<String, String> map) {

        return List.of(Map.of("RESULT", "SUCCESS"));
    }*/
}
