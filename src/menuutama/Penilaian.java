
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
public class Penilaian extends javax.swing.JPanel {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    protected KriteriaWp pKriteria = new KriteriaWp(); 
    protected  String noId;
    /**
     * Creates new form Penilaian
     */
    public Penilaian() {
        initComponents();
        dataFromDataBaseToComboBox();
//        dataTabelS();
        dataTabelPenilaian();
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
    protected void dataTabelPenilaian(){
        Object[] Baris = {"Kode Pelamar", "Nama", "No HP", "Divisi Dituju", "Pendidikan Terakhir", "Nilai Wawancara", "Pengalaman Kerja", "Sikap"};
        tabmode = new DefaultTableModel(null, Baris);
        tabelPenilaian.setModel(tabmode);

        String sql = "SELECT * FROM bobot_kandidat";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            int i = 1;
            while(hasil.next()){
                String a = hasil.getString("id_pelamar");
                String b = hasil.getString("nama_pelamar");
                String c = hasil.getString("no_hp");
                String d = hasil.getString("divisi");
                String e = hasil.getString("c1");
                String f = hasil.getString("c2");
                String g = hasil.getString("c3");
                String h = hasil.getString("c4");
                
                String[] data={a, b, c, d, e, f, g, h};                
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
                cbDiv2.addItem(rs.getString("divisi"));
//                    String item = rs.getString("kd_lowongan") + " - " + rs.getString("divisi");
//                    cbDiv.addItem(item);
            }

            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();

        } catch (SQLException e) {
        }
        
    }
    
    public void dataCBpenilaian(){        
        try {
            String x = cbDiv2.getSelectedItem().toString();
            String kdKriteriaC1 = c1.getText();
            String kdKriteriaC2 = c2.getText();
            String kdKriteriaC3 = c3.getText();
            String kdKriteriaC4 = c4.getText();
            String query = "SELECT * FROM subkriteria WHERE kd_lowongan LIKE ? AND kd_kriteria LIKE ?";
            String query2 = "SELECT * FROM subkriteria WHERE kd_lowongan LIKE ? AND kd_kriteria LIKE ?";
            String query3 = "SELECT * FROM subkriteria WHERE kd_lowongan LIKE ? AND kd_kriteria LIKE ?";
            String query4 = "SELECT * FROM subkriteria WHERE kd_lowongan LIKE ? AND kd_kriteria LIKE ?";
            PreparedStatement stat = conn.prepareStatement(query);
            PreparedStatement stat2 = conn.prepareStatement(query2);
            PreparedStatement stat3 = conn.prepareStatement(query3);
            PreparedStatement stat4 = conn.prepareStatement(query4);
            stat.setString(1, "%" + x + "%");
            stat2.setString(1, "%" + x + "%");
            stat3.setString(1, "%" + x + "%");
            stat4.setString(1, "%" + x + "%");
            stat.setString(2, "%" + kdKriteriaC1 + "%");
            stat2.setString(2, "%" + kdKriteriaC2 + "%");
            stat3.setString(2, "%" + kdKriteriaC3 + "%");
            stat4.setString(2, "%" + kdKriteriaC4 + "%");
            ResultSet rs = stat.executeQuery();
            ResultSet rs2 = stat2.executeQuery();
            ResultSet rs3 = stat3.executeQuery();
            ResultSet rs4 = stat4.executeQuery();

            while (rs.next()) {                
                cbPendidikan.addItem(rs.getString("nama_subkriteria"));
            }
            while (rs2.next()) {                
                cbWawancara.addItem(rs2.getString("nama_subkriteria"));
            }
            while (rs3.next()) {                
                cbPengalaman.addItem(rs3.getString("nama_subkriteria"));
            }
            while (rs4.next()) {                
                cbSikap.addItem(rs4.getString("nama_subkriteria"));
            }

            rs.last();
            rs2.last();
            rs3.last();
            rs4.last();
            int jumlahdata = rs.getRow();
            int jumlahdata2 = rs2.getRow();
            int jumlahdata3 = rs3.getRow();
            int jumlahdata4 = rs4.getRow();
            rs.first();
            rs2.first();
            rs3.first();
            rs4.first();

        } catch (SQLException e) {
        }
//        try {
//            String x = cbDiv2.getSelectedItem().toString();
//            String kdKriteriaC2 = c2.getText();
//            String query = "SELECT * FROM subkriteria WHERE kd_lowongan LIKE ? AND kd_kriteria LIKE ?";
//            PreparedStatement stat = conn.prepareStatement(query);
//            stat.setString(1, "%" + x + "%");
//            stat.setString(2, "%" + kdKriteriaC2 + "%");
//            ResultSet rs = stat.executeQuery();
//
//            while (rs.next()) {                
//                cbWawancara.addItem(rs.getString("nama_subkriteria"));
////                    String item = rs.getString("kd_lowongan") + " - " + rs.getString("divisi");
////                    cbLowongan.addItem(item);
//            }
//
//            rs.last();
//            int jumlahdata = rs.getRow();
//            rs.first();
//
//        } catch (SQLException e) {
//        }
//        try {
//            String x = cbDiv2.getSelectedItem().toString();
//            String kdKriteria = c3.getText();
//            String query = "SELECT * FROM subkriteria WHERE kd_lowongan LIKE ? AND kd_kriteria LIKE ?";
//            PreparedStatement stat = conn.prepareStatement(query);
//            stat.setString(1, "%" + x + "%");
//            stat.setString(2, "%" + kdKriteria + "%");
//            ResultSet rs = stat.executeQuery();
//
//            while (rs.next()) {                
//                cbPengalaman.addItem(rs.getString("nama_subkriteria"));
////                    String item = rs.getString("kd_lowongan") + " - " + rs.getString("divisi");
////                    cbLowongan.addItem(item);
//            }
//
//            rs.last();
//            int jumlahdata = rs.getRow();
//            rs.first();
//
//        } catch (SQLException e) {
//        }
//        try {
//            String x = cbDiv2.getSelectedItem().toString();
//            String kdKriteria = c4.getText();
//            String query = "SELECT * FROM subkriteria WHERE kd_lowongan LIKE ? AND kd_kriteria LIKE ?";
//            PreparedStatement stat = conn.prepareStatement(query);
//            stat.setString(1, "%" + x + "%");
//            stat.setString(2, "%" + kdKriteria + "%");
//            ResultSet rs = stat.executeQuery();
//
//            while (rs.next()) {                
//                cbSikap.addItem(rs.getString("nama_subkriteria"));
////                    String item = rs.getString("kd_lowongan") + " - " + rs.getString("divisi");
////                    cbLowongan.addItem(item);
//            }
//
//            rs.last();
//            int jumlahdata = rs.getRow();
//            rs.first();
//
//        } catch (SQLException e) {
//        }
    }
    
    protected void getDataTabel() {        
        int bar = tabelPelamar.getSelectedRow();

        if (bar != -1) {
            Object valueA = tabmode.getValueAt(bar, 0);
            Object valueB = tabmode.getValueAt(bar, 1);
            Object valueC = tabmode.getValueAt(bar, 2);
            Object valueD = tabmode.getValueAt(bar, 3);

            String a = (valueA != null) ? valueA.toString() : "";
            String b = (valueB != null) ? valueB.toString() : "";
            String c = (valueC != null) ? valueC.toString() : "";
            String d = (valueD != null) ? valueD.toString() : "";

            txtKode.setText(a);
            txtNama.setText(b);            
            txtHp.setText(c);
            txtDivisi.setText(d);
        }
    }
    
    protected void kosong(){        
        dataTabelPelamar();
        txtKode.setText("");
        txtNama.setText("");
        txtHp.setText("");
        txtDivisi.setText("");
        txtC1.setText("");
        txtC2.setText("");
        txtC3.setText("");
        txtC4.setText("");
        cbPendidikan.setSelectedIndex(0);
        cbWawancara.setSelectedIndex(0);
        cbPengalaman.setSelectedIndex(0);
        cbSikap.setSelectedIndex(0);
    }
    
    protected void insertDataPenilaian(){
        try{
            String sql = "INSERT INTO bobot_kandidat (id_pelamar, nama_pelamar, no_hp, divisi, c1, c2, c3, c4) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtKode.getText());
            stat.setString(2, txtNama.getText());                
            stat.setString(3, txtHp.getText());                
            stat.setString(4, txtDivisi.getText());
            stat.setString(5, txtC1.getText());
            stat.setString(6, txtC2.getText());
            stat.setString(7, txtC3.getText());
            stat.setString(8, txtC4.getText());
            stat.executeUpdate();                
            JOptionPane.showMessageDialog(null, "DATA Berhasil Disimpan");
            kosong();
            dataTabelPenilaian();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }
    }
    
    protected void editDataPenilaian(){
        String sql = "UPDATE bobot_kandidat SET nama_pelamar=?, no_hp=?, c1=?, c2=?, c3=?, c4=? WHERE id_pelamar=?";
        try{
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtNama.getText());
            stat.setString(2, txtHp.getText());
            stat.setString(3, txtC1.getText());
            stat.setString(4, txtC2.getText());
            stat.setString(5, txtC3.getText());
            stat.setString(6, txtC4.getText());
            stat.setString(7, txtKode.getText());
            stat.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "DATA Berhasil Diubah");
            kosong();
            dataTabelPenilaian();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPelamar = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtHp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDivisi = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPenilaian = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cbDiv = new javax.swing.JComboBox<>();
        btnPilih = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        c1 = new javax.swing.JLabel();
        cbPendidikan = new javax.swing.JComboBox<>();
        txtC1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        c2 = new javax.swing.JLabel();
        cbWawancara = new javax.swing.JComboBox<>();
        txtC2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        c3 = new javax.swing.JLabel();
        cbPengalaman = new javax.swing.JComboBox<>();
        txtC3 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbSikap = new javax.swing.JComboBox<>();
        c4 = new javax.swing.JLabel();
        txtC4 = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        cbDiv2 = new javax.swing.JComboBox<>();

        judul.setBackground(new java.awt.Color(255, 255, 255));
        judul.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        judul.setForeground(new java.awt.Color(0, 51, 153));
        judul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judul.setText("  Penilaian Data Pelamar");
        judul.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Nama Pelamar");

        jLabel3.setText("Kode Pelamar ");

        jLabel2.setText("No. HP");

        txtKode.setEditable(false);

        txtNama.setEditable(false);

        txtHp.setEditable(false);

        jLabel4.setText("Divisi Dituju");

        txtDivisi.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHp)
                            .addComponent(txtDivisi)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKode, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                            .addComponent(txtNama))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtDivisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabelPenilaian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Pelamar", "Nama", "No HP", "Divisi Dituju", "C1", "C2", "C3", "C4"
            }
        ));
        tabelPenilaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPenilaianMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelPenilaian);

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

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("Pendidikan Terakhir");

        c1.setText("C1");

        cbPendidikan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbPendidikanItemStateChanged(evt);
            }
        });
        cbPendidikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPendidikanActionPerformed(evt);
            }
        });

        txtC1.setEditable(false);

        jLabel9.setText("Nilai Wawancara");

        c2.setText("C2");

        cbWawancara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbWawancaraActionPerformed(evt);
            }
        });

        txtC2.setEditable(false);

        jLabel11.setText("Pengalaman Kerja");

        c3.setText("C3");

        cbPengalaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPengalamanActionPerformed(evt);
            }
        });

        txtC3.setEditable(false);

        jLabel13.setText("Sikap");

        cbSikap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSikapActionPerformed(evt);
            }
        });

        c4.setText("C4");

        txtC4.setEditable(false);

        btnTambah.setText("Tambah");
        btnTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahMouseClicked(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
        });

        btnHapus.setText("Hapus");

        btnReset.setText("Reset");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(c2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbWawancara, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtC2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(c1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbPendidikan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtC1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(c3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbPengalaman, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtC3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(c4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbSikap, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtC4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(34, 34, 34)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(c1)
                                    .addComponent(cbPendidikan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(c2)
                                    .addComponent(cbWawancara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(c4)
                                .addComponent(cbSikap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtC4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(c3)
                            .addComponent(cbPengalaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(txtC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnEdit)
                    .addComponent(btnHapus)
                    .addComponent(btnReset))
                .addContainerGap())
        );

        cbDiv2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDiv2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(cbDiv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnPilih, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(130, 130, 130))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jLabel6))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 337, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbDiv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cbDiv))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPilih)
                        .addComponent(cbDiv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
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
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tabelPelamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPelamarMouseClicked
        // TODO add your handling code here:
//        kosong();
        getDataTabel();
    }//GEN-LAST:event_tabelPelamarMouseClicked

    private void tabelPenilaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPenilaianMouseClicked
        // TODO add your handling code here:
        kosong();
        int bar = tabelPenilaian.getSelectedRow();

        if (bar != -1) {
            Object valueA = tabmode.getValueAt(bar, 0);
            Object valueB = tabmode.getValueAt(bar, 1);
            Object valueC = tabmode.getValueAt(bar, 2);
            Object valueD = tabmode.getValueAt(bar, 3);
            Object valueE = tabmode.getValueAt(bar, 4);
            Object valueF = tabmode.getValueAt(bar, 5);
            Object valueG = tabmode.getValueAt(bar, 6);
            Object valueH = tabmode.getValueAt(bar, 7);

            String a = (valueA != null) ? valueA.toString() : "";
            String b = (valueB != null) ? valueB.toString() : "";
            String c = (valueC != null) ? valueC.toString() : "";
            String d = (valueD != null) ? valueD.toString() : "";
            String e = (valueE != null) ? valueE.toString() : "";
            String f = (valueF != null) ? valueF.toString() : "";
            String g = (valueG != null) ? valueG.toString() : "";
            String h = (valueH != null) ? valueH.toString() : "";

            txtKode.setText(a);
            txtNama.setText(b);
            txtHp.setText(c);
            txtDivisi.setText(d);
//            cbDiv.setSelectedItem(d);
            txtC1.setText(e);
            txtC2.setText(f);
            txtC3.setText(g);
            txtC4.setText(h);
        }
    }//GEN-LAST:event_tabelPenilaianMouseClicked

    private void btnPilihMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPilihMouseClicked
        // TODO add your handling code here:
        dataTabelPelamar();
    }//GEN-LAST:event_btnPilihMouseClicked

    private void cbDivItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDivItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDivItemStateChanged

    private void cbPendidikanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbPendidikanItemStateChanged
        // TODO add your handling code here:
//        txtC1.setText("");
        
    }//GEN-LAST:event_cbPendidikanItemStateChanged

    private void cbPendidikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPendidikanActionPerformed
        // TODO add your handling code here:
        try {
            String x = cbDiv2.getSelectedItem().toString();
            String kdKriteria = c1.getText();
            String namaSubkriteria = (String) cbPendidikan.getSelectedItem();

            // Check if namaSubkriteria is null
            if (namaSubkriteria != null) {
                String query = "SELECT * FROM subkriteria WHERE kd_lowongan LIKE ? AND kd_kriteria LIKE ? AND nama_subkriteria=?";
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, "%" + x + "%");
                stat.setString(2, "%" + kdKriteria + "%");
                stat.setString(3, namaSubkriteria);
                ResultSet rs = stat.executeQuery();

                if (rs.next()) {
                    String c1 = rs.getString("bobot_sub");
                    txtC1.setText(c1);
                }

                rs.close();
                stat.close();
            } else {
                txtC1.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbPendidikanActionPerformed

    private void cbDivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDivActionPerformed
        // TODO add your handling code here:        
    }//GEN-LAST:event_cbDivActionPerformed

    private void cbWawancaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbWawancaraActionPerformed
        // TODO add your handling code here:
        try {
            String x = cbDiv2.getSelectedItem().toString();
            String kdKriteria = c2.getText();
            String namaSubkriteria = (String) cbWawancara.getSelectedItem();

            // Check if namaSubkriteria is null
            if (namaSubkriteria != null) {
                String query = "SELECT * FROM subkriteria WHERE kd_lowongan LIKE ? AND kd_kriteria LIKE ? AND nama_subkriteria=?";
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, "%" + x + "%");
                stat.setString(2, "%" + kdKriteria + "%");
                stat.setString(3, namaSubkriteria);
                ResultSet rs = stat.executeQuery();

                if (rs.next()) {
                    String c2 = rs.getString("bobot_sub");
                    txtC2.setText(c2);
                }

                rs.close();
                stat.close();
            } else {
                txtC2.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbWawancaraActionPerformed

    private void cbPengalamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPengalamanActionPerformed
        // TODO add your handling code here:
        try {
            String x = cbDiv2.getSelectedItem().toString();
            String kdKriteria = c3.getText();
            String namaSubkriteria = (String) cbPengalaman.getSelectedItem();

            // Check if namaSubkriteria is null
            if (namaSubkriteria != null) {
                String query = "SELECT * FROM subkriteria WHERE kd_lowongan LIKE ? AND kd_kriteria LIKE ? AND nama_subkriteria=?";
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, "%" + x + "%");
                stat.setString(2, "%" + kdKriteria + "%");
                stat.setString(3, namaSubkriteria);
                ResultSet rs = stat.executeQuery();

                if (rs.next()) {
                    String c3 = rs.getString("bobot_sub");
                    txtC3.setText(c3);
                }

                rs.close();
                stat.close();
            } else {
                txtC3.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbPengalamanActionPerformed

    private void cbSikapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSikapActionPerformed
        // TODO add your handling code here:
        try {
            String x = cbDiv2.getSelectedItem().toString();
            String kdKriteria = c4.getText();
            String namaSubkriteria = (String) cbSikap.getSelectedItem();

            // Check if namaSubkriteria is null
            if (namaSubkriteria != null) {
                String query = "SELECT * FROM subkriteria WHERE kd_lowongan LIKE ? AND kd_kriteria LIKE ? AND nama_subkriteria=?";
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, "%" + x + "%");
                stat.setString(2, "%" + kdKriteria + "%");
                stat.setString(3, namaSubkriteria);
                ResultSet rs = stat.executeQuery();

                if (rs.next()) {
                    String c4 = rs.getString("bobot_sub");
                    txtC4.setText(c4);
                }

                rs.close();
                stat.close();
            } else {
                txtC4.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbSikapActionPerformed

    private void btnTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseClicked
        // TODO add your handling code here:
        kosong();
        insertDataPenilaian();
    }//GEN-LAST:event_btnTambahMouseClicked

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        // TODO add your handling code here:
        editDataPenilaian();
    }//GEN-LAST:event_btnEditMouseClicked

    private void cbDiv2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDiv2ActionPerformed
        // TODO add your handling code here:
        cbPendidikan.removeAllItems();
        cbWawancara.removeAllItems();
        cbPengalaman.removeAllItems();
        cbSikap.removeAllItems();
        dataCBpenilaian();
    }//GEN-LAST:event_cbDiv2ActionPerformed

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_btnResetMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnPilih;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel c1;
    private javax.swing.JLabel c2;
    private javax.swing.JLabel c3;
    private javax.swing.JLabel c4;
    private javax.swing.JComboBox<String> cbDiv;
    private javax.swing.JComboBox<String> cbDiv2;
    private javax.swing.JComboBox<String> cbPendidikan;
    private javax.swing.JComboBox<String> cbPengalaman;
    private javax.swing.JComboBox<String> cbSikap;
    private javax.swing.JComboBox<String> cbWawancara;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel judul;
    private javax.swing.JTable tabelPelamar;
    private javax.swing.JTable tabelPenilaian;
    private javax.swing.JTextField txtC1;
    private javax.swing.JTextField txtC2;
    private javax.swing.JTextField txtC3;
    private javax.swing.JTextField txtC4;
    private javax.swing.JTextField txtDivisi;
    private javax.swing.JTextField txtHp;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}
