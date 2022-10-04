package com.example.linkgarden;


import com.example.linkgarden.model.User;
import com.example.linkgarden.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    UserRepository repo;

    @Test
    public void createUser(){
        User user = new User("test2","asda@hotmail.com", "@aagoasdasrnj",
                "123", "image/somethin");

        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
    }


//    @Test
//    public void createGarden(){
//        UUID id = UUID.fromString("b0fcbe04-cdbd-4a64-bb34-ac31fac9ae58");
//        User user = new User("new title 2", "newurl.com");
//
//        Optional<User> optionalLinkGarden = repo.findById(id);
//        //BeanUtils.copyProperties(linkGarden, optionalLinkGarden.get());
//        optionalLinkGarden.get().setLinkTitle(user.getLinkTitle());
//        optionalLinkGarden.get().setLinkUrl(user.getLinkUrl());
//
//        User savedUser = repo.save(optionalLinkGarden.get());
//
//        Assertions.assertThat(savedUser).isNotNull();
//        Assertions.assertThat(savedUser.getLinkTitle()).isEqualTo(user.getLinkTitle());
//        Assertions.assertThat(savedUser.getLinkUrl()).isEqualTo(user.getLinkUrl());
//    }
//
//
//    @Test
//    public void loginUser(){
//        //LinkGarden userInfo = new LinkGarden("joaquina@gmail.com", "123456");
//        User garden = repo.findFirstByEmailAndPassword("joaquina@gmail.com", "123456");
//        Assertions.assertThat(garden).isNotNull();
//    }
//
//
//    @Test
//    public void update(){
//        UUID id = UUID.fromString("b0fcbe04-cdbd-4a64-bb34-ac31fac9ae58");
//        User userBody = new User();
//        userBody.setLinkTitle("new title");
//
//        Optional<User> linkGarden = repo.findById(id);
//        BeanUtils.copyProperties(userBody, linkGarden);
//
//        repo.save(linkGarden.get());
//
//        Assertions.assertThat(linkGarden).isPresent().isNotNull();
//        Assertions.assertThat(linkGarden.get().getLinkTitle()).isEqualTo(userBody.getLinkTitle());
//
//    }
//

//    @Test
//    public void deleteGarden(){
//        String email = "igornj@dale.com";
//        repo.del(uuid);
//
//    }
//
//    @Test
//    public void deleteUser(){
//        UUID uuid = UUID.fromString("b0fcbe04-cdbd-4a64-bb34-ac31fac9ae58");
//        repo.deleteUser(uuid);
//    }
}
