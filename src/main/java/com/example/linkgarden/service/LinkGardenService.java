package com.example.linkgarden.service;

import com.example.linkgarden.model.LinkGarden;
import com.example.linkgarden.repository.LinkGardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LinkGardenService {

    private final LinkGardenRepository linkGardenRepository;
    @Autowired
    LinkGardenService(LinkGardenRepository linkGardenRepository){
        this.linkGardenRepository = linkGardenRepository;
    }


    public void save(LinkGarden linkGarden){
        linkGardenRepository.save(linkGarden);
    }

    public List<LinkGarden> getGardens(){
        return linkGardenRepository.findAll();
    }

}
