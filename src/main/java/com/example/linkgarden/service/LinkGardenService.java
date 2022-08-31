package com.example.linkgarden.service;

import com.example.linkgarden.model.LinkGarden;
import com.example.linkgarden.repository.LinkGardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class LinkGardenService {

    private final LinkGardenRepository repo;
    @Autowired
    LinkGardenService(LinkGardenRepository linkGardenRepository){
        this.repo = linkGardenRepository;
    }


    public LinkGarden save(LinkGarden linkGarden){
        return repo.save(linkGarden);
    }

    public List<LinkGarden> getGardens(){
        return repo.findAll();
    }

    public Optional<LinkGarden> findById(UUID id) {
       return repo.findById(id);
    }

    public Optional<LinkGarden> getUser(String email, String password){
        return repo.findFirstByEmailAndPassword(email, password);
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
