package com.kayum.mamun.merchant_products.model;

import com.google.gson.annotations.SerializedName;

public class Collects {

    @SerializedName("id")
    private String id;
    @SerializedName("collection_id")
    private String collection_id;
    @SerializedName("product_id")
    private String product_id;

    public Collects(String id, String collection_id, String product_id) {
        this.id = id;
        this.collection_id = collection_id;
        this.product_id = product_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}