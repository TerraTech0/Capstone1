package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/get")
    public ArrayList<Category> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("category added successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable String id, @RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (id != null) {
            categoryService.updateCategory(id, category);
            return ResponseEntity.status(200).body(new ApiResponse("category updated successfully!"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("id doesn't exist"));
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id) {
        if (id != null) {
            categoryService.deleteCategory(id);
            return ResponseEntity.status(200).body(new ApiResponse("Category deleted successfully!"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("id doesn't exist"));
        }

    }
}
