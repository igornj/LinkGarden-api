package com.example.linkgarden.controller;

import com.example.linkgarden.model.LinkGarden;
import com.example.linkgarden.service.LinkGardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping(path = "api/garden")
public class LinkGardenController {
    private final LinkGardenService service;

    @Autowired
    LinkGardenController(LinkGardenService service){
        this.service = service;
    }


    @GetMapping()
    public List<LinkGarden> getGardens(){
       return service.getGardens();
    }


    @PostMapping
    public void save(@RequestBody LinkGarden garden){
        service.save(garden);
    }
}
