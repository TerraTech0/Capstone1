package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final CategoryService categoryService;


    ArrayList<Product> products = new ArrayList<>();


    public ArrayList<Product> showProducts() {
        return products;
    }

    public String addProducts(Product product) {
        for (Category category : categoryService.getCategories()) {
            if (category.getId().equalsIgnoreCase(product.getCategoryID())) {
                return "can't created!";
            }
            products.add(product);
            return "done";
        }
        return "null";
    }

    public boolean updateProduct(String id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equalsIgnoreCase(id)) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equalsIgnoreCase(id)) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equalsIgnoreCase(productId)) {
                return product;
            }
        }
        return null;
    }


}
