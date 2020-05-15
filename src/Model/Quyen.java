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
public class Quyen {
    private String ma, diengiai;

    public Quyen(String ma, String diengiai) {
        this.ma = ma;
        this.diengiai = diengiai;
    }
    public String getMa(){
        return ma;
    }
    public String getDienGiai(){
        return diengiai;
    }
    public void setMa(String str){
        ma = str;
    }
    public void setDienGiai(String str){
        diengiai = str;
    }

    @Override
    public String toString() {
        return getMa()+"|"+getDienGiai();
    }
    
}
