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
        jLabelUserId.setVisible(false);
        jLabelUserId.setText(user.getUid());
        uid = jLabelUserId.getText();
        jPassword.setText(user.getPassword());
        jPassword2.setText(user.getPassword());
        jLabelUsername.setText(user.getUsername());
        jTextEmail.setText(user.getEmail());
        jLabelBirthday.setText(user.getBirthday());
        jTextPrename.setText(user.getPrename());
        jTextSurname.setText(user.getSurname());
        jTextAddress.setText(user.getStreet());
        jTextZipcode.setText(user.getZipcode());
        jTextCity.setText(user.getCity());
        if(user.getIban() != null && !(user.getIban().equals(""))){
            iban = user.getIban();
            ibanhidden = "******************" + iban.substring(iban.length()-4,iban.length());
            jTextIban.setText(ibanhidden);
        }else{
            iban = "";
            jTextIban.setText(iban);
        }
        if(user.getBic() != null && !(user.getBic().equals(""))){
            bic = user.getBic();
            bichidden = "****" + bic.substring(bic.length()-4,bic.length());
            jTextBic.setText(bichidden);
        }
        else{
            bic = "";
            jTextBic.setText(bic);
        }
        
    }

    private ChangeAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextSurname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextBic = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextZipcode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextAddress = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabelBirthday = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextPrename = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextEmail = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButtonChange = new javax.swing.JButton();
        jButtonReturn = new javax.swing.JButton();
        jPassword = new javax.swing.JPasswordField();
        jTextIban = new javax.swing.JTextField();
        jTextCity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPassword2 = new javax.swing.JPasswordField();
        jLabel20 = new javax.swing.JLabel();
        jLabelUsername = new javax.swing.JLabel();
        jButtonDelete = new javax.swing.JButton();
        jLabelUserId = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel8.setText("Prename :");

        jLabel7.setText("Birthday* :");

        jLabel6.setText("Email* :");

        jLabel15.setText("City :");

        jLabelBirthday.setText("jLabelBirthday");

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel1.setText("Change Account Information");

        jLabel3.setText("Password* :");

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel16.setText("(min 8 char with 1 special sign) ");

        jLabel4.setText("Username* :");

        jTextEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextEmailActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel17.setText("Mandatory Fields");

        jLabel18.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel18.setText("Optional Fields");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movierental/Logo.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel9.setText("Surname :");

        jLabel10.setText("Address :");

        jLabel11.setText("Zipcode :");

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

        jLabel5.setText("IBAN :");

        jLabel19.setText("BIC :");

        jLabel20.setText("Retype password:");

        jLabelUsername.setText("jLabel21");

        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jLabelUserId.setText("jLabel12");

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
                                .addComponent(jLabel17)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16)
                                        .addComponent(jPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabelUserId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextBic)
                                            .addComponent(jTextIban, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addGap(26, 26, 26))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextSurname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                                .addComponent(jTextAddress, javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextZipcode, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextCity, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jTextPrename, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonReturn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
                        .addComponent(jButtonDelete)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonChange)
                        .addGap(25, 25, 25))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(jLabel1)))
                    .addContainerGap(296, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(186, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabelUsername))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabelBirthday))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextPrename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextZipcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addComponent(jLabelUserId)
                                .addGap(47, 47, 47))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextIban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextBic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonReturn)
                    .addComponent(jButtonChange)
                    .addComponent(jButtonDelete))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(29, 29, 29)
                    .addComponent(jLabel1)
                    .addContainerGap(337, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextEmailActionPerformed

    private void jButtonChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeActionPerformed
        password = new String(jPassword.getPassword());
        password2 = new String(jPassword2.getPassword());
        email = jTextEmail.getText();
        prename = jTextPrename.getText();
        surname = jTextSurname.getText();
        address = jTextAddress.getText();
        zipcode = jTextZipcode.getText();
        city = jTextCity.getText();
        iban = jTextIban.getText();
        bic = jTextBic.getText();
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
                        if(!jTextIban.getText().startsWith("*")){
                            if(user.changeInformation(new String(jPassword.getPassword()), new String(jPassword2.getPassword()), jTextEmail.getText(), jTextPrename.getText(), jTextSurname.getText(), jTextAddress.getText(), jTextZipcode.getText(), jTextCity.getText(), jTextIban.getText(), jTextBic.getText()) == 1){

                            user.setPassword(new String(jPassword.getPassword()));
                            user.setEmail(jTextEmail.getText());
                            user.setPrename(jTextPrename.getText());
                            user.setSurname(jTextSurname.getText());
                            user.setStreet(jTextAddress.getText());
                            user.setZipcode(jTextZipcode.getText());
                            user.city = jTextCity.getText();
                            if(!jTextIban.getText().startsWith("*"))
                                user.setIban(jTextIban.getText());
                            if(!jTextBic.getText().startsWith("*"))
                                user.setBic(jTextBic.getText());
                            System.out.println("Iban: " + user.getIban());
                                setVisible(false);
                            new Account(user).setVisible(true);
                            setVisible(false);
                        }
                        }else{
                            if(user.changeInformation(new String(jPassword.getPassword()), new String(jPassword2.getPassword()), jTextEmail.getText(), jTextPrename.getText(), jTextSurname.getText(), jTextAddress.getText(), jTextZipcode.getText(), jTextCity.getText(), user.getIban(), user.getBic()) == 1){

                            user.setPassword(new String(jPassword.getPassword()));
                            user.setEmail(jTextEmail.getText());
                            user.setPrename(jTextPrename.getText());
                            user.setSurname(jTextSurname.getText());
                            user.setStreet(jTextAddress.getText());
                            user.setZipcode(jTextZipcode.getText());
                            user.city = jTextCity.getText();
                            if(!jTextIban.getText().startsWith("*"))
                                user.setIban(jTextIban.getText());
                            if(!jTextBic.getText().startsWith("*"))
                                user.setBic(jTextBic.getText());
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
            
    }//GEN-LAST:event_jButtonChangeActionPerformed

    private void jButtonReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturnActionPerformed
        setVisible(false);
        new Account(user).setVisible(true);
    }//GEN-LAST:event_jButtonReturnActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        if(evt.getSource() == jButtonDelete){
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
    }//GEN-LAST:event_jButtonDeleteActionPerformed

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
    private javax.swing.JButton jButtonChange;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonReturn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBirthday;
    private javax.swing.JLabel jLabelUserId;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JPasswordField jPassword2;
    private javax.swing.JTextField jTextAddress;
    private javax.swing.JTextField jTextBic;
    private javax.swing.JTextField jTextCity;
    private javax.swing.JTextField jTextEmail;
    private javax.swing.JTextField jTextIban;
    private javax.swing.JTextField jTextPrename;
    private javax.swing.JTextField jTextSurname;
    private javax.swing.JTextField jTextZipcode;
    // End of variables declaration//GEN-END:variables
}
