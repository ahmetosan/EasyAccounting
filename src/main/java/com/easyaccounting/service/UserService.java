package com.easyaccounting.service;

import com.easyaccounting.dto.UserDTO;

import java.util.List;

public interface UserService  {

    List<UserDTO> listAllUsers();
    void save(UserDTO dto);
    UserDTO findByUsername(String username);
    UserDTO update(UserDTO dto);
    void deleteByUsername(String username);

}
