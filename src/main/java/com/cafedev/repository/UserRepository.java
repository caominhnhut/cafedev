package com.cafedev.repository;

import com.cafedev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername( String username );
    
}

