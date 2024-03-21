package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {

    @NotEmpty(message = "id can't be empty!")
    private String id;

    @NotEmpty(message = "name can't be empty!")
    @Size(min = 3, message = "name must be more than 3 length long!")
    private String name;
}
