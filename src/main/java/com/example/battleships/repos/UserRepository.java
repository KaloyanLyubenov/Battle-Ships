package com.example.battleships.repos;

import com.example.battleships.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    Optional<User> findByIdNot(Long id);

}
