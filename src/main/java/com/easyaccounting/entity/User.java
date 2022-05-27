package com.easyaccounting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@Where(clause = "is_deleted=false")
public class User extends BaseEntity{
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String userName;

    private String passWord;
    private boolean enabled;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    //review when Role entity is added
    //@ManyToOne
    //@JoinColumn(name = "role_id")
   // private Role role;







}
