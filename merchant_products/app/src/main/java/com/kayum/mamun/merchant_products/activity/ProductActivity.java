package com.kayum.mamun.merchant_products.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kayum.mamun.merchant_products.R;
import com.kayum.mamun.merchant_products.adapter.ProductAdapter;
import com.kayum.mamun.merchant_products.model.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductActivity extends AppCompatActivity {

    private ProductAdapter adapter;
    private RecyclerView recyclerView;
    private TextView titleView, bodyHtmlView, totalProductView;
    private ImageView collImageView;
    private ArrayList<Products> productsArrayList = new ArrayList<Products>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        recyclerView = findViewById(R.id.recycler_view_product_list);
        titleView = (TextView)findViewById(R.id.coll_title);
        bodyHtmlView = (TextView)findViewById(R.id.coll_body_html);
        totalProductView = (TextView)findViewById(R.id.coll_total_product);
        collImageView = (ImageView) findViewById(R.id.coll_img);


        Bundle extras = getIntent().getExtras();
        HashMap collectionMap = (HashMap)extras.getSerializable("collectionMap");
        String collectionTitle = collectionMap.get("collectionTitle").toString();
        String collectionImageSrc = collectionMap.get("imgSrc").toString();
        String bodyHtml = collectionMap.get("bodyHtml").toString();
        String productsString = extras.getString("productsString");

        //Log.i("onResponse", productsString);
        titleView.setText(collectionTitle);
        bodyHtmlView.setText(bodyHtml);
        Picasso.get().load(collectionImageSrc).into(collImageView);


        JsonElement jelement = new JsonParser().parse(productsString);
        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray jarray = jobject.getAsJsonArray("products");
        for (int i = 0; i < jarray.size(); i++) {
            JsonObject jsonObject = jarray.get(i).getAsJsonObject();
            String result = jsonObject.get("id").getAsString();

            Log.i("id", result);
            String productTitle = jsonObject.get("title").getAsString();

            JsonArray image_array = jsonObject.getAsJsonArray("images");
            JsonObject image_jobject = image_array.get(0).getAsJsonObject();
            String image = image_jobject.get("src").getAsString();
            JsonArray variant_array = jsonObject.getAsJsonArray("variants");
            int totalInventory = 0;
            for (int j = 0; j < variant_array.size(); j++) {
                JsonObject varient_jobject = variant_array.get(j).getAsJsonObject();
                totalInventory = totalInventory + varient_jobject.get("inventory_quantity").getAsInt();
            }
            Log.i("data1: ", productTitle + ", " + collectionTitle + ", " + totalInventory + ", " + image);
            productsArrayList.add(new Products(productTitle, collectionTitle, totalInventory, image, collectionImageSrc));
        }
        totalProductView.setText(String.valueOf(productsArrayList.size() +" Products"));
        generateProductList(productsArrayList);
    }

        private void generateProductList(ArrayList<Products> productArrayList) {
            //recyclerView = findViewById(R.id.recycler_view_notice_list);
            adapter = new ProductAdapter(productArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProductActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }



}