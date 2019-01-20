package com.kayum.mamun.merchant_products.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CollectList {

    @SerializedName("collects")
    private ArrayList<Collects> collectList;

    public ArrayList<Collects> getCollectList() {
        return collectList;
    }

    public void setCollectList(ArrayList<Collects> collectList) {
        this.collectList = collectList;
    }
}