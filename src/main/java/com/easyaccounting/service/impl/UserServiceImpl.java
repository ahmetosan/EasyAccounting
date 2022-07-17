package com.easyaccounting.service.impl;

import com.easyaccounting.dto.UserDTO;
import com.easyaccounting.entity.Company;
import com.easyaccounting.entity.User;
import com.easyaccounting.enums.ProductStatus;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.mapper.UserMapper;
import com.easyaccounting.repository.CompanyRepository;
import com.easyaccounting.repository.UserRepository;
import com.easyaccounting.service.UserService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final UserMapper userMapper;
    private final CompanyRepository companyRepository;
    //private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil, UserMapper userMapper, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;

        this.userMapper = userMapper;
        this.companyRepository = companyRepository;
    }


    @Override
    public List<UserDTO> listAllUsers() {

        return userRepository.findAll().stream().map(user -> mapperUtil.convert(user, new UserDTO())).collect(Collectors.toList());
    }

    @Override
    public void save(UserDTO dto) {
        //dto.setEnabled(true);
        User obj = mapperUtil.convert(dto,new User());
        obj.setCompany(getCurrentCompany());
        obj.setProductStatus(ProductStatus.ACTIVE);
        //obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        userRepository.save(obj);


    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return userMapper.convertToDto(user);
    }

    @Override
    public UserDTO update(UserDTO dto) {

        User user = userRepository.findByUsername(dto.getUsername());

        User convertedUser =userMapper.convertToEntity(dto);

        convertedUser.setId(user.getId());

        userRepository.save(convertedUser);
        return findByUsername(dto.getUsername());
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);

    }

    public Company getCurrentCompany(){
        return companyRepository.findById(1L).get();
    }

//    @Override
//    public Company getCurrentCompany() throws Exception {
//
//        userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        log.info("User Principal logged in USER NAME  : " + userPrincipal.getUsername());
//        log.info("User Principal logged in user COMPANY ID : " + userPrincipal.getLoggedInUserCompanyId());
//        return companyRepository
//                .findById(
//                        userPrincipal.getLoggedInUserCompanyId()
//                ).orElseThrow(() -> new Exception("This Company is not available"));
//    }









}
