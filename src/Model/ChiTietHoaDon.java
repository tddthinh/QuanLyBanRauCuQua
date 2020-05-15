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
public class ChiTietHoaDon {
    private float SoLuong;
    private float TongTien;
    private SanPham SanPham;
    public ChiTietHoaDon(SanPham SanPham, float SoLuong, float TongTien) {
        this.SoLuong = SoLuong;
        this.TongTien = TongTien;
        this.SanPham = SanPham;
    }
    public ChiTietHoaDon(){
        
    }
    public float getTongTien() {
        return TongTien;
    }
    
    public float getSoLuong() {
        return SoLuong;
    }

    public SanPham getSanPham() {
        return SanPham;
    }
    public void setSanPham(SanPham SanPham) {
        this.SanPham = SanPham;
    }

    public void setSoLuong(float SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    @Override
    public String toString() {
        return getSanPham()+"|"+getSoLuong()+"|"+getTongTien();
    }
    
}
