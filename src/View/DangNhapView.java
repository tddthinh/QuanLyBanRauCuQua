package View;

import View.Parent.Viewer;
import Model.NhanVien;
import java.sql.SQLException;

public class DangNhapView extends Viewer {

    public DangNhapView() {
        super();
        initComponents();
        getRootPane().setDefaultButton(jButton_dangnhap);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_tk = new javax.swing.JTextField();
        jPasswordField_mk = new javax.swing.JPasswordField();
        jButton_dangnhap = new javax.swing.JButton();
        jLabel_error = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng nhập tài khoản");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("Đăng nhập tài khoản");
        jLabel4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Tên tài khoản");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mật khẩu");

        jTextField_tk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_tk.setText("nhanvien1");

        jPasswordField_mk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPasswordField_mk.setText("matkhau");

        jButton_dangnhap.setText("Đăng nhập");
        jButton_dangnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_dangnhapActionPerformed(evt);
            }
        });

        jLabel_error.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_error.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_dangnhap))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(jTextField_tk, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_error)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPasswordField_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_tk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_error)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jButton_dangnhap)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
        @Override
    public void setVisible(boolean b) {
        jPasswordField_mk.setText("");
        super.setVisible(b); //To change body of generated methods, choose Tools | Templates.
    }

    public void resetAccountFieldIfHasText() {
        jPasswordField_mk.setText("");
        jTextField_tk.setText("");
        if (!"".equals(jLabel_error.getText())) {
            jLabel_error.setText("");
        }

    }

    private NhanVien currentNhanVien;

    private void success() {
        GiaoDienChinhView GiaoDienChinhView = new GiaoDienChinhView(this);
        GiaoDienChinhView.updateNhanVien(currentNhanVien);
        GiaoDienChinhView.setVisible(true);
        this.setVisible(false);
    }

    private void error(String text) {
        jLabel_error.setText(text);
    }
    private void jButton_dangnhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_dangnhapActionPerformed

        String username = jTextField_tk.getText();
        String password = new String(jPasswordField_mk.getPassword());
        try {
            currentNhanVien = NhanVienService.loginAccount(username, password);
            resetAccountFieldIfHasText();
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("loginAccount Exception: " + ex);
        }
        if (currentNhanVien != null) {
            success();
        } else {
            error("Lỗi đăng nhập.");
        }

    }//GEN-LAST:event_jButton_dangnhapActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_dangnhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_error;
    private javax.swing.JPasswordField jPasswordField_mk;
    private javax.swing.JTextField jTextField_tk;
    // End of variables declaration//GEN-END:variables
}
