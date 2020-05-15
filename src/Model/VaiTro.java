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
public class VaiTro {
    private String ma, ten;
    private ArrayList<Quyen> quyenList;
    public VaiTro(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
        quyenList = new ArrayList<>();
    }
    public String getMa(){
        return ma;
    }
    public String getTen(){
        return ten;
    }

    public ArrayList<Quyen> getQuyen() {
        return quyenList;
    }
    
    public void setMa(String str){
        ma = str;
    }
    public void setTen(String str){
        ten = str;
    }

    public void setQuyenList(ArrayList<Quyen> quyenList) {
        this.quyenList = quyenList;
    }
    public void addQuyen(Quyen quyen){
        quyenList.add(quyen);
    }

    @Override
    public String toString() {
        return getTen();
    }
    
}
