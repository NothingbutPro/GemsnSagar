package com.aryanonline.Model;


import java.io.Serializable;

public class Product_model implements Serializable {

    String product_id;
    String product_name;
    String product_description;
    String product_image;
    String category_id;
    String in_stock;
    String Mrp;
    String price;
    String unit_value;
    String unit;
    String increament;
    String title;

    public Product_model(String product_id, String product_name, String product_image, String title , String unit_value , String in_stock ,  String Mrp ) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_image = product_image;
        this.title = title;
        this.unit_value = unit_value;
        this.in_stock = in_stock;
        this.price = Mrp;


    }
    //    public Product_model(String product_id, String product_name, String price) {
//        this.product_id = product_id;
//        this.product_name = product_name;
//        this.price = price;
//    }
    //    public Product_model(String productId, String model, String quantity, String image, String stockStatus, String price, String productname) {
//        this.product_id = productId;
//        this.title = model;
//        this.unit_value = quantity;
//        this.image = image;
//        this.stockStatus = stockStatus;
//        this.price = price;
//        this.productname = productname;
//    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public String getProduct_image() {
        return product_image;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getIn_stock() {
        return in_stock;
    }

    public String getPrice() {
        return price;
    }

    public String getUnit_value() {
        return unit_value;
    }

    public String getUnit() {
        return unit;
    }


    public String getIncreament() {
        return increament;
    }

    public String getTitle() {
        return title;
    }

    public String getMrp() {
        return Mrp;
    }

}
