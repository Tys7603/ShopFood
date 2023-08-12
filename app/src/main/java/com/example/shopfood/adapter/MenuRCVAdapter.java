package com.example.shopfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopfood.modal.Food;
import com.example.shopfood.R;

import java.util.List;

public class MenuRCVAdapter extends RecyclerView.Adapter<MenuRCVAdapter.viewHolder> {
    Context context;
    List<Food> list;

    public MenuRCVAdapter(Context context, List<Food> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menuproduct , parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        if(list != null){
            Glide.with(context).load(list.get(position).getImage()).into(holder.imgMenuFood);
            holder.nameMenuFood.setText(list.get(position).getName());
            holder.contentMenuFood.setText(list.get(position).getContent());
            holder.priceMenuFood.setText(String.valueOf(list.get(position).getPrice()));
        }else {
            Toast.makeText(context, "List product meal menu null", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        if (list == null){
            return 0;
        }
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imgMenuFood;
        TextView nameMenuFood, priceMenuFood, contentMenuFood;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imgMenuFood = itemView.findViewById(R.id.imgMenuFood);
            nameMenuFood = itemView.findViewById(R.id.nameMenuFood);
            priceMenuFood = itemView.findViewById(R.id.priceMenuFood);
            contentMenuFood = itemView.findViewById(R.id.contentMenuFood);
        }
    }

}
