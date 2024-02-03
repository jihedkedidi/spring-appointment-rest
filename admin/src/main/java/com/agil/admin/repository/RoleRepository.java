package com.agil.admin.repository;

import java.util.Optional;


import com.agil.admin.model.ERole;
import com.agil.admin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}