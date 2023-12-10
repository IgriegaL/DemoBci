package com.example.demoBci.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters long.")
    private String nombre;
    @NotBlank(message = "Email is required.")
    @Email(message = "Email is not valid.")
    private String correo;
    @Size(min = 5, message = "Password ust have at list 5 characters")
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private List<Phone> phones;

    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean isActive;
}
