package com.dsi.mm.repository;

import com.dsi.mm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> getUsersByNameContaining(String keyword);
}
