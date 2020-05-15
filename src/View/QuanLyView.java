package View;

import View.Parent.SubViewer;
import Constant.MaQuyen;
import Model.NhanVien;
import Model.Quyen;
import Model.VaiTro;
import View.Model.NhaCungCapList_TableModel;
import View.Model.NhanVienList_TableModel;
import View.Model.QuyenList_TableModel;
import View.Model.SanPhamList_TableModel;
import View.Model.TaiKhoanList_TableModel;
import View.Model.VaiTroList_ComboboxModel;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class QuanLyView extends SubViewer {

    public QuanLyView(GiaoDienChinhView parentView) {
        super(parentView);
        initComponents();
        initListenner();
    }

    private void initListenner() {
        JTextField_SetPlaceholder(jTextField_timkiem_ncc, "Tìm kiếm..");
        JTextField_SetPlaceholder(jTextField_timkiem_qlnv, "Tìm kiếm..");
        JTextField_SetPlaceholder(jTextField_timkiem_qlsp, "Tìm kiếm..");
        jComboBox_qlvt.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                VaiTro vaitro = (VaiTro) jComboBox_qlvt.getSelectedItem();
                updateQuyen(vaitro.getQuyen());
            }
        });
    }
    
    public void updateNhanVien(NhanVien nhanvien) {
        currentNhanVien = nhanvien;
        removeAllTab();
        addSpecificTab();

    }

    public void removeAllTab() {
        jTabbedPane.removeAll();
    }

    private void addSpecificTab() {

        ArrayList<Quyen> quyenList = currentNhanVien.getVaiTro().getQuyen();
        for (Quyen quyen : quyenList) {
            JPanel panel = (JPanel) getPanelInTabbedPanel_ByQuyen(quyen);
            if (panel != null) {
                jTabbedPane.addTab(quyen.getDienGiai(), panel);
            }
        }
        jTabbedPane.addTab("Thống kê", jPanel_thongke);
    }

    private Component getPanelInTabbedPanel_ByQuyen(Quyen quyen) {
        switch (quyen.getMa()) {
            case MaQuyen.QUANLY_TAIKHOAN:
                updateTaiKhoan();
                return jPanel_qltk;
            case MaQuyen.QUANLY_VAITRO:
                updateVaiTro();
                return jPanel_qlvt;
            case MaQuyen.QUANLY_SANPHAM:
                updateSanPham();
                return jPanel_qlsp;
            case MaQuyen.QUANLY_NHACUNGCAP:
                updateNhaCungCap();
                return jPanel_qlncc;
            case MaQuyen.QUANLY_NHANVIEN:
                updateNhanVien();
                return jPanel_qlnv;
            default:
                return null;
        }
    }

    private void updateTaiKhoan() {
        try {
            TaiKhoanList_TableModel model = new TaiKhoanList_TableModel(NhanVienService.getAllNhanVien());
            jTable_qltk.setModel(model);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateVaiTro() {
        try {
            VaiTroList_ComboboxModel model = new VaiTroList_ComboboxModel(NhanVienService.getAllVaiTro());
            jComboBox_qlvt.setModel(model);
            VaiTro vaitro = (VaiTro) jComboBox_qlvt.getSelectedItem();
            updateQuyen(vaitro.getQuyen());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateSanPham() {
        try {
            SanPhamList_TableModel model = new SanPhamList_TableModel(SanPhamService.getAllSanPham());
            jTable_qlsp.setModel(model);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateNhaCungCap() {
        try {
            NhaCungCapList_TableModel model = new NhaCungCapList_TableModel(SanPhamService.getAllNhaCungCap());
            jTable_qlncc.setModel(model);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateNhanVien() {
        try {
            NhanVienList_TableModel model = new NhanVienList_TableModel(NhanVienService.getAllNhanVien());
            jTable_qlnv.setModel(model);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateQuyen(ArrayList<Quyen> quyenList) {
        QuyenList_TableModel model = new QuyenList_TableModel(quyenList);
        jTable_qlvt.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel_qltk = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jTextField_qltk = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable_qltk = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jButton_xoa_qltk = new javax.swing.JButton();
        jButton_sua_qltk = new javax.swing.JButton();
        jButton_them_qltk = new javax.swing.JButton();
        jPanel_qlvt = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jComboBox_qlvt = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable_qlvt = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jButton_xoa_qlvt = new javax.swing.JButton();
        jButton_sua_qlvt = new javax.swing.JButton();
        jButton_them_qlvt = new javax.swing.JButton();
        jPanel_qlnv = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_qlnv = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton_sua_qlnv = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTextField_timkiem_qlnv = new javax.swing.JTextField();
        jPanel_qlncc = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jButton_xoa_ncc = new javax.swing.JButton();
        jButton_sua_ncc = new javax.swing.JButton();
        jButton_them_ncc = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable_qlncc = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jTextField_timkiem_ncc = new javax.swing.JTextField();
        jPanel_qlsp = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_qlsp = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jTextField_timkiem_qlsp = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jButton_xoa_qlsp = new javax.swing.JButton();
        jButton_sua_qlsp = new javax.swing.JButton();
        jButton_them_qlsp = new javax.swing.JButton();
        jPanel_thongke = new javax.swing.JPanel();
        jButton_tonkho = new javax.swing.JButton();
        jButton_tkdoanhthu = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jFormattedTextField_ngaybd_tk = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jFormattedTextField_ngaykt_tk = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Quản lý");

        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel_qltk.setLayout(new java.awt.BorderLayout());

        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField_qltk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_qltk.setText("Tìm kiếm..");
        jPanel17.add(jTextField_qltk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, -1));

        jPanel_qltk.add(jPanel17, java.awt.BorderLayout.PAGE_START);

        jTable_qltk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTable_qltk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Tên Nhân Viên", "Tên tài khoản", "Mật Khẩu", "Quyền", "Truy cập gần nhất"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_qltk.setRowHeight(32);
        jTable_qltk.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane7.setViewportView(jTable_qltk);

        jPanel_qltk.add(jScrollPane7, java.awt.BorderLayout.CENTER);

        jButton_xoa_qltk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_xoa_qltk.setForeground(new java.awt.Color(255, 0, 0));
        jButton_xoa_qltk.setText("Xóa");

        jButton_sua_qltk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_sua_qltk.setText("Sửa");

        jButton_them_qltk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_them_qltk.setForeground(new java.awt.Color(0, 153, 51));
        jButton_them_qltk.setText("Thêm mới");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addComponent(jButton_xoa_qltk, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
                .addComponent(jButton_sua_qltk, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_them_qltk, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_them_qltk)
                    .addComponent(jButton_sua_qltk)
                    .addComponent(jButton_xoa_qltk)))
        );

        jPanel_qltk.add(jPanel18, java.awt.BorderLayout.PAGE_END);

        jTabbedPane.addTab("Quản lý Tài khoản", jPanel_qltk);

        jPanel_qlvt.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox_qlvt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox_qlvt.setForeground(new java.awt.Color(0, 153, 51));
        jComboBox_qlvt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản Lý", "Nhân Viên Bán Hàng" }));
        jPanel12.add(jComboBox_qlvt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 160, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Chọn vai trò");
        jPanel12.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, -1, -1));

        jPanel_qlvt.add(jPanel12, java.awt.BorderLayout.PAGE_START);

        jTable_qlvt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTable_qlvt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Quyền", "Diễn Giải"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_qlvt.setRowHeight(32);
        jTable_qlvt.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane6.setViewportView(jTable_qlvt);

        jPanel_qlvt.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        jButton_xoa_qlvt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_xoa_qlvt.setForeground(new java.awt.Color(255, 0, 0));
        jButton_xoa_qlvt.setText("Xóa");

        jButton_sua_qlvt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_sua_qlvt.setText("Sửa");

        jButton_them_qlvt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_them_qlvt.setForeground(new java.awt.Color(0, 153, 51));
        jButton_them_qlvt.setText("Thêm mới");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jButton_xoa_qlvt, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 348, Short.MAX_VALUE)
                .addComponent(jButton_sua_qlvt, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_them_qlvt, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_them_qlvt)
                    .addComponent(jButton_sua_qlvt)
                    .addComponent(jButton_xoa_qlvt)))
        );

        jPanel_qlvt.add(jPanel16, java.awt.BorderLayout.PAGE_END);

        jTabbedPane.addTab("Quản lý Vai trò", jPanel_qlvt);

        jPanel_qlnv.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jTable_qlnv.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTable_qlnv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên Nhân Viên", "Tên tài khoản", "Quyền", "Số tài khoản", "Địa chỉ", "Số điện thoại", "Email", "Giới tính", "Truy cập gần nhất"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_qlnv.setRowHeight(32);
        jTable_qlnv.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jTable_qlnv);

        jPanel4.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jButton_sua_qlnv.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_sua_qlnv.setText("Sửa");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 537, Short.MAX_VALUE)
                .addComponent(jButton_sua_qlnv, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton_sua_qlnv))
        );

        jPanel4.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel_qlnv.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField_timkiem_qlnv.setText("Tìm kiếm..");
        jPanel5.add(jTextField_timkiem_qlnv, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, -1));

        jPanel_qlnv.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jTabbedPane.addTab("Quản lý Nhân viên", jPanel_qlnv);

        jPanel_qlncc.setLayout(new java.awt.BorderLayout());

        jButton_xoa_ncc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_xoa_ncc.setForeground(new java.awt.Color(255, 0, 0));
        jButton_xoa_ncc.setText("Xóa");

        jButton_sua_ncc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_sua_ncc.setText("Sửa");

        jButton_them_ncc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_them_ncc.setForeground(new java.awt.Color(0, 153, 0));
        jButton_them_ncc.setText("Thêm mới");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addComponent(jButton_xoa_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
                .addComponent(jButton_sua_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_them_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_them_ncc)
                    .addComponent(jButton_sua_ncc)
                    .addComponent(jButton_xoa_ncc)))
        );

        jPanel_qlncc.add(jPanel19, java.awt.BorderLayout.PAGE_END);

        jTable_qlncc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTable_qlncc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên", "Địa chỉ", "Số điện thoại", "Mã số thuế", "Fax", "Người liên hệ", "Số tài khoản"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_qlncc.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable_qlncc.setRowHeight(32);
        jTable_qlncc.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(jTable_qlncc);

        jPanel_qlncc.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField_timkiem_ncc.setText("Tìm kiếm..");
        jPanel15.add(jTextField_timkiem_ncc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, -1));

        jPanel_qlncc.add(jPanel15, java.awt.BorderLayout.PAGE_START);

        jTabbedPane.addTab("Quản lý Nhà cung cấp", jPanel_qlncc);

        jPanel_qlsp.setLayout(new java.awt.BorderLayout());

        jTable_qlsp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTable_qlsp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Loại", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_qlsp.setRowHeight(32);
        jTable_qlsp.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(jTable_qlsp);

        jPanel_qlsp.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField_timkiem_qlsp.setText("Tìm kiếm..");
        jPanel7.add(jTextField_timkiem_qlsp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, -1));

        jPanel_qlsp.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jButton_xoa_qlsp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_xoa_qlsp.setForeground(new java.awt.Color(255, 0, 0));
        jButton_xoa_qlsp.setText("Xóa");

        jButton_sua_qlsp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_sua_qlsp.setText("Sửa");

        jButton_them_qlsp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_them_qlsp.setForeground(new java.awt.Color(0, 153, 51));
        jButton_them_qlsp.setText("Thêm mới");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jButton_xoa_qlsp, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
                .addComponent(jButton_sua_qlsp, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_them_qlsp, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_them_qlsp)
                    .addComponent(jButton_sua_qlsp)
                    .addComponent(jButton_xoa_qlsp)))
        );

        jPanel_qlsp.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        jTabbedPane.addTab("Quản lý Sản phẩm", jPanel_qlsp);

        jButton_tonkho.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_tonkho.setForeground(new java.awt.Color(0, 153, 51));
        jButton_tonkho.setText("Thống kê tồn kho");
        jButton_tonkho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_tonkhoActionPerformed(evt);
            }
        });

        jButton_tkdoanhthu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_tkdoanhthu.setForeground(new java.awt.Color(0, 153, 51));
        jButton_tkdoanhthu.setText("Thống kê doanh thu");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 153));
        jLabel12.setText("Từ ngày");

        try {
            jFormattedTextField_ngaybd_tk.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 153));
        jLabel10.setText("đến");

        try {
            jFormattedTextField_ngaykt_tk.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel_thongkeLayout = new javax.swing.GroupLayout(jPanel_thongke);
        jPanel_thongke.setLayout(jPanel_thongkeLayout);
        jPanel_thongkeLayout.setHorizontalGroup(
            jPanel_thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_thongkeLayout.createSequentialGroup()
                .addGroup(jPanel_thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_thongkeLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField_ngaybd_tk, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField_ngaykt_tk, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_thongkeLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel_thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_tkdoanhthu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_tonkho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(346, Short.MAX_VALUE))
        );
        jPanel_thongkeLayout.setVerticalGroup(
            jPanel_thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_thongkeLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel_thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jFormattedTextField_ngaybd_tk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField_ngaykt_tk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_tkdoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_tonkho, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Thống kê", jPanel_thongke);

        jPanel10.add(jTabbedPane, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel10, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_tonkhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_tonkhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_tonkhoActionPerformed
    private NhanVien currentNhanVien;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_sua_ncc;
    private javax.swing.JButton jButton_sua_qlnv;
    private javax.swing.JButton jButton_sua_qlsp;
    private javax.swing.JButton jButton_sua_qltk;
    private javax.swing.JButton jButton_sua_qlvt;
    private javax.swing.JButton jButton_them_ncc;
    private javax.swing.JButton jButton_them_qlsp;
    private javax.swing.JButton jButton_them_qltk;
    private javax.swing.JButton jButton_them_qlvt;
    private javax.swing.JButton jButton_tkdoanhthu;
    private javax.swing.JButton jButton_tonkho;
    private javax.swing.JButton jButton_xoa_ncc;
    private javax.swing.JButton jButton_xoa_qlsp;
    private javax.swing.JButton jButton_xoa_qltk;
    private javax.swing.JButton jButton_xoa_qlvt;
    private javax.swing.JComboBox<String> jComboBox_qlvt;
    private javax.swing.JFormattedTextField jFormattedTextField_ngaybd_tk;
    private javax.swing.JFormattedTextField jFormattedTextField_ngaykt_tk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel_qlncc;
    private javax.swing.JPanel jPanel_qlnv;
    private javax.swing.JPanel jPanel_qlsp;
    private javax.swing.JPanel jPanel_qltk;
    private javax.swing.JPanel jPanel_qlvt;
    private javax.swing.JPanel jPanel_thongke;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTable_qlncc;
    private javax.swing.JTable jTable_qlnv;
    private javax.swing.JTable jTable_qlsp;
    private javax.swing.JTable jTable_qltk;
    private javax.swing.JTable jTable_qlvt;
    private javax.swing.JTextField jTextField_qltk;
    private javax.swing.JTextField jTextField_timkiem_ncc;
    private javax.swing.JTextField jTextField_timkiem_qlnv;
    private javax.swing.JTextField jTextField_timkiem_qlsp;
    // End of variables declaration//GEN-END:variables
}
