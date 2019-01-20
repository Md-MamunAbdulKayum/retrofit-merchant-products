package com.kayum.mamun.merchant_products.interfaces;



import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kayum.mamun.merchant_products.model.CollectList;
import com.kayum.mamun.merchant_products.model.Collections;
import com.kayum.mamun.merchant_products.model.CollectionList;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ProductDataService {

    @GET("admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    Call<CollectionList> getCollectionData();

    @GET("admin/collects.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    Call<CollectList> getProductIds(@Query("collection_id") String collectionId);


    @GET("admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    Call<JsonObject> getProductList(@Query("ids") String product_ids);



}