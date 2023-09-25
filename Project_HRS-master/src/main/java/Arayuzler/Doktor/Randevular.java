package Arayuzler.Doktor;

import DB.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Randevular extends javax.swing.JPanel {

    private Long doktor;
    
    public Randevular() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jCheckBox1.setText("T�m�");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });

        jCheckBox2.setText("�leri");
        jCheckBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox2MouseClicked(evt);
            }
        });

        jCheckBox3.setText("Ge�mi�");
        jCheckBox3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox3MouseClicked(evt);
            }
        });

        jButton1.setText("Arama");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Kimlik Numaras�", "Ad", "Soyad", "Randevu Tarihi"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(132, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        jCheckBox1.setSelected(true);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jCheckBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox2MouseClicked
        jCheckBox2.setSelected(true);
        jCheckBox1.setSelected(false);
        jCheckBox3.setSelected(false);
    }//GEN-LAST:event_jCheckBox2MouseClicked

    private void jCheckBox3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox3MouseClicked
        jCheckBox3.setSelected(true);
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);     
    }//GEN-LAST:event_jCheckBox3MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        String kelime = deleteSpaces(jTextField1.getText());
        delRows();
        String sorgu;
        if(kelime.length() == 0){
            sorgu = "SELECT r.tarih, r.hastano, h.ad, h.soyad FROM randevu r JOIN hasta h ON r.hastano=h.kimlikno AND r.doktorid=?";
        }
        else{
            sorgu = "SELECT r.tarih, r.hastano, h.ad, h.soyad FROM randevu r JOIN hasta h ON r.hastano=h.kimlikno AND r.doktorid=? AND (TO_CHAR(r.tarih, 'DD-MM-YYYY HH24:MI') LIKE ? OR CONCAT(r.hastano,'') LIKE ? OR h.ad LIKE ? OR h.soyad LIKE ?)";
        }
        if(jCheckBox2.isSelected()) {
            sorgu = sorgu + " AND r.tarih > TO_DATE('" + (new Tarih()).bugunkiTarihFromDateSaatIle() + "', 'DD-MM-YYYY HH24:MI') ORDER BY r.tarih";
        }
        else if(jCheckBox3.isSelected()) {
            sorgu = sorgu + " AND r.tarih < TO_DATE('" + (new Tarih()).bugunkiTarihFromDateSaatIle() + "', 'DD-MM-YYYY HH24:MI') ORDER BY r.tarih";
        }
        Query db = new Query();
        Connection con = db.getCon();
        try {
            PreparedStatement stmt = con.prepareStatement(sorgu);
            stmt.setLong(1, doktor);
            if(kelime.length() != 0){
                stmt.setString(2, "%"+kelime+"%");
                stmt.setString(3, "%"+kelime+"%");
                stmt.setString(4, "%"+kelime+"%");
                stmt.setString(5, "%"+kelime+"%");
            }
            ResultSet result = stmt.executeQuery();
            DefaultTableModel table = (DefaultTableModel) jTable1.getModel();
            while(result.next()){
                String[] tarihtut = result.getTimestamp("tarih").toString().split(":");
                String[] tarihtut2 = tarihtut[0].split(" ")[0].split("-");
                table.addRow(new Object[] {String.valueOf(result.getLong("hastano")), result.getString("ad"), result.getString("soyad"), tarihtut2[2]+"-"+tarihtut2[1]+"-"+tarihtut2[0]+" "+tarihtut[0].split(" ")[1]+":"+tarihtut[1]});
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        jButton1MouseClicked(null);
    }//GEN-LAST:event_jTextField1KeyReleased
    
    private String deleteSpaces(String kelime){
        String temp = kelime;
        if(temp.length() != 0){
            int i = 0;
            while(temp.substring(i, i+1).equals(" ")){
                temp = temp.substring(1);
            }
            int t = temp.length();
            while(temp.substring(t-i-1, t-i).equals(" ")){
            i = i+1;
            }
            return temp.substring(0, t);
        }
        else{
            return temp;
        }
    }
    
    private void delRows(){
        DefaultTableModel table = (DefaultTableModel) jTable1.getModel();
        int rowCount = table.getRowCount();
        for(int i = 0; i < rowCount; i++){
            table.removeRow(0);
        }
    }
    
    public void setDoktor(Long doktor){
        this.doktor = doktor;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
