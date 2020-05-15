package Model;

import java.sql.Date;
import java.sql.Time;
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
public class HoaDon {
    private String ma;
    private Date ThoiGianLapDate;
    private Time ThoiGianLapTime;
    private NhanVien NhanVien;
    private ArrayList<ChiTietHoaDon> ChiTietHoaDon;
    private float TongTien;
    public HoaDon(NhanVien NhanVien) {
        this.NhanVien = NhanVien;
    }
    
    public String getMa() {
        return ma;
    }
    
    public NhanVien getNhanVien() {
        return NhanVien;
    }

    public Date getThoiGianLapDate() {
        return ThoiGianLapDate;
    }

    public Time getThoiGianLapTime() {
        return ThoiGianLapTime;
    }
    
    public ArrayList<ChiTietHoaDon> getChiTietHoaDon() {
        return ChiTietHoaDon;
    }

    public float getTongTien() {
        return TongTien;
    }
    
    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setNhanVien(NhanVien NhanVien) {
        this.NhanVien = NhanVien;
    }

    public void setThoiGianLapDate(Date ThoiGianLapDate) {
        this.ThoiGianLapDate = ThoiGianLapDate;
    }

    public void setThoiGianLapTime(Time ThoiGianLapTime) {
        this.ThoiGianLapTime = ThoiGianLapTime;
    }

    public void setChiTietHoaDon(ArrayList<ChiTietHoaDon> ChiTietHoaDon) {
        this.ChiTietHoaDon = ChiTietHoaDon;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }
    
    @Override
    public String toString() {
        return getMa()+"|"+getThoiGianLapDate()+" - "+getThoiGianLapTime()+"|"+getTongTien();
    }
    
}
