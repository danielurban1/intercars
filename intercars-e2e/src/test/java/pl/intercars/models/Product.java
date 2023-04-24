package pl.intercars.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String name;
    private String price;
    private double priceValue;
    private String currency;
    private int quantity;

    public Product(String name, String price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        String[] priceTable = price.split("[ /]");
        this.priceValue = Double.parseDouble(priceTable[0].replace(",", "."));
        this.currency = priceTable[1];
    }
}
