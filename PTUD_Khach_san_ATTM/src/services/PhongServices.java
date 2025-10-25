/*
 * @ (#) PhongServices.java     1.0     10/23/2025
 *
 *Copyright (c) 2025 IUH. All rights reserved.
 */
package services;


import database.PhongDao;
import entity.Phong;

import java.util.ArrayList;

/*
 * @description: This class represents a bank with many bank accounts
 * @author: Anh, Le The Anh
 * @date: 10/23/2025
 * @version: 1.0
 */
public class PhongServices {

    PhongDao pd = new PhongDao();

    public ArrayList<Phong> getDSP(){
        return pd.getDSPhong();

    }
    public Phong timPhongBangMa(String ma){
        return pd.timPhongBangMa(ma);
    }
    public boolean themPhong(Phong p){
        return pd.themPhong(p);
    }
    public boolean capNhatPhong(Phong p){
        return pd.capNhatPhong(p);
    }
    public  ArrayList<Phong> locPhongTheoLoai(String s){
        return pd.locPhongTheoLoai(s);
    }
}
