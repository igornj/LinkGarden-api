package com.example.linkgarden.controller;

import com.example.linkgarden.dto.GardenDto;
import com.example.linkgarden.dto.LinkGardenDto;
import com.example.linkgarden.model.Garden;
import com.example.linkgarden.model.LinkGarden;
import com.example.linkgarden.service.LinkGardenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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
    public ResponseEntity<Object> createGarden(@PathVariable(value = "id") UUID id, @RequestBody @Valid GardenDto gardenDto){

        Optional<LinkGarden> optionalUser = service.findById(id);
        Garden garden = new Garden();

        if(!optionalUser.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("An error occurred when trying to create a new Garden");
        }

        BeanUtils.copyProperties(gardenDto, garden);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveGarden(garden.getLinkTitle(), garden.getLinkUrl(), id));

    }


    @PostMapping(value = "/login-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> loginUser(@RequestBody @Valid LinkGardenDto loginBody){

        LinkGarden loginUser = new LinkGarden();
        BeanUtils.copyProperties(loginBody, loginUser);

        LinkGarden garden = service.getUser(loginUser.getEmail(), loginUser.getPassword());
        System.out.println(garden.getEmail());

       if(!garden.getEmail().equals(loginBody.getEmail()) && !garden.getPassword().equals(loginBody.getPassword())){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exists.");
       }

       return ResponseEntity.status(HttpStatus.OK).body(new LinkGarden(garden.getEmail(), garden.getId()));

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


    @GetMapping("/find-gardens/{id}")
    public ResponseEntity<?> findGarden(@PathVariable(value = "id") UUID id){
        Optional<List<Garden>> gardens = service.findGarden(id);

        if(!gardens.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no link garden attached to this user.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(gardens);
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
