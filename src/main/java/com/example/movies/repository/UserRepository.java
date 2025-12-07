package com.example.movies.repository;

import com.example.movies.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    java.util.Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    java.util.Optional<User> findByUsername(String username);
}
