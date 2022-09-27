package com.example.linkgarden.service;

import com.example.linkgarden.model.Garden;
import com.example.linkgarden.model.User;
import com.example.linkgarden.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepository repo;
    @Autowired
    UserService(UserRepository userRepository){
        this.repo = userRepository;
    }


    public User save(User user){
        return repo.save(user);
    }

    public List<User> getGardens(){
        return repo.findAll();
    }

    public Optional<User> findById(UUID id) {
       return repo.findById(id);
    }

    public Optional<User> getUser(String email, String password){
        return repo.findFirstByEmailAndPassword(email, password);
    }

    public List<Garden> saveGarden(String link_title, String link_url, UUID id){
        return repo.saveGarden(link_title, link_url, id);
    }

    public Optional<List<Garden>> findGarden(UUID id){
        return repo.findGarden(id);
    }

    @Transactional
    public void deleteGarden(UUID id){
        repo.deleteGarden(id);
    }

    @Transactional
    public void deleteUser(UUID id){
        repo.deleteUser(id);
    }
}
