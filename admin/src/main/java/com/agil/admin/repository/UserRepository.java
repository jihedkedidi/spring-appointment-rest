package com.agil.admin.repository;

import com.agil.admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findAll();

    Boolean existsByEmail(String email);
    Optional<User> findById(Long id);
}
