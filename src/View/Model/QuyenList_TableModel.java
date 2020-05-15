package View.Model;

import Model.Quyen;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
public class QuyenList_TableModel extends AbstractTableModel{
    private final ArrayList<Quyen> QuyenList;
    protected String[] columnNames = {"Mã quyền", "Diễn giải"};
    private final int MAQUYEN_COL = 0;
    private final int DIENGIAI_COL = 1;

    public QuyenList_TableModel(ArrayList<Quyen> QuyenList) {
        this.QuyenList = QuyenList;
    }
    public void setColumnNames(String[] columnNames){
        this.columnNames = columnNames;
    }
    @Override
    public int getRowCount() {
        return QuyenList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch (i1) {
            case MAQUYEN_COL:
                return QuyenList.get(i).getMa();
            case DIENGIAI_COL:
                return QuyenList.get(i).getDienGiai();
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
