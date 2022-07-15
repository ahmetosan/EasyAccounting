package com.easyaccounting.entity;

import com.easyaccounting.enums.ProductStatus;
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
    private String email;
    private String firstname;
    private String lastname;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
    private boolean enabled;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    //review when Role entity is added
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;







}
