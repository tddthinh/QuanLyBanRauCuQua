package View.Model;

import Model.NhanVien;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
public class NhanVienList_TableModel extends AbstractTableModel{

    protected final ArrayList<NhanVien> NhanVienList;
    protected String[] columnNames = {
        "Tên Nhân Viên", "Tên tài khoản", "Mật Khẩu", "Vai Trò"
    };
    private final int TENNHANVIEN_COL = 0;
    private final int TAIKHOAN_COL = 1;
    private final int MATKHAU_COL = 2;
    private final int VAITRO_COL = 3;

    public NhanVienList_TableModel(ArrayList<NhanVien> NhanVienList) {
        this.NhanVienList = NhanVienList;
    }
    public void setColumnNames(String[] columnNames){
        this.columnNames = columnNames;
    }
    @Override
    public int getRowCount() {
        return NhanVienList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch (i1) {
            case TENNHANVIEN_COL:
                return NhanVienList.get(i).getTen();
            case TAIKHOAN_COL:
                return NhanVienList.get(i).getTenTaiKhoan();
            case MATKHAU_COL:
                return NhanVienList.get(i).getMatkhau();
            case VAITRO_COL:
                return NhanVienList.get(i).getVaiTro().getTen();
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
