package utils;

import model.beans.Product;

import java.util.List;

public class CartService {

    public static double calculatePrice (List<? extends Product> cartList) {

        return cartList.stream().mapToDouble(Product::getCost).sum();

    }




}
