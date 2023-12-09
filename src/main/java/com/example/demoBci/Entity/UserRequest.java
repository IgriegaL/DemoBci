package com.example.demoBci.Entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserRequest {

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters long.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email is not valid.")
    private String email;

    @Size(min = 5, message = "Password ust have at list 5 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).*$", message = "The password must have one letter and one number")
    private String password;

    @Valid
    @NotEmpty(message = "At least one phone number must be provided.")
    private List<PhoneRequest> phones;
    public List<Phone> toPhoneList() {
        return phones.stream()
                .map(phoneRequest ->
                        new Phone(null,phoneRequest.getNumber(), phoneRequest.getCityCode(), phoneRequest.getCountryCode()))
                .collect(Collectors.toList());
    }
}
