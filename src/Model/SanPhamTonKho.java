package Model;
import java.sql.Date;
public class SanPhamTonKho {
    private SanPham sanPham;
    private String tenSanPham;
    private String maSanPham;
    private Date ngay;
    private float soLuongNhap;
    private float soLuongBan;
    private float soLuongTon;
    private float tongTienMua;
    private float tongTienBan;
    private float doanhThu;

    public SanPhamTonKho(SanPham SanPham, Date Ngay, float SoLuongNhap, float SoLuongBan, float TongTienMua, float TongTienBan) {
        this.sanPham = SanPham;
        this.tenSanPham = SanPham.getTen();
        this.maSanPham = SanPham.getMa();
        this.ngay = Ngay;
        this.soLuongNhap = SoLuongNhap;
        this.soLuongBan = SoLuongBan;
        this.tongTienMua = TongTienMua;
        this.tongTienBan = TongTienBan;
    }
    
    public SanPham getSanPham() {
        return sanPham;
    }

    public Date getNgay() {
        return ngay;
    }

    public float getSoLuongBan() {
        return soLuongBan;
    }

    public float getSoLuongNhap() {
        return soLuongNhap;
    }

    public float getSoLuongTon() {
        return soLuongTon;
    }

    public float getTongTienBan() {
        return tongTienBan;
    }

    public float getTongTienMua() {
        return tongTienMua;
    }

    public float getDoanhThu() {
        return doanhThu;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setNgay(Date Ngay) {
        this.ngay = Ngay;
    }

    public void setSanPham(SanPham SanPham) {
        this.sanPham = SanPham;
    }

    public void setSoLuongBan(float SoLuongBan) {
        this.soLuongBan = SoLuongBan;
    }

    public void setSoLuongNhap(float SoLuongNhap) {
        this.soLuongNhap = SoLuongNhap;
    }

    public void setSoLuongTon(float SoLuongTon) {
        this.soLuongTon = SoLuongTon;
    }

    public void setTongTienBan(float TongTienBan) {
        this.tongTienBan = TongTienBan;
    }

    public void setTongTienMua(float TongTienMua) {
        this.tongTienMua = TongTienMua;
    }

    public void setDoanhThu(float DoanhThu) {
        this.doanhThu = DoanhThu;
    }

    public void setMaSanPham(String MaSanPham) {
        this.maSanPham = MaSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.tenSanPham = TenSanPham;
    }

    
    
    @Override
    public String toString() {
        return getSanPham().getTen()+"|"+
        ngay+"|"+
        soLuongNhap+"|"+
        soLuongBan+"|"+
        soLuongTon+"|"+
        tongTienMua+"|"+
        tongTienBan+"|"+
        doanhThu;
    }
    
    
    
    
}
