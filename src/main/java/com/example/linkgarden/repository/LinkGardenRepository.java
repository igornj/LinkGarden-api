package com.example.linkgarden.repository;

import com.example.linkgarden.model.LinkGarden;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LinkGardenRepository extends CrudRepository<LinkGarden, UUID> {

    @Query(value = "SELECT * FROM users INNER JOIN links ON users.id=links.link_id;", nativeQuery = true)
    List<LinkGarden> findAll();


    @Modifying
    @Query(value = "DELETE FROM links WHERE link_id = :id", nativeQuery = true)
    void deleteGarden(@Param("id") UUID id);


    @Modifying
    @Query(value = "DELETE FROM users WHERE id = :id", nativeQuery = true)
    void deleteUser(@Param("id") UUID id);
}
