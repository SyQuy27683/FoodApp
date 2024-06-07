package com.example.foodapp.dao;

import static com.example.foodapp.database.DbHelper.TABLE_USER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodapp.database.DbHelper;
import com.example.foodapp.model.Member;

import java.util.ArrayList;

public class MemberDAO {
    DbHelper helper;
    Context context;

    public MemberDAO(Context context) {
        helper = new DbHelper(context);
    }

    //getALL
    public ArrayList<Member> getAll() {
        ArrayList<Member> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT id,name, phone, avatar, passwordUser FROM USER";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Integer id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String avatar = cursor.getString(3);
            String passUser = cursor.getString(4);
            Member member = new Member(id, name, phone, avatar, passUser);
            list.add(member);
            cursor.moveToNext();
        }
        db.close();
        return list;
    }

    //Insert
    public boolean insert(Member member) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", member.getName());
        values.put("phone", member.getPhone());
        values.put("avatar", member.getAvatar());
        values.put("passwordUser", member.getPasswordUser());
        values.put("roleUser", member.getRoleUser());
        long add = db.insert("User", null, values);
        db.close();
        return add != -1;
    }


    public boolean update(Member member) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", member.getName());
        values.put("phone", member.getPhone());
        values.put("passUser", member.getPasswordUser());
        long edit = db.update("USER", values, "id = ?", new String[]{String.valueOf(member.getId())});
        return edit > 0;
    }

    //Delete
    public boolean delete(Integer id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int del = db.delete("User", "id =?", new String[]{String.valueOf(id)});
        db.close();
        return del > 0;
    }

    public String checkLogin(String username, String password) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT roleUser FROM User WHERE name = ? AND passwordUser = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password});

        if (cursor.moveToFirst()) {
            int roleUser = cursor.getInt(0);
            cursor.close();
            db.close();
            return roleUser == 0 ? "admin" : "user";
        } else {
            cursor.close();
            db.close();
            return "invalid";
        }
    }

//    public Member getCurrentUser() {
//        SQLiteDatabase db = helper.getReadableDatabase();
//        Member currentUser = null;
//
//        // Lấy ID của người dùng hiện tại từ bất kỳ nguồn nào (ví dụ: SharedPreferences, Session, ...).
//        int userId = 1; // Thay đổi cách lấy ID thực sự của người dùng tại đây.
//
//        // Thực hiện truy vấn để lấy thông tin của người dùng dựa trên ID.
//        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE id = ?";
//        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(userId)});
//
//        // Kiểm tra xem cursor có dữ liệu không.
//        if (cursor != null && cursor.moveToFirst()) {
//            // Khởi tạo đối tượng Member từ dữ liệu của cursor.
//            currentUser = new Member(
//                    cursor.getInt(cursor.getColumnIndex("id")),
//                    cursor.getString(cursor.getColumnIndex("name")),
//                    cursor.getString(cursor.getColumnIndex("phone")),
//                    cursor.getString(cursor.getColumnIndex("avatar")),
//                    cursor.getString(cursor.getColumnIndex("orderID")),
//                    cursor.getString(cursor.getColumnIndex("passwordUser")),
//                    cursor.getInt(cursor.getColumnIndex("roleUser"))
//            );
//        }
//
//        // Đóng cursor và database.
//        if (cursor != null) {
//            cursor.close();
//        }
//        db.close();
//
//        // Trả về đối tượng Member của người dùng hiện tại.
//        return currentUser;
//    }


    public Member getCurrentUser(int userId) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Member currentUser = null;

        // Thực hiện truy vấn để lấy thông tin của người dùng dựa trên ID.
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE id = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(userId)});

        // Kiểm tra xem cursor có dữ liệu không.
        if (cursor != null && cursor.moveToFirst()) {
            // Khởi tạo đối tượng Member từ dữ liệu của cursor.
            currentUser = new Member(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("phone")),
                    cursor.getString(cursor.getColumnIndex("avatar")),
                    cursor.getString(cursor.getColumnIndex("orderID")),
                    cursor.getString(cursor.getColumnIndex("passwordUser")),
                    cursor.getInt(cursor.getColumnIndex("roleUser"))
            );
        }

        // Đóng cursor và database.
        if (cursor != null) {
            cursor.close();
        }
        db.close();

        // Trả về đối tượng Member của người dùng hiện tại.
        return currentUser;
    }

}
