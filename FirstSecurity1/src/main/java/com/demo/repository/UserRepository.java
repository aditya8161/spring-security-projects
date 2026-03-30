package com.demo.repository;

import com.demo.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser,Long>
{
    //find user by username
    Optional<MyUser> findByUsername(String username);
}
