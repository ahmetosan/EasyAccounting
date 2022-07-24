package com.easyaccounting.controller;

import com.easyaccounting.dto.UserDTO;
import com.easyaccounting.service.CompanyService;
import com.easyaccounting.service.RoleService;
import com.easyaccounting.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService; //TODO
    private final CompanyService companyService;

    public UserController(UserService userService, RoleService roleService, CompanyService companyService) {
        this.userService = userService;
        this.roleService = roleService;
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public String openUserList(Model model){

        model.addAttribute("users", userService.listAllUsers());

        return "/user/user-list";
    }

    @GetMapping("/add")
    public String addUser(Model model){
        model.addAttribute("user",new UserDTO());
        model.addAttribute("companies",companyService.listAllCompanies());
        model.addAttribute("roles",roleService.listAllRoles());//TODO

        return "/user/user-add";
    }



    @PostMapping("/add")
    public String addUser(UserDTO user, BindingResult bindingResult){

//    if(bindingResult.hasErrors()){
//        model.addAttribute("roles",roleService.listAllRoles());
//        model.addAttribute("users",userService.listAllUsers());
//        return "/user/user-add";
//    }
        userService.save(user);
        return "redirect:/user/list";
    }


    @GetMapping("/update/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {

        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.listAllRoles());
        model.addAttribute("users", userService.listAllUsers());

        return "user/user-update";//thymeleaf

    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, UserDTO dto) {

       userService.update(dto,id);

       return "redirect:/user/list";

    }



    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
         return "redirect:/user/list";//thymeleaf
    }



}
