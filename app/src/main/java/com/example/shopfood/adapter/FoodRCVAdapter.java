package com.example.shopfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopfood.activity.DetailMenuActivity;
import com.example.shopfood.modal.Food;
import com.example.shopfood.R;

import java.util.List;

public class FoodRCVAdapter extends RecyclerView.Adapter<FoodRCVAdapter.viewHolder>{
    Context context;
    List<Food> list;
    Intent intent;

    public FoodRCVAdapter(Context context, List<Food> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        if (list != null){
            Glide.with(context).load(list.get(position).getImage()).into(holder.foodImg);
            holder.foodName.setText(list.get(position).getName());
            holder.foodPrice.setText(String.valueOf(list.get(position).getPrice()));
            holder.foodContent.setText(list.get(position).getContent());

            holder.layout_itemProduct.setOnClickListener(view -> {
                intent = new Intent(context, DetailMenuActivity.class);
                intent.putExtra("idFood", list.get(position).getId());
                intent.putExtra("foodImg", list.get(position).getImage());
                intent.putExtra("foodName",list.get(position).getName());
                intent.putExtra("foodPrice",String.valueOf(list.get(position).getPrice()));
                intent.putExtra("foodContent",list.get(position).getContent());
                context.startActivity(intent);
            });
        }else {
            Toast.makeText(context, "List null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        if (list == null){
            return 0;
        }
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView foodImg;
        TextView foodName , foodPrice, foodContent;
        CardView layout_itemProduct;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            layout_itemProduct = itemView.findViewById(R.id.layout_itemProduct);
            foodImg = itemView.findViewById(R.id.foodImgOrder);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.foodPriceOrder);
            foodContent = itemView.findViewById(R.id.foodContent);
        }
    }
}
