package com.kayum.mamun.merchant_products.model;

import com.google.gson.annotations.SerializedName;

public class Products {

    private String id;
    private String product_title;
    private String collection_title;
    private int total_inventory;
    private String image;
    private String collectionImageSrc;

    public Products(String product_title, String collection_title, int total_inventory, String image, String collectionImageSrc) {
        this.product_title = product_title;
        this.collection_title = collection_title;
        this.total_inventory = total_inventory;
        this.image = image;
        this.collectionImageSrc = collectionImageSrc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getCollection_title() {
        return collection_title;
    }

    public void setCollection_title(String collection_title) {
        this.collection_title = collection_title;
    }

    public int getTotal_inventory() {
        return total_inventory;
    }

    public void setTotal_inventory(int total_inventory) {
        this.total_inventory = total_inventory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCollectionImageSrc() {
        return collectionImageSrc;
    }

    public void setCollectionImageSrc(String collectionImageSrc) {
        this.collectionImageSrc = collectionImageSrc;
    }
}
