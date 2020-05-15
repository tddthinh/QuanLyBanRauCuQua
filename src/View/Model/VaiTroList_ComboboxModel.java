package View.Model;

import Model.VaiTro;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
public class VaiTroList_ComboboxModel extends AbstractListModel implements ComboBoxModel{
    private final ArrayList<VaiTro> VaiTroList;
    private VaiTro selection;
    public VaiTroList_ComboboxModel(ArrayList<VaiTro> VaiTroList) {
        this.VaiTroList = VaiTroList;
        selection = this.VaiTroList.get(0);
    }
    
    @Override
    public int getSize() {
        return VaiTroList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return VaiTroList.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (VaiTro) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
    
}
