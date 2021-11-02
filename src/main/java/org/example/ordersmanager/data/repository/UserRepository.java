package org.example.ordersmanager.data.repository;

import org.example.ordersmanager.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Long>{
    User findUserByName(String username);
}
