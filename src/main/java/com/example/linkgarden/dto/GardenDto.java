package com.example.linkgarden.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class GardenDto {
    @Size(min = 4, message = "Link title should have at least 4 characters")
    String linkTitle;

    @Size(min = 8, message = "Link url should have at least 10 characters")
    String linkUrl;

}
