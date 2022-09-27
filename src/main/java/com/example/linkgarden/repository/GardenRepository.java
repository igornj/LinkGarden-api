package com.example.linkgarden.repository;

import com.example.linkgarden.model.Garden;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GardenRepository extends CrudRepository<Garden, Integer> {

    @Query(value = "SELECT * FROM gardens WHERE user_email = :userEmail", nativeQuery = true)
    Optional<List<Garden>> findGardens(@Param("userEmail") String userEmail);

}
