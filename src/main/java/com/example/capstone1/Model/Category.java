package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@AllArgsConstructor
public class Category {

    @NotEmpty(message = "id can't be empty!")
    private String id;

    @NotEmpty(message = "name can't be empty!")
    @Size(min = 3, message = "size must be more than 3 length long!")
    private String name;
}
