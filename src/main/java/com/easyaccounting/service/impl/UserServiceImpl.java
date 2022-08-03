package com.easyaccounting.service.impl;

import com.easyaccounting.dto.CompanyDTO;
import com.easyaccounting.dto.UserDTO;
import com.easyaccounting.entity.Company;
import com.easyaccounting.entity.User;
import com.easyaccounting.entity.common.UserPrincipal;
import com.easyaccounting.enums.ProductStatus;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.mapper.UserMapper;
import com.easyaccounting.repository.CompanyRepository;
import com.easyaccounting.repository.UserRepository;
import com.easyaccounting.service.UserService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final UserMapper userMapper;
    private final CompanyRepository companyRepository;
    private UserPrincipal userPrincipal;
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
//
//    @Override
//    public UserDTO findByUsername(String username) {
//        return null;
//    }

//    @Override
//    public UserDTO findByUsername(String username) {
//        User user = userRepository.findByUsername(username);
//        return userMapper.convertToDto(user);
//    }

    @Override
    public UserDTO findById(Long id) {

        return userMapper.convertToDto(userRepository.findById(id).get());
    }


    @Override
    public void update(UserDTO dto, Long id) {
        dto.setProductStatus(ProductStatus.ACTIVE);
        dto.setCompany(mapperUtil.convert(getCurrentCompany(),new CompanyDTO()));
        dto.setId(id);
        User user=userMapper.convertToEntity(dto);
        userRepository.save(user);

    }

    @Override
    public void delete(Long id) {
     User user = userRepository.getById(id);
     user.setIsDeleted(true);
     userRepository.save(user);

    }

    public Company getCurrentCompany(){
        userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return companyRepository.findById(userPrincipal.getLoggedInUserCompanyId()).get();
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
