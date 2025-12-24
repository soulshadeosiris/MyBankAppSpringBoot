package com.example.myBankApp.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myBankApp.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
