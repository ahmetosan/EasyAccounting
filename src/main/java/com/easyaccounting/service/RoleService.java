package com.easyaccounting.service;

import com.easyaccounting.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> listAllRoles();

    RoleDTO findById(Long id);


}
