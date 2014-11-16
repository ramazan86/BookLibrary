package bookrental;

import java.awt.Window;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ChangeAccount extends javax.swing.JFrame {
    
    User user;
    Verbindung db;
    Connection conn;
    Statement stmt;
    String uid,username,email,prename,surname,address,password,password2,birthday,day,month,year,city,zipcode,bic,iban,bichidden,ibanhidden;
    String pattern = "^[_A-Za-z0-9-](?=.*[!@#$%]).{7,50}";
    String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String dayreg = "(0?[1-9]|[12][0-9]|3[01])";
    String monthreg = "(0?[1-9]|1[012])";
    String yearreg = "((19|20)\\d\\d)";
    String ibanreg = "[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}";
    String bicreg = "([a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?)";
    
    public ChangeAccount(User user) throws SQLException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.user = user;
        jLabel_userId.setVisible(false);
        jLabel_userId.setText(user.getUid());
        uid = jLabel_userId.getText();
        jPassword_type.setText(user.getPassword());
        jPassword_retype.setText(user.getPassword());
        jLabelUsername.setText(user.getUsername());
        jTextField_eMail.setText(user.getEmail());
        jLabelBirthday.setText(user.getBirthday());
        jTextField_prename.setText(user.getPrename());
        jTextField_surname.setText(user.getSurname());
        jTextField_address.setText(user.getStreet());
        jTextField_zipcode.setText(user.getZipcode());
        jTextField_city.setText(user.getCity());
        if(user.getIban() != null && !(user.getIban().equals(""))){
            iban = user.getIban();
            ibanhidden = "******************" + iban.substring(iban.length()-4,iban.length());
            jTextField_iban.setText(ibanhidden);
        }else{
            iban = "";
            jTextField_iban.setText(iban);
        }
        if(user.getBic() != null && !(user.getBic().equals(""))){
            bic = user.getBic();
            bichidden = "****" + bic.substring(bic.length()-4,bic.length());
            jTextField_bic.setText(bichidden);
        }
        else{
            bic = "";
            jTextField_bic.setText(bic);
        }
        
    }

    private ChangeAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        jLabel_city = new javax.swing.JLabel();
        jLabelBirthday = new javax.swing.JLabel();
        jLabel_changeAccountInf = new javax.swing.JLabel();
        jTextField_prename = new javax.swing.JTextField();
        jLabel_password = new javax.swing.JLabel();
        jLabel_pswSpecial = new javax.swing.JLabel();
        jLabel_username = new javax.swing.JLabel();
        jTextField_eMail = new javax.swing.JTextField();
        jLabel_mandatoryField = new javax.swing.JLabel();
        jLabel_optionalField = new javax.swing.JLabel();
        jLabel_logo = new javax.swing.JLabel();
        jLabel_surname = new javax.swing.JLabel();
        jLabel_adress = new javax.swing.JLabel();
        jLabel_zipcode = new javax.swing.JLabel();
        jButton_change = new javax.swing.JButton();
        jButton_return = new javax.swing.JButton();
        jPassword_type = new javax.swing.JPasswordField();
        jTextField_iban = new javax.swing.JTextField();
        jTextField_city = new javax.swing.JTextField();
        jLabel_iban = new javax.swing.JLabel();
        jLabel_bic = new javax.swing.JLabel();
        jPassword_retype = new javax.swing.JPasswordField();
        jLabel20 = new javax.swing.JLabel();
        jLabelUsername = new javax.swing.JLabel();
        jButton_delete = new javax.swing.JButton();
        jLabel_userId = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_prename.setText("Prename :");

        jLabel_birthday.setText("Birthday* :");

        jLabel_eMail.setText("Email* :");

        jLabel_city.setText("City :");

        jLabelBirthday.setText("jLabelBirthday");

        jLabel_changeAccountInf.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel_changeAccountInf.setText("Change Account Information");

        jLabel_password.setText("Password* :");

        jLabel_pswSpecial.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel_pswSpecial.setText("(min 8 char with 1 special sign) ");

        jLabel_username.setText("Username* :");

        jTextField_eMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_eMailActionPerformed(evt);
            }
        });

        jLabel_mandatoryField.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel_mandatoryField.setText("Mandatory Fields");

        jLabel_optionalField.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel_optionalField.setText("Optional Fields");

        jLabel_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png"))); // NOI18N
        jLabel_logo.setText("jLabel2");

        jLabel_surname.setText("Surname :");

        jLabel_adress.setText("Address :");

        jLabel_zipcode.setText("Zipcode :");

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

        jLabel_iban.setText("IBAN :");

        jLabel_bic.setText("BIC :");

        jLabel20.setText("Retype password:");

        jLabelUsername.setText("jLabel21");

        jButton_delete.setText("Delete");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        jLabel_userId.setText("jLabel12");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel_mandatoryField)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel_username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel_password, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPassword_type, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel_eMail, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel_birthday, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_pswSpecial)
                                        .addComponent(jPassword_retype, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField_eMail, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel_userId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_optionalField)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel_iban, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel_bic, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField_bic)
                                            .addComponent(jTextField_iban, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel_surname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel_prename, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addGap(26, 26, 26))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel_city, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel_zipcode, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel_adress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextField_surname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                                .addComponent(jTextField_address, javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextField_zipcode, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextField_city, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jTextField_prename, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_return)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
                        .addComponent(jButton_delete)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_change)
                        .addGap(25, 25, 25))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(jLabel_changeAccountInf)
                    .addContainerGap(296, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(186, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_optionalField)
                    .addComponent(jLabel_mandatoryField))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_username)
                            .addComponent(jLabelUsername))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_password)
                            .addComponent(jPassword_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_pswSpecial, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jPassword_retype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_eMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_eMail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_birthday)
                            .addComponent(jLabelBirthday))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_prename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_prename))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_surname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_surname))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_adress))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_zipcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_zipcode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_city))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addComponent(jLabel_userId)
                                .addGap(47, 47, 47))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField_iban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_iban))
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField_bic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_bic))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_return)
                    .addComponent(jButton_change)
                    .addComponent(jButton_delete))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel_changeAccountInf)
                    .addContainerGap(327, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_eMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_eMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_eMailActionPerformed

    private void jButton_changeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_changeActionPerformed
        password = new String(jPassword_type.getPassword());
        password2 = new String(jPassword_retype.getPassword());
        email = jTextField_eMail.getText();
        prename = jTextField_prename.getText();
        surname = jTextField_surname.getText();
        address = jTextField_address.getText();
        zipcode = jTextField_zipcode.getText();
        city = jTextField_city.getText();
        iban = jTextField_iban.getText();
        bic = jTextField_bic.getText();
        if(password.equals("") || email.equals("")){
                JOptionPane.showMessageDialog(null, "Please fill in all mandatory fields.");
            }else{
                
                if(!(password.matches(pattern))){
                    JOptionPane.showMessageDialog(null, "Password must have at least 8 characters and 1 special sign.");
                }else if(!password.equals(password2)){
                    JOptionPane.showMessageDialog(null, "The two passwords aren't the same.");
                }else if(!(email.matches(emailreg))){
                    JOptionPane.showMessageDialog(null, "Please enter a correct email address.");
                }else if(!(iban.equals("")||iban.startsWith("*")) && !(iban.matches(ibanreg)||iban.startsWith("*"))){
                    JOptionPane.showMessageDialog(null,"Please enter a correct iban.");                    
                }else if(!(bic.equals("")||bic.startsWith("*")) && !(bic.matches(bicreg)||bic.startsWith("*"))){
                    JOptionPane.showMessageDialog(null,"Please enter a correct bic.");                    
                }else{    
                    try {
                        if(!jTextField_iban.getText().startsWith("*")){
                            if(user.changeInformation(new String(jPassword_type.getPassword()), new String(jPassword_retype.getPassword()), jTextField_eMail.getText(), jTextField_prename.getText(), jTextField_surname.getText(), jTextField_address.getText(), jTextField_zipcode.getText(), jTextField_city.getText(), jTextField_iban.getText(), jTextField_bic.getText()) == 1){

                            user.setPassword(new String(jPassword_type.getPassword()));
                            user.setEmail(jTextField_eMail.getText());
                            user.setPrename(jTextField_prename.getText());
                            user.setSurname(jTextField_surname.getText());
                            user.setStreet(jTextField_address.getText());
                            user.setZipcode(jTextField_zipcode.getText());
                            user.city = jTextField_city.getText();
                            if(!jTextField_iban.getText().startsWith("*"))
                                user.setIban(jTextField_iban.getText());
                            if(!jTextField_bic.getText().startsWith("*"))
                                user.setBic(jTextField_bic.getText());
                            System.out.println("Iban: " + user.getIban());
                                setVisible(false);
                            new Account(user).setVisible(true);
                            setVisible(false);
                        }
                        }else{
                            if(user.changeInformation(new String(jPassword_type.getPassword()), new String(jPassword_retype.getPassword()), jTextField_eMail.getText(), jTextField_prename.getText(), jTextField_surname.getText(), jTextField_address.getText(), jTextField_zipcode.getText(), jTextField_city.getText(), user.getIban(), user.getBic()) == 1){

                            user.setPassword(new String(jPassword_type.getPassword()));
                            user.setEmail(jTextField_eMail.getText());
                            user.setPrename(jTextField_prename.getText());
                            user.setSurname(jTextField_surname.getText());
                            user.setStreet(jTextField_address.getText());
                            user.setZipcode(jTextField_zipcode.getText());
                            user.city = jTextField_city.getText();
                            if(!jTextField_iban.getText().startsWith("*"))
                                user.setIban(jTextField_iban.getText());
                            if(!jTextField_bic.getText().startsWith("*"))
                                user.setBic(jTextField_bic.getText());
                            System.out.println("Iban: " + user.getIban());
                                setVisible(false);
                            new Account(user).setVisible(true);
                            setVisible(false);                            
                            }
                        }
                        } catch (SQLException | UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                            Logger.getLogger(ChangeAccount.class.getName()).log(Level.SEVERE, null, ex);
                }   
                } 
                }
            
    }//GEN-LAST:event_jButton_changeActionPerformed

    private void jButton_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_returnActionPerformed
        setVisible(false);
        new Account(user).setVisible(true);
    }//GEN-LAST:event_jButton_returnActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        if(evt.getSource() == jButton_delete){
            if(JOptionPane.showConfirmDialog(null,"Do you really want to delete your account?.") == 0){
                try {
                db = new Verbindung();
                db.start();
                conn = db.getVerbindung();
                
                stmt = conn.createStatement();
                stmt.executeUpdate("DELETE FROM user WHERE Uid = '"+uid+"'");
                System.gc();  
                for (Window window : Window.getWindows()) {  
                    window.dispose();  
                }
                
                new Login().setVisible(true);
                } catch (SQLException | IOException ex) {
                    Logger.getLogger(ChangeAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
        }
    }//GEN-LAST:event_jButton_deleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChangeAccount().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_change;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_return;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabelBirthday;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JLabel jLabel_adress;
    private javax.swing.JLabel jLabel_bic;
    private javax.swing.JLabel jLabel_birthday;
    private javax.swing.JLabel jLabel_changeAccountInf;
    private javax.swing.JLabel jLabel_city;
    private javax.swing.JLabel jLabel_eMail;
    private javax.swing.JLabel jLabel_iban;
    private javax.swing.JLabel jLabel_logo;
    private javax.swing.JLabel jLabel_mandatoryField;
    private javax.swing.JLabel jLabel_optionalField;
    private javax.swing.JLabel jLabel_password;
    private javax.swing.JLabel jLabel_prename;
    private javax.swing.JLabel jLabel_pswSpecial;
    private javax.swing.JLabel jLabel_surname;
    private javax.swing.JLabel jLabel_userId;
    private javax.swing.JLabel jLabel_username;
    private javax.swing.JLabel jLabel_zipcode;
    private javax.swing.JPasswordField jPassword_retype;
    private javax.swing.JPasswordField jPassword_type;
    private javax.swing.JTextField jTextField_address;
    private javax.swing.JTextField jTextField_bic;
    private javax.swing.JTextField jTextField_city;
    private javax.swing.JTextField jTextField_eMail;
    private javax.swing.JTextField jTextField_iban;
    private javax.swing.JTextField jTextField_prename;
    private javax.swing.JTextField jTextField_surname;
    private javax.swing.JTextField jTextField_zipcode;
    // End of variables declaration//GEN-END:variables
}
