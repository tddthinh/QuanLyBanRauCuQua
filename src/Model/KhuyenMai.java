package Model;

import java.sql.Date;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author student
 */
public class KhuyenMai {

    private String ma, hinhthuc;
    private Date ngaybd, ngaykt;
    private float donvi;

    public KhuyenMai(String ma, String hinhthuc, Date ngaybd, Date ngaykt, float donvi) {
        this.ma = ma;
        this.hinhthuc = hinhthuc;
        this.ngaybd = ngaybd;
        this.ngaykt = ngaykt;
        this.donvi = donvi;
    }

    public String getMa() {
        return ma;
    }

    public String getHinhThuc() {
        return hinhthuc;
    }

    public Date getNgayBatDau() {
        return ngaybd;
    }

    public Date getNgayKetThuc() {
        return ngaykt;
    }

    public float getDonVi() {
        return donvi;
    }

    public void setMa(String str) {
        ma = str;
    }
    public void setHinhThuc(String str) {
        hinhthuc = str;
    }
    public void setNgayBatDau(Date date){
        ngaybd = date;
    }
    public void setNgayKetThuc(Date date){
        ngaykt = date;
    }
    public void setDonVi(float i){
        donvi = i;
    }

    @Override
    public String toString() {
        return getMa()+"|"+getNgayBatDau().toString()+"|"+getNgayKetThuc().toString()+"|"+getHinhThuc()+"|"+getDonVi();
    }
    
}
