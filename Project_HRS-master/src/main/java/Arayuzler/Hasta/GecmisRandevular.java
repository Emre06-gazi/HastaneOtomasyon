/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arayuzler.Hasta;

import DB.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class GecmisRandevular extends javax.swing.JPanel {

    private Long kullanici;
    
    public GecmisRandevular() {
        initComponents();
    }
    
    public void baslat(){
        jTable1.getTableHeader().setResizingAllowed(false);
        jButton1MouseClicked(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Hastane", "Klinik", "Muayene Yeri", "Doktor", "Tarih"
            }
        ));
        jTable1.setRowHeight(23);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

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

        jButton3.setText("Sil");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        String kelime = deleteSpaces(jTextField1.getText());
        delRows();
        String sorgu;
        if(kelime.length() == 0){
            sorgu = "SELECT hastaneadi, klinikadi, klinikno, doktoradsoyad, randevutarihi FROM randevubilgilerigecmis(?,?) ORDER BY randevutarihi";
        }
        else{
            sorgu = "SELECT hastaneadi, klinikadi, klinikno, doktoradsoyad, randevutarihi FROM randevubilgilerigecmis(?,?) WHERE hastaneadi LIKE ? OR klinikadi LIKE ? OR CONCAT(klinikno,'') LIKE ? OR doktoradsoyad LIKE ? OR TO_CHAR(randevutarihi, 'DD-MM-YYYY HH24:MI') LIKE ? ORDER BY randevutarihi";
        }
        Query db = new Query();
        Connection con = db.getCon();
        try {
            PreparedStatement stmt = con.prepareStatement(sorgu);
            stmt.setLong(1, kullanici);
            Tarih trh = new Tarih();
            stmt.setString(2, trh.bugunkiTarihFromDateSaatIle());
            if(kelime.length() != 0){
                stmt.setString(3, "%"+kelime+"%");
                stmt.setString(4, "%"+kelime+"%");
                stmt.setString(5, "%"+kelime+"%");
                stmt.setString(6, "%"+kelime+"%");
                stmt.setString(7, "%"+kelime+"%");
            }
            ResultSet result = stmt.executeQuery();
            DefaultTableModel table = (DefaultTableModel) jTable1.getModel();
            while(result.next()){
                String[] tarihtut = result.getTimestamp("randevutarihi").toString().split(":");
                String[] tarihtut2 = tarihtut[0].split(" ")[0].split("-");
                table.addRow(new Object[] {result.getString("hastaneadi"), result.getString("klinikadi"), String.valueOf(result.getInt("klinikno")), result.getString("doktoradsoyad"), tarihtut2[2]+"-"+tarihtut2[1]+"-"+tarihtut2[0]+" "+tarihtut[0].split(" ")[1]+":"+tarihtut[1]});
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        jButton1MouseClicked(null);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTextField1.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

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
    
    public void setKullanici(Long kullanici){
        this.kullanici = kullanici;
    }
    
    protected void setZero(){
        jTextField1.setText("");
        jButton1MouseClicked(null);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
