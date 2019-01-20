package com.kayum.mamun.merchant_products.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.kayum.mamun.merchant_products.R;
import com.kayum.mamun.merchant_products.adapter.CollectionAdapter;
import com.kayum.mamun.merchant_products.interfaces.ProductDataService;
import com.kayum.mamun.merchant_products.model.CollectList;
import com.kayum.mamun.merchant_products.model.Collections;
import com.kayum.mamun.merchant_products.model.CollectionList;
import com.kayum.mamun.merchant_products.model.Collects;
import com.kayum.mamun.merchant_products.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CollectionAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Collections> collectionArrayList;

    ProductDataService service = RetrofitInstance.getRetrofitInstance().create(ProductDataService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view_notice_list);



        /**
         * RecyclerView: Implementing single item click and long press
         * */
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Toast.makeText(MainActivity.this, "Single Click on position :"+collectionArrayList.get(position).getId(),
                //       Toast.LENGTH_SHORT).show();
                final HashMap collectionMap = new HashMap();
                collectionMap.put("collectionTitle",collectionArrayList.get(position).getTitle());
                collectionMap.put("imgSrc",collectionArrayList.get(position).getImagesPrty().getSrc());
                collectionMap.put("bodyHtml",collectionArrayList.get(position).getBody_html());

                getProductsIDs(collectionArrayList.get(position).getId(), collectionMap);
            }

            @Override
            public void onLongClick(View view, int position) {
                //Toast.makeText(MainActivity.this, "Long press on position :"+position,
                //        Toast.LENGTH_LONG).show();
            }
        }));


        /** Call the method with parameter in the interface to get the notice data*/
        Call<CollectionList> call = service.getCollectionData();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<CollectionList>() {
            @Override
            public void onResponse(Call<CollectionList> call, Response<CollectionList> response) {
                collectionArrayList = response.body().getCollectionArrayList();
                Log.wtf("Image link0: ",  collectionArrayList.get(0).getImagesPrty().getSrc());
                generateNoticeList(collectionArrayList);
            }

            @Override
            public void onFailure(Call<CollectionList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateNoticeList(ArrayList<Collections> noticeArrayList) {
        adapter = new CollectionAdapter(noticeArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    private void getProductsIDs (String collectionId, final HashMap collectionMap) {
        Response<CollectList> productId_response;
        Log.wtf("collection id", collectionId + "");
        /** Call the method with parameter in the interface to get the notice data*/
        Call<CollectList> call = service.getProductIds(collectionId);

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<CollectList>() {
            @Override
            public void onResponse(Call<CollectList> call, Response<CollectList> response) {
               // noticeArrayList = response.body().getNoticeArrayList();
                // generateNoticeList(noticeArrayList);
               getProducts(response, collectionMap) ;
                Log.wtf("Product id", response.body().getCollectList().get(0).getProduct_id() + "");
            }

            @Override
            public void onFailure(Call<CollectList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getProducts(Response<CollectList> response, final HashMap collectionMap) {

        StringBuilder idBuilder = new StringBuilder();
        for(Collects collects: response.body().getCollectList()) {
            idBuilder.append(collects.getProduct_id()).append(",");
        }

        /** Call the method with parameter in the interface to get the notice data*/
        String productid = idBuilder.toString();
        Call<JsonObject> call = service.getProductList(productid.substring(0, productid.length()-1));

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                String jsonString = response.body().toString();

                Intent productIntent = new Intent(MainActivity.this, ProductActivity.class);
                productIntent.putExtra("collectionMap",collectionMap);
                productIntent.putExtra("productsString",jsonString);
                startActivity(productIntent);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("onFailure", t.toString());
            }
        });
    }

    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


}