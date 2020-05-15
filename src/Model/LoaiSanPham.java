package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
public class LoaiSanPham {

    private String ma, ten, dvt;

    public LoaiSanPham(String ma, String ten, String dvt) {
        this.ma = ma;
        this.ten = ten;
        this.dvt = dvt;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public String getDonViTinh() {
        return dvt;
    }

    public void setMa(String str) {
        ma = str;
    }

    public void setTen(String str) {
        ten = str;
    }

    public void setDonViTinh(String str) {
        dvt = str;
    }

    @Override
    public String toString() {
        return getMa()+"|"+getTen()+"|"+getDonViTinh();
    }
    
}
