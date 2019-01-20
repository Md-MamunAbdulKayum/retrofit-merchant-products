package com.kayum.mamun.merchant_products.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Collections {

    @SerializedName("id")
    private String id;
    @SerializedName("handle")
    private String handle;
    @SerializedName("title")
    private String title;
    @SerializedName("body_html")
    private String body_html;
    @SerializedName("image")
    private CollectionImages imagesPrty;

    public Collections(String id, String handle, String title, String body_html, CollectionImages imagesPrty) {
        this.id = id;
        this.title = title;
        this.handle = handle;
        this.body_html = body_html;
        this.imagesPrty = imagesPrty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public CollectionImages getImagesPrty() {
        return imagesPrty;
    }

    public void setImagesPrty(CollectionImages imagesPrty) {
        this.imagesPrty = imagesPrty;
    }
}