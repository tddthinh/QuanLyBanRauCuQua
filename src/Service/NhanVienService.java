package Service;
import Model.NhanVien;
import Model.Quyen;
import Model.VaiTro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class NhanVienService {

    private static NhanVienService Instance = null;

    private NhanVienService() {
    }

    public static NhanVienService getInstance() {
        if (Instance == null) {
            Instance = new NhanVienService();
        }
        return Instance;
    }
    public ArrayList<NhanVien> getAllNhanVien() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM `nhanvien`";
        PreparedStatement pStatement = (PreparedStatement) Database.getInstance().prepareStatement(sql);
        ResultSet resultSet = pStatement.executeQuery();
        ArrayList<NhanVien> nhanvienList = new ArrayList<>();
        while(resultSet.next()) {
            NhanVien nhanvien = ResultSet_toNhanVien(resultSet);
            nhanvienList.add(nhanvien);
        }
        return nhanvienList;
    }

    public NhanVien loginAccount(String username, String password) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM `nhanvien` WHERE NV_TENTK = ? and NV_MATKHAU = ?";
        PreparedStatement pStatement = (PreparedStatement) Database.getInstance().prepareStatement(sql);
        pStatement.setString(1, username);
        pStatement.setString(2, password);
        ResultSet resultSet = pStatement.executeQuery();
        if (resultSet.next()) {
            return ResultSet_toNhanVien(resultSet);
        } else {
            return null;
        }
    }
    public NhanVien ResultSet_toNhanVien(ResultSet rs) throws SQLException, ClassNotFoundException {
        String ma = rs.getString("nv_ma");
        String ten = rs.getString("nv_ten");
        String diachi = rs.getString("nv_diachi");
        String sdt = rs.getString("nv_sdt");
        String email = rs.getString("nv_email");
        boolean gt = rs.getBoolean("nv_gioitinh");
        String stk = rs.getString("nv_stk");
        String matkhau = rs.getString("nv_matkhau");
        String tentk = rs.getString("nv_tentk");
        NhanVien nhanvien = new NhanVien(ma, ten, diachi, sdt, email, stk, matkhau, tentk, gt);

        String ma_vaitro = rs.getString("vt_ma");
        VaiTro vaitro = getVaiTro(ma_vaitro).get(0);
        nhanvien.setVaiTro(vaitro);
        return nhanvien;
    }
    public boolean createNewAccount(NhanVien nhanvien) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO `nhanvien` "
                + "(`NV_MA`, `VT_MA`, `NV_TEN`, `NV_DIACHI`, `NV_SDT`, `NV_EMAIL`, `NV_GIOITINH`, `NV_STK`, `NV_MATKHAU`, `NV_TENTK`) "
                + "VALUES (?, ?, ?, '', 0, '', 0, '', ?, ?);";
        PreparedStatement pStatement = (PreparedStatement) Database.getInstance().prepareStatement(sql);
        int MaNhanVien = nextCurrentMaNhanVien();
        pStatement.setInt(1, MaNhanVien);
        pStatement.setString(2, nhanvien.getVaiTro().getMa());
        pStatement.setString(3, nhanvien.getTen());
        pStatement.setString(4, nhanvien.getTenTaiKhoan());
        pStatement.setString(5, nhanvien.getMatkhau());
        return pStatement.executeUpdate() > 0;
    }
    private int nextCurrentMaNhanVien() throws SQLException, ClassNotFoundException{
        PreparedStatement pStatement = (PreparedStatement) Database.getInstance().prepareStatement("SELECT * FROM `ma_nhanvien`");
        ResultSet resultSet = pStatement.executeQuery();
        resultSet.next();
        int currentMaNhanVien = resultSet.getInt("mahientai");
        pStatement = (PreparedStatement) Database.getInstance().prepareStatement("UPDATE `ma_nhanvien` SET `mahientai`= `mahientai` + 1");
        pStatement.execute();
        return currentMaNhanVien;
    }
    public boolean updateNhanVien(NhanVien nhanvien) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE `nhanvien` SET " +
                "`NV_TEN`=?," +
                "`NV_DIACHI`=?," +
                "`NV_SDT`=?," +
                "`NV_EMAIL`=?," +
                "`NV_GIOITINH`=?" +
                " WHERE `NV_MA`=?";
        PreparedStatement pStatement = Database.getInstance().prepareStatement(sql);
        pStatement.setString(1, nhanvien.getTen());
        pStatement.setString(2, nhanvien.getDiachi());
        pStatement.setInt(3, Integer.parseInt(nhanvien.getSoDienThoai()));
        pStatement.setString(4, nhanvien.getEmail());
        pStatement.setBoolean(5, nhanvien.getGioiTinh());
        pStatement.setInt(6, Integer.parseInt(nhanvien.getMa()));
        return pStatement.executeUpdate() > 0;
    }
    public ArrayList<VaiTro> getAllVaiTro() throws ClassNotFoundException, SQLException {
        return getVaiTro("*");
    }
    public ArrayList<VaiTro> getVaiTro(String ma_timkiem) throws ClassNotFoundException, SQLException {
        String adding_sql = "";
        if (!ma_timkiem.equals("*")) {
            adding_sql = "vaitro.VT_MA = ? and";
        }
        String sql = "SELECT * FROM `vaitro`,`quyen`,`vaitro_quyen` "
                + "WHERE " + adding_sql + " vaitro.VT_MA = vaitro_quyen.VT_MA and quyen.QUYEN_MA = vaitro_quyen.QUYEN_MA";
        PreparedStatement pStatement = (PreparedStatement) Database.getInstance().prepareStatement(sql);
        if (!ma_timkiem.equals("*")) {
            pStatement.setString(1, ma_timkiem);
        }
        ResultSet resultSet = pStatement.executeQuery();
        ArrayList<VaiTro> vaitroList = new ArrayList<>();
        while (resultSet.next()) {
            String ma_vt = resultSet.getString("vt_ma");
            VaiTro vaitro = searchVaiTroInList_CreateNewIfNotExist(vaitroList, ma_vt);
            String ten_vt = resultSet.getString("vt_ten");
            vaitro.setTen(ten_vt);
            Quyen quyen = ResultSet_toQuyen(resultSet);
            vaitro.addQuyen(quyen);
        }
        return vaitroList;
    }
    public VaiTro searchVaiTroInList_CreateNewIfNotExist(ArrayList<VaiTro> vaitroList, String ma_vt) {
        for (VaiTro vaitro : vaitroList) {
            if (vaitro.getMa().equals(ma_vt)) {
                return vaitro;
            }
        }
        VaiTro vaitro = new VaiTro(ma_vt, "");
        vaitroList.add(vaitro);
        return vaitro;
    }
    public ArrayList<Quyen> getAllQuyen() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM `quyen`";
        PreparedStatement pStatement = (PreparedStatement) Database.getInstance().prepareStatement(sql);
        ResultSet resultSet = pStatement.executeQuery();
        ArrayList<Quyen> quyenList = new ArrayList<>();
        while (resultSet.next()) {
            Quyen quyen = ResultSet_toQuyen(resultSet);
            quyenList.add(quyen);
        }
        return quyenList;
    }
    public Quyen ResultSet_toQuyen(ResultSet rs) throws SQLException {
        String ma = rs.getString("quyen_ma");
        String diengiai = rs.getString("quyen_diengiai");
        Quyen quyen = new Quyen(ma, diengiai);
        return quyen;
    }
    public boolean updateVaiTro(VaiTro vaitro) throws SQLException, ClassNotFoundException {
        String sql = "update vaitro set VT_TEN = ? where VT_MA = ?";
        PreparedStatement pStatement = Database.getInstance().prepareStatement(sql);
        pStatement.setString(1, vaitro.getTen());
        pStatement.setString(2, vaitro.getMa());
        return pStatement.executeUpdate() > 0;
    }
    public boolean addVaiTro(VaiTro vaitro) throws ClassNotFoundException, SQLException {
        String sql = "insert into vaitro values(?, ?)";
        PreparedStatement pStatement = Database.getInstance().prepareStatement(sql);
        pStatement.setString(1, vaitro.getMa());
        pStatement.setString(2, vaitro.getTen());
        return pStatement.executeUpdate() > 0;    }
}
