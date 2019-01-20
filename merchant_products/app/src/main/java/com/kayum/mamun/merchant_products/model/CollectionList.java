package com.kayum.mamun.merchant_products.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CollectionList {

    @SerializedName("custom_collections")
    private ArrayList<Collections> collectionList;

    public ArrayList<Collections> getCollectionArrayList() {
        return collectionList;
    }

    public void setCollectionArrayList(ArrayList<Collections> noticeArrayList) {
        this.collectionList = noticeArrayList;
    }


}