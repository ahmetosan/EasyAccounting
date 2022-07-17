package com.easyaccounting.service.impl;

import com.easyaccounting.dto.RoleDTO;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.repository.RoleRepository;
import com.easyaccounting.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final MapperUtil mapperUtil;

    public RoleServiceImpl(RoleRepository roleRepository, MapperUtil mapperUtil) {
        this.roleRepository = roleRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public List<RoleDTO> listAllRoles() {
        return roleRepository.findAll().stream().map(role -> mapperUtil.convert(role,new RoleDTO())).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {

        return mapperUtil.convert(roleRepository.findById(id).get(),new RoleDTO());
    }
}
