package menuutama;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import koneksi.Koneksi;
import java.sql.PreparedStatement;

public class KriteriaWp {
    private Connection conn = new Koneksi().connect();

    class Kriteria {
        String kd_kriteria;
        String nama_kriteria;

        Kriteria(String kd_kriteria, String nama_kriteria) {
            this.kd_kriteria = kd_kriteria;
            this.nama_kriteria = nama_kriteria;
        }
    }

    public double[] perbaikan(){
        String sql = "SELECT * FROM kriteria";
        String sqlInsert = "INSERT INTO bobot_kriteria (c1, c2, c3, c4) VALUES (?,?,?,?)";
        List<Integer> bobotList = new ArrayList<>();
        List<Kriteria> kriteriaList = new ArrayList<>();
        double totalBobot = 0.0;

        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                int bobot = hasil.getInt("bobot");
                bobotList.add(bobot);
                totalBobot += bobot;
            }

            if (totalBobot == 0) {
                JOptionPane.showMessageDialog(null, "Total bobot is zero, cannot divide by zero.");
                return null;
            }

            double[] hasilPembagian = new double[bobotList.size()];
            for (int i = 0; i < bobotList.size(); i++) {
                hasilPembagian[i] = bobotList.get(i) / totalBobot;
            }

            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
            for (int i = 0; i < 1; i++) {
                pstmt.setDouble(1, hasilPembagian[0]);
                pstmt.setDouble(2, hasilPembagian[1]);
                pstmt.setDouble(3, hasilPembagian[2]);
                pstmt.setDouble(4, hasilPembagian[3]);

                pstmt.addBatch();
            }
            pstmt.executeBatch();

            return hasilPembagian;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
