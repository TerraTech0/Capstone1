package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {


    private final CategoryService categoryService;
    private final MerchantService merchantService;

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();


    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public String addMerchantStocks(MerchantStock merchantStock) {
        for (Merchant merchant : merchantService.getMerchants()) {
            for (Category category : categoryService.getCategories()) {
                if (merchant.getId().equalsIgnoreCase(merchantStock.getMerchantId())
                        && category.getId().equalsIgnoreCase(merchantStock.getProductId())) {
                    return "can't create!";
                }
                merchantStocks.add(merchantStock);
                return "done";
            }
        }
        return "null";
    }

    public boolean updateMerchantStocks(String id, MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equalsIgnoreCase(id)) {
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStocks(String id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equalsIgnoreCase(id)) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }


    /*
    Create endpoint where user can add more stocks of product to a
    merchant Stock
        • this endpoint should accept a product id and merchant id and the amount of
            additional stock.
     */
    public boolean addStock(String productId, String merchantId, int amount) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getProductId().equalsIgnoreCase(productId) && merchantStock.getMerchantId().equalsIgnoreCase(merchantId)) {
                int newStock = merchantStock.getStock() + amount;
                merchantStock.setStock(newStock);
                return true;
            }
        }
        return false;
    }


    public MerchantStock findMerchantById(String merchantId) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getProductId().equalsIgnoreCase(merchantId)) {
                return merchantStock;
            }
        }
        return null;
    }


}

