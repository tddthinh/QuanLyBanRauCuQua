package Model;

import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
public class NhanVien {
    private String ma, ten, diachi, sdt, email, stk, matkhau, tentk;
    private boolean GioiTinh;
    private VaiTro VaiTro;
    public NhanVien(String ma, String ten, String diachi, String sdt, String email, String stk, String matkhau, String tentk, boolean GioiTinh) {
        this.ma = ma;
        this.ten = ten;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
        this.stk = stk;
        this.matkhau = matkhau;
        this.tentk = tentk;
        this.GioiTinh = GioiTinh;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getSoDienThoai() {
        return sdt;
    }

    public String getEmail() {
        return email;
    }

    public String getTenTaiKhoan() {
        return tentk;
    }

    public String getMatkhau() {
        return matkhau;
    }
    public boolean getGioiTinh(){
        return GioiTinh;
    }

    public VaiTro getVaiTro() {
        return VaiTro;
    }

    public String getSoTaiKhoan() {
        return stk;
    }
    
    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setSoDienThoai(String sdt) {
        this.sdt = sdt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public void setSoTaiKhoan(String stk) {
        this.stk = stk;
    }

    public void setTenTaiKhoan(String tentk) {
        this.tentk = tentk;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public void setVaiTro(VaiTro VaiTro) {
        this.VaiTro = VaiTro;
    }

    @Override
    public String toString() {
        return ma+"|"+ten+"|"+diachi+"|"+sdt+"|"+email+"|"+stk+"|"+matkhau+"|"+tentk+"|"+GioiTinh;
    }
    
    
}
