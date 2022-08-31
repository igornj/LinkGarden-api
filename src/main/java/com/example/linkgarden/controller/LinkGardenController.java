package com.example.linkgarden.controller;

import com.example.linkgarden.dto.LinkGardenDto;
import com.example.linkgarden.model.LinkGarden;
import com.example.linkgarden.service.LinkGardenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*")
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


    @PostMapping("/create-user")
    public ResponseEntity<Object> createUser(@RequestBody @Valid LinkGardenDto gardenDto){
        LinkGarden garden = new LinkGarden();
        BeanUtils.copyProperties(gardenDto, garden);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(garden));
    }

    @PostMapping("/create-garden/{id}")
    public ResponseEntity<Object> createGarden(@PathVariable(value = "id") UUID id, @RequestBody @Valid LinkGardenDto gardenDto){

        Optional<LinkGarden> optionalLinkGarden = service.findById(id);
        LinkGarden garden = new LinkGarden();

        if(!optionalLinkGarden.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("An error occurred when trying to create a new Garden");
        }

        BeanUtils.copyProperties(gardenDto, garden);
        optionalLinkGarden.get().setLinkTitle(garden.getLinkTitle());
        optionalLinkGarden.get().setLinkUrl(garden.getLinkUrl());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(optionalLinkGarden.get()));

    }


    @PostMapping("/login-user")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LinkGardenDto loginDto){

        LinkGarden loginUser = new LinkGarden();
        BeanUtils.copyProperties(loginDto, loginUser);

        Optional<LinkGarden>  optionalUser = service.getUser(loginUser.getEmail(), loginUser.getPassword());
        System.out.println(optionalUser);

       if(!optionalUser.isPresent()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ASDASDA");
       }

        return ResponseEntity.status(HttpStatus.FOUND).body("The user was logged in successfully.");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateGarden(@PathVariable(value = "id") UUID id, @RequestBody @Valid LinkGardenDto linkGardenDto ){
        Optional<LinkGarden> optionalLinkGarden = service.findById(id);
        LinkGarden garden = new LinkGarden();

        if(!optionalLinkGarden.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Garden was not found.");
        }

        BeanUtils.copyProperties(linkGardenDto, garden);
        garden.setId(optionalLinkGarden.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(service.save(garden));
    }

    @DeleteMapping("/deleteGarden/{id}")
    public ResponseEntity<?> deleteGarden(@PathVariable(value = "id") UUID id){

        Optional<LinkGarden> optionalLinkGarden = service.findById(id);

        if(!optionalLinkGarden.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Garden with id: " + id + " does not exist");
        }

        service.deleteGarden(id);
        return ResponseEntity.status(HttpStatus.OK).body("Garden with id: " + id + " was deleted");
    }


    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") UUID id){

        Optional<LinkGarden> optionalLinkGarden = service.findById(id);

        if(!optionalLinkGarden.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id: " + id + " does not exist");
        }

        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User with id: " + id + " was deleted");

    }
}
