package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.dto.UserDto;
import com.thoughtworks.capacity.gtb.mvc.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final Map<String, UserDto> users = new HashMap<>(16);

    public void register(UserDto userDto) {
        String username = userDto.getUsername();

        if (users.containsKey(username)) {
            throw new UserAlreadyExistsException();
        }

        users.put(username, userDto);
    }
}
