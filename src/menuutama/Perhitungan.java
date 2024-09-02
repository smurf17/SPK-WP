
package menuutama;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;

/**
 *
 * @author alfar
 */
public class Perhitungan extends javax.swing.JPanel {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    protected KriteriaWp pKriteria = new KriteriaWp(); 
    protected  String noId;
    /**
     * Creates new form Seleksi
     */
    public Perhitungan() {
        initComponents();
//        dataTabel();
        dataTabelKriteria();
        dataFromDataBaseToComboBox();
    }
    
    
    protected void dataTabelPelamar(){
        String x = cbDiv.getSelectedItem().toString();
        Object[] Baris = {"Kode Pelamar", "Nama", "No Hp", "Divisi Dituju"};
        tabmode = new DefaultTableModel(null, Baris);
        tabelPelamar.setModel(tabmode);

        String sql = "SELECT * FROM calon_pelamar WHERE divisi='"+ x +"'";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            int i = 1;
            while(hasil.next()){
                String a = hasil.getString("id_pelamar");
                String b = hasil.getString("nama");
                String c = hasil.getString("no_hp");
                String d = hasil.getString("divisi");                                
                
                String[] data={a, b, c, d};
                tabmode.addRow(data);
                i++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    protected void dataTabelKriteria(){
        Object[] Baris = {"C1", "C2", "C3", "C4"};
        tabmode = new DefaultTableModel(null, Baris);
        tblBobot.setModel(tabmode);

        String sql = "SELECT * FROM bobot_kriteria";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            int i = 1;
            while(hasil.next()){
                String a = hasil.getString("c1");
                String b = hasil.getString("c2");
                String c = hasil.getString("c3");
                String d = hasil.getString("c4");                
                
                String[] data={a, b, c, d,};                
                tabmode.addRow(data);
                i++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void dataFromDataBaseToComboBox(){        
        try {
            String query = "SELECT * FROM lowongan";
            java.sql.Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);

            while (rs.next()) {                
                cbDiv.addItem(rs.getString("divisi"));
//                cbDiv2.addItem(rs.getString("divisi"));
//                    String item = rs.getString("kd_lowongan") + " - " + rs.getString("divisi");
//                    cbDiv.addItem(item);
            }

            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();

        } catch (SQLException e) {
        }
        
    }
    
//    protected void dataTabel(){
//        Object[] Baris = {"Ranking","No.ID","Nama Calon Pelamar","No. HP","Hasil Penilaian"};
//        tabmode = new DefaultTableModel(null, Baris);
//        tabelRanking.setModel(tabmode);
//
//        String sql = "SELECT v.no_id, cp.nama, cp.no_hp, v.jumlah AS hasil_penilaian " +
//                     "FROM vektor_v v " +
//                     "JOIN calon_pelamar cp ON v.no_id = cp.no_id " +
//                     "ORDER BY v.jumlah DESC";
//        try{
//            java.sql.Statement stat = conn.createStatement();
//            ResultSet hasil = stat.executeQuery(sql);
//            int i = 1;
//            while(hasil.next()){
//                String a = Integer.toString(i);
//                String b = hasil.getString("no_id");
//                String c = hasil.getString("nama");
//                String d = hasil.getString("no_hp");
//                String e = hasil.getString("hasil_penilaian");
//                
//                String[] data={a, b, c, d, e};
//                tabmode.addRow(data);
//                i++;
//            }
//        }catch(SQLException e){
//            JOptionPane.showMessageDialog(null,e);
//        }
//    }
    
    protected void kosong(){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] {"Kode Pelamar", "Nama", "No Hp", "Divisi Dituju"});
        tabelPelamar.setModel(model);
        DefaultTableModel modelS = new DefaultTableModel();
        modelS.setColumnIdentifiers(new String[] {"Kode Pelamar", "Divisi", "C1", "C2", "C3", "C4", "Jumlah"});
        tblVektorS.setModel(modelS);
        DefaultTableModel modelV = new DefaultTableModel();
        modelV.setColumnIdentifiers(new String[] {"Kode Pelamar", "Vektor V"});
        tblVektorV.setModel(modelV);
        DefaultTableModel modelRanking = new DefaultTableModel();
        modelRanking.setColumnIdentifiers(new String[] {"Ranking","Kode Pelamar", "Nilai"});
        tblRanking.setModel(modelRanking);
    }
    
    protected void getDataTabel(){
        int bar = tabelPelamar.getSelectedRow();

        if (bar != -1) {
            Object valueA = tabmode.getValueAt(bar, 0);
            Object valueB = tabmode.getValueAt(bar, 1);
            Object valueC = tabmode.getValueAt(bar, 2);

            String a = (valueA != null) ? valueA.toString() : "";
            String b = (valueB != null) ? valueB.toString() : "";
            String c = (valueC != null) ? valueC.toString() : "";
            
            try{
                String sql = "INSERT INTO seleksi (id_pelamar, nama_pelamar, hasil_penilaian) VALUES (?,?,?)";
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, a);
                stat.setString(2, b);
                stat.setString(3, c);
                stat.executeUpdate();                
                JOptionPane.showMessageDialog(null, "DATA Berhasil Disimpan");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
            }
        }
    }
    
    protected void dataTabelVektorS(){
        String x = cbDiv.getSelectedItem().toString();
        Object[] Baris = {"Kode Pelamar", "Divisi", "C1", "C2", "C3", "C4", "Jumlah"};
        tabmode = new DefaultTableModel(null, Baris);
        tblVektorS.setModel(tabmode);

        String sql = "SELECT * FROM vektor_s WHERE divisi='"+ x +"'";
        
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            int i = 1;
            while(hasil.next()){
                String a = hasil.getString("id_pelamar");
                String b = hasil.getString("divisi");
                String c = hasil.getString("c1");
                String d = hasil.getString("c2");
                String e = hasil.getString("c3");                
                String f = hasil.getString("c4");                
                String g = hasil.getString("jumlah");                
                
                String[] data={a, b, c, d, e, f, g};                
                tabmode.addRow(data);
                i++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    protected void dataTabelVektorV(){
        String x = cbDiv.getSelectedItem().toString();
        Object[] Baris = {"Kode Pelamar", "Jumlah"};
        tabmode = new DefaultTableModel(null, Baris);
        tblVektorV.setModel(tabmode);

        String sql = "SELECT * FROM vektor_v WHERE divisi='"+ x +"'";
        
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            int i = 1;
            while(hasil.next()){
                String a = hasil.getString("id_pelamar");                                
                String b = hasil.getString("jumlah");                
                
                String[] data={a, b};                
                tabmode.addRow(data);
                i++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    protected void dataTabelRanking(){
        String x = cbDiv.getSelectedItem().toString();
        Object[] Baris = {"Ranking", "Kode Pelamar", "Nilai"};
        tabmode = new DefaultTableModel(null, Baris);
        tblRanking.setModel(tabmode);

        String sql = "SELECT * FROM vektor_v WHERE divisi='"+ x +"' ORDER BY jumlah DESC";
        
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            int i = 1;
            while(hasil.next()){
                String a = Integer.toString(i);
                String b = hasil.getString("id_pelamar");                                
                String c = hasil.getString("jumlah");                
                
                String[] data={a, b, c};                
                tabmode.addRow(data);
                i++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        judul = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbDiv = new javax.swing.JComboBox<>();
        btnPilih = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPelamar = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBobot = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVektorS = new javax.swing.JTable();
        btnVektorS = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblVektorV = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRanking = new javax.swing.JTable();
        btnVektorV = new javax.swing.JButton();
        btnRanking = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        judul.setBackground(new java.awt.Color(255, 255, 255));
        judul.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        judul.setForeground(new java.awt.Color(0, 51, 153));
        judul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judul.setText("  Perhitungan Data Pelamar");
        judul.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Lowongan Divisi  Yang Ingin Diolah :");

        cbDiv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDivItemStateChanged(evt);
            }
        });
        cbDiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDivActionPerformed(evt);
            }
        });

        btnPilih.setText("Pilih");
        btnPilih.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPilihMouseClicked(evt);
            }
        });

        tabelPelamar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Pelamar", "Nama", "No Hp", "Divisi Dituju"
            }
        ));
        tabelPelamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPelamarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPelamar);

        tblBobot.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "C1", "C2", "C3", "C4"
            }
        ));
        jScrollPane2.setViewportView(tblBobot);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Bobot");

        tblVektorS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Pelamar", "Divisi", "C1", "C2", "C3", "C4", "Jumlah"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblVektorS);

        btnVektorS.setText("Hitung Vektor S");
        btnVektorS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVektorSMouseClicked(evt);
            }
        });

        tblVektorV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Kode Pelamar", "Vektor V"
            }
        ));
        jScrollPane4.setViewportView(tblVektorV);

        tblRanking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ranking", "Kode Pelamar", "Nilai"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblRanking);

        btnVektorV.setText("HItung Vektor V");
        btnVektorV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVektorVMouseClicked(evt);
            }
        });

        btnRanking.setText("Ranking");
        btnRanking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRankingMouseClicked(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbDiv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPilih)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 345, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnVektorS)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVektorV))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnRanking)
                                .addGap(124, 124, 124)
                                .addComponent(btnReset))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbDiv, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPilih)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVektorS)
                    .addComponent(btnVektorV)
                    .addComponent(btnRanking)
                    .addComponent(btnReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(judul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbDivItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDivItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDivItemStateChanged

    private void cbDivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDivActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDivActionPerformed

    private void btnPilihMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPilihMouseClicked
        // TODO add your handling code here:
        dataTabelPelamar();
    }//GEN-LAST:event_btnPilihMouseClicked

    private void tabelPelamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPelamarMouseClicked
        // TODO add your handling code here:
//        kosong();
//        getDataTabel();
    }//GEN-LAST:event_tabelPelamarMouseClicked

    private void btnVektorSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVektorSMouseClicked
        // TODO add your handling code here:
        Vektor v = new Vektor();
        v.olahData();
        dataTabelVektorS();
    }//GEN-LAST:event_btnVektorSMouseClicked

    private void btnVektorVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVektorVMouseClicked
        // TODO add your handling code here:
        dataTabelVektorV();
    }//GEN-LAST:event_btnVektorVMouseClicked

    private void btnRankingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRankingMouseClicked
        // TODO add your handling code here:
        dataTabelRanking();
    }//GEN-LAST:event_btnRankingMouseClicked

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_btnResetMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPilih;
    private javax.swing.JButton btnRanking;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnVektorS;
    private javax.swing.JButton btnVektorV;
    private javax.swing.JComboBox<String> cbDiv;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel judul;
    private javax.swing.JTable tabelPelamar;
    private javax.swing.JTable tblBobot;
    private javax.swing.JTable tblRanking;
    private javax.swing.JTable tblVektorS;
    private javax.swing.JTable tblVektorV;
    // End of variables declaration//GEN-END:variables
}
