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
import com.example.shopfood.R;
import com.example.shopfood.interfacee.OrderInterface;
import com.example.shopfood.modal.Cart;
import com.example.shopfood.presenter.OrderPresenter;

import java.util.List;

public class OrderRCVAdapter extends RecyclerView.Adapter<OrderRCVAdapter.viewHolder> {
    Context context;
    List<Cart> list;
    OrderPresenter mOrderPresenter;

    public OrderRCVAdapter(Context context, List<Cart> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_orderdetails, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        mOrderPresenter = new OrderPresenter((OrderInterface) context, context);
        if (list != null){
            Glide.with(context).load(list.get(position).getImage()).into(holder.img);
            holder.name.setText(list.get(position).getName());
            holder.price.setText(String.valueOf(list.get(position).getPrice()));
            holder.quantity.setText(String.valueOf(list.get(position).getQuantity()));
            //
            mOrderPresenter.calculateBill(subTotal());
            String email = list.get(position).getIdUser();
            //
            holder.add.setOnClickListener(view -> {
                int sl = Integer.parseInt(holder.quantity.getText().toString());
                if (sl >= 10 ){
                    Toast.makeText(context, "không được quá 10 ", Toast.LENGTH_SHORT).show();
                }else {
                    holder.quantity.setText(String.valueOf(sl + 1));
                    mOrderPresenter.updateQuantityToCart(String.valueOf(list.get(position).getId()),
                            holder.quantity.getText().toString(), subTotal(), email);
                }
            });
            holder.reduce.setOnClickListener(view -> {
                int sl = Integer.parseInt(holder.quantity.getText().toString());
                if (sl <= 1 ){
                    Toast.makeText(context, "không được quá 1 ", Toast.LENGTH_SHORT).show();
                }else {
                    holder.quantity.setText(String.valueOf(sl - 1));
                    mOrderPresenter.updateQuantityToCart(String.valueOf(list.get(position).getId()),
                            holder.quantity.getText().toString(), subTotal(), email);
                }
            });

            // delete cart
            holder.ivDelete.setOnClickListener(view -> mOrderPresenter.deleteByIdCart(String.valueOf(list.get(position).getId())));

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

    public float subTotal(){
        float tong = 0;
        for (Cart cart : list) {
            tong += cart.getPrice() * cart.getQuantity();
        }
        return tong;
    }
//    public float subTotalClick(int sl, float price){
//        return price * sl;
//    }
    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView img , add , reduce, ivDelete;
        TextView name, price, quantity;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.foodImgOrder);
            add = itemView.findViewById(R.id.addQuantityButton);
            reduce = itemView.findViewById(R.id.reduceQuantityButton);
            name = itemView.findViewById(R.id.foodNameOrder);
            price = itemView.findViewById(R.id.foodPriceOrder);
            quantity = itemView.findViewById(R.id.foodQuantityOrder);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
}
