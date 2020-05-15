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
public class NhaCungCap {

    private String ma, ten, diachi, nguoilienhe, sotaikhoan, sdt, fax, mathue;

    public NhaCungCap(String ma, String ten, String diachi, String nguoilienhe, String sotaikhoan, String sdt, String fax, String mathue) {
        this.ma = ma;
        this.ten = ten;
        this.diachi = diachi;
        this.nguoilienhe = nguoilienhe;
        this.sotaikhoan = sotaikhoan;
        this.sdt = sdt;
        this.fax = fax;
        this.mathue = mathue;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public String getDiaChi() {
        return diachi;
    }

    public String getSoDienThoai() {
        return sdt;
    }

    public String getMaSoThue() {
        return mathue;
    }

    public String getSoFax() {
        return fax;
    }

    public String getNguoiLienHe() {
        return nguoilienhe;
    }

    public String getSoTaiKhoan() {
        return sotaikhoan;
    }

    public void setMa(String str) {
        ma = str;
    }

    public void setTen(String str) {
        ten = str;
    }

    public void setDiaChi(String str) {
        diachi = str;
    }

    public void setSoDienThoai(String str) {
        sdt = str;
    }

    public void setMaSoThue(String str) {
        mathue = str;
    }

    public void setSoFax(String str) {
        fax = str;
    }

    public void setNguoiLienHe(String str) {
        nguoilienhe = str;
    }

    public void setSoTaiKhoan(String str) {
        sotaikhoan = str;
    }

    @Override
    public String toString() {
        return getMa()+"|"+getTen()+"|"+getDiaChi()+"|"+getSoDienThoai()+"|"+getSoDienThoai()+"|"+getSoFax()+"|"+getNguoiLienHe();
    }
    
}
