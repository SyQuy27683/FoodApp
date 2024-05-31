package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.dao.NoticeDAO;
import com.example.foodapp.model.Notice;

import java.util.ArrayList;

public class NoticeAdminAdapter extends RecyclerView.Adapter<NoticeAdminAdapter.MyViewHolder> {
    private ArrayList<Notice> list;
    private Context context;
    private NoticeDAO noticeDAO;

    public NoticeAdminAdapter(ArrayList<Notice> list, Context context) {
        this.list = list;
        this.context = context;
        this.noticeDAO = new NoticeDAO(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_notice_admin, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Notice notice = list.get(holder.getAdapterPosition());
        holder.tvTitle.setText(notice.getTitle());
        holder.tvContent.setText(notice.getContent());
        holder.tvID.setText(String.valueOf(notice.getId()));

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                try {
                    Notice noticeToDelete = list.get(adapterPosition);
                    if (noticeDAO.delete(noticeToDelete.getId())) {
                        list.remove(adapterPosition);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Xóa thành công thông báo", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    Toast.makeText(context, "ex" + ex, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvContent;
        TextView tvID;
        ImageButton btnDelete, btnEdit;

        public MyViewHolder(@NonNull View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvContent = view.findViewById(R.id.tvContent);
            btnDelete = view.findViewById(R.id.btnDelete);
            btnEdit = view.findViewById(R.id.btnEdit);
            tvID = view.findViewById(R.id.tvID);
        }
    }
}
