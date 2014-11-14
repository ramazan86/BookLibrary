/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookrental;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author stefano
 */
public class Rent extends javax.swing.JFrame {

    Book movie;
    User user;
    static int previous = 0;

    public Rent(User obj, Book obj2) throws MalformedURLException, SQLException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        user = obj;
        movie = obj2;
        Date now = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);         // add 5 days to calendar instance
        if (!movie.getDeadline().equals("")) {
            calendar.add(Calendar.DAY_OF_MONTH, Integer.valueOf(movie.getDeadline()) + 2);
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, 2);
        }

        Date future = calendar.getTime(); // get the date instance
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");         // print out the dates...

        jLabelDeadline.setText(dateFormat.format(future));
        jLabelBild.setIcon(new ImageIcon(new URL(movie.getImglink())));
        jLabelTitle.setText(movie.getTitle());
        jLabelPrice.setText(movie.getPrice() + "€");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelBild = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelTitle = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButtonRent = new javax.swing.JButton();
        jButtonReturn = new javax.swing.JButton();
        jCheckGTC = new javax.swing.JCheckBox();
        jCheckDirectDebitPayment = new javax.swing.JCheckBox();
        jLabelDeadline = new javax.swing.JLabel();
        jLabelPrice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movierental/Logo.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel5.setText("Rent");

        jLabelTitle.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabelTitle.setText("Turbo - Kleine Schnecke, großer Traum");

        jLabel6.setText("Deadline: ");

        jLabel7.setText("Price:");

        jButtonRent.setText("Rent");
        jButtonRent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRentActionPerformed(evt);
            }
        });

        jButtonReturn.setText("Return");
        jButtonReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturnActionPerformed(evt);
            }
        });

        jCheckGTC.setText("GTC");

        jCheckDirectDebitPayment.setText("Direct Debit Payment");

        jLabelDeadline.setText("jLabel4");

        jLabelPrice.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jCheckDirectDebitPayment)
                                    .addComponent(jCheckGTC)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(147, 147, 147)
                                        .addComponent(jLabel3))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelBild, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabelDeadline, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                            .addComponent(jLabelPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(89, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonReturn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRent)
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTitle)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabelDeadline))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabelPrice)))
                    .addComponent(jLabelBild, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckGTC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckDirectDebitPayment)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRent)
                    .addComponent(jButtonReturn))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturnActionPerformed
        if (evt.getSource() == jButtonReturn) {
            dispose();
            if (Rent.previous == 0) {
                try {
                    new MovieInfo(user, movie).setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Rent.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    new VideoLibrary(user).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Rent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButtonReturnActionPerformed

    private void jButtonRentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRentActionPerformed

        Verbindung db = new Verbindung();
        db.start();
        Connection conn = db.getVerbindung();
        if (jCheckDirectDebitPayment.isSelected() && jCheckGTC.isSelected()) {
            JOptionPane.setDefaultLocale(Locale.ENGLISH);
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select *,DATEDIFF(deadline,now()) as deadlinex from rents where uid = '" + user.getUid() + "' and mid = '" + movie.getMid() + "'");
                if (rs.next()) {
                    Statement stmtupdate = conn.createStatement();
                    if (Integer.parseInt(rs.getString("deadlinex")) < 0) {
                        if (JOptionPane.showConfirmDialog(null, "Do you want to rent the movie " + movie.getTitle() + "?") == 0) {
                            stmtupdate.executeUpdate("UPDATE rents SET deadline = (SELECT DATE_ADD( now() , INTERVAL 2 DAY) ), time = now() where uid = '" + user.getUid() + "' and mid = '" + movie.getMid() + "'");
                            JOptionPane.showMessageDialog(null, "Congratulations! You can now watch the movie in your video library.");
                            dispose();
                            new VideoLibrary(user).setVisible(true);
                        }
                    } else {
                        if (JOptionPane.showConfirmDialog(null, "Do you want to extend the Deadline of the movie " + movie.getTitle() + "?") == 0) {
                            stmtupdate.executeUpdate("UPDATE rents SET deadline = (SELECT DATE_ADD( deadline , INTERVAL 2 DAY) ), time = now() where uid = '" + user.getUid() + "' and mid = '" + movie.getMid() + "'");
                            JOptionPane.showMessageDialog(null, "Congratulations! You have extended the deadline for two days.");
                            dispose();
                            new VideoLibrary(user).setVisible(true);
                        }
                    }
                } else {
                    if (JOptionPane.showConfirmDialog(null, "Do you want to rent the movie " + movie.getTitle() + "?") == 0) {
                        Statement stmtinsert = conn.createStatement();
                        stmt.executeUpdate("INSERT INTO rents (uid, mid, deadline, time) VALUES ('" + user.getUid() + "', '" + movie.getMid() + "', (SELECT DATE_ADD( {fn curdate()} , INTERVAL 2 DAY)), now() )");
                        JOptionPane.showMessageDialog(null, "Congratulations! You can now watch the movie in your video library.");
                        dispose();
                        new VideoLibrary(user).setVisible(true);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Rent.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please accept the Direct Debit Payment and the GTC!");
        }

    }//GEN-LAST:event_jButtonRentActionPerformed

    public static void setPrevious(int previous) {
        Rent.previous = previous;
    }

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
            java.util.logging.Logger.getLogger(Rent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRent;
    private javax.swing.JButton jButtonReturn;
    private javax.swing.JCheckBox jCheckDirectDebitPayment;
    private javax.swing.JCheckBox jCheckGTC;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelBild;
    private javax.swing.JLabel jLabelDeadline;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabelTitle;
    // End of variables declaration//GEN-END:variables
}
