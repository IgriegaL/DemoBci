package com.example.demoBci.Entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PhoneRequest {
    @NotBlank(message = "Phone number is required.")
    private String number;

    @NotBlank(message = "City code is required.")
    private String cityCode;

    @NotBlank(message = "Country code is required.")
    private String countryCode;
}
