package com.example.linkgarden.controller;

import com.example.linkgarden.dto.UserDto;
import com.example.linkgarden.model.User;
import com.example.linkgarden.service.UserService;
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
public class UserController {

    @Autowired
    UserService service;


    @GetMapping()
    public List<User> getGardens(){
       return service.getGardens();
    }


    @PostMapping("/create-user")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }


    @PostMapping(value = "/login-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> loginUser(@RequestBody @Valid UserDto userDto){

        Optional<User> optionalUser = service.getUser(userDto.getEmail(), userDto.getPassword());

       if(!optionalUser.isPresent() && !optionalUser.get().getEmail().equals(userDto.getEmail()) && !optionalUser.get().getPassword().equals(userDto.getPassword())){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exists.");
       }

       return ResponseEntity.status(HttpStatus.OK).body(new User(optionalUser.get().getEmail(), optionalUser.get().getId()));

    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") UUID id){

        Optional<User> optionalLinkGarden = service.findById(id);

        if(!optionalLinkGarden.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id: " + id + " does not exist");
        }

        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User with id: " + id + " was deleted");

    }
}
