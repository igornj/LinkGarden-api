package com.example.linkgarden.service;

import com.example.linkgarden.model.User;
import com.example.linkgarden.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepository repo;
    @Autowired
    UserService(UserRepository userRepository){
        this.repo = userRepository;
    }


    public User saveUser(User user){
        return repo.save(user);
    }

    public Optional<User> getUser(String email) {
       return repo.findByEmail(email);
    }

    public Optional<User> loginUser(String email, String password){
        return repo.findFirstByEmailAndPassword(email, password);
    }


    @Transactional
    public void deleteUser(String email){
        repo.deleteUser(email);
    }

}
