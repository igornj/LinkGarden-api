package com.example.linkgarden;


import com.example.linkgarden.model.LinkGarden;
import com.example.linkgarden.repository.LinkGardenRepository;
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
public class LinkGardenRepositoryTests {

    @Autowired
    LinkGardenRepository repo;

    @Test
    public void createUser(){
        LinkGarden linkGarden = new LinkGarden("test2","asda@hotmail.com", "@aagoasdasrnj",
                "123", "image/somethin");

        LinkGarden savedUser = repo.save(linkGarden);

        Assertions.assertThat(savedUser).isNotNull();
    }


    @Test
    public void createGarden(){
        UUID id = UUID.fromString("b0fcbe04-cdbd-4a64-bb34-ac31fac9ae58");
        LinkGarden linkGarden = new LinkGarden("new title 2", "newurl.com");

        Optional<LinkGarden> optionalLinkGarden = repo.findById(id);
        //BeanUtils.copyProperties(linkGarden, optionalLinkGarden.get());
        optionalLinkGarden.get().setLinkTitle(linkGarden.getLinkTitle());
        optionalLinkGarden.get().setLinkUrl(linkGarden.getLinkUrl());

        LinkGarden savedUser = repo.save(optionalLinkGarden.get());

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getLinkTitle()).isEqualTo(linkGarden.getLinkTitle());
        Assertions.assertThat(savedUser.getLinkUrl()).isEqualTo(linkGarden.getLinkUrl());
    }


    @Test
    public void update(){
        UUID id = UUID.fromString("b0fcbe04-cdbd-4a64-bb34-ac31fac9ae58");
        LinkGarden linkGardenBody = new LinkGarden();
        linkGardenBody.setLinkTitle("new title");

        Optional<LinkGarden> linkGarden = repo.findById(id);
        BeanUtils.copyProperties(linkGardenBody, linkGarden);

        repo.save(linkGarden.get());

        Assertions.assertThat(linkGarden).isPresent().isNotNull();
        Assertions.assertThat(linkGarden.get().getLinkTitle()).isEqualTo(linkGardenBody.getLinkTitle());

    }


    @Test
    public void deleteGarden(){
        UUID uuid = UUID.fromString("a7276180-bfce-48ea-a502-e2bbf40c043d");
        repo.deleteGarden(uuid);

    }

    @Test
    public void deleteUser(){
        UUID uuid = UUID.fromString("b0fcbe04-cdbd-4a64-bb34-ac31fac9ae58");
        repo.deleteUser(uuid);
    }
}
