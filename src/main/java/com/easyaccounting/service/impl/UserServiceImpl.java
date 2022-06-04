package com.easyaccounting.service.impl;

import com.easyaccounting.dto.UserDTO;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.repository.UserRepository;
import com.easyaccounting.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public List<UserDTO> listAllUsers() {

        return userRepository.findAll().stream().map(user -> mapperUtil.convert(user, new UserDTO())).collect(Collectors.toList());
    }
}
