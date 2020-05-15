package View.Model;

import Model.SanPham;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
public class SanPhamList_TableModel extends AbstractTableModel {

    private final ArrayList<SanPham> SanPhamList;
    protected String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Đơn giá"};
    private final int MASANPHAM_COL = 0;
    private final int TENSANPHAM_COL = 1;
    private final int DONVITINH_COL = 2;
    private final int DONGIA_COL = 3;

    public SanPhamList_TableModel(ArrayList<SanPham> SanPhamList) {
        this.SanPhamList = SanPhamList;
    }
    public void setColumnNames(String[] columnNames){
        this.columnNames = columnNames;
    }
    @Override
    public int getRowCount() {
        return SanPhamList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch (i1) {
            case MASANPHAM_COL:
                String ma = SanPhamList.get(i).getLoaiSanPham().getMa()+SanPhamList.get(i).getMa();
                return ma;
            case TENSANPHAM_COL:
                return SanPhamList.get(i).getTen();
            case DONVITINH_COL:
                return SanPhamList.get(i).getLoaiSanPham().getDonViTinh();
            case DONGIA_COL:
                return SanPhamList.get(i).getDonGiaBan();
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
    public SanPham getRow(int i){
        return SanPhamList.get(i);
    }
}
