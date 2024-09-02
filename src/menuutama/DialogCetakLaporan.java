
package menuutama;
import com.mysql.jdbc.Statement;
import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JRootPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author alfar
 */
public class DialogCetakLaporan extends javax.swing.JDialog {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    private String kode;
    
    /**
     * Creates new form DialogTambahData
     */
    public DialogCetakLaporan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //add Panel, add panel(sebuah panel)
        Pane.add(PanelKandidat);
        Pane.repaint();
        Pane.revalidate();
        dataFromDataBaseToComboBox();
    }
    
    protected void dataTabelPelamar() {
        String x = cbDiv.getSelectedItem().toString();
        Object[] Baris = {"Kode Pelamar", "Nama", "Divisi", "Nilai"};
        tabmode = new DefaultTableModel(null, Baris);
        tabelSeleksi.setModel(tabmode);
        try {
            String sql2 = "SELECT kuota FROM lowongan WHERE divisi = ?";
            String sql = "SELECT v.id_pelamar, cp.nama, v.divisi, v.jumlah FROM vektor_v v JOIN lowongan l ON v.divisi = l.divisi JOIN calon_pelamar cp ON v.id_pelamar=cp.id_pelamar WHERE v.divisi = ? ORDER BY v.jumlah DESC LIMIT ?";
            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            stmt2.setString(1, x);
            ResultSet rs = stmt2.executeQuery();
            if (rs.next()) {
                int kuota = rs.getInt("kuota");
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, x);
                stat.setInt(2, kuota);
                ResultSet hasil = stat.executeQuery();
                while (hasil.next()) {
                    String a = hasil.getString("id_pelamar");
                    String b = hasil.getString("nama");
                    String c = hasil.getString("divisi");
                    String d = hasil.getString("jumlah");

                    String[] data = {a, b, c, d};
                    tabmode.addRow(data);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
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
            
    
    protected void kosong(){
        
    }
    
    
    protected void printReportFromTable() {
        try {
            String reportPath = "src/laporan/LaporanHasilSeleksi.jasper"; // Ganti dengan path sebenarnya
            JasperReportFromJTable reportGenerator = new JasperReportFromJTable();
            reportGenerator.generateReport(tabelSeleksi, reportPath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error printing report: " + e.getMessage());
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
        btnCetak = new javax.swing.JLabel();
        judul = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbDiv = new javax.swing.JComboBox<>();
        btnPilih = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelSeleksi = new javax.swing.JTable();
        IsiKosong = new javax.swing.JOptionPane();
        Pane = new javax.swing.JPanel();

        btnCetak.setBackground(new java.awt.Color(0, 51, 102));
        btnCetak.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCetak.setForeground(new java.awt.Color(255, 255, 255));
        btnCetak.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCetak.setText("Cetak");
        btnCetak.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnCetak.setOpaque(true);
        btnCetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCetakMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCetakMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCetakMouseExited(evt);
            }
        });

        judul.setBackground(new java.awt.Color(253, 5, 5));
        judul.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        judul.setForeground(new java.awt.Color(255, 255, 255));
        judul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        judul.setText("Seleksi");
        judul.setOpaque(true);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Pilih Lowongan untuk dicetak"));

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

        tabelSeleksi.setModel(new javax.swing.table.DefaultTableModel(
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
                "Kode Pelamar", "Nama", "Divisi", "Nilai"
            }
        ));
        tabelSeleksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelSeleksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelSeleksi);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(cbDiv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPilih, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(48, 48, 48))
                            .addComponent(jLabel6))
                        .addGap(309, 309, 309))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(6, 6, 6)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cbDiv))
                    .addComponent(btnPilih))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

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
                        .addGap(339, 339, 339)
                        .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        PanelKandidatLayout.setVerticalGroup(
            PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKandidatLayout.createSequentialGroup()
                .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
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

    private void btnCetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseClicked
        // TODO add your handling code here:
        printReportFromTable();
        dispose();
    }//GEN-LAST:event_btnCetakMouseClicked

    private void btnCetakMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseEntered
        // TODO add your handling code here:
        btnCetak.setBackground(new Color(0,51,153));
        btnCetak.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnCetakMouseEntered

    private void btnCetakMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseExited
        // TODO add your handling code here:
        btnCetak.setBackground(new Color(0,51,102));
    }//GEN-LAST:event_btnCetakMouseExited

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

    private void tabelSeleksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelSeleksiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelSeleksiMouseClicked

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
            java.util.logging.Logger.getLogger(DialogCetakLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogCetakLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogCetakLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogCetakLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogCetakLaporan dialog = new DialogCetakLaporan(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel btnCetak;
    private javax.swing.ButtonGroup btnG;
    private javax.swing.JButton btnPilih;
    private javax.swing.JComboBox<String> cbDiv;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel judul;
    private javax.swing.JTable tabelSeleksi;
    // End of variables declaration//GEN-END:variables

    void show(JRootPane rootPane) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
