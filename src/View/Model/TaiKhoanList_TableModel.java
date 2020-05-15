/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Model;

import Model.NhanVien;
import java.util.ArrayList;

/**
 *
 * @author THINH
 */
public class TaiKhoanList_TableModel extends NhanVienList_TableModel {
    private final int TENNHANVIEN_COL = 0;
    private final int TAIKHOAN_COL = 1;
    private final int VAITRO_COL = 2;
    private final int SOTAIKHOAN_COL = 3;
    private final int DIACHI_COL = 4;
    private final int SODIENTHOAI_COL = 5;
    private final int EMAIL_COL = 6;
    private final int GIOITINH_COL = 7;
    
    public TaiKhoanList_TableModel(ArrayList<NhanVien> NhanVienList) {
        super(NhanVienList);
        String[] column = {
            "Tên Nhân Viên", "Tên tài khoản", "Vai Trò", "Số tài khoản", "Địa chỉ", "Số điện thoại", "Email", "Giới tính"
        };
        this.setColumnNames(column);
    }
        @Override
    public Object getValueAt(int i, int i1) {
        switch (i1) {
            case TENNHANVIEN_COL:
                return NhanVienList.get(i).getTen();
            case TAIKHOAN_COL:
                return NhanVienList.get(i).getTenTaiKhoan();
            case VAITRO_COL:
                return NhanVienList.get(i).getVaiTro().getTen();
            case SOTAIKHOAN_COL:
                return NhanVienList.get(i).getSoTaiKhoan();
            case DIACHI_COL:
                return NhanVienList.get(i).getDiachi();
            case SODIENTHOAI_COL:
                return NhanVienList.get(i).getSoDienThoai();
            case EMAIL_COL:
                return NhanVienList.get(i).getEmail();
            case GIOITINH_COL:
                return NhanVienList.get(i).getGioiTinh();
            default:
                return null;
        }
    }
}
