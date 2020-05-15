/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Model;

import Model.ChiTietHoaDon;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author THINH
 */
public class ChiTietHoaDonList_TableModel extends AbstractTableModel {

    private final ArrayList<ChiTietHoaDon> CTHDList;
    protected String[] columnNames = {"Tên sản phẩm", "Đơn vị tính", "Đơn giá", "Số lượng", "Thành tiền"};
    private final int TENSANPHAM_COL = 0;
    private final int DONVITINH_COL = 1;
    private final int DONGIA_COL = 2;
    private final int SOLUONG_COL = 3;
    private final int THANHTIEN_COL = 4;
    
    public ChiTietHoaDonList_TableModel(ArrayList<ChiTietHoaDon> CTHDList) {
        this.CTHDList = CTHDList;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return CTHDList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch (i1) {
            case TENSANPHAM_COL:
                return CTHDList.get(i).getSanPham().getTen();
            case DONVITINH_COL:
                return CTHDList.get(i).getSanPham().getLoaiSanPham().getDonViTinh();
            case DONGIA_COL:
                return CTHDList.get(i).getSanPham().getDonGiaBan();
            case SOLUONG_COL:
                return CTHDList.get(i).getSoLuong();
            case THANHTIEN_COL:
                return CTHDList.get(i).getTongTien();
            default:
                return null;
        }
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }

    public ChiTietHoaDon getRow(int i) {
        return CTHDList.get(i);
    }

    public void addRow(ChiTietHoaDon cthd) {
        CTHDList.add(cthd);
        int size = CTHDList.size();
        fireTableRowsInserted(size - 1, size - 1);
    }
    public void editRow(int i, ChiTietHoaDon cthd){
        CTHDList.set(i, cthd);
        fireTableRowsUpdated(i, i);
    }
    public void removeRow(int i) {
        CTHDList.remove(i);
        fireTableRowsDeleted(i, i);
    }

    public void removeAllRow() {
        for (int i = CTHDList.size() - 1; i >= 0; i--) {
            CTHDList.remove(i);
            fireTableRowsDeleted(i, i);
        }

    }

    public boolean editRowByChiTietHoaDon(ChiTietHoaDon cthd) {
        for (int i = 0; i < CTHDList.size(); i++) {
            if(CTHDList.get(i).getSanPham().getMa().equals(cthd.getSanPham().getMa())){
                CTHDList.set(i, cthd);
                fireTableRowsUpdated(i, i);
                return true;
            }
        }
        return false;
    }
    
    public float getTongTienHoaDon(){
        float tongtien = 0;
        for(ChiTietHoaDon cthd : CTHDList){
            tongtien += cthd.getTongTien();
        }
        return tongtien;
    }
    
    public ArrayList<ChiTietHoaDon> getAllRow(){
        return CTHDList;
    }
}
