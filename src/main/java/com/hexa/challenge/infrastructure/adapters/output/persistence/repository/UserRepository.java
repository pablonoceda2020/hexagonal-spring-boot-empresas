package com.hexa.challenge.infrastructure.adapters.output.persistence.repository;

import com.hexa.challenge.infrastructure.adapters.output.persistence.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
