package Model;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
public class SanPhamNhap {
    private SanPham SanPham;
    private Date NgayNhap;
    private float DonGiaNhap;
    private int SoLuong;

    public SanPhamNhap(SanPham SanPham, Date NgayNhap, float DonGiaNhap, int SoLuong) {
        this.SanPham = SanPham;
        this.NgayNhap = NgayNhap;
        this.DonGiaNhap = DonGiaNhap;
        this.SoLuong = SoLuong;
    }

    public float getDonGiaNhap() {
        return DonGiaNhap;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public SanPham getSanPham() {
        return SanPham;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setDonGiaNhap(float DonGiaNhap) {
        this.DonGiaNhap = DonGiaNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public void setSanPham(SanPham SanPham) {
        this.SanPham = SanPham;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    @Override
    public String toString() {
        return getSanPham().getMa()+"|"+getNgayNhap()+"|"+getDonGiaNhap()+"|"+getSoLuong();
    }
    
}
