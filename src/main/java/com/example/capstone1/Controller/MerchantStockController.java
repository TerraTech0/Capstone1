package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant-stock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;


    @GetMapping("/get")
    public ArrayList<MerchantStock> getMerchantStock() {
        return merchantStockService.getMerchantStocks();
    }


    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        String result = merchantStockService.addMerchantStocks(merchantStock);
        if (result.equalsIgnoreCase("done")) {
            return ResponseEntity.ok("merchant stock added successfully!");
        } else {
            return ResponseEntity.badRequest().body("can't add marchent stock, coz you have to add marchent and product first!");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable String id, @RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (id == null) {
            return ResponseEntity.status(400).body("id doesn't exist!!!");
        } else {
            merchantStockService.updateMerchantStocks(id, merchantStock);
            return ResponseEntity.status(200).body(new ApiResponse("merchant stock updated successfully!"));
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable String id) {
        if (id == null) {
            return ResponseEntity.status(400).body("bad request");
        } else {
            merchantStockService.deleteMerchantStocks(id);
            return ResponseEntity.status(200).body(new ApiResponse("merchant stock deleted successfully!"));
        }

    }

    @PostMapping("/addStock/{productId}/{merchantId}/{additionalStock}")
    public ResponseEntity addStock(@PathVariable String productId, @PathVariable String merchantId, @PathVariable int additionalStock) {
        if (productId == null || merchantId == null || additionalStock == 0) {
            return ResponseEntity.status(400).body(new ApiResponse("Empty!"));
        }
        merchantStockService.addStock(productId, merchantId, additionalStock);
        return ResponseEntity.status(200).body(new ApiResponse("Stock updated successfully!"));
    }

    @GetMapping("/find/{merchatId}")
    public ResponseEntity findMerchantById(@PathVariable String merchatId) {
        return ResponseEntity.status(200).body(merchantStockService.findMerchantById(merchatId));
    }
}
