package com.example.shopfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopfood.Modal.Product;
import com.example.shopfood.R;

import java.util.List;

public class AdapterRCVProduct extends RecyclerView.Adapter<AdapterRCVProduct.viewHolder>{
    Context context;
    List<Product> list;

    public AdapterRCVProduct(Context context, List<Product> list) {
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
            holder.productImg.setImageResource(R.drawable.hambuger);
            holder.productName.setText(list.get(position).getProductName());
            holder.productPrice.setText(String.valueOf(list.get(position).getProductPrice()));
        }else {
            Toast.makeText(context, "List null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView productImg;
        TextView productName , productPrice;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            productImg = itemView.findViewById(R.id.productImg);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
        }
    }
}
