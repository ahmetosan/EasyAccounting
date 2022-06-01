package com.easyaccounting.service;

import com.easyaccounting.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
}
