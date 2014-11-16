/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bookrental;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Registry extends javax.swing.JFrame {
    String username,email,prename,surname,address,password,password2,birthday,day,month,year,city,zipcode,bic,iban;
    String pattern = "^[_A-Za-z0-9-](?=.*[!@#$%]).{7,50}";
    String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String dayreg = "(0?[1-9]|[12][0-9]|3[01])";
    String monthreg = "(0?[1-9]|1[012])";
    String yearreg = "((19|20)\\d\\d)";
    String ibanreg = "[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}";
    String bicreg = "([a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?)";
    double d,m,y;
    Date birth = new Date();
    
    public Registry() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        jTextField_username.setToolTipText("beispiel@gmx.de");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField_surname = new javax.swing.JTextField();
        jLabel_prename = new javax.swing.JLabel();
        jTextField_bic = new javax.swing.JTextField();
        jLabel_birthday = new javax.swing.JLabel();
        jTextField_zipcode = new javax.swing.JTextField();
        jLabel_eMail = new javax.swing.JLabel();
        jTextField_address = new javax.swing.JTextField();
        jTextField_year = new javax.swing.JTextField();
        jLabel_pointMandY = new javax.swing.JLabel();
        jLabel_city = new javax.swing.JLabel();
        jLabel_birthdayExample = new javax.swing.JLabel();
        jLabel_registry = new javax.swing.JLabel();
        jTextField_prename = new javax.swing.JTextField();
        jLabel_password = new javax.swing.JLabel();
        jLabel_passworSpecial = new javax.swing.JLabel();
        jLabel_username = new javax.swing.JLabel();
        jTextField_eMail = new javax.swing.JTextField();
        jTextField_day = new javax.swing.JTextField();
        jLabel_mandatoryField = new javax.swing.JLabel();
        jLabel_optionalField = new javax.swing.JLabel();
        jLabel_pointDandM = new javax.swing.JLabel();
        jTextField_username = new javax.swing.JTextField();
        jTextField_month = new javax.swing.JTextField();
        jLabel_logo = new javax.swing.JLabel();
        jLabel_surname = new javax.swing.JLabel();
        jLabel_adress = new javax.swing.JLabel();
        jLabel_zipcode = new javax.swing.JLabel();
        jButton_register = new javax.swing.JButton();
        jButton_return = new javax.swing.JButton();
        jPassword_forUser = new javax.swing.JPasswordField();
        jTextFieldiban = new javax.swing.JTextField();
        jTextField_city = new javax.swing.JTextField();
        jLabel_iban = new javax.swing.JLabel();
        jLabel_bic = new javax.swing.JLabel();
        canvas1 = new java.awt.Canvas();
        jPassword2_retype = new javax.swing.JPasswordField();
        jLabel_retypePassword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_prename.setText("Prename :");

        jLabel_birthday.setText("Birthday* :");

        jLabel_eMail.setText("Email* :");

        jTextField_year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_yearActionPerformed(evt);
            }
        });

        jLabel_pointMandY.setText(".");

        jLabel_city.setText("City :");

        jLabel_birthdayExample.setText("(e.g. 17.03.1985)");

        jLabel_registry.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel_registry.setText("Registry");

        jLabel_password.setText("Password* :");

        jLabel_passworSpecial.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel_passworSpecial.setText("(min 8 char with 1 special sign) ");

        jLabel_username.setText("Username* :");

        jTextField_eMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_eMailActionPerformed(evt);
            }
        });

        jTextField_day.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_dayActionPerformed(evt);
            }
        });

        jLabel_mandatoryField.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel_mandatoryField.setText("Mandatory Fields");

        jLabel_optionalField.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel_optionalField.setText("Optional Fields");

        jLabel_pointDandM.setText(".");

        jLabel_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png"))); // NOI18N
        jLabel_logo.setText("jLabel2");

        jLabel_surname.setText("Surname :");

        jLabel_adress.setText("Address :");

        jLabel_zipcode.setText("Zipcode :");

        jButton_register.setText("Register");
        jButton_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_registerActionPerformed(evt);
            }
        });

        jButton_return.setText("Return");
        jButton_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_returnActionPerformed(evt);
            }
        });

        jPassword_forUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPassword_forUserActionPerformed(evt);
            }
        });

        jLabel_iban.setText("IBAN :");

        jLabel_bic.setText("BIC :");

        jLabel_retypePassword.setText("Retype pass:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_return)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_register))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_mandatoryField)
                                .addGap(208, 208, 208)
                                .addComponent(jLabel_optionalField)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(346, 346, 346)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_surname)
                                    .addComponent(jLabel_prename)
                                    .addComponent(jLabel_bic)
                                    .addComponent(jLabel_iban)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel_city, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel_zipcode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel_adress, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_bic)
                                    .addComponent(jTextFieldiban)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_zipcode, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextField_prename, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                                .addComponent(jTextField_address)
                                                .addComponent(jTextField_surname)))
                                        .addGap(0, 4, Short.MAX_VALUE))
                                    .addComponent(jTextField_city)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_password, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel_birthdayExample))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPassword_forUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_username, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel_passworSpecial)
                                .addComponent(jTextField_eMail, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(25, 25, 25))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_birthday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel_retypePassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addComponent(jLabel_eMail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField_day, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_pointDandM, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_month, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_pointMandY, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_year, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPassword2_retype, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(1, 1, 1))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(jLabel_registry)
                    .addContainerGap(536, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_mandatoryField)
                    .addComponent(jLabel_optionalField))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_username)
                    .addComponent(jTextField_prename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_prename, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_password)
                    .addComponent(jPassword_forUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_surname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_surname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_passworSpecial, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_retypePassword)
                            .addComponent(jPassword2_retype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_eMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_eMail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_pointDandM)
                            .addComponent(jTextField_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_pointMandY)
                            .addComponent(jTextField_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_birthday))
                        .addGap(5, 5, 5)
                        .addComponent(jLabel_birthdayExample))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_adress))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_zipcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_zipcode, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_city))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldiban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_iban))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_bic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel_bic)))))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_return)
                    .addComponent(jButton_register))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel_logo)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel_registry)
                    .addContainerGap(371, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_yearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_yearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_yearActionPerformed

    private void jTextField_dayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_dayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_dayActionPerformed

    private void jTextField_eMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_eMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_eMailActionPerformed

    private void jButton_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_registerActionPerformed
        username = jTextField_username.getText();
        password = new String(jPassword_forUser.getPassword());
        password2 = new String(jPassword2_retype.getPassword());
        email = jTextField_eMail.getText();
        day = jTextField_day.getText();
        month = jTextField_month.getText();
        year = jTextField_year.getText();
        birthday = year + "-" + month + "-" + day;
        prename = jTextField_prename.getText();
        surname = jTextField_surname.getText();
        address = jTextField_address.getText();
        zipcode = jTextField_zipcode.getText();
        city = jTextField_city.getText();
        iban = jTextFieldiban.getText();
        bic = jTextField_bic.getText();
        
        if(evt.getSource() == jButton_register){
        
            if(username.equals("") || password.equals("") || email.equals("") || birthday.equals("")){
                JOptionPane.showMessageDialog(null, "Please fill in all mandatory fields.");
            }else{      
                Date now = new Date();

                d = Double.valueOf(day)/365.25;
                m = (Double.valueOf(month)/12);
                y = (Double.valueOf(year)-1970);
 
                double time = (double)now.getTime()/31557600000L + 0.088095425159075;
                double birthdays = (y+m+d);
                
                if(!(password.matches(pattern))){
                    JOptionPane.showMessageDialog(null, "Password must have at least 8 characters and 1 special sign.");
                }else if(!password.equals(password2)){
                    JOptionPane.showMessageDialog(null, "The two passwords aren't the same.");
                }else if(!(email.matches(emailreg))){
                    JOptionPane.showMessageDialog(null, "Please enter a correct email address.");
                }else if(!(day.matches(dayreg) && month.matches(monthreg) && year.matches(yearreg))){
                    JOptionPane.showMessageDialog(null,"Please enter your birthday as in the example.");
                }else if(time - birthdays < 18){
                    JOptionPane.showMessageDialog(null,"You must be above 18 to register.");
                }else if(!iban.equals("") && !iban.matches(ibanreg)){
                    JOptionPane.showMessageDialog(null,"Please enter a correct iban.");                    
                }else if(!bic.equals("") && !bic.matches(bicreg)){
                    JOptionPane.showMessageDialog(null,"Please enter a correct bic.");                    
                }else{    
                    try {
                    if(User.register(username, password, email, birthday, prename, surname, address, zipcode, city, iban, bic))
                        setVisible(false);
                    } catch (SQLException | UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(Registry.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } 
                }
                
                }
        
    }//GEN-LAST:event_jButton_registerActionPerformed

    private void jPassword_forUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPassword_forUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPassword_forUserActionPerformed

    private void jButton_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_returnActionPerformed
        dispose();
    }//GEN-LAST:event_jButton_returnActionPerformed

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
            java.util.logging.Logger.getLogger(Registry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Registry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas canvas1;
    private javax.swing.JButton jButton_register;
    private javax.swing.JButton jButton_return;
    private javax.swing.JLabel jLabel_adress;
    private javax.swing.JLabel jLabel_bic;
    private javax.swing.JLabel jLabel_birthday;
    private javax.swing.JLabel jLabel_birthdayExample;
    private javax.swing.JLabel jLabel_city;
    private javax.swing.JLabel jLabel_eMail;
    private javax.swing.JLabel jLabel_iban;
    private javax.swing.JLabel jLabel_logo;
    private javax.swing.JLabel jLabel_mandatoryField;
    private javax.swing.JLabel jLabel_optionalField;
    private javax.swing.JLabel jLabel_passworSpecial;
    private javax.swing.JLabel jLabel_password;
    private javax.swing.JLabel jLabel_pointDandM;
    private javax.swing.JLabel jLabel_pointMandY;
    private javax.swing.JLabel jLabel_prename;
    private javax.swing.JLabel jLabel_registry;
    private javax.swing.JLabel jLabel_retypePassword;
    private javax.swing.JLabel jLabel_surname;
    private javax.swing.JLabel jLabel_username;
    private javax.swing.JLabel jLabel_zipcode;
    private javax.swing.JPasswordField jPassword2_retype;
    private javax.swing.JPasswordField jPassword_forUser;
    private javax.swing.JTextField jTextField_address;
    private javax.swing.JTextField jTextField_bic;
    private javax.swing.JTextField jTextField_city;
    private javax.swing.JTextField jTextField_day;
    private javax.swing.JTextField jTextField_eMail;
    private javax.swing.JTextField jTextField_month;
    private javax.swing.JTextField jTextField_prename;
    private javax.swing.JTextField jTextField_surname;
    private javax.swing.JTextField jTextField_username;
    private javax.swing.JTextField jTextField_year;
    private javax.swing.JTextField jTextField_zipcode;
    private javax.swing.JTextField jTextFieldiban;
    // End of variables declaration//GEN-END:variables
}
