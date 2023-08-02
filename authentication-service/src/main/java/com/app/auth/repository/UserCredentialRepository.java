package com.app.auth.repository;

import com.app.auth.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential,Long> {
    Optional<UserCredential> findByName(String username);
}
