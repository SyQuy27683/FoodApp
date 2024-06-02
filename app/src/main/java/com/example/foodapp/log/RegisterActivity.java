package com.example.foodapp.log;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.dao.MemberDAO;
import com.example.foodapp.model.Member;

public class RegisterActivity extends AppCompatActivity {
    private LinearLayout btnGoToLogin;
    private EditText edtPhoneRegis, edtNameRegis, edtPassRegis, edtRePassRegis;
    private Button btnRegister;
    private MemberDAO memberDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void addControls() {
        edtPhoneRegis = findViewById(R.id.edtPhoneRegis);
        edtNameRegis = findViewById(R.id.edtNameRegis);
        edtPassRegis = findViewById(R.id.edtPassRegis);
        edtRePassRegis = findViewById(R.id.edtRePassRegis);
        btnRegister = findViewById(R.id.btnRegister);
        btnGoToLogin = findViewById(R.id.btnToLogin);
        memberDAO = new MemberDAO(this);
    }

    private void registerUser() {
        String phone = edtPhoneRegis.getText().toString().trim();
        String name = edtNameRegis.getText().toString().trim();
        String password = edtPassRegis.getText().toString().trim();
        String rePassword = edtRePassRegis.getText().toString().trim();

        if (phone.isEmpty() || name.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(rePassword)) {
            Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        Member member = new Member(name, phone, password,1);
        boolean isInserted = memberDAO.insert(member);

        if (isInserted) {
            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
