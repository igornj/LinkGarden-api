package com.example.linkgarden.service;

import com.example.linkgarden.model.Garden;
import com.example.linkgarden.model.User;
import com.example.linkgarden.repository.GardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GardenService {

    @Autowired
    GardenRepository repo;


    public Optional<Garden> findById(Integer id) {
        return repo.findById(id);
    }

    public Optional<List<Garden>> findGardens(String userEmail) {
        return repo.findGardens(userEmail);
    }



    public void saveGarden(Garden garden) {
        repo.save(garden);
    }

    @Transactional
    public void deleteGarden(Integer id) {
        repo.deleteById(id);
    }


}
