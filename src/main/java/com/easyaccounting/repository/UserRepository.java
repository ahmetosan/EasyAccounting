package com.easyaccounting.repository;

import com.easyaccounting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);


    List<User> findAllByRoleDescriptionIgnoreCase(String description);
}
