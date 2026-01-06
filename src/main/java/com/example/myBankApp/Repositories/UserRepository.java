package com.example.myBankApp.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myBankApp.Models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}
