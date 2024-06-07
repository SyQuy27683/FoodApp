package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.fragment.AllFragment;

public class DetailFoodActivity extends AppCompatActivity {

    private TextView tvFoodName, tvFoodPrice;
    private ImageView imgFood;
    private EditText etQuantity;
    private Button btnAddToCart;
    private ImageButton btnDecrease, btnIncrease, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        // Initialize views
        tvFoodName = findViewById(R.id.tvFoodNameDetail);
        tvFoodPrice = findViewById(R.id.tvFoodPriceDetail);
        imgFood = findViewById(R.id.imgFoodDetail);
        etQuantity = findViewById(R.id.etQuantity);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBack = findViewById(R.id.btnBack);

        // Get data from intent
        String foodName = getIntent().getStringExtra("foodName");
        String foodPrice = getIntent().getStringExtra("foodPrice");
        String foodImage = getIntent().getStringExtra("foodImage");

        // Set data to views
        tvFoodName.setText(foodName);
        tvFoodPrice.setText(foodPrice+"$");
        int resImg = getResources().getIdentifier(foodImage, "drawable", getPackageName());
        imgFood.setImageResource(resImg);

        // Configure quantity buttons
        btnDecrease.setOnClickListener(v -> {
            int quantity = getQuantity();
            if (quantity > 1) {
                quantity--;
                etQuantity.setText(String.valueOf(quantity));
            }
        });

        btnIncrease.setOnClickListener(v -> {
            int quantity = getQuantity();
            if (quantity < 50) {
                quantity++;
                etQuantity.setText(String.valueOf(quantity));
            }
        });

        // Handle Add to Cart button click
        btnAddToCart.setOnClickListener(v -> {
            int quantity = getQuantity();
            if (quantity > 0 && quantity <= 50) {
                // Add logic to handle adding to cart
                Toast.makeText(DetailFoodActivity.this, "Added " + quantity + " items to cart.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DetailFoodActivity.this, "Please enter a valid quantity (1-50).", Toast.LENGTH_SHORT).show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });
        // Add text change listener to ensure valid input
        etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                validateQuantity();
            }
        });
    }

    private int getQuantity() {
        try {
            return Integer.parseInt(etQuantity.getText().toString());
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    private void validateQuantity() {
        int quantity = getQuantity();
        if (quantity < 1) {
            etQuantity.setText("1");
        } else if (quantity > 100) {
            etQuantity.setText("100");
        }
    }
}
