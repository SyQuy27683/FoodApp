package com.example.foodapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.R;
import com.example.foodapp.adapter.AccountAdapter;
import com.example.foodapp.dao.MemberDAO;
import com.example.foodapp.model.Member;

import java.util.ArrayList;


public class AdminMemberFragment extends Fragment {

    RecyclerView rcvAccount;
    MemberDAO memberDAO;
    AccountAdapter accountAdapter;
    ArrayList<Member> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_admin_member, container, false);

        rcvAccount = view.findViewById(R.id.rcvListAccount);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvAccount.setLayoutManager(layoutManager);

        memberDAO = new MemberDAO(getContext());
        list = memberDAO.getAll();
        accountAdapter = new AccountAdapter(getContext(), list);
        rcvAccount.setAdapter(accountAdapter);
        return  view;
    }
}