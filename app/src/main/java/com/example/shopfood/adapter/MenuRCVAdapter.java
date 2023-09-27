package com.example.shopfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopfood.activity.DetailMenuActivity;
import com.example.shopfood.modal.Food;
import com.example.shopfood.R;

import java.util.ArrayList;
import java.util.List;

public class MenuRCVAdapter extends RecyclerView.Adapter<MenuRCVAdapter.viewHolder> {
    Context context;
    List<Food> list;
    List<Food> listText;
    Intent intent;
    public MenuRCVAdapter(Context context, List<Food> list) {
        this.context = context;
        this.list = list;
        this.listText = list;
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

            holder.layout_menu.setOnClickListener(view -> {
                intent = new Intent(context, DetailMenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("idFood", list.get(position).getId());
                intent.putExtra("foodImg", list.get(position).getImage());
                intent.putExtra("foodName",list.get(position).getName());
                intent.putExtra("foodPrice",String.valueOf(list.get(position).getPrice()));
                intent.putExtra("foodContent",list.get(position).getContent());
                context.startActivity(intent);
            });
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

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            // loc du lieu theo dk
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String search = charSequence.toString();
//
//                if (search.isEmpty()){
//                    list = listText;
//                }else {
//                    List<Food> listFood = new ArrayList<>();
//
//                    for (Food food : listText ) {
//                        if(food.getName().toLowerCase().contains(search.toLowerCase())){
//                            listFood.add(food);
//                        }
//
//                    }
//
//                    list = listFood;
//                }
//
//                FilterResults  filterResults = new FilterResults();
//                filterResults.values = list;
//                return filterResults;
//            }
//
//            // lay ket qua loc
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                list = (List<Food>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imgMenuFood;
        TextView nameMenuFood, priceMenuFood, contentMenuFood;
        ConstraintLayout layout_menu;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imgMenuFood = itemView.findViewById(R.id.imgMenuFood);
            nameMenuFood = itemView.findViewById(R.id.nameMenuFood);
            priceMenuFood = itemView.findViewById(R.id.priceMenuFood);
            contentMenuFood = itemView.findViewById(R.id.contentMenuFood);
            layout_menu = itemView.findViewById(R.id.layout_menuFood);
        }
    }

}
