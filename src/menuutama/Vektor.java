/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menuutama;
import com.mysql.jdbc.ResultSetMetaData;
import koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author alfar
 */
public class Vektor {
    private Connection conn = new Koneksi().connect();
    
    public void olahData(){               
        List<Object[]> vektorS = hitungVektorS();
        List<Object[]> vektorV = hitungVektorV(vektorS);

        
        simpanVektorS(vektorS);
        simpanVektorV(vektorV);
    }
    
//    private List<double[]> hitungVektorS() {
//        // Ambil nilai kriteria dari database
//        LinkedList<Double> max = new LinkedList();
//        LinkedList<Double> mm = new LinkedList();
//        String sql = "SELECT * FROM bobot_kandidat";
//        String sqlbobot = "SELECT * FROM bobot_kriteria";
//        try {
//            java.sql.Statement stmt = conn.createStatement();
//            ResultSet a = stmt.executeQuery(sqlbobot);
//            java.sql.Statement stat = conn.createStatement();
//            ResultSet hasil = stat.executeQuery(sql);
//            double x = 0.0;
//            double y = 0.0;
//            double z = 0.0;
//            double ab = 0.0;
//            double ac = 0.0;
//            
//            
//            List<double[]> person = new ArrayList<>();
//
//            if(a.first()){
//                y = a.getDouble("c1");
//                z = a.getDouble("c2");
//                ab = a.getDouble("c3");
//                ac = a.getDouble("c4");
//            }
//            while(hasil.next()){
//                double[] vektorS = new double[5];
//                double b1 = hasil.getDouble("c1");
//                double b2 = hasil.getDouble("c2");
//                double b3 = hasil.getDouble("c3");
//                double b4 = hasil.getDouble("c4");
//                String divisi = hasil.getString("divisi");
//                String id = hasil.getString("id_pelamar");
//                String numericId = id.substring(1);
//                    vektorS[0] = Double.valueOf(numericId); 
//                    vektorS[1] = Math.pow(b1,y); 
//                    vektorS[2] = Math.pow(b2,z); 
//                    vektorS[3] = Math.pow(b3,ab); 
//                    vektorS[4] = Math.pow(b4,ac);
//                    person.add(vektorS);
//            }
//
//            return person;
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e);
//            return null;
//        }
//
//    }
    
    private List<Object[]> hitungVektorS() {
        // Ambil nilai kriteria dari database
        LinkedList<Double> max = new LinkedList<>();
        LinkedList<Double> mm = new LinkedList<>();
        String sql = "SELECT * FROM bobot_kandidat";
        String sqlbobot = "SELECT * FROM bobot_kriteria";
        try {
            java.sql.Statement stmt = conn.createStatement();
            ResultSet a = stmt.executeQuery(sqlbobot);
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            double y = 0.0;
            double z = 0.0;
            double ab = 0.0;
            double ac = 0.0;

            List<Object[]> person = new ArrayList<>();

            if (a.first()) {
                y = a.getDouble("c1");
                z = a.getDouble("c2");
                ab = a.getDouble("c3");
                ac = a.getDouble("c4");
            }
            while (hasil.next()) {
                Object[] vektorS = new Object[6];
                double b1 = hasil.getDouble("c1");
                double b2 = hasil.getDouble("c2");
                double b3 = hasil.getDouble("c3");
                double b4 = hasil.getDouble("c4");
                String id = hasil.getString("id_pelamar");
                String numericId = id.substring(1);
                String divisi = hasil.getString("divisi");

                vektorS[0] = Integer.valueOf(numericId);
                vektorS[1] = Math.pow(b1, y);
                vektorS[2] = Math.pow(b2, z);
                vektorS[3] = Math.pow(b3, ab);
                vektorS[4] = Math.pow(b4, ac);
                vektorS[5] = divisi;

                person.add(vektorS);
            }

            return person;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    private List<Object[]> hitungVektorV(List<Object[]> vektorS) {
    double totalS = 0.0; 
        // Ambil totalS dari database
        String sqlTotalS = "SELECT SUM(jumlah) AS total FROM vektor_s";
        String sqltotal = "SELECT * FROM vektor_s";
        List<Object[]> vektorV = new ArrayList<>();
        try {
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlTotalS);
            if (rs.next()) {
                totalS = rs.getDouble("total");
            }
            ResultSet hasil = stmt.executeQuery(sqltotal);
            while(hasil.next()){
                String id = hasil.getString("id_pelamar");
                String divisi = hasil.getString("divisi");
                String numericId = id.substring(1);
                int numericIdInt = Integer.parseInt(numericId);
                double[] v = new double[3];
//                v[0] = Double.valueOf(numericId);
                double jumlah = hasil.getDouble("jumlah");
                double nilaiV = jumlah / totalS;
                vektorV.add(new Object[] { numericIdInt, nilaiV, divisi });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil totalS dari database: " + e.getMessage());
            return null;
        }       
        return vektorV;
    }


    
//    private List<double[]> hitungVektorV(List<double[]> vektorS) {
//        double totalS = 0.0; 
//         // Ambil totalS dari database
//        String sqlTotalS = "SELECT SUM(jumlah) AS total FROM vektor_s";
//        String sqltotal = "SELECT * FROM vektor_s";
//        List<double[]> vektorV = new ArrayList<>();
//        try {
//            java.sql.Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sqlTotalS);
//            if (rs.next()) {
//                totalS = rs.getDouble("total");
//            }
//            ResultSet hasil = stmt.executeQuery(sqltotal);
//            while(hasil.next()){
//                String id = hasil.getString("no_id");
//                double[] v = new double[2];
//                v[0] = Double.valueOf(id);
//                double jumlah = hasil.getDouble("jumlah");
//                v[1] = jumlah / totalS;
//                vektorV.add(v);
//            }
//            
//            
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "Gagal mengambil totalS dari database: " + e.getMessage());
//                return null;
//        }       
//        return vektorV;
//
//    }
    
//    private void simpanVektorS(List<double[]> vektorS) {
//    String sql = "INSERT INTO vektor_s (id_pelamar,c1,c2,c3,c4,jumlah) VALUES (?,?,?,?,?,?)";
//        try {
//            PreparedStatement stat = conn.prepareStatement(sql);
//            for (double[] vector : vektorS) {
//                // Reconstruct the ID with the letter "A" prefix
//                String completeId = "A" + String.format("%03d", (int) vector[0]);
//
//                stat.setString(1, completeId);
//                int number = 2;
//                double result = 1.0;
//                for (int i = 1; i < vector.length; i++) {
//                    stat.setDouble(number, vector[i]);
//                    result *= vector[i];
//                    number++;
//                }
//                stat.setDouble(6, result);
//                stat.addBatch();
//            }
//            stat.executeBatch();
//        } catch (SQLException e) {
////            JOptionPane.showMessageDialog(null, "Gagal menyimpan vektor S: " + e.getMessage());
//        }
//    }
    
    private void simpanVektorS(List<Object[]> vektorS) {
        String sql2 = "TRUNCATE TABLE vektor_s";
        String sql = "INSERT INTO vektor_s (id_pelamar, c1, c2, c3, c4, jumlah, divisi) VALUES (?,?,?,?,?,?,?)";
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql2);
            
            PreparedStatement stat = conn.prepareStatement(sql);
            for (Object[] vector : vektorS) {
                // Reconstruct the ID with the letter "A" prefix
                String completeId = "A" + String.format("%03d", (int) vector[0]);

                stat.setString(1, completeId);
                int number = 2;
                double result = 1.0;
                for (int i = 1; i < vector.length - 1; i++) {
                    stat.setDouble(number, (double) vector[i]);
                    result *= (double) vector[i];
                    number++;
                }
                stat.setDouble(6, result);
                stat.setString(7, (String) vector[5]); // Set divisi as string
                stat.addBatch();
            }
            stat.executeBatch();
        } catch (SQLException e) {
    //        JOptionPane.showMessageDialog(null, "Gagal menyimpan vektor S: " + e.getMessage());
        }
    }



//    private void simpanVektorS(List<double[]> vektorS) {
//        String sql = "INSERT INTO vektor_s (id_pelamar,c1,c2,c3,c4,jumlah) VALUES (?,?,?,?,?,?)";
//        try {            
//            PreparedStatement stat = conn.prepareStatement(sql);
//            for(double[]  vector : vektorS){
//                stat.setString(1,String.valueOf(vector[0]));
//                int number = 2;
//                double result = 1.0;
//                for (int i=1;i<vector.length;i++){
//                    stat.setDouble(number,vector[i]);
//                    result *= vector[i];
//                    number++;
//                }
//                stat.setDouble(6,result);
//                stat.addBatch();
//            }           
//            stat.executeBatch();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Gagal menyimpan vektor S: " + e.getMessage());
//        }
//    }
    
//    private void simpanVektorV(List<double[]> vektorV) {
//        String sql = "INSERT INTO vektor_v (no_id, jumlah) VALUES (?, ?)";        
//        try {
//            PreparedStatement stat = conn.prepareStatement(sql);            
//            
//            for (double[] vektor : vektorV) {
//                stat.setDouble(1, vektor[0]);
//                stat.setDouble(2, vektor[1]);
//                stat.addBatch();
//            }
//            stat.executeBatch();                        
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Gagal menyimpan vektor V: " + e.getMessage());
//        }
//    }       
    
    private void simpanVektorV(List<Object[]> vektorV) {
        String sql2 = "TRUNCATE TABLE vektor_V";
        String sql = "INSERT INTO vektor_v (id_pelamar, divisi, jumlah) VALUES (?, ?, ?)";        
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql2);
            
            PreparedStatement stat = conn.prepareStatement(sql);            

            for (Object[] vektor : vektorV) {
                int numericId = (int) vektor[0];
                String completeId = "A" + String.format("%03d", numericId);
                stat.setString(1, completeId);  // Reconstruct the ID with the letter "A" prefix
                stat.setString(2, (String) vektor[2]);
                stat.setDouble(3, (double) vektor[1]);
                stat.addBatch();
            }
            stat.executeBatch();                        
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Gagal menyimpan vektor V: " + e.getMessage());
        }
    }

//    private void simpanVektorV(List<double[]> vektorV) {
//    String sql = "INSERT INTO vektor_v (no_id, jumlah) VALUES (?, ?)";
//    String sql2 = "INSERT INTO seleksi (no_id, nama, no_hp, hasil_penilaian) VALUES (?, ?, ?, ?)";
//    
//    try {
//        // Prepare statements for batch insert
//        PreparedStatement stat = conn.prepareStatement(sql);
//        PreparedStatement stmt = conn.prepareStatement(sql2);
//
//        // Insert into vektor_v
//        for (double[] vektor : vektorV) {
//            stat.setDouble(1, vektor[0]);
//            stat.setDouble(2, vektor[1]);
//            stat.addBatch();
//        }
//        stat.executeBatch();
//
//        // Insert into seleksi
//        for (double[] vektor : vektorV) {
//            String sql3 = "SELECT no_id, nama, no_hp FROM calon_pelamar WHERE no_id = ?";
//            PreparedStatement stnt = conn.prepareStatement(sql3);
//            stnt.setDouble(1, vektor[0]);
//            ResultSet noob = stnt.executeQuery();
//            
////            ResultSetMetaData rsmd = (ResultSetMetaData) noob.getMetaData();
////            int columnsNumber = rsmd.getColumnCount();
////            for (int i = 1; i <= columnsNumber; i++) {
////                System.out.println("Column " + i + ": " + rsmd.getColumnName(i));
////            }
//
//            // Retrieve data for each no_id and add to batch for seleksi
//            while (noob.next()) {
//                String nama = noob.getString("nama");
//                String no_hp = noob.getString("no_hp");
//
//                stmt.setDouble(1, vektor[0]);
//                stmt.setString(2, nama);
//                stmt.setString(3, no_hp);
//                stmt.setDouble(4, vektor[1]);
//                stmt.addBatch();
//            }
//        }
//        stmt.executeBatch();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Gagal menyimpan vektor V: " + e.getMessage());
//        }
//    }


}
