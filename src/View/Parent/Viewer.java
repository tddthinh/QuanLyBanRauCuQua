package View.Parent;

import Service.HoaDonService;
import Service.NhanVienService;
import Service.SanPhamService;
import View.GiaoDienChinhView;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

public class Viewer extends javax.swing.JFrame {

    protected HoaDonService HoaDonService;
    protected NhanVienService NhanVienService;
    protected SanPhamService SanPhamService;

    public Viewer() {
        applyWindowTheme();
        initServices();
    }

    private void initServices() {
        HoaDonService = HoaDonService.getInstance();
        NhanVienService = NhanVienService.getInstance();
        SanPhamService = SanPhamService.getInstance();
    }

    private void applyWindowTheme() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GiaoDienChinhView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        protected void JTextField_SetPlaceholder(JTextField textFieldComponent, String outfocus) {
        textFieldComponent.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                if (textFieldComponent.getText().equals(outfocus)) {
                    textFieldComponent.setText("");
                }
                super.focusGained(fe); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (textFieldComponent.getText().equals("")) {
                    textFieldComponent.setText(outfocus);
                }
                super.focusLost(fe); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

}
