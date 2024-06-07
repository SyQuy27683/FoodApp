package com.example.foodapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodapp.R;
import com.example.foodapp.activity.EditProfileActivity;
import com.example.foodapp.dao.MemberDAO;
import com.example.foodapp.log.LoginActivity;
import com.example.foodapp.model.Member;

public class UserProfileFragment extends Fragment {

    private TextView tvName, tvPhone, tvAvatar;
    private MemberDAO memberDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        // Khởi tạo MemberDAO
        memberDAO = new MemberDAO(getContext());

        // Lấy thông tin của người dùng hiện tại
        Member currentUser = getCurrentUser();

        // Ánh xạ các thành phần UI
        tvName = view.findViewById(R.id.tvName);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvAvatar = view.findViewById(R.id.tvAvatar);
        Button btnEditProfile = view.findViewById(R.id.btnEditProfile);
        Button btnLogout = view.findViewById(R.id.btnLogout);

        // Hiển thị thông tin của người dùng
        displayUserInfo(currentUser);

        // Thiết lập sự kiện click cho nút chỉnh sửa thông tin
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình chỉnh sửa thông tin cá nhân
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        // Thiết lập sự kiện click cho nút đăng xuất
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình đăng nhập
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish(); // Kết thúc UserProfileFragment
            }
        });

        return view;
    }

    // Phương thức để lấy thông tin của người dùng hiện tại
    private Member getCurrentUser() {
        return memberDAO.getCurrentUser();
    }

    // Phương thức để hiển thị thông tin của người dùng
    private void displayUserInfo(Member user) {
        if (user != null) {
            tvName.setText(user.getName());
            tvPhone.setText(user.getPhone());
            tvAvatar.setText(user.getAvatar());
        }
    }
}
