package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Service.MerchantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ArrayList<Merchant> getMerchant() {
        return merchantService.getMerchants();
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("Merchant added successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id, @RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (id != null) {
            merchantService.updateMerchant(id, merchant);
            return ResponseEntity.status(200).body(new ApiResponse("merchant updated successfully!"));
        } else {
            return ResponseEntity.status(400).body("id doesn't exist!!!");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id) {
        if (id != null) {
            merchantService.deleteMerchant(id);
            return ResponseEntity.status(200).body(new ApiResponse("merchant deleted successfully!"));
        } else {
            return ResponseEntity.status(400).body("id doesn't exist!!!");
        }

    }
}
