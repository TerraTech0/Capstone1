package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users = new ArrayList<>();
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();

    private final MerchantStockService merchantStockService;
    private final ProductService productService;

    public UserService(MerchantStockService merchantStockService, ProductService productService) {
        this.merchantStockService = merchantStockService;
        this.productService = productService;
    }


    public void addMerchantStock(MerchantStock merchantStock){
        merchantStocks.add(merchantStock);
    }
    public void addProducts(Product product){
        products.add(product);
    }
    public ArrayList<Product> getProducts(){
        return products;
    }
    public ArrayList<MerchantStock> getMerchantStocks(){
        return merchantStocks;
    }
    public ArrayList<User> getUsers(){
        return users;
    }

    public void addUsers(User user) {
        users.add(user);
    }

    public boolean updateUser(String id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equalsIgnoreCase(id)) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equalsIgnoreCase(id)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

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

    /*
    12-
        Create endpoint where user can buy a product directly
        • this endpoint should accept user id, product id, merchant id.
        • check if all the given ids are valid or not
        • check if the merchant has the product in stock or return bad request.
        • reduce the stock from the MerchantStock.
        • deducted the price of the product from the user balance.
        • if balance is less than the product price return bad request.
     */
    public User findUserById(String userId){
        for (User user : users){
            if (user.getId().equalsIgnoreCase(userId)){
                return user;
            }
        }
        return null;
    }
    public MerchantStock findMerchantStock(String productId, String merchantId){
        for (MerchantStock merchantStock : merchantStocks){
            if (merchantStock.getProductId().equalsIgnoreCase(productId) && merchantStock.getMerchantId().equalsIgnoreCase(merchantId)) {
                return merchantStock;
            }
        }
        return null;
    }

    public Product findProductById(String productId){
        for (Product product : products){
            if (product.getProductId().equalsIgnoreCase(productId)){
                return product;
            }
        }
        System.out.println("Product with ID " + productId + " not found.");
        return null;
    }


    public String buyProduct(String userId, String productId, String merchantId) {
        User user = findUserById(userId);
        if (user == null) {
            return "User not found";
        }
        Product product = findProductById(productId);
        if (product == null) {
            System.out.println("Product not found. Product ID: " + productId);
            return "Product not found";
        }
        MerchantStock merchantStock = findMerchantStock(productId, merchantId);
        if (merchantStock == null) {
            return "Merchant or product not found in merchant's stock";
        }
        if (merchantStock.getStock() <= 0) {
            return "Product not in stock";
        }
        if (user.getBalance() < product.getPrice()) {
            return "Not enough balance";
        }
        user.setBalance(user.getBalance() - product.getPrice());
        merchantStock.setStock(merchantStock.getStock() - 1);
        return "Successfully buy the product!";
    }




    //this method to rete the product
    public String rateProduct(String userId, String productId, int rating) {
        User user = findUserById(userId);
        if (user == null) {
            return "User not found";
        }

        Product product = findProductById(productId);
        if (product == null) {
            System.out.println("product is : "+product);
            return "Product not found";
        }
        product.setRating(rating);
        return "Rating successful";
    }

    //write a method to add product into cart !
    private final ArrayList<Product> cart = new ArrayList<>();
    public ArrayList<Product> addProductToCart(String productId, int quantity){
        for (Product product : products){
            if (product.getProductId().equalsIgnoreCase(productId)){
                for (int i = 0; i < quantity; i++) {
                    cart.add(product);
                }
                break;
            }
        }
        return cart;
    }



    // This method allows users to write reviews for products
    public String addReview(String userId, String productId, String reviews) {
        User user = findUserById(userId);
        if (user == null) {
            return "User not found";
        }

        Product product = findProductById(productId);
        if (product == null) {
            return "Product not found";
        }
        product.getReviews().add(reviews);
        return "Review added successfully";
    }

}
