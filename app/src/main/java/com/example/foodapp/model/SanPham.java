package com.example.foodapp.model;

public class SanPham {
    private int masp;
    private String tensp;
    private int giaban;
    private int soluong;
    private String loaisp;
    private String avatar;

    public SanPham(int masp, String tensp, int giaban, int soluong, String loaisp, String avatar) {
        this.masp = masp;
        this.tensp = tensp;
        this.giaban = giaban;
        this.soluong = soluong;
        this.loaisp = loaisp;
        this.avatar = avatar;
    }

    public SanPham() {
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(String loaisp) {
        this.loaisp = loaisp;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
