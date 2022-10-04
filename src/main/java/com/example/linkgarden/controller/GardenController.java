package com.example.linkgarden.controller;

import com.example.linkgarden.dto.GardenDto;
import com.example.linkgarden.model.Garden;
import com.example.linkgarden.model.User;
import com.example.linkgarden.service.GardenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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

        service.saveGarden(garden);
        return ResponseEntity.status(HttpStatus.CREATED).body(garden);

    }

    @GetMapping(value = "/find-gardens/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findGardens(@PathVariable(value = "userEmail")  String userEmail){
        Optional<List<Garden>> optionalGardens = service.findGardens(userEmail);

        if(!optionalGardens.isPresent()){
            return new ResponseEntity<Object>("There is no link garden attached to this user.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Object>(optionalGardens.get(), HttpStatus.OK);
    }


    @PutMapping("/update-garden/{id}")
    public ResponseEntity<Object> updateGarden(@PathVariable(value = "id") Integer id, @RequestBody @Valid Garden gardenDto ){
        Optional<Garden> optionalLinkGarden = service.findById(id);

        if(!optionalLinkGarden.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Garden was not found.");
        }

        BeanUtils.copyProperties(gardenDto, optionalLinkGarden);
        service.saveGarden(optionalLinkGarden.get());
        return ResponseEntity.status(HttpStatus.OK).body("The Garden was updated.");
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
