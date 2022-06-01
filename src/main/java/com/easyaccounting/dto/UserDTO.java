package com.easyaccounting.dto;

import com.easyaccounting.entity.Company;
import com.easyaccounting.entity.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private boolean enabled;
    private String phone;


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getPhone() {
        return phone;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //These fields will be added once they are available
    //    private CompanyDTO company;

   //    private RoleDTO role;

}
