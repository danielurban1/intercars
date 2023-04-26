package pl.intercars.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String name;
    private String price;
    private String priceValueString;
    private double priceValueDouble;
    private String currency;
    private int quantity;

    public Product(String name, String price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        String[] priceTable = price.split("[ /]");
        this.priceValueString = priceTable[0];
        this.priceValueDouble = Double.parseDouble(priceValueString.replace(",", "."));
        this.currency = priceTable[1];
    }

    public String getUnitPrice(){
        return calculatePrice(1);
    }

    public String getFullPrice(){
        return calculatePrice(quantity);
    }

    private String calculatePrice(final int quantity){
        return String.valueOf(priceValueDouble * quantity).replace(".", ",") + " " + currency;
    }
}
