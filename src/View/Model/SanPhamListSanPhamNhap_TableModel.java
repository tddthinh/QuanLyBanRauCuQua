/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Model;

import Model.SanPham;
import java.util.ArrayList;

/**
 *
 * @author THINH
 */
public class SanPhamListSanPhamNhap_TableModel extends SanPhamList_TableModel{
    
    public SanPhamListSanPhamNhap_TableModel(ArrayList<SanPham> SanPhamList) {
        super(SanPhamList);
        String[] column = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính"};
        this.setColumnNames(column);
    }
    
}
