package com.aryanonline.Basic_by_PArag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JwellandGems  {
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("stockStatus")
    @Expose
    private String stockStatus;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("productname")
    @Expose
    private String productname;

    public JwellandGems(String productId, String model, String quantity, String image, String stockStatus, String price, String productname) {
        this.productId = productId;
        this.model = model;
        this.quantity = quantity;
        this.image = image;
        this.stockStatus = stockStatus;
        this.price = price;
        this.productname = productname;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
