package com.example.linkgarden;


import com.example.linkgarden.model.LinkGarden;
import com.example.linkgarden.repository.LinkGardenRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class LinkGardenRepositoryTests {

    @Autowired
    LinkGardenRepository repo;

    @Test
    public void addNewUser(){
        LinkGarden linkGarden = new LinkGarden("test2","asda@hotmail.com", "@aagoasdasrnj",
                "123", "image/somethin", "instagram", "instagram.com");

        LinkGarden savedUser = repo.save(linkGarden);

        Assertions.assertThat(savedUser).isNotNull();
    }


    @Test
    public void findAll(){
        List<LinkGarden> linkGardens = repo.findAll();
        Assertions.assertThat(linkGardens).hasSizeGreaterThan(0);

        for (LinkGarden linkGarden : linkGardens){
            System.out.println(linkGarden);
        }
    }
}
