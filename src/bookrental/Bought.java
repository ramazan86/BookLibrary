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
public class Bought extends javax.swing.JFrame {

    Book book;
    User user;
    static int previous = 0;

    public Bought(User obj, Book obj2) throws MalformedURLException, SQLException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        user = obj;
        book = obj2;
        Date now = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);         // add 5 days to calendar instance
     
        Date future = calendar.getTime(); // get the date instance
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");         // print out the dates...

        jLabelDeadline.setText(dateFormat.format(future));
        jLabel_image.setIcon(new ImageIcon(new URL(book.getImglink())));
        jLabel_title.setText(book.getTitle());
        jLabelPrice.setText(book.getPrice() + "€");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_rent = new javax.swing.JLabel();
        jLabel_image = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel_title = new javax.swing.JLabel();
        jLabel_deadline = new javax.swing.JLabel();
        jLabel_price = new javax.swing.JLabel();
        jButton_rent = new javax.swing.JButton();
        jButton_return = new javax.swing.JButton();
        jCheck_GTC = new javax.swing.JCheckBox();
        jCheck_directDebitPayment = new javax.swing.JCheckBox();
        jLabelDeadline = new javax.swing.JLabel();
        jLabelPrice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_rent.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel_rent.setText("Rent");

        jLabel_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/graphicsUderC2.png"))); // NOI18N

        jLabel_title.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel_title.setText("Graphics under C");

        jLabel_deadline.setText("Deadline: ");

        jLabel_price.setText("Price:");

        jButton_rent.setText("Rent");
        jButton_rent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_rentActionPerformed(evt);
            }
        });

        jButton_return.setText("Return");
        jButton_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_returnActionPerformed(evt);
            }
        });

        jCheck_GTC.setText("GTC");

        jCheck_directDebitPayment.setText("Direct Debit Payment");

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
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_rent)
                                    .addComponent(jCheck_directDebitPayment)
                                    .addComponent(jCheck_GTC)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(147, 147, 147)
                                        .addComponent(jLabel3))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_image, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel_price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel_deadline))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabelDeadline, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                            .addComponent(jLabelPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jLabel_title, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(89, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_return)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_rent)
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jLabel_rent)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_title)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_deadline)
                            .addComponent(jLabelDeadline))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_price)
                            .addComponent(jLabelPrice))
                        .addGap(0, 138, Short.MAX_VALUE))
                    .addComponent(jLabel_image, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheck_GTC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheck_directDebitPayment)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_rent)
                    .addComponent(jButton_return))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_returnActionPerformed
        if (evt.getSource() == jButton_return) {
            dispose();
            if (Bought.previous == 0) {
                try {
                    new BookInfo(user, book).setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Bought.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    new BookLibrary(user).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Bought.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButton_returnActionPerformed

    private void jButton_rentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_rentActionPerformed

        Verbindung db = new Verbindung();
        db.start();
        Connection conn = db.getVerbindung();
        if (jCheck_directDebitPayment.isSelected() && jCheck_GTC.isSelected()) {
            JOptionPane.setDefaultLocale(Locale.ENGLISH);
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select *,DATEDIFF(deadline,now()) as deadlinex from rents where uid = '" + user.getUid() + "' and mid = '" + book.getMid() + "'");
                if (rs.next()) {
                    Statement stmtupdate = conn.createStatement();
                    if (Integer.parseInt(rs.getString("deadlinex")) < 0) {
                        if (JOptionPane.showConfirmDialog(null, "Do you want to rent the book " + book.getTitle() + "?") == 0) {
                            stmtupdate.executeUpdate("UPDATE rents SET deadline = (SELECT DATE_ADD( now() , INTERVAL 2 DAY) ), time = now() where uid = '" + user.getUid() + "' and mid = '" + book.getMid() + "'");
                            JOptionPane.showMessageDialog(null, "Congratulations! You can now watch the book in your video library.");
                            dispose();
                            new BookLibrary(user).setVisible(true);
                        }
                    } else {
                        if (JOptionPane.showConfirmDialog(null, "Do you want to extend the Deadline of the book " + book.getTitle() + "?") == 0) {
                            stmtupdate.executeUpdate("UPDATE rents SET deadline = (SELECT DATE_ADD( deadline , INTERVAL 2 DAY) ), time = now() where uid = '" + user.getUid() + "' and mid = '" + book.getMid() + "'");
                            JOptionPane.showMessageDialog(null, "Congratulations! You have extended the deadline for two days.");
                            dispose();
                            new BookLibrary(user).setVisible(true);
                        }
                    }
                } else {
                    if (JOptionPane.showConfirmDialog(null, "Do you want to buy the book " + book.getTitle() + "?") == 0) {
                        Statement stmtinsert = conn.createStatement();
                        stmt.executeUpdate("INSERT INTO bought (uid, mid, time) VALUES ('" + user.getUid() + "', '" + book.getMid() + "', now() )");
                        JOptionPane.showMessageDialog(null, "Congratulations! You can now read the book in your book library.");
                        dispose();
                        new BookLibrary(user).setVisible(true);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Bought.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please accept the Direct Debit Payment and the GTC!");
        }

    }//GEN-LAST:event_jButton_rentActionPerformed

    public static void setPrevious(int previous) {
        Bought.previous = previous;
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
            java.util.logging.Logger.getLogger(Bought.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bought.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bought.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bought.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_rent;
    private javax.swing.JButton jButton_return;
    private javax.swing.JCheckBox jCheck_GTC;
    private javax.swing.JCheckBox jCheck_directDebitPayment;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelDeadline;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabel_deadline;
    private javax.swing.JLabel jLabel_image;
    private javax.swing.JLabel jLabel_price;
    private javax.swing.JLabel jLabel_rent;
    private javax.swing.JLabel jLabel_title;
    // End of variables declaration//GEN-END:variables
}
