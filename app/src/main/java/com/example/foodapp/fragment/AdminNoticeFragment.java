package com.example.foodapp.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.adapter.NoticeAdminAdapter;
import com.example.foodapp.dao.NoticeDAO;
import com.example.foodapp.model.Notice;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdminNoticeFragment extends Fragment {
    NoticeDAO noticeDAO;
    NoticeAdminAdapter noticeAdminAdapter;
    ArrayList<Notice> listNotice;
    RecyclerView rcvNotice;
    FloatingActionButton addNotice;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_notice,container,false);
        addNotice = view.findViewById(R.id.addNotice);
        rcvNotice = view.findViewById(R.id.rcvNotice);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvNotice.setLayoutManager(layoutManager);

        noticeDAO = new NoticeDAO(getContext());
        listNotice=noticeDAO.getAll();
        noticeAdminAdapter = new NoticeAdminAdapter(listNotice, getContext());

        rcvNotice.setAdapter(noticeAdminAdapter);
        addNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.add_notice_diglog, null);
                builder.setView(view);

                EditText edtTitleNotice, edtContentNotice;
                Button btnAddNotice;

                edtTitleNotice = view.findViewById(R.id.edtTitleNotice);
                edtContentNotice = view.findViewById(R.id.edtContentNotice);
                btnAddNotice=view.findViewById(R.id.btnAddNotice);
                AlertDialog dialog = builder.create();

                btnAddNotice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = edtTitleNotice.getText().toString();
                        String content  =edtContentNotice.getText().toString();
                        Notice notice = new Notice(title,content);
                        if(noticeDAO.insert(notice)){
                            Toast.makeText(getContext(), "Thêm thông báo thành công", Toast.LENGTH_SHORT).show();
                            listNotice.clear();
                            noticeAdminAdapter.notifyDataSetChanged();
                            listNotice.addAll(noticeDAO.getAll());
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getContext(), "Thêm thông báo thất bại", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });




                dialog.show();
            }
        });

        return view;
    }
}
