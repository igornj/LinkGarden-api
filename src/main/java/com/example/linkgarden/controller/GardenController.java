package com.example.linkgarden.controller;

import com.example.linkgarden.dto.GardenDto;
import com.example.linkgarden.model.Garden;
import com.example.linkgarden.service.GardenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/garden")
public class GardenController {

    @Autowired
    GardenService service;


    @PostMapping("/create-garden/")
    public ResponseEntity<Object> createGarden(@RequestBody @Valid GardenDto gardenDto){
        //ResponseEntity.status(HttpStatus.NOT_FOUND).body("An error occurred when trying to create a new Garden");

        Garden garden = new Garden();
        BeanUtils.copyProperties(gardenDto, garden);

        service.save(garden);
        return ResponseEntity.status(HttpStatus.CREATED).body(garden);

    }


    @PutMapping("/update-garden/{id}")
    public ResponseEntity<Object> updateGarden(@PathVariable(value = "id") Integer id, @RequestBody @Valid Garden gardenDto ){
        Optional<Garden> optionalLinkGarden = service.findById(id);

        if(!optionalLinkGarden.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Garden was not found.");
        }

        BeanUtils.copyProperties(gardenDto, optionalLinkGarden);
        return ResponseEntity.status(HttpStatus.OK).body("The Garden was updated.");
    }


    @GetMapping("/find-gardens/{userEmail}")
    public ResponseEntity<?> findGardens(@PathVariable(value = "userEmail")  String userEmail){
        Optional<List<Garden>> gardens = service.findGardens(userEmail);

        if(!gardens.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no link garden attached to this user.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(gardens);
    }


    @DeleteMapping("/deleteGarden/{id}")
    public ResponseEntity<?> deleteGarden(@PathVariable(value = "id") Integer id){

        Optional<Garden> optionalGarden = service.findById(id);

        if(!optionalGarden.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Garden with id: " + id + " does not exist");
        }

        service.deleteGarden(id);
        return ResponseEntity.status(HttpStatus.OK).body("Garden with id: " + id + " was deleted");
    }
}
