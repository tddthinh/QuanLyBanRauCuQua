package Service;

import Model.KhuyenMai;
import Model.LoaiSanPham;
import Model.NhaCungCap;
import Model.SanPham;
import Model.SanPhamNhap;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamService {

    private static SanPhamService Instance = null;

    private SanPhamService() {
    }

    public static SanPhamService getInstance() {
        if (Instance == null) {
            Instance = new SanPhamService();
        }
        return Instance;
    }

    public SanPham getSanPhamByMaSanPham(String ma_sp) throws ClassNotFoundException, SQLException {
        String sql = "SELECT loaisanpham.LSP_MA, SP_MA, SP_TEN, SP_DONGIA, loaisanpham.LSP_DVT, loaisanpham.LSP_TEN "
                + "FROM `sanpham`, `loaisanpham` WHERE sanpham.LSP_MA = loaisanpham.LSP_MA and sp_ma = ?";
        PreparedStatement pStatement = Database.getInstance().prepareStatement(sql);
        pStatement.setString(1, ma_sp);
        ResultSet resultSet = pStatement.executeQuery();
        if (resultSet.next()) {
            SanPham sanpham = ResultSet_toSanPham(resultSet);
            return sanpham;
        } else {
            return null;
        }

    }

    public ArrayList<SanPham> getAllSanPham() throws ClassNotFoundException, SQLException {
        String sql = "SELECT loaisanpham.LSP_MA, SP_MA, SP_TEN, SP_DONGIA, loaisanpham.LSP_DVT, loaisanpham.LSP_TEN "
                + "FROM `sanpham`, `loaisanpham` WHERE sanpham.LSP_MA = loaisanpham.LSP_MA";
        PreparedStatement pStatement = Database.getInstance().prepareStatement(sql);
        ResultSet resultSet = pStatement.executeQuery();
        ArrayList<SanPham> sanphamList = new ArrayList<>();
        while (resultSet.next()) {
            SanPham sanpham = ResultSet_toSanPham(resultSet);
            ArrayList<NhaCungCap> nccList = getNhaCungCap(sanpham.getMa());
            sanpham.setNhaCungCap(nccList);
            sanphamList.add(sanpham);
        }
        return sanphamList;
    }

    public SanPham ResultSet_toSanPham(ResultSet rs) throws SQLException {
        String ma = rs.getString("sp_ma");
        String ten = rs.getString("sp_ten");
        float dongiaban = rs.getFloat("sp_dongia");
        SanPham sanpham = new SanPham(ma, ten);
        sanpham.setDonGiaBan(dongiaban);

        LoaiSanPham lsp = ResultSet_toLoaiSanPham(rs);
        sanpham.setLoaiSanPham(lsp);
        return sanpham;
    }

    public LoaiSanPham ResultSet_toLoaiSanPham(ResultSet rs) throws SQLException, SQLException, SQLException, SQLException {
        String ma = rs.getString("lsp_ma");
        String ten = rs.getString("lsp_ten");
        String dvt = rs.getString("lsp_dvt");
        LoaiSanPham loaisanpham = new LoaiSanPham(ma, ten, dvt);
        return loaisanpham;
    }

    public ArrayList<NhaCungCap> getAllNhaCungCap() throws SQLException, ClassNotFoundException {
        return getNhaCungCap("*");
    }

    public ArrayList<NhaCungCap> getNhaCungCap(String ma_sanpham) throws SQLException, ClassNotFoundException {
        String sql;
        if (ma_sanpham.equals("*")) {
            sql = "SELECT * FROM `nhacungcap`";
        } else {
            sql = "SELECT * "
                    + "FROM `sanpham_ncc`, `nhacungcap` "
                    + "where nhacungcap.NCC_MA = sanpham_ncc.NCC_MA and sanpham_ncc.SP_MA = ?";
        }
        PreparedStatement pStatement = (PreparedStatement) Database.getInstance().prepareStatement(sql);
        if (!ma_sanpham.equals("*")) {
            pStatement.setString(1, ma_sanpham);
        }
        ResultSet resultSet = pStatement.executeQuery();
        ArrayList<NhaCungCap> nccList = new ArrayList<>();
        while (resultSet.next()) {
            NhaCungCap ncc = ResultSet_toNhaCungCap(resultSet);
            nccList.add(ncc);
        }
        return nccList;
    }

    public NhaCungCap ResultSet_toNhaCungCap(ResultSet rs) throws SQLException {
        String ma = rs.getString("ncc_ma");
        String ten = rs.getString("ncc_ten");
        String diachi = rs.getString("ncc_diachi");
        String sdt = rs.getString("ncc_sdt");
        String mst = rs.getString("ncc_mst");
        String sofax = rs.getString("ncc_sofax");
        String nguoilh = rs.getString("ncc_nguoilh");
        String stk = rs.getString("ncc_stk");
        NhaCungCap ncc = new NhaCungCap(ma, ten, diachi, nguoilh, stk, sdt, sofax, mst);
        return ncc;
    }

    public boolean updateSanPham(SanPham sanpham) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE `sanpham` SET `LSP_MA`= ? ,`SP_TEN`= ? ,`SP_DONGIA`= ? WHERE SP_MA = ?";
        PreparedStatement pStatement = Database.getInstance().prepareStatement(sql);
        pStatement.setString(1, sanpham.getLoaiSanPham().getMa());
        pStatement.setString(2, sanpham.getTen());
        pStatement.setFloat(3, sanpham.getDonGiaBan());
        pStatement.setString(4, sanpham.getMa());
        return pStatement.executeUpdate() > 0;
    }

    public ArrayList<KhuyenMai> getKhuyenMaiToday() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `khuyenmai` WHERE KM_NGAYKT >= (select CURDATE())";
        PreparedStatement pStatement = Database.getInstance().prepareStatement(sql);
        ResultSet resultSet = pStatement.executeQuery();
        ArrayList<KhuyenMai> kmList = new ArrayList<>();
        while (resultSet.next()) {
            KhuyenMai khuyenmai = ResultSet_toKhuyenMai(resultSet);
            kmList.add(khuyenmai);
        }
        return kmList;
    }

    public KhuyenMai ResultSet_toKhuyenMai(ResultSet rs) throws SQLException {
        String ma = rs.getString("km_ma");
        Date ngaybd = rs.getDate("km_ngaybd");
        Date ngaykt = rs.getDate("km_ngaykt");
        String hinhthuc = rs.getString("km_hinhthuc");
        float donvi = rs.getFloat("km_donvi");
        KhuyenMai khuyenmai = new KhuyenMai(ma, hinhthuc, ngaybd, ngaykt, donvi);
        return khuyenmai;
    }

    public ArrayList<KhuyenMai> getKhuyenMaiByDate(Date ngaybatdau, Date ngayketthuc) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `khuyenmai` WHERE ? >= KM_NGAYBD and ? <= KM_NGAYKT";
        PreparedStatement pStatement = Database.getInstance().prepareStatement(sql);
        pStatement.setDate(1, ngaybatdau);
        pStatement.setDate(2, ngayketthuc);
        ResultSet resultSet = pStatement.executeQuery();
        ArrayList<KhuyenMai> kmList = new ArrayList<>();
        while (resultSet.next()) {
            KhuyenMai khuyenmai = ResultSet_toKhuyenMai(resultSet);
            kmList.add(khuyenmai);
        }
        return kmList;
    }

    public boolean addNhaCungCap(NhaCungCap nhacungcap) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO `nhacungcap` (`NCC_MA`, `NCC_TEN`, `NCC_DIACHI`, `NCC_SDT`, `NCC_MST`, `NCC_SOFAX`, `NCC_NGUOILH`, `NCC_STK`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement pStatement = Database.getInstance().prepareStatement(sql);
        String ma = createMaNhaCungCap();
        pStatement.setString(1, ma);
        pStatement.setString(2, nhacungcap.getTen());
        pStatement.setString(3, nhacungcap.getDiaChi());
        pStatement.setString(4, nhacungcap.getSoDienThoai());
        pStatement.setString(5, nhacungcap.getMaSoThue());
        pStatement.setString(6, nhacungcap.getSoFax());
        pStatement.setString(7, nhacungcap.getNguoiLienHe());
        pStatement.setString(8, nhacungcap.getSoTaiKhoan());
        nhacungcap.setMa(ma);
        return pStatement.executeUpdate() > 0;
    }

    //tạo mã nhà cung cấp, 3 ký tự, 000 - 999
    private String createMaNhaCungCap() {
        Random r = new Random();
        String alphabet = "12345678901234567890";
        String result = "";
        for (int i = 0; i < 3; i++) {
            result += alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return result;
    }

    public boolean updateNhaCungCap(NhaCungCap nhacungcap) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE `nhacungcap` "
                + "SET `NCC_TEN` = ?, "
                + "`NCC_DIACHI` = ?, "
                + "`NCC_SDT` = ?, "
                + "`NCC_MST` = ?, "
                + "`NCC_SOFAX` = ?, "
                + "`NCC_NGUOILH` = ?, "
                + "`NCC_STK` = ? "
                + "WHERE `nhacungcap`.`NCC_MA` = ? ;";
        PreparedStatement pStatement = Database.getInstance().prepareStatement(sql);
        pStatement.setString(1, nhacungcap.getTen());
        pStatement.setString(2, nhacungcap.getDiaChi());
        pStatement.setString(3, nhacungcap.getSoDienThoai());
        pStatement.setString(4, nhacungcap.getMaSoThue());
        pStatement.setString(5, nhacungcap.getSoFax());
        pStatement.setString(6, nhacungcap.getNguoiLienHe());
        pStatement.setString(7, nhacungcap.getSoTaiKhoan());
        pStatement.setString(8, nhacungcap.getMa());
        return pStatement.executeUpdate() > 0;
    }

    public ArrayList<LoaiSanPham> getAllLoaiSanPham() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM `loaisanpham`";
        PreparedStatement pStatement = Database.getInstance().prepareStatement(sql);
        ResultSet resultSet = pStatement.executeQuery();
        ArrayList<LoaiSanPham> lspList = new ArrayList<>();
        while (resultSet.next()) {
            LoaiSanPham loaisanpham = ResultSet_toLoaiSanPham(resultSet);
            lspList.add(loaisanpham);
        }
        return lspList;
    }

    public ArrayList<SanPhamNhap> getSanPhamNhapByDate(Date ngaybatdau, Date ngayketthuc, String ma_sanpham) throws ClassNotFoundException, SQLException {
        String adding_sql = "";
        if (!ma_sanpham.equals("*")) {
            adding_sql = " and sanpham.SP_MA = ?";
        }
        String sql = "SELECT * "
                + "FROM `chitietspnhap`,`sanpham`,`loaisanpham` "
                + "WHERE "
                + "(`CTSPN_NGAYNHAP` BETWEEN ? and ?) "
                + "and chitietspnhap.SP_MA = sanpham.SP_MA and loaisanpham.LSP_MA = sanpham.LSP_MA"
                + adding_sql;
        PreparedStatement pStatement = Database.getInstance().prepareStatement(sql);
        pStatement.setDate(1, ngaybatdau);
        pStatement.setDate(2, ngayketthuc);
        if (!ma_sanpham.equals("*")) {
            pStatement.setString(3, ma_sanpham);
        }
        ResultSet resultSet = pStatement.executeQuery();
        ArrayList<SanPhamNhap> spnList = new ArrayList<>();
        while (resultSet.next()) {
            SanPhamNhap spn = ResultSet_toSanPhamNhap(resultSet);
            spnList.add(spn);
        }
        return spnList;
    }

    public SanPhamNhap ResultSet_toSanPhamNhap(ResultSet rs) throws SQLException {
        Date ngaynhap = rs.getDate("CTSPN_NGAYNHAP");
        float dongia = rs.getFloat("CTSPN_DONGIA");
        int soluong = rs.getInt("CTSPN_SOLUONG");
        SanPham sanpham = ResultSet_toSanPham(rs);
        SanPhamNhap spn = new SanPhamNhap(sanpham, ngaynhap, dongia, soluong);
        return spn;
    }
    
    public Date createDate(String year, String month, String day) {
        return Date.valueOf(year + "-" + month + "-" + day);
    }
}
