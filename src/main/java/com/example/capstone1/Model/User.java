package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "id can't be empty!")
    private String id;

    @NotEmpty(message = "username can't be empty!")
    @Size(min = 5, message = "username has to be more than 5 length long!")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "please ensure that the input of password has at least one character and one digit!")
    @NotEmpty(message = "password can't be empty!")
    private String password;

    @NotEmpty(message = "email can't be empty!")
    @Email
    private String email;

    @NotEmpty(message = "role can't be empty!")
    @Pattern(regexp = "^(Admin|Customer)$")
    private String role;

    @NotNull(message = "balance can't be null!")
    @Positive(message = "balance must be poisitve!")
    private  double balance;

}
