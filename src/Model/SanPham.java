package Model;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
public class SanPham {

    private String ma, ten;
    private LoaiSanPham loai;
    private ArrayList<NhaCungCap> nccList;
    private ArrayList<KhuyenMai> kmList;
    private float DonGiaBan;
    public SanPham(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }
    
    public LoaiSanPham getLoaiSanPham() {
        return loai;
    }

    public ArrayList<NhaCungCap> getNhaCungCap() {
        return nccList;
    }

    public ArrayList<KhuyenMai> getKhuyenMai() {
        return kmList;
    }
    public float getDonGiaBan() {
        return DonGiaBan;
    }
    
    public void setMa(String str) {
        ma = str;
    }

    public void setTen(String str) {
        ten = str;
    }

    public void setLoaiSanPham(LoaiSanPham loai) {
        this.loai = loai;
    }

    public void setNhaCungCap(ArrayList<NhaCungCap> nccList) {
        this.nccList = nccList;
    }

    public void setKhuyenMai(ArrayList<KhuyenMai> kmList) {
        this.kmList = kmList;
    }

    public void setDonGiaBan(float DonGiaBan) {
        this.DonGiaBan = DonGiaBan;
    }

    @Override
    public String toString() {
        return getMa()+"|"+getTen()+"|"+getDonGiaBan();
    }
    
    
}
