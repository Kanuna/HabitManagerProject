package com.example.habitmanager.repositories;

import com.example.habitmanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUSerByEmail(String username);
}