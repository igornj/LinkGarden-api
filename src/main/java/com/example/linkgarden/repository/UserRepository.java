package com.example.linkgarden.repository;

import com.example.linkgarden.model.Garden;
import com.example.linkgarden.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findFirstByEmailAndPassword(String email, String password);

    @Modifying
    @Query(value = "DELETE FROM users WHERE email = :email", nativeQuery = true)
    void deleteUser(@Param("email") String email);
}
