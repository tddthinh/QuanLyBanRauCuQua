package View;

import Constant.MaQuyen;
import Model.ChiTietHoaDon;
import View.Add_Edit_Remove.TaoSanPhamView;
import View.Add_Edit_Remove.SuaNhanVienView;
import View.Add_Edit_Remove.ThongTinNhanVienView;
import View.Add_Edit_Remove.TaoVaiTroView;
import View.Add_Edit_Remove.TaoNhaCungCapView;
import View.Add_Edit_Remove.TaoTaiKhoanView;
import View.Parent.Viewer;
import View.Model.SanPhamListHoaDon_TableModel;
import View.Model.HoaDonList_TableModel;
import Model.HoaDon;
import Model.NhanVien;
import Model.Quyen;
import Model.SanPham;
import View.Model.ChiTietHoaDonList_TableModel;
import View.Model.SanPhamListSanPhamNhap_TableModel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class GiaoDienChinhView extends Viewer {

    private final DangNhapView DangNhapView;
    private final GiaoDienChinhView thisView = this;

    public GiaoDienChinhView(DangNhapView parentView) {
        super();
        this.DangNhapView = parentView;

        initComponents();
        initSubView();
        initModel();
        initListenner();
        initShortcutKeyForButton();
    }

    private void initSubView() {
        QuanLyView = new QuanLyView(this);
        SuaNhanVienView = new SuaNhanVienView(this);
        TaoNhaCungCapView = new TaoNhaCungCapView(this);
        TaoSanPhamView = new TaoSanPhamView(this);
        TaoTaiKhoanView = new TaoTaiKhoanView(this);
        TaoVaiTroView = new TaoVaiTroView(this);
        ThongTinNhanVienView = new ThongTinNhanVienView(this);
    }

    public void removeAllTab() {
        jTabbedPane1.removeAll();
    }

    private void initListenner() {
        startAutoUpdateNgayNhapTextField();
        JTextField_SetPlaceholder(jTextField_timkiem_bh, "Tìm kiếm.. [ctrl + f]");
        JTextField_SetPlaceholder(jTextField_timkiem_spn, "Tìm kiếm.. [ctrl + f]");
        JTextField_SetPlaceholder(jTextField_timkiem_hd, "Tìm kiếm.. [ctrl + f]");
        addListener_TableSanPhamList_BanHang();
        addListener_TimKiemTextField();
    }

    private void initShortcutKeyForButton() {
        KeyStroke ctrl_space = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.CTRL_MASK);
        addShortcutKey(jButton_xuathd_bh, ctrl_space, "submit", shortcutAction);
        addShortcutKey(jButton_ghinhan_spn, ctrl_space, "submit", shortcutAction);
        KeyStroke f5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
        addShortcutKey(jButton_lammoisp_hd, f5, "refresh", shortcutAction);
        addShortcutKey(jButton_lammoisp_spn, f5, "refresh", shortcutAction);
        KeyStroke ctrl_f = KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK);
        addShortcutKey(jTextField_timkiem_bh, ctrl_f, "finding", shortcutAction);
        addShortcutKey(jTextField_timkiem_spn, ctrl_f, "finding", shortcutAction);
        addShortcutKey(jTextField_timkiem_hd, ctrl_f, "finding", shortcutAction);

    }

    private void addListener_TimKiemTextField() {
        jTextField_timkiem_bh.addKeyListener(TimKiemTextField_KeyAdapter);
        jTextField_timkiem_spn.addKeyListener(TimKiemTextField_KeyAdapter);
    }
    private final KeyAdapter TimKiemTextField_KeyAdapter = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            JTextField obj = (JTextField) e.getSource();
            String txt = obj.getText();
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (isEntered_TimKiem) {
                    return;
                }
                if (obj == jTextField_timkiem_bh) {
                    chooseSanPham_BanHang();
                } else if (obj == jTextField_timkiem_spn) {
                    chooseSanPham_NhapHang();
                }
                isEntered_TimKiem = true;
                obj.setText("");
            } else {
                isEntered_TimKiem = false;
                filterTableRow(txt, jTable_dssp_spn);
                if (obj == jTextField_timkiem_bh) {
                    filterTableRow(txt, jTable_dssp_bh);
                } else if (obj == jTextField_timkiem_spn) {
                    filterTableRow(txt, jTable_dssp_spn);
                }
            }
        }
    };

    private void chooseSanPham_BanHang() {
        jTable_dssp_bh.setRowSelectionInterval(0, 0);
        updateChiTietHoaDonTable_BanHang();
        filterTableRow("", jTable_dssp_bh);
    }

    private void chooseSanPham_NhapHang() {
        jTable_dssp_spn.setRowSelectionInterval(0, 0);
        updateChiTietHoaDonTable_SanPhamNhap();
        filterTableRow("", jTable_dssp_spn);
    }

    private void filterTableRow(String txt, JTable table) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        if (txt.equals("")) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txt));
        }
        table.setRowSorter(sorter);
    }

    private void addListener_TableSanPhamList_BanHang() {
        jTable_dssp_bh.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    updateChiTietHoaDonTable_BanHang();
                }
            }
        });
        jTable_dssp_spn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    updateChiTietHoaDonTable_SanPhamNhap();
                }
            }
        });

    }

    private void updateChiTietHoaDonTable_BanHang() {
        try {
            SanPham sanpham = getSelectedSanPham_BanHang();
            ChiTietHoaDonList_TableModel model = (ChiTietHoaDonList_TableModel) jTable_chitiethoadon.getModel();
            ChiTietHoaDon CTHD = new ChiTietHoaDon();
            CTHD.setSanPham(sanpham);
            float soluong = showInputGetFloat("Nhập số lượng cho [" + sanpham.getTen() + "]");
            CTHD.setSoLuong(soluong);
            float tongtien = soluong * sanpham.getDonGiaBan();
            CTHD.setTongTien(tongtien);
            if (!model.editRowByChiTietHoaDon(CTHD)) {
                model.addRow(CTHD);
            }
            updateTien();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    private void updateChiTietHoaDonTable_SanPhamNhap() {
        try {
            SanPham sanpham = getSelectedSanPham_SanPhamNhap();
            ChiTietHoaDonList_TableModel model = (ChiTietHoaDonList_TableModel) jTable_chitietsp_spn.getModel();
            ChiTietHoaDon CTHD = new ChiTietHoaDon();
            CTHD.setSanPham(sanpham);
            float dongia = showInputGetFloat("Nhập đơn giá cho [" + sanpham.getTen() + "]");
            sanpham.setDonGiaBan(dongia);
            float soluong = showInputGetFloat("Nhập số lượng cho [" + sanpham.getTen() + "]");
            CTHD.setSoLuong(soluong);
            float tongtien = soluong * dongia;
            CTHD.setTongTien(tongtien);
            if (!model.editRowByChiTietHoaDon(CTHD)) {
                model.addRow(CTHD);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    private void resetTien(){
        jFormattedTextField_thanhtien_bh.setValue(new Float(0));
        jFormattedTextField_tongtien_bh.setValue(new Float(0));
        jTextField_tiennhan_bh.setText("");
        jFormattedTextField_tientra_bh.setValue(new Float(0));
        jSpinner_vat.setValue(new Float(10));
        jSpinner_chietkhau_bh.setValue(new Float(0));
        
    }
    private void updateTien() {
        ChiTietHoaDonList_TableModel model = (ChiTietHoaDonList_TableModel) jTable_chitiethoadon.getModel();
        float thanhtien = model.getTongTienHoaDon();
        Float VAT = (Float) jSpinner_vat.getValue();
        Float CK = (Float) jSpinner_chietkhau_bh.getValue();
        float tongtien = thanhtien + (thanhtien * VAT / 100) - (thanhtien * CK / 100);
        jFormattedTextField_thanhtien_bh.setValue(thanhtien);
        jFormattedTextField_tongtien_bh.setValue(tongtien);
        updateTienTra();
    }

    private void updateTienTra() {
        try{
            Float tiennhan = Float.parseFloat(jTextField_tiennhan_bh.getText());
            Float tongtien = (Float) jFormattedTextField_tongtien_bh.getValue();
            jFormattedTextField_tientra_bh.setValue(tiennhan - tongtien);
        }catch(NumberFormatException ex){
            System.err.println(ex);
        }
    }

    private SanPham getSelectedSanPham_BanHang() {
        int i_row = jTable_dssp_bh.getSelectedRow();
        i_row = jTable_dssp_bh.convertRowIndexToModel(i_row);
        SanPhamListHoaDon_TableModel model = (SanPhamListHoaDon_TableModel) jTable_dssp_bh.getModel();
        return model.getRow(i_row);
    }

    private SanPham getSelectedSanPham_SanPhamNhap() {
        int i_row = jTable_dssp_spn.getSelectedRow();
        i_row = jTable_dssp_spn.convertRowIndexToModel(i_row);
        SanPhamListSanPhamNhap_TableModel model = (SanPhamListSanPhamNhap_TableModel) jTable_dssp_spn.getModel();
        return model.getRow(i_row);
    }

    private float showInputGetFloat(String txt) throws Exception {
        float soluong = Float.parseFloat(
                JOptionPane.showInputDialog(
                        this,
                        txt,
                        "",
                        JOptionPane.INFORMATION_MESSAGE)
        );
        return soluong;
    }

    private void nextHoaDon() {
        ArrayList<ChiTietHoaDon> CTHDList = new ArrayList<>();
        ChiTietHoaDonList_TableModel model = new ChiTietHoaDonList_TableModel(CTHDList);
        jTable_chitiethoadon.setModel(model);
        resetTien();
    }

    private void nextSanPhamNhap() {
        ArrayList<ChiTietHoaDon> CTHDList = new ArrayList<>();
        ChiTietHoaDonList_TableModel model = new ChiTietHoaDonList_TableModel(CTHDList);
        jTable_chitietsp_spn.setModel(model);
    }

    private void addShortcutKey(JComponent button, KeyStroke key, String actionKey, Action callback) {
        InputMap inputMap = button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(key, actionKey);
        button.getActionMap().put(actionKey, callback);
    }

    private void initModel() {

        try {
            ArrayList<SanPham> sanphamList = SanPhamService.getAllSanPham();
            updateSanPhamList_BanHang(sanphamList);
            updateSanPhamList_SanPhamNhap(sanphamList);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GiaoDienChinhView.class.getName()).log(Level.SEVERE, null, ex);
        }

        nextHoaDon();
        nextSanPhamNhap();
    }

    public void updateSanPhamList_BanHang(ArrayList<SanPham> sanphamList) {
        SanPhamListHoaDon_TableModel modelSanPhamListHoaDon = new SanPhamListHoaDon_TableModel(sanphamList);
        jTable_dssp_bh.setModel(modelSanPhamListHoaDon);
    }

    public void updateSanPhamList_SanPhamNhap(ArrayList<SanPham> sanphamList) {
        SanPhamListSanPhamNhap_TableModel modelSanPhamListSanPhamNhap = new SanPhamListSanPhamNhap_TableModel(sanphamList);
        jTable_dssp_spn.setModel(modelSanPhamListSanPhamNhap);
    }

    public void updateHoaDonList(ArrayList<HoaDon> hoadonList) {
        HoaDonList_TableModel modelHoaDonList = new HoaDonList_TableModel(hoadonList);
        jTable_hoadon.setModel(modelHoaDonList);
    }

    public void updateNhanVien(NhanVien nhanvien) {
        String TenNhanVien = nhanvien.getTen();
        jTextField_ttnhanvien_bh.setText(TenNhanVien);
        jTextField_ttnhanvien_spn.setText(TenNhanVien);
        if (!searchQuyen(nhanvien, MaQuyen.QUANLY)) //xóa button [Quản lý viên] nếu nhân viên không có quyền [Quản lý].
        {
            jButton_quanly.setVisible(false);
        }
        removeAllTab();
        ArrayList<Quyen> quyenList = nhanvien.getVaiTro().getQuyen();
        for (Quyen quyen : quyenList) {
            JPanel panel = (JPanel) getPanelInTabbedPanel_ByQuyen(quyen);
            if (panel != null) {
                jTabbedPane1.addTab(quyen.getDienGiai(), panel);
            }
        }
        currentNhanVien = nhanvien;

    }

    private Component getPanelInTabbedPanel_ByQuyen(Quyen quyen) {
        switch (quyen.getMa()) {
            case MaQuyen.QUANLY_NHAPXUAT_HOADONBANHANG:
                return jPanel_bh;
            case MaQuyen.QUANLY_HOADON:
                return jPanel_hd;
            case MaQuyen.QUANLY_NHAPXUAT_HOADONNHAPHANG:
                return jPanel_spn;
            default:
                return null;
        }
    }

    private boolean searchQuyen(NhanVien nhanvien, String ma_quyen) {
        for (Quyen quyen : nhanvien.getVaiTro().getQuyen()) {
            if (quyen.getMa().equals(ma_quyen)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        jButton_quanly = new javax.swing.JButton();
        jButton_thongtincanhan = new javax.swing.JButton();
        jButton_dangxuat = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel_bh = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_dssp_bh = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jTextField_timkiem_bh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton_lammoisp_hd = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jFormattedTextField_thanhtien_bh = new javax.swing.JFormattedTextField();
        jFormattedTextField_tongtien_bh = new javax.swing.JFormattedTextField();
        jFormattedTextField_tientra_bh = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jSpinner_vat = new javax.swing.JSpinner();
        jSpinner_chietkhau_bh = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_mahoadon_bh = new javax.swing.JTextField();
        jTextField_ngaylap_bh = new javax.swing.JTextField();
        jTextField_ttnhanvien_bh = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_chitiethoadon = new javax.swing.JTable();
        jButton_xoahd_bh = new javax.swing.JButton();
        jButton_xuathd_bh = new javax.swing.JButton();
        jButton_xoatatcahd_bh = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField_tiennhan_bh = new javax.swing.JTextField();
        jPanel_spn = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_dssp_spn = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jTextField_timkiem_spn = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton_lammoisp_spn = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField_ngaynhap_spn = new javax.swing.JTextField();
        jTextField_ttnhanvien_spn = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable_chitietsp_spn = new javax.swing.JTable();
        jButton_xoasp_spn = new javax.swing.JButton();
        jButton_xoatatca_spn = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jButton_ghinhan_spn = new javax.swing.JButton();
        jPanel_hd = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_hoadon = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jTextField_timkiem_hd = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jFormattedTextField_ngaybd_hd = new javax.swing.JFormattedTextField();
        jFormattedTextField_ngaykt_hd = new javax.swing.JFormattedTextField();
        jButton_xem_hd = new javax.swing.JButton();
        jButton_in_hd = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý bán rau củ quả");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel17.setLayout(new java.awt.BorderLayout());

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        jButton_quanly.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_quanly.setForeground(new java.awt.Color(0, 102, 0));
        jButton_quanly.setText("Quản lý viên");
        jButton_quanly.setFocusable(false);
        jButton_quanly.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_quanly.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_quanly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_quanlyActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton_quanly);

        jButton_thongtincanhan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_thongtincanhan.setForeground(new java.awt.Color(0, 102, 0));
        jButton_thongtincanhan.setText("Thông tin cá nhân");
        jButton_thongtincanhan.setFocusable(false);
        jButton_thongtincanhan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_thongtincanhan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_thongtincanhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_thongtincanhanActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton_thongtincanhan);

        jButton_dangxuat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_dangxuat.setText("Đăng xuất");
        jButton_dangxuat.setFocusable(false);
        jButton_dangxuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_dangxuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_dangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_dangxuatActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton_dangxuat);

        jPanel17.add(jToolBar2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel17, java.awt.BorderLayout.PAGE_START);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel_bh.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jPanel5.setLayout(new java.awt.BorderLayout());

        jTable_dssp_bh.setAutoCreateRowSorter(true);
        jTable_dssp_bh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable_dssp_bh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_dssp_bh.setRowHeight(32);
        jTable_dssp_bh.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable_dssp_bh);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTextField_timkiem_bh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_timkiem_bh.setText("Tìm kiếm.. [ctrl + f]");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("Danh sách sản phẩm");
        jLabel4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jButton_lammoisp_hd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_lammoisp_hd.setForeground(new java.awt.Color(0, 153, 51));
        jButton_lammoisp_hd.setText("Làm mới [F5]");
        jButton_lammoisp_hd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_lammoisp_hdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton_lammoisp_hd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField_timkiem_bh, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_timkiem_bh, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_lammoisp_hd)))
        );

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jLabel6.setText("     ");
        jPanel5.add(jLabel6, java.awt.BorderLayout.LINE_START);

        jLabel7.setText("   ");
        jPanel5.add(jLabel7, java.awt.BorderLayout.LINE_END);

        jPanel3.add(jPanel5);

        jFormattedTextField_thanhtien_bh.setEditable(false);
        jFormattedTextField_thanhtien_bh.setForeground(new java.awt.Color(255, 0, 0));
        jFormattedTextField_thanhtien_bh.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###"))));
        jFormattedTextField_thanhtien_bh.setText("0");
        jFormattedTextField_thanhtien_bh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jFormattedTextField_tongtien_bh.setEditable(false);
        jFormattedTextField_tongtien_bh.setForeground(new java.awt.Color(255, 0, 0));
        jFormattedTextField_tongtien_bh.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###"))));
        jFormattedTextField_tongtien_bh.setText("0");
        jFormattedTextField_tongtien_bh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jFormattedTextField_tientra_bh.setEditable(false);
        jFormattedTextField_tientra_bh.setForeground(new java.awt.Color(255, 0, 0));
        jFormattedTextField_tientra_bh.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###"))));
        jFormattedTextField_tientra_bh.setText("0");
        jFormattedTextField_tientra_bh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("VAT %");

        jSpinner_vat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jSpinner_vat.setModel(new javax.swing.SpinnerNumberModel(10.0f, null, null, 1.0f));
        jSpinner_vat.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner_vatStateChanged(evt);
            }
        });

        jSpinner_chietkhau_bh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jSpinner_chietkhau_bh.setModel(new javax.swing.SpinnerNumberModel(0.0f, null, null, 0.5f));
        jSpinner_chietkhau_bh.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner_chietkhau_bhStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Chiết khấu %");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Mã hóa đơn");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Ngày lập");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Nhân viên");

        jTextField_mahoadon_bh.setEditable(false);
        jTextField_mahoadon_bh.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_mahoadon_bh.setText("MSHD");

        jTextField_ngaylap_bh.setEditable(false);
        jTextField_ngaylap_bh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_ngaylap_bh.setText("dd/mm/yyyy hh:mm");

        jTextField_ttnhanvien_bh.setEditable(false);
        jTextField_ttnhanvien_bh.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_ttnhanvien_bh.setForeground(new java.awt.Color(0, 102, 0));
        jTextField_ttnhanvien_bh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_ttnhanvien_bh.setText("MANV-TENNV");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Thành tiền");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Tổng tiền");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Tiền nhận");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Tiền trả");

        jTable_chitiethoadon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable_chitiethoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Đơn vị tính", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_chitiethoadon.setRowHeight(32);
        jScrollPane3.setViewportView(jTable_chitiethoadon);

        jButton_xoahd_bh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_xoahd_bh.setForeground(new java.awt.Color(255, 0, 0));
        jButton_xoahd_bh.setText("Xóa");
        jButton_xoahd_bh.setPreferredSize(new java.awt.Dimension(73, 23));
        jButton_xoahd_bh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_xoahd_bhActionPerformed(evt);
            }
        });

        jButton_xuathd_bh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_xuathd_bh.setForeground(new java.awt.Color(0, 153, 0));
        jButton_xuathd_bh.setText("Xuất hóa đơn [Ctrl+Space]");
        jButton_xuathd_bh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_xuathd_bhActionPerformed(evt);
            }
        });

        jButton_xoatatcahd_bh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_xoatatcahd_bh.setForeground(new java.awt.Color(255, 0, 0));
        jButton_xoatatcahd_bh.setText("Xóa tất cả");
        jButton_xoatatcahd_bh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_xoatatcahd_bhActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText("Hóa đơn bán hàng");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField_tiennhan_bh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField_tiennhan_bh.setForeground(new java.awt.Color(0, 153, 51));
        jTextField_tiennhan_bh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_tiennhan_bhKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jTextField_mahoadon_bh, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_ngaylap_bh))
                            .addComponent(jTextField_ttnhanvien_bh)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton_xuathd_bh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_xoahd_bh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_xoatatcahd_bh))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextField_tientra_bh, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addComponent(jTextField_tiennhan_bh)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jFormattedTextField_thanhtien_bh, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField_tongtien_bh))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner_chietkhau_bh, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner_vat, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jSpinner_chietkhau_bh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jSpinner_vat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jTextField_mahoadon_bh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_ngaylap_bh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField_ttnhanvien_bh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField_thanhtien_bh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField_tongtien_bh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jTextField_tiennhan_bh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField_tientra_bh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_xuathd_bh, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_xoahd_bh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_xoatatcahd_bh)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4);

        jPanel_bh.add(jPanel3, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Bán hàng", jPanel_bh);

        jPanel_spn.setLayout(new java.awt.BorderLayout());

        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new java.awt.GridLayout(1, 0));

        jPanel13.setLayout(new java.awt.BorderLayout());

        jTable_dssp_spn.setAutoCreateRowSorter(true);
        jTable_dssp_spn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable_dssp_spn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_dssp_spn.setRowHeight(32);
        jTable_dssp_spn.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(jTable_dssp_spn);

        jPanel13.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jTextField_timkiem_spn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_timkiem_spn.setText("Tìm kiếm.. [ctrl + f]");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 102));
        jLabel15.setText("Danh sách sản phẩm");
        jLabel15.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jButton_lammoisp_spn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_lammoisp_spn.setForeground(new java.awt.Color(0, 153, 51));
        jButton_lammoisp_spn.setText("Làm mới [F5]");
        jButton_lammoisp_spn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_lammoisp_spnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton_lammoisp_spn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField_timkiem_spn, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_timkiem_spn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_lammoisp_spn)))
        );

        jPanel13.add(jPanel14, java.awt.BorderLayout.PAGE_START);

        jLabel16.setText("     ");
        jPanel13.add(jLabel16, java.awt.BorderLayout.LINE_START);

        jLabel19.setText("   ");
        jPanel13.add(jLabel19, java.awt.BorderLayout.LINE_END);

        jPanel12.add(jPanel13);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("Ngày nhập");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("Nhân viên");

        jTextField_ngaynhap_spn.setEditable(false);
        jTextField_ngaynhap_spn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_ngaynhap_spn.setText("dd/mm/yyyy hh:mm");

        jTextField_ttnhanvien_spn.setEditable(false);
        jTextField_ttnhanvien_spn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_ttnhanvien_spn.setForeground(new java.awt.Color(0, 102, 0));
        jTextField_ttnhanvien_spn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_ttnhanvien_spn.setText("MANV-TENNV");

        jTable_chitietsp_spn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable_chitietsp_spn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Đơn vị tính", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_chitietsp_spn.setRowHeight(32);
        jScrollPane5.setViewportView(jTable_chitietsp_spn);

        jButton_xoasp_spn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_xoasp_spn.setForeground(new java.awt.Color(255, 0, 0));
        jButton_xoasp_spn.setText("Xóa");
        jButton_xoasp_spn.setPreferredSize(new java.awt.Dimension(73, 23));
        jButton_xoasp_spn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_xoasp_spnActionPerformed(evt);
            }
        });

        jButton_xoatatca_spn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_xoatatca_spn.setForeground(new java.awt.Color(255, 0, 0));
        jButton_xoatatca_spn.setText("Xóa tất cả");
        jButton_xoatatca_spn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_xoatatca_spnActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 102));
        jLabel29.setText("Sản phẩm nhập");
        jLabel29.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jButton_ghinhan_spn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_ghinhan_spn.setForeground(new java.awt.Color(0, 153, 51));
        jButton_ghinhan_spn.setText("Lưu [Ctrl+Space]");
        jButton_ghinhan_spn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ghinhan_spnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jButton_ghinhan_spn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_xoasp_spn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_xoatatca_spn))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_ttnhanvien_spn)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jTextField_ngaynhap_spn, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField_ngaynhap_spn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField_ttnhanvien_spn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_xoasp_spn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_xoatatca_spn)
                    .addComponent(jButton_ghinhan_spn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
        );

        jPanel12.add(jPanel15);

        jPanel11.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel_spn.add(jPanel11, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Sản phẩm nhập", jPanel_spn);

        jPanel_hd.setLayout(new java.awt.BorderLayout());

        jTable_hoadon.setAutoCreateRowSorter(true);
        jTable_hoadon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable_hoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Ngày lập", "Nhân viên lập", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_hoadon.setRowHeight(32);
        jTable_hoadon.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable_hoadon);

        jPanel_hd.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jTextField_timkiem_hd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_timkiem_hd.setText("Tìm kiếm.. [ctrl + f]");

        jLabel10.setText("đến");

        jLabel12.setText("Từ ngày");

        jFormattedTextField_ngaybd_hd.setForeground(new java.awt.Color(0, 102, 0));
        try {
            jFormattedTextField_ngaybd_hd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_ngaybd_hd.setText("2017-10-18");

        jFormattedTextField_ngaykt_hd.setForeground(new java.awt.Color(0, 102, 0));
        try {
            jFormattedTextField_ngaykt_hd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_ngaykt_hd.setText("2017-10-20");

        jButton_xem_hd.setText("Xem");
        jButton_xem_hd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_xem_hdActionPerformed(evt);
            }
        });

        jButton_in_hd.setText("In");
        jButton_in_hd.setEnabled(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField_ngaybd_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField_ngaykt_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_xem_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_in_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, Short.MAX_VALUE)
                .addComponent(jTextField_timkiem_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel12)
                        .addComponent(jFormattedTextField_ngaybd_hd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextField_ngaykt_hd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_xem_hd)
                        .addComponent(jButton_in_hd))
                    .addComponent(jTextField_timkiem_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel_hd.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jLabel11.setText("   ");
        jPanel_hd.add(jLabel11, java.awt.BorderLayout.LINE_END);

        jPanel9.setLayout(new java.awt.BorderLayout());
        jPanel_hd.add(jPanel9, java.awt.BorderLayout.LINE_START);

        jTabbedPane1.addTab("Hóa đơn", jPanel_hd);

        jPanel1.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_quanlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_quanlyActionPerformed
        showQuanTri();
    }//GEN-LAST:event_jButton_quanlyActionPerformed

    private void jButton_thongtincanhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_thongtincanhanActionPerformed
        showNhanVienProfile();
    }//GEN-LAST:event_jButton_thongtincanhanActionPerformed

    private void jButton_dangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_dangxuatActionPerformed
        DangNhapView.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton_dangxuatActionPerformed

    private void jButton_lammoisp_hdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_lammoisp_hdActionPerformed
        try {
            ArrayList<SanPham> sanphamList = SanPhamService.getAllSanPham();
            updateSanPhamList_BanHang(sanphamList);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GiaoDienChinhView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_lammoisp_hdActionPerformed

    private void jButton_lammoisp_spnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_lammoisp_spnActionPerformed
        try {
            ArrayList<SanPham> sanphamList = SanPhamService.getAllSanPham();
            updateSanPhamList_SanPhamNhap(sanphamList);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GiaoDienChinhView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_lammoisp_spnActionPerformed

    private void jButton_xem_hdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_xem_hdActionPerformed
        String txtNgayBatDau = jFormattedTextField_ngaybd_hd.getText();
        String txtNgayKetThuc = jFormattedTextField_ngaykt_hd.getText();
        Date NgayBatDau = Date.valueOf(txtNgayBatDau);
        Date NgayKetThuc = Date.valueOf(txtNgayKetThuc);
        try {
            ArrayList<HoaDon> hoadonList = HoaDonService.getHoaDonByDate(NgayBatDau, NgayKetThuc);
            updateHoaDonList(hoadonList);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GiaoDienChinhView.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton_xem_hdActionPerformed

    private void jButton_xuathd_bhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_xuathd_bhActionPerformed
        ChiTietHoaDonList_TableModel model = (ChiTietHoaDonList_TableModel) jTable_chitiethoadon.getModel();
        ArrayList<ChiTietHoaDon> CTHDList = model.getAllRow();
        HoaDon hoadon = new HoaDon(currentNhanVien);
        hoadon.setChiTietHoaDon(CTHDList);
        try {
            HoaDonService.addHoaDon(hoadon);
            nextHoaDon();
            
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
        
        
    }//GEN-LAST:event_jButton_xuathd_bhActionPerformed

    private void jButton_ghinhan_spnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ghinhan_spnActionPerformed
        System.out.println("Ctrl + Enter 2");
    }//GEN-LAST:event_jButton_ghinhan_spnActionPerformed

    private void jButton_xoahd_bhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_xoahd_bhActionPerformed
        try {
            int i_row = jTable_chitiethoadon.getSelectedRow();
            ChiTietHoaDonList_TableModel model = (ChiTietHoaDonList_TableModel) jTable_chitiethoadon.getModel();
            model.removeRow(i_row);
            updateTien();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton_xoahd_bhActionPerformed

    private void jButton_xoatatcahd_bhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_xoatatcahd_bhActionPerformed
        ChiTietHoaDonList_TableModel model = (ChiTietHoaDonList_TableModel) jTable_chitiethoadon.getModel();
        model.removeAllRow();
        updateTien();
    }//GEN-LAST:event_jButton_xoatatcahd_bhActionPerformed

    private void jSpinner_chietkhau_bhStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner_chietkhau_bhStateChanged
        updateTien();
    }//GEN-LAST:event_jSpinner_chietkhau_bhStateChanged

    private void jSpinner_vatStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner_vatStateChanged
        updateTien();
    }//GEN-LAST:event_jSpinner_vatStateChanged

    private void jTextField_tiennhan_bhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_tiennhan_bhKeyReleased
        updateTienTra();
    }//GEN-LAST:event_jTextField_tiennhan_bhKeyReleased

    private void jButton_xoasp_spnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_xoasp_spnActionPerformed
        try {
            int i_row = jTable_chitietsp_spn.getSelectedRow();
            ChiTietHoaDonList_TableModel model = (ChiTietHoaDonList_TableModel) jTable_chitietsp_spn.getModel();
            model.removeRow(i_row);
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton_xoasp_spnActionPerformed

    private void jButton_xoatatca_spnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_xoatatca_spnActionPerformed
        ChiTietHoaDonList_TableModel model = (ChiTietHoaDonList_TableModel) jTable_chitietsp_spn.getModel();
        model.removeAllRow();
    }//GEN-LAST:event_jButton_xoatatca_spnActionPerformed
    public void showNhanVienProfile() {
        ThongTinNhanVienView.updateNhanVien(currentNhanVien);
        ThongTinNhanVienView.setVisible(true);
        this.setEnabled(false);
    }

    public void showQuanTri() {

        QuanLyView.setVisible(true);
        QuanLyView.updateNhanVien(currentNhanVien);
        this.setEnabled(false);
    }

    private QuanLyView QuanLyView;
    private SuaNhanVienView SuaNhanVienView;
    private TaoNhaCungCapView TaoNhaCungCapView;
    private TaoSanPhamView TaoSanPhamView;
    private TaoTaiKhoanView TaoTaiKhoanView;
    private TaoVaiTroView TaoVaiTroView;
    private ThongTinNhanVienView ThongTinNhanVienView;
    private NhanVien currentNhanVien;
    private final Action shortcutAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object currentObject = e.getSource();
            if (currentObject == jButton_xuathd_bh) {
                jButton_xuathd_bhActionPerformed(e);
            } else if (currentObject == jButton_lammoisp_hd) {
                jButton_lammoisp_hdActionPerformed(e);
            } else if (currentObject == jButton_lammoisp_spn) {
                jButton_lammoisp_spnActionPerformed(e);
            } else if (currentObject == jButton_ghinhan_spn) {
                jButton_ghinhan_spnActionPerformed(e);
            } else if (currentObject == jTextField_timkiem_bh) {
                jTextField_timkiem_bh.requestFocus();
            } else if (currentObject == jTextField_timkiem_spn) {
                jTextField_timkiem_spn.requestFocus();
            } else if (currentObject == jTextField_timkiem_hd) {
                jTextField_timkiem_hd.requestFocus();
            }
        }
    };
    private final Action shortcutTable = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object currentObject = e.getSource();
            System.out.println(currentObject);
            if (currentObject == jTable_dssp_bh) {
                addSanPhamToHoaDonBanHang();
            } else if (currentObject == jTable_dssp_spn) {

            }
        }
    };
    private boolean isEntered_TimKiem = false;

    private void addSanPhamToHoaDonBanHang() {
        System.out.println("enter");
    }

    public void startAutoUpdateNgayNhapTextField() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long time_now = System.currentTimeMillis();
                    String nowDate = new Date(time_now).toString();
                    String nowTime = new Time(time_now).toString();
                    jTextField_ngaylap_bh.setText(nowDate + " " + nowTime);
                    jTextField_ngaynhap_spn.setText(nowDate + " " + nowTime);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GiaoDienChinhView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_dangxuat;
    private javax.swing.JButton jButton_ghinhan_spn;
    private javax.swing.JButton jButton_in_hd;
    private javax.swing.JButton jButton_lammoisp_hd;
    private javax.swing.JButton jButton_lammoisp_spn;
    private javax.swing.JButton jButton_quanly;
    private javax.swing.JButton jButton_thongtincanhan;
    private javax.swing.JButton jButton_xem_hd;
    private javax.swing.JButton jButton_xoahd_bh;
    private javax.swing.JButton jButton_xoasp_spn;
    private javax.swing.JButton jButton_xoatatca_spn;
    private javax.swing.JButton jButton_xoatatcahd_bh;
    private javax.swing.JButton jButton_xuathd_bh;
    private javax.swing.JFormattedTextField jFormattedTextField_ngaybd_hd;
    private javax.swing.JFormattedTextField jFormattedTextField_ngaykt_hd;
    private javax.swing.JFormattedTextField jFormattedTextField_thanhtien_bh;
    private javax.swing.JFormattedTextField jFormattedTextField_tientra_bh;
    private javax.swing.JFormattedTextField jFormattedTextField_tongtien_bh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_bh;
    private javax.swing.JPanel jPanel_hd;
    private javax.swing.JPanel jPanel_spn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpinner_chietkhau_bh;
    private javax.swing.JSpinner jSpinner_vat;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable_chitiethoadon;
    private javax.swing.JTable jTable_chitietsp_spn;
    private javax.swing.JTable jTable_dssp_bh;
    private javax.swing.JTable jTable_dssp_spn;
    private javax.swing.JTable jTable_hoadon;
    private javax.swing.JTextField jTextField_mahoadon_bh;
    private javax.swing.JTextField jTextField_ngaylap_bh;
    private javax.swing.JTextField jTextField_ngaynhap_spn;
    private javax.swing.JTextField jTextField_tiennhan_bh;
    private javax.swing.JTextField jTextField_timkiem_bh;
    private javax.swing.JTextField jTextField_timkiem_hd;
    private javax.swing.JTextField jTextField_timkiem_spn;
    private javax.swing.JTextField jTextField_ttnhanvien_bh;
    private javax.swing.JTextField jTextField_ttnhanvien_spn;
    private javax.swing.JToolBar jToolBar2;
    // End of variables declaration//GEN-END:variables
}
