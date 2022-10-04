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

    @PostMapping("/create-user")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUser(user));
    }


    @GetMapping(value="/get-user/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserInfo(@PathVariable(value = "userEmail") String userEmail){

        Optional<User> optionalUser = service.getUser(userEmail);

        if(!optionalUser.isPresent()){
            return new ResponseEntity<>("User with email: " + userEmail + " does not exist", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Object>(optionalUser.get(), HttpStatus.OK);
    }


    @PostMapping(value = "/login-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> loginUser(@RequestBody @Valid UserDto userDto){

        Optional<User> optionalUser = service.loginUser(userDto.getEmail(), userDto.getPassword());

       if(!optionalUser.isPresent() && !optionalUser.get().getEmail().equals(userDto.getEmail()) && !optionalUser.get().getPassword().equals(userDto.getPassword())){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exists.");
       }

       return ResponseEntity.status(HttpStatus.OK).body(new User(optionalUser.get().getEmail(), optionalUser.get().getId()));

    }


    @PutMapping("/update-user/")
    public ResponseEntity<?> getUserInfo(@RequestBody @Valid UserDto userDto){

        Optional<User> optionalUser = service.getUser(userDto.getEmail());
        if(!optionalUser.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email: " + userDto.getEmail() + " does not exist");

        BeanUtils.copyProperties(userDto, optionalUser);
        service.saveUser(optionalUser.get());

        return new ResponseEntity<>("User was updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/delete-user/{userEmail}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userEmail") String userEmail){

        Optional<User> optionalLinkGarden = service.getUser(userEmail);

        if(!optionalLinkGarden.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email: " + userEmail + " does not exist");
        }

        service.deleteUser(userEmail);
        return ResponseEntity.status(HttpStatus.OK).body("User with userEmail: " + userEmail + " was deleted");

    }
}
