package com.example.foodapp.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Products;

import java.util.List;

public class Food1Adapter extends RecyclerView.Adapter<Food1Adapter.ViewHolder> {
    private final List<Products> productList;
    private final Context context;

    public Food1Adapter(List<Products> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Products product = productList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText(String.valueOf(product.getPrice()));

        int resId = context.getResources().getIdentifier(product.getImage(), "drawable", context.getPackageName());

        if (resId != 0) {
            holder.imageView.setImageResource(resId);
        } else {

            holder.imageView.setImageResource(R.drawable.anh1);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, descriptionTextView, categoryTextView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tvFoodNameDemo);
            priceTextView = itemView.findViewById(R.id.tvFoodPriceDemo);
//            categoryTextView = itemView.findViewById(R.id.tv);
            imageView = itemView.findViewById(R.id.imgFoodDemo);
        }
    }
}
