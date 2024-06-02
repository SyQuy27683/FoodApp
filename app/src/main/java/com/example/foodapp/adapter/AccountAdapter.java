package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Member;

import java.util.ArrayList;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.MyViewHoler> {
    Context context;
    ArrayList<Member> list;

    public AccountAdapter(Context context, ArrayList<Member> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_account_member, parent,false);
        return new MyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoler holder, int position) {
        int index = holder.getAdapterPosition();
        Member member = list.get(index);
        holder.tvUserId.setText(String.valueOf(member.getId()));
        holder.tvUserName.setText(member.getName());
        holder.tvUserPhone.setText(member.getPhone());
        holder.tvUserPass.setText(member.getPasswordUser());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoler extends RecyclerView.ViewHolder{
        TextView tvUserId,tvUserName, tvUserPhone, tvUserPass;
        public MyViewHoler(@NonNull View view) {
            super(view);
            tvUserId = view.findViewById(R.id.tvUserId);
            tvUserName = view.findViewById(R.id.tvUserName);
            tvUserPhone = view.findViewById(R.id.tvUserPhone);
            tvUserPass =view.findViewById(R.id.tvUserPass);
        }
    }
}
