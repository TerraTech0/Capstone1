package com.example.capstone1.Model;

import com.example.capstone1.Service.CategoryService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "id can't be empty!")
    private String id;

    @NotEmpty(message = "product id can't be empty!")
    private String productId;

    @NotEmpty(message = "mercant id can't be empty!")
    private String merchantId;


    @NotNull(message = "stock can't be null!")
    @Min(value = 10, message = "stock has to be more than 10 at start")
    private Integer stock;
}
