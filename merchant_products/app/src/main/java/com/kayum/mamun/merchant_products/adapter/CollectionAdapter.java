package com.kayum.mamun.merchant_products.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.kayum.mamun.merchant_products.model.Collections;
import com.kayum.mamun.merchant_products.R;

import java.util.ArrayList;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {

    private ArrayList<Collections> dataList;

    public CollectionAdapter(ArrayList<Collections> dataList) {
        this.dataList = dataList;
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row, parent, false);
        return new CollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder holder, int position) {
        holder.txtCollectionTitle.setText(dataList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CollectionViewHolder extends RecyclerView.ViewHolder {

        TextView txtCollectionTitle;

        CollectionViewHolder(View itemView) {
            super(itemView);
            txtCollectionTitle =  itemView.findViewById(R.id.txt_notice_title);

        }
    }


}