package org.example.ordersmanager.data.repository;

import org.example.ordersmanager.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{
}
