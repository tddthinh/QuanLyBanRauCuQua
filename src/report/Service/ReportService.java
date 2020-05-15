/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report.Service;

import Model.NhanVien;
import Model.SanPhamTonKho;
import Service.HoaDonService;
import Service.NhanVienService;
import java.awt.List;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author THINH
 */
public class ReportService {

    public static void main(String[] args) throws JRException {
        try {
            String sourceReportRaw = "src\\report\\Template\\TonKhoReport.jasper";
            Date ngaybatdau = Date.valueOf("2017-10-18");
            Date ngayketthuc = Date.valueOf("2017-10-20");
            ArrayList<SanPhamTonKho> sptkList = HoaDonService.getInstance().getSanPhamTonKhoByDate(ngaybatdau, ngayketthuc);
            Map<String, Object> params = new HashMap<>();

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sptkList);
            params.put("ItemDataSource", beanColDataSource);
            
            JasperPrint print = JasperFillManager.fillReport(sourceReportRaw, params, new JREmptyDataSource());
            JasperViewer.viewReport(print);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
