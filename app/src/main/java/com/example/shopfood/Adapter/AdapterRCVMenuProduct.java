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

public class AdapterRCVMenuProduct extends RecyclerView.Adapter<AdapterRCVMenuProduct.viewHolder> {
    Context context;
    List<Product> list;

    public AdapterRCVMenuProduct(Context context, List<Product> list) {
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
            holder.productMenuImg.setImageResource(R.drawable.pic_menu);
            holder.productMenuName.setText(list.get(position).getProductName());
            holder.productMenuPrice.setText(String.valueOf(list.get(position).getProductPrice()));
        }else {
            Toast.makeText(context, "List product meal menu null", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView productMenuImg;
        TextView productMenuName, productMenuPrice;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            productMenuImg = itemView.findViewById(R.id.imgMenuProduct);
            productMenuName = itemView.findViewById(R.id.nameProductMenu);
            productMenuPrice = itemView.findViewById(R.id.priceProductMenu);

        }
    }

}
