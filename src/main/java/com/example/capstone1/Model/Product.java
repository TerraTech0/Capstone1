package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Product {

    @NotEmpty(message = "id must not be empty")
    private String productId;

    @NotEmpty(message = "name can't be empty!")
    @Size(min = 3, message = "name must have more than 3 length long")
    private String name;

    @NotNull(message = "price can't be null!")
    @Positive(message = "price must be positive numbers!")
    private double price;

    @NotNull(message = "category id can't be empty!")
    private String categoryID;

    @Max(value = 5,message = "the rating range must be 0-5")
    @Positive
    private int rating;

    private ArrayList<String> reviews = new ArrayList<>();

}
