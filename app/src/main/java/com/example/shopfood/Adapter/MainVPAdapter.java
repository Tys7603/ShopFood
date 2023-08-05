package com.example.shopfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopfood.Modal.Photo;
import com.example.shopfood.R;

import java.util.List;

public class MainVPAdapter extends RecyclerView.Adapter<MainVPAdapter.viewHolder> {
    List<Photo> list;
    Context context;
    public MainVPAdapter(List<Photo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager_auto, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        if (list != null){
            holder.img.setImageResource(list.get(position).getImg());
        }
    }

    @Override
    public int getItemCount() {
       if (list != null){
           return list.size();
       }
       return 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.bannerHome);
        }
    }
}
