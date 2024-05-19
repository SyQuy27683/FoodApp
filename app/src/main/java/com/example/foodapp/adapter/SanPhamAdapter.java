package com.example.foodapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.SanPham;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {

    private List<SanPham> sanPhamList;

    public SanPhamAdapter(List<SanPham> sanPhamList) {
        this.sanPhamList = sanPhamList;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recycler, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        holder.tvTenSP.setText(sanPham.getTensp());
        holder.tvGiaBanSP.setText("Giá: " + sanPham.getGiaban() + " VND");
        holder.tvSoLuongSP.setText("Số lượng: " + sanPham.getSoluong());
        holder.tvLoaiSP.setText("Loại: " + sanPham.getLoaisp());
        Picasso.get().load(sanPham.getAvatar()).into(holder.imgViewSP);

        // Xử lý sự kiện click cho các icon nếu cần
        holder.icDeleteSP.setOnClickListener(view -> {
            // Xử lý sự kiện xoá sản phẩm
        });

        holder.icEditSP.setOnClickListener(view -> {
            // Xử lý sự kiện chỉnh sửa sản phẩm
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public static class SanPhamViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewSP;
        TextView tvTenSP, tvGiaBanSP, tvSoLuongSP, tvLoaiSP;
        ImageView icDeleteSP, icEditSP;

        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewSP = itemView.findViewById(R.id.imgViewSP);
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvGiaBanSP = itemView.findViewById(R.id.tvGiaBanSP);
            tvSoLuongSP = itemView.findViewById(R.id.tvSoLuongSP);
            tvLoaiSP = itemView.findViewById(R.id.tvLoaiSP);
            icDeleteSP = itemView.findViewById(R.id.icDeleteSP);
            icEditSP = itemView.findViewById(R.id.icEditSP);
        }
    }
}

