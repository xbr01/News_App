package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.Holder> {

    private List<FavoriteList> favoriteLists;
    Context context;

    public FavoriteAdapter(List<FavoriteList> favoriteLists,Context context) {
        this.context = context;
        this.favoriteLists = favoriteLists;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFavoriteLists(List<FavoriteList> favoriteLists) {
        this.favoriteLists = favoriteLists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent,int i) {
        return new Holder(LayoutInflater.from(context)
                .inflate(R.layout.favorite_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        FavoriteList f1 = favoriteLists.get(position);
        //holder.tv.setText(f1.getName());

    }

    @Override
    public int getItemCount() {
        return favoriteLists.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView tv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.ftv_name);
        }
    }

}
