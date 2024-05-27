package com.example.foodapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodapp.R;
import com.example.foodapp.fragment.AdminBillFragment;
import com.example.foodapp.fragment.AdminChartFragment;
import com.example.foodapp.fragment.AdminMemberFragment;
import com.example.foodapp.fragment.AdminNoticeFragment;
import com.example.foodapp.fragment.AdminProductFragment;
//import com.example.foodapp.fragment.AdminTypeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // Set default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new AdminProductFragment()).commit();
        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    selectedFragment = new AdminProductFragment();
                } else if (itemId == R.id.food) {
                    selectedFragment = new AdminMemberFragment();
                } else if (itemId == R.id.cart) {
                    selectedFragment = new AdminBillFragment();
                }else if(itemId==R.id.profile){
                    selectedFragment= new AdminNoticeFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();
                }

                return true;
            }
        });
    }
}
