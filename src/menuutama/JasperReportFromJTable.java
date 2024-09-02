/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menuutama;

/**
 *
 * @author alfar
 */
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JTable;
import java.util.HashMap;
import net.sf.jasperreports.engine.util.JRLoader;

public class JasperReportFromJTable {
    public void generateReport(JTable table, String reportPath) {
        try {
            // Membuat datasource dari JTable
            JRDataSource dataSource = new JTableDataSource(table);

            // Memuat file jasper report
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);

            // Mengisi laporan dengan datasource dan parameter kosong
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

            // Menampilkan laporan
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
