/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arayuzler.Hasta;

/**
 *
 * @author MTxMCT
 */
public class HastaArayuz extends javax.swing.JFrame {

    private Long kullanici;
    public HastaArayuz() {
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

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        randevuAl1 = new Arayuzler.Hasta.RandevuAl();
        ileriRandevular1 = new Arayuzler.Hasta.IleriRandevular();
        gecmisRandevular1 = new Arayuzler.Hasta.GecmisRandevular();
        tahliler1 = new Arayuzler.Hasta.Tahlil();
        bilgilerim1 = new Arayuzler.Hasta.Bilgilerim();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(954, 616));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });
        jTabbedPane1.addTab("               Randevu Al               ", randevuAl1);
        jTabbedPane1.addTab("               �leri Randevular�m               ", ileriRandevular1);
        jTabbedPane1.addTab("               Ge�mi� Randevular�m                ", gecmisRandevular1);
        jTabbedPane1.addTab("               Tahlillerim               ", tahliler1);
        jTabbedPane1.addTab("               Bilgilerim               ", bilgilerim1);

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setText("��k��");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        int index = jTabbedPane1.getSelectedIndex();
        if(index == 0){
            randevuAl1.setZero();
        }
        else if(index == 1){
            ileriRandevular1.setZero();
        }
        else if(index == 2){
            gecmisRandevular1.setZero();
        }
        else if(index == 3){
            tahliler1.setZero();
        }
        else if(index == 4){
            bilgilerim1.setZero();
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    public void setkullanici(Long kullanici){
        this.kullanici = kullanici;
        baslat();
    }
    
    protected void baslat(){
        randevuAl1.setKullanici(kullanici);
        ileriRandevular1.setKullanici(kullanici);
        gecmisRandevular1.setKullanici(kullanici);
        tahliler1.setKullanici(kullanici);
        bilgilerim1.setKullanici(kullanici);
        randevuAl1.baslat();
        ileriRandevular1.baslat();
        gecmisRandevular1.baslat();
        tahliler1.baslat();
        bilgilerim1.baslat();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Arayuzler.Hasta.Bilgilerim bilgilerim1;
    private Arayuzler.Hasta.GecmisRandevular gecmisRandevular1;
    private Arayuzler.Hasta.IleriRandevular ileriRandevular1;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private Arayuzler.Hasta.RandevuAl randevuAl1;
    private Arayuzler.Hasta.Tahlil tahliler1;
    // End of variables declaration//GEN-END:variables
}
