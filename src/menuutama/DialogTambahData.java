
package menuutama;
import com.mysql.jdbc.Statement;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JRootPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;

/**
 *
 * @author alfar
 */
public class DialogTambahData extends javax.swing.JDialog {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    private String kode;
    
    /**
     * Creates new form DialogTambahData
     */
    public DialogTambahData(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //add Panel, add panel(sebuah panel)
        Pane.add(PanelKandidat);
        Pane.repaint();
        Pane.revalidate();
        dataFromDataBaseToComboBox();
    }
    
    public void dataFromDataBaseToComboBox(){
        try {
            String query = "SELECT * FROM lowongan";
            java.sql.Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            
            while (rs.next()) {                
                cbDiv.addItem(rs.getString("divisi"));
            }
            
            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();
            
        } catch (SQLException e) {
        }
    }
    
    
    public void setDataTabel(String id, String nama, String jekel, String noHp, String alamat, String divisi){
        tNoId.setText(id);
        tNama.setText(nama);
        if(jekel.equals("Laki-Laki")){
            rbLaki.setSelected(true);
            rbPerempuan.setSelected(false);
        }else if(jekel.equals("Perempuan")){
            rbLaki.setSelected(false);
            rbPerempuan.setSelected(true);
        }
        tNoHp.setText(noHp);
        tAlamat.setText(alamat);
        cbDiv.setSelectedItem(divisi);
    }
    
    protected void kosong(){
        tNoId.setText("");
        tNama.setText("");
        btnG.clearSelection();
        tAlamat.setText("");
        tNoHp.setText("");
        cbDiv.setSelectedIndex(0);
    }
    
    private void insertDataCalonPelamar(){        
        String sql = "INSERT INTO calon_pelamar (id_pelamar, nama, jeniskel, no_hp, alamat, divisi) VALUES (?,?,?,?,?,?)";
//        String sqlBobotKandidat = "INSERT INTO bobot_kandidat (id_pelamar, kd_lowongan, pendidikan_terakhir, nilai_wawancara, pengalaman_kerja, sikap) VALUES (?,?,?,?,?,?)";
        String sqlLowongan = "SELECT * FROM lowongan WHERE divisi=?";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, tNoId.getText());
                stat.setString(2, tNama.getText());

                String jkel = "";
                if(rbLaki.isSelected()){
                    jkel="Laki-Laki";
                }else if(rbPerempuan.isSelected()){
                    jkel="Perempuan";
                }
                stat.setString(3, jkel);
                stat.setString(4, tNoHp.getText());
                stat.setString(5, tAlamat.getText());
                stat.setString(6, cbDiv.getSelectedItem().toString());
                stat.executeUpdate();                                                

//                PreparedStatement statBobotKandidat = conn.prepareStatement(sqlBobotKandidat);
//                PreparedStatement statLowongan = conn.prepareStatement(sqlLowongan);
//                statLowongan.setString(1, cbDiv.getSelectedItem().toString());
//                ResultSet hasil = statLowongan.executeQuery();
//                String kode = null;
//                if (hasil.next()) {
//                    kode = hasil.getString("kd_lowongan");
//                }
//                
//                 if (kode != null) {
//                    PreparedStatement statBobotKandidat = conn.prepareStatement(sqlBobotKandidat);
//                    statBobotKandidat.setString(1, tNoId.getText());
//                    statBobotKandidat.setString(2, kode);
//                    statBobotKandidat.setString(3, nilaiPendidikan);
//                    statBobotKandidat.setString(4, nilaiWawancara);
//                    statBobotKandidat.setString(5, nilaiPengalaman);
//                    statBobotKandidat.setString(6, nilaiSikap);
//                    statBobotKandidat.executeUpdate();
//
//                    JOptionPane.showMessageDialog(null, "DATA Berhasil Disimpan");
//                    kosong();
//                    tNoId.requestFocus();
//                } else {
//                    JOptionPane.showMessageDialog(null, "Kode lowongan tidak ditemukan");
//                }
//                statBobotKandidat.setString(1, tNoId.getText());            
//                statBobotKandidat.setString(2, kode);
//                statBobotKandidat.setString(3, nilaiPendidikan);
//                statBobotKandidat.setString(4, nilaiWawancara);
//                statBobotKandidat.setString(5, nilaiPengalaman);
//                statBobotKandidat.setString(6, nilaiSikap);
//                statBobotKandidat.executeUpdate();
//                
//                JOptionPane.showMessageDialog(null, "DATA Berhasil Disimpan");
//                kosong();
//                tNoId.requestFocus();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan Biodata"+e);
            }
    }
    
    private void editDataCalonPelamar(){
        try{
            String sql = "UPDATE calon_pelamar set nama=?, jeniskel=?, no_hp=?, alamat=? WHERE id_pelamar=?";
//            String sqlBobotKandidat = "UPDATE bobot_kandidat set pendidikan_terakhir=?, nilai_wawancara=?, pengalaman_kerja=?, sikap=? WHERE no_id=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, tNama.getText());
            String jkel = "";
                if(rbLaki.isSelected()){
                    jkel="Laki-Laki";
                }else if(rbPerempuan.isSelected()){
                    jkel="Perempuan";
                }
            stat.setString(2, jkel);
            stat.setString(3, tNoHp.getText());
            stat.setString(4, tAlamat.getText());
            stat.setString(5, tNoId.getText());
            stat.executeUpdate();                        

//            PreparedStatement statBobotKandidat = conn.prepareStatement(sqlBobotKandidat);
//            statBobotKandidat.setString(5, tNoId.getText());
//            statBobotKandidat.setString(1, nilaiPendidikan);
//            statBobotKandidat.setString(2, nilaiWawancara);
//            statBobotKandidat.setString(3, nilaiPengalaman);
//            statBobotKandidat.setString(4, nilaiSikap);
//            statBobotKandidat.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Berhasil diUbah ");
            kosong();
            tNoId.requestFocus();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Data Gagal DiUbah " + e);
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

        btnG = new javax.swing.ButtonGroup();
        PanelKandidat = new javax.swing.JPanel();
        tombolEdit = new javax.swing.JLabel();
        judul = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tNama = new javax.swing.JTextField();
        tNoHp = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tAlamat = new javax.swing.JTextArea();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        tNoId = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        cbDiv = new javax.swing.JComboBox<>();
        tombolTambah = new javax.swing.JLabel();
        IsiKosong = new javax.swing.JOptionPane();
        Pane = new javax.swing.JPanel();

        tombolEdit.setBackground(new java.awt.Color(0, 51, 102));
        tombolEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tombolEdit.setForeground(new java.awt.Color(255, 255, 255));
        tombolEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tombolEdit.setText("Edit Data");
        tombolEdit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tombolEdit.setOpaque(true);
        tombolEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tombolEditMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tombolEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tombolEditMouseExited(evt);
            }
        });

        judul.setBackground(new java.awt.Color(253, 5, 5));
        judul.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        judul.setForeground(new java.awt.Color(255, 255, 255));
        judul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        judul.setText("Tambah Data Calon Pelamar");
        judul.setOpaque(true);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Biodata Calon Pelamar"));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Nama");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Jenis Kelamin");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Alamat");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("No. Hp");

        tAlamat.setColumns(20);
        tAlamat.setRows(5);
        jScrollPane3.setViewportView(tAlamat);

        btnG.add(rbLaki);
        rbLaki.setText("Laki - Laki");

        btnG.add(rbPerempuan);
        rbPerempuan.setText("Perempuan");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("No. ID");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Divisi Dituju");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(105, 105, 105)
                        .addComponent(tNoId))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel27))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tNama)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                            .addComponent(tNoHp)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(rbLaki)
                                .addGap(18, 18, 18)
                                .addComponent(rbPerempuan)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbDiv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tNoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rbLaki)
                    .addComponent(rbPerempuan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(cbDiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        tombolTambah.setBackground(new java.awt.Color(0, 51, 102));
        tombolTambah.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tombolTambah.setForeground(new java.awt.Color(255, 255, 255));
        tombolTambah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tombolTambah.setText("Tambah Data");
        tombolTambah.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tombolTambah.setOpaque(true);
        tombolTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tombolTambahMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tombolTambahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tombolTambahMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelKandidatLayout = new javax.swing.GroupLayout(PanelKandidat);
        PanelKandidat.setLayout(PanelKandidatLayout);
        PanelKandidatLayout.setHorizontalGroup(
            PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(judul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelKandidatLayout.createSequentialGroup()
                .addGroup(PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKandidatLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelKandidatLayout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(tombolTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tombolEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        PanelKandidatLayout.setVerticalGroup(
            PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKandidatLayout.createSequentialGroup()
                .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tombolTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tombolEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Pane.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(888, 577));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tombolTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolTambahMouseClicked
        // TODO add your handling code here:
        if(!tNama.getText().equals("") && btnG.getSelection() != null && !tAlamat.getText().equals("") && !tNoHp.getText().equals("")){    
            insertDataCalonPelamar();
            dispose();
        }else{
            IsiKosong.showMessageDialog(rootPane, "Mohon isi semua kolom isian pada form !", "Error", ERROR_MESSAGE );
        }
    }//GEN-LAST:event_tombolTambahMouseClicked

    private void tombolTambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolTambahMouseEntered
        // TODO add your handling code here:
        tombolTambah.setBackground(new Color(0,51,153));
        tombolTambah.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_tombolTambahMouseEntered

    private void tombolTambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolTambahMouseExited
        // TODO add your handling code here:
        tombolTambah.setBackground(new Color(0,51,102));
    }//GEN-LAST:event_tombolTambahMouseExited

    private void tombolEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolEditMouseClicked
        // TODO add your handling code here:
        editDataCalonPelamar();
        dispose();
    }//GEN-LAST:event_tombolEditMouseClicked

    private void tombolEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolEditMouseEntered
        // TODO add your handling code here:
        tombolEdit.setBackground(new Color(0,51,153));
        tombolEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_tombolEditMouseEntered

    private void tombolEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolEditMouseExited
        // TODO add your handling code here:
        tombolEdit.setBackground(new Color(0,51,102));
    }//GEN-LAST:event_tombolEditMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogTambahData dialog = new DialogTambahData(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JOptionPane IsiKosong;
    private javax.swing.JPanel Pane;
    private javax.swing.JPanel PanelKandidat;
    private javax.swing.ButtonGroup btnG;
    private javax.swing.JComboBox<String> cbDiv;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel judul;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTextArea tAlamat;
    private javax.swing.JTextField tNama;
    private javax.swing.JTextField tNoHp;
    private javax.swing.JTextField tNoId;
    private javax.swing.JLabel tombolEdit;
    private javax.swing.JLabel tombolTambah;
    // End of variables declaration//GEN-END:variables

    void show(JRootPane rootPane) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
