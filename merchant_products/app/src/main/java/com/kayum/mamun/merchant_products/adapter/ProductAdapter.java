package com.kayum.mamun.merchant_products.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kayum.mamun.merchant_products.R;
import com.kayum.mamun.merchant_products.model.Collections;
import com.kayum.mamun.merchant_products.model.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<Products> dataList;

    public ProductAdapter(ArrayList<Products> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //
        Log.i("data1: ", dataList.get(position).getProduct_title() + ", " + dataList.get(position).getCollection_title() + ", " + dataList.get(position).getTotal_inventory() + ", " + dataList.get(position).getImage());

        holder.txtProductTitle.setText(dataList.get(position).getProduct_title());
        holder.txtCollectionTitle.setText(dataList.get(position).getCollection_title());
        holder.txtInventory.setText(String.valueOf("Available inventory: "+dataList.get(position).getTotal_inventory()));
        Picasso.get().load(dataList.get(position).getImage()).into(holder.imgCollection);
        // holder.txtNoticeFilePath.setText(dataList.get(position).getFileSource());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView txtCollectionTitle, txtProductTitle, txtInventory;
        ImageView imgCollection;
        ProductViewHolder(View itemView) {
            super(itemView);
            txtCollectionTitle =  itemView.findViewById(R.id.txt_collection_title);
            txtProductTitle =  itemView.findViewById(R.id.txt_product_title);
            txtInventory =  itemView.findViewById(R.id.txt_totla_inventory);
            imgCollection =  itemView.findViewById(R.id.img_collection);
        }
    }


}