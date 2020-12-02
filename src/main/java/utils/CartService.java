package utils;

import model.beans.Product;

import java.util.LinkedHashMap;
import java.util.List;

public class CartService {

    public static double calculatePrice (LinkedHashMap<? extends Product,Integer> cartList) {

        return cartList.keySet().stream().mapToDouble(Product::getCost).sum();

    }




}
