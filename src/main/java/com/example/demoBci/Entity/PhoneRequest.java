package com.example.demoBci.Entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PhoneRequest {

    @Size(min = 8, message = "Password ust have at list 5 characters")
    @NotBlank(message = "Phone number is required.")
    private String number;

    @NotBlank(message = "City code is required.")
    private String citycode;

    @NotBlank(message = "Country code is required.")
    private String contrycode;
}
