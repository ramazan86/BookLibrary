/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bookrental;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author stefano
 */
public class Account extends javax.swing.JFrame {

    
    User user;
    String password, iban, bic;
    public Account() {
        initComponents();
    }
    public Account(User obj) {
        initComponents();
        user = obj;
        setLocationRelativeTo(getParent());
        setResizable(false);
        jLabelUsername.setText(user.getUsername());
        jLabelPassword.setText("******");
        jLabelEmail.setText(user.getEmail());
        jLabelBirthday.setText(user.getBirthday());
        jLabelPrename.setText(user.getPrename());
        jLabelSurname.setText(user.getSurname());
        jLabelAddress.setText(user.getStreet());
        jLabelZipcode.setText(user.getZipcode());
        jLabelCity.setText(user.getCity());
        if(user.getIban() != null && !(user.getIban().equals(""))){
            iban = user.getIban();
            iban = "******************" + iban.substring(iban.length()-4,iban.length());
        }else{
            iban = "";
        }
        if(user.getBic() != null && !(user.getBic().equals(""))){
            bic = user.getBic();
            bic = "*******" + bic.substring(bic.length()-4,bic.length());
        }
        else{
            bic = "";
        }
        jLabelIban.setText(iban);
        jLabelBic.setText(bic);
        
        
       
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelUsername = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelBirthday = new javax.swing.JLabel();
        jLabelPrename = new javax.swing.JLabel();
        jLabelSurname = new javax.swing.JLabel();
        jLabelAddress = new javax.swing.JLabel();
        jLabelIban = new javax.swing.JLabel();
        jLabelBic = new javax.swing.JLabel();
        jButtonChange = new javax.swing.JButton();
        jButtonReturn = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabelCity = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabelZipcode = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movierental/Logo.png"))); // NOI18N
        jLabel_logo.setText("jLabel2");

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel1.setText("Account");

        jLabel3.setText("Username:");

        jLabel4.setText("Password:");

        jLabel5.setText("Email:");

        jLabel6.setText("Birthday:");

        jLabel7.setText("Prename:");

        jLabel8.setText("Surname:");

        jLabel9.setText("Address:");

        jLabel10.setText("IBAN:");

        jLabel11.setText("BIC:");

        jLabelUsername.setText("Halloo123");

        jLabelPassword.setText("******");

        jLabelEmail.setText("Hallo@gmail.de");

        jLabelBirthday.setText("12.05.1983");

        jLabelPrename.setText("Max");

        jLabelSurname.setText("Musterman");

        jLabelAddress.setText("hallostr.19");

        jLabelIban.setText("DE23100000001234567890");

        jLabelBic.setText("DEUTDEDB110");

        jButtonChange.setText("Change");
        jButtonChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeActionPerformed(evt);
            }
        });

        jButtonReturn.setText("Return");
        jButtonReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturnActionPerformed(evt);
            }
        });

        jLabel21.setText("City:");

        jLabelCity.setText("Frankfurt");

        jLabel23.setText("Zipcode:");

        jLabelZipcode.setText("60487");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(94, 491, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonReturn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonChange)
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBirthday)
                            .addComponent(jLabelEmail)
                            .addComponent(jLabelPassword)
                            .addComponent(jLabelUsername))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelPrename))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelSurname))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabelAddress))
                                    .addComponent(jLabelCity)
                                    .addComponent(jLabelZipcode)
                                    .addComponent(jLabelBic)
                                    .addComponent(jLabelIban))))
                        .addGap(76, 76, 76))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabelUsername))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabelPassword))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabelEmail))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabelBirthday))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonReturn)
                            .addComponent(jButtonChange))
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabelPrename))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabelSurname))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelAddress)
                                .addGap(14, 14, 14)
                                .addComponent(jLabelZipcode)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelCity)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelIban)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelBic)))
                        .addContainerGap(96, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeActionPerformed
        if(evt.getSource() == jButtonChange){
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Enter your password:");
            JPasswordField pass = new JPasswordField(20);
            panel.add(label);
            panel.add(pass);
            String[] options = new String[]{"Cancel", "OK"};
            int option = JOptionPane.showOptionDialog(null, panel, "Confirmation",
                                     JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                     null, options, options[1]);
            if(option == 1) // pressing OK button
            {
                String newpass;
                newpass = String.valueOf(pass.getPassword());
                if(!(user.getPassword().equals(newpass))){
                    JOptionPane.showMessageDialog(null, "Password wrong!");
                }else{
                    try {
                        new ChangeAccount(user).setVisible(true);
                        setVisible(false);
                    } catch (SQLException ex) {
                        Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }    
    }//GEN-LAST:event_jButtonChangeActionPerformed

    private void jButtonReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturnActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonReturnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException {
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
            java.util.logging.Logger.getLogger(Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Account().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonChange;
    private javax.swing.JButton jButtonReturn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAddress;
    private javax.swing.JLabel jLabelBic;
    private javax.swing.JLabel jLabelBirthday;
    private javax.swing.JLabel jLabelCity;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelIban;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelPrename;
    private javax.swing.JLabel jLabelSurname;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JLabel jLabelZipcode;
    private javax.swing.JLabel jLabel_logo;
    // End of variables declaration//GEN-END:variables
}
