package com.example.foodapp.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.dao.MemberDAO;
import com.example.foodapp.model.Member;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etName, etPhone, etPassword;
    private MemberDAO memberDAO;
    private Member currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        memberDAO = new MemberDAO(this);
        // Assuming user ID is passed via Intent
        int userId = getIntent().getIntExtra("USER_ID", -1);
        currentUser = memberDAO.getCurrentUser(userId);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        Button btnSave = findViewById(R.id.btnSave);

        if (currentUser != null) {
            etName.setText(currentUser.getName());
            etPhone.setText(currentUser.getPhone());
            etPassword.setText(currentUser.getPasswordUser());
        }

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String phone = etPhone.getText().toString();
            String password = etPassword.getText().toString();

            currentUser.setName(name);
            currentUser.setPhone(phone);
            currentUser.setPasswordUser(password);

            boolean isUpdated = memberDAO.update(currentUser);
            if (isUpdated) {
                Toast.makeText(EditProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(EditProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
