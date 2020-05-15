/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Model;

import Model.NhaCungCap;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author THINH
 */
public class NhaCungCapList_TableModel extends AbstractTableModel{
    
    protected final ArrayList<NhaCungCap> NhaCungCapList;
    protected String[] columnNames = {
        "Tên", "Địa chỉ", "Số điện thoại", "Mã số thuế", "Fax", "Người liên hệ", "Số tài khoản"
    };
    private final int TENNHACUNGCAP_COL = 0;
    private final int DIACHI_COL = 1;
    private final int SODIENTHOAI_COL = 2;
    private final int MST_COL = 3;
    private final int FAX_COL = 4;
    private final int NGUOILIENHE_COL = 5;
    private final int STK_COL = 6;
    

    public NhaCungCapList_TableModel(ArrayList<NhaCungCap> NhaCungCapList) {
        this.NhaCungCapList = NhaCungCapList;
    }
    public void setColumnNames(String[] columnNames){
        this.columnNames = columnNames;
    }
    @Override
    public int getRowCount() {
        return NhaCungCapList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch (i1) {
            case TENNHACUNGCAP_COL:
                return NhaCungCapList.get(i).getTen();
            case DIACHI_COL:
                return NhaCungCapList.get(i).getDiaChi();
            case SODIENTHOAI_COL:
                return NhaCungCapList.get(i).getSoDienThoai();
            case MST_COL:
                return NhaCungCapList.get(i).getMaSoThue();
            case FAX_COL:
                return NhaCungCapList.get(i).getSoFax();
            case NGUOILIENHE_COL:
                return NhaCungCapList.get(i).getNguoiLienHe();
            case STK_COL:
                return NhaCungCapList.get(i).getSoTaiKhoan();
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
}
