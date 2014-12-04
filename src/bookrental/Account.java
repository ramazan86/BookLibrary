/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bookrental;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author Ali Hannoun & Ramazan Cinardere
 */
public class Account extends javax.swing.JFrame {

    /*Class Attributes*/
    
    User user;
    String password, iban, bic;
    
    /*Class Methods*/
    
    public Account() {
        initComponents();
    }
    
    public Account(User obj) {
        
        initComponents();
        
        user = obj;
        setLocationRelativeTo(getParent());
        setResizable(false);
        
        jLabel_userName.setText(user.getUsername());
        jLabelPassword.setText("******");
        jLabel_Email.setText(user.getEmail());
        jLabel_Birthday.setText(user.getBirthday());
        jLabel_Prename.setText(user.getPrename());
        jLabel_Surname.setText(user.getSurname());
        jLabel_Address.setText(user.getStreet());
        jLabel_Zipcode.setText(user.getZipcode());
        jLabel_City.setText(user.getCity());
        
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
        jLabel_Iban.setText(iban);
        jLabel_Bic.setText(bic);
             
    }//Account(User obj) closing
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_account = new javax.swing.JLabel();
        jLabel_username = new javax.swing.JLabel();
        jLabel_passWord = new javax.swing.JLabel();
        jLabel_eMail = new javax.swing.JLabel();
        jLabel_birthday = new javax.swing.JLabel();
        jLabel_preName = new javax.swing.JLabel();
        jLabel_surname = new javax.swing.JLabel();
        jLabel_adress = new javax.swing.JLabel();
        jLabel_iban = new javax.swing.JLabel();
        jLabel_bic = new javax.swing.JLabel();
        jLabel_userName = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jLabel_Email = new javax.swing.JLabel();
        jLabel_Birthday = new javax.swing.JLabel();
        jLabel_Prename = new javax.swing.JLabel();
        jLabel_Surname = new javax.swing.JLabel();
        jLabel_Address = new javax.swing.JLabel();
        jLabel_Iban = new javax.swing.JLabel();
        jLabel_Bic = new javax.swing.JLabel();
        jButton_change = new javax.swing.JButton();
        jButton_return = new javax.swing.JButton();
        jLabel_city = new javax.swing.JLabel();
        jLabel_City = new javax.swing.JLabel();
        jLabel_zipCode = new javax.swing.JLabel();
        jLabel_Zipcode = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_account.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel_account.setText("Account");

        jLabel_username.setText("Username:");

        jLabel_passWord.setText("Password:");

        jLabel_eMail.setText("Email:");

        jLabel_birthday.setText("Birthday:");

        jLabel_preName.setText("Prename:");

        jLabel_surname.setText("Surname:");

        jLabel_adress.setText("Address:");

        jLabel_iban.setText("IBAN:");

        jLabel_bic.setText("BIC:");

        jLabel_userName.setText("Halloo123");

        jLabelPassword.setText("******");

        jLabel_Email.setText("Hallo@gmail.de");

        jLabel_Birthday.setText("12.05.1983");

        jLabel_Prename.setText("Max");

        jLabel_Surname.setText("Musterman");

        jLabel_Address.setText("hallostr.19");

        jLabel_Iban.setText("DE23100000001234567890");

        jLabel_Bic.setText("DEUTDEDB110");

        jButton_change.setText("Change");
        jButton_change.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_changeActionPerformed(evt);
            }
        });

        jButton_return.setText("Return");
        jButton_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_returnActionPerformed(evt);
            }
        });

        jLabel_city.setText("City:");

        jLabel_City.setText("Frankfurt");

        jLabel_zipCode.setText("Zipcode:");

        jLabel_Zipcode.setText("60487");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_account)
                        .addGap(94, 491, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_return)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_change)
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_username)
                            .addComponent(jLabel_passWord)
                            .addComponent(jLabel_eMail)
                            .addComponent(jLabel_birthday))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_Birthday)
                            .addComponent(jLabel_Email)
                            .addComponent(jLabelPassword)
                            .addComponent(jLabel_userName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_preName)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_Prename))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_surname)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_Surname))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_adress)
                                    .addComponent(jLabel_zipCode)
                                    .addComponent(jLabel_city)
                                    .addComponent(jLabel_iban)
                                    .addComponent(jLabel_bic))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel_Address))
                                    .addComponent(jLabel_City)
                                    .addComponent(jLabel_Zipcode)
                                    .addComponent(jLabel_Bic)
                                    .addComponent(jLabel_Iban))))
                        .addGap(76, 76, 76))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jLabel_account)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_username)
                            .addComponent(jLabel_userName))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_passWord)
                            .addComponent(jLabelPassword))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_eMail)
                            .addComponent(jLabel_Email))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_birthday)
                            .addComponent(jLabel_Birthday))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_return)
                            .addComponent(jButton_change))
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_preName)
                            .addComponent(jLabel_Prename))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_surname)
                            .addComponent(jLabel_Surname))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_adress)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel_zipCode)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_city)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_iban)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_bic))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_Address)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel_Zipcode)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_City)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_Iban)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_Bic)))
                        .addContainerGap(96, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_changeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_changeActionPerformed
        if(evt.getSource() == jButton_change){
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
    }//GEN-LAST:event_jButton_changeActionPerformed

    private void jButton_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_returnActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton_returnActionPerformed

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
    private javax.swing.JButton jButton_change;
    private javax.swing.JButton jButton_return;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabel_Address;
    private javax.swing.JLabel jLabel_Bic;
    private javax.swing.JLabel jLabel_Birthday;
    private javax.swing.JLabel jLabel_City;
    private javax.swing.JLabel jLabel_Email;
    private javax.swing.JLabel jLabel_Iban;
    private javax.swing.JLabel jLabel_Prename;
    private javax.swing.JLabel jLabel_Surname;
    private javax.swing.JLabel jLabel_Zipcode;
    private javax.swing.JLabel jLabel_account;
    private javax.swing.JLabel jLabel_adress;
    private javax.swing.JLabel jLabel_bic;
    private javax.swing.JLabel jLabel_birthday;
    private javax.swing.JLabel jLabel_city;
    private javax.swing.JLabel jLabel_eMail;
    private javax.swing.JLabel jLabel_iban;
    private javax.swing.JLabel jLabel_passWord;
    private javax.swing.JLabel jLabel_preName;
    private javax.swing.JLabel jLabel_surname;
    private javax.swing.JLabel jLabel_userName;
    private javax.swing.JLabel jLabel_username;
    private javax.swing.JLabel jLabel_zipCode;
    // End of variables declaration//GEN-END:variables
}
