package com.kayum.mamun.merchant_products.model;

import com.google.gson.annotations.SerializedName;

public class CollectionImages {

    @SerializedName("created_at")
    private String created_at;
    @SerializedName("width")
    private String width;
    @SerializedName("height")
    private String height;
    @SerializedName("src")
    private String src;

    public CollectionImages(String created_at, String width, String height, String src) {
        this.created_at = created_at;
        this.width = width;
        this.height = height;
        this.src = src;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}