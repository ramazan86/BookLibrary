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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

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

        jLabel_buy = new javax.swing.JLabel();
        jLabel_image = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel_title = new javax.swing.JLabel();
        jLabel_deadline = new javax.swing.JLabel();
        jLabel_price = new javax.swing.JLabel();
        jButton_buy = new javax.swing.JButton();
        jButton_return = new javax.swing.JButton();
        jCheck_GTC = new javax.swing.JCheckBox();
        jCheck_directDebitPayment = new javax.swing.JCheckBox();
        jLabelDeadline = new javax.swing.JLabel();
        jLabelPrice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        jLabel_buy.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel_buy.setText("Buy");

        jLabel_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bookLibrary.png"))); // NOI18N

        jLabel_title.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel_title.setText("Graphics under C");

        jLabel_deadline.setText("Deadline: ");

        jLabel_price.setText("Price:");

        jButton_buy.setText("OK");
        jButton_buy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_buyActionPerformed(evt);
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
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("D:\\IT\\Eclipse\\WorkSpace\\BookLibrary\\src\\Images\\mylog2.png"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(26)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(47)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(jLabel_buy)
        								.addComponent(jCheck_directDebitPayment)
        								.addComponent(jCheck_GTC)
        								.addGroup(layout.createSequentialGroup()
        									.addGap(147)
        									.addComponent(jLabel3))))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jLabel_image, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createSequentialGroup()
        									.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        										.addComponent(jLabel_price, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        										.addComponent(jLabel_deadline))
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        										.addComponent(jLabelDeadline, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
        										.addComponent(jLabelPrice, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        								.addComponent(jLabel_title, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE))))
        					.addContainerGap(89, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jButton_return)
        					.addPreferredGap(ComponentPlacement.RELATED, 476, Short.MAX_VALUE)
        					.addComponent(jButton_buy)
        					.addGap(40))))
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblNewLabel)
        			.addContainerGap(598, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblNewLabel)
        			.addGap(107)
        			.addComponent(jLabel_buy)
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel_title)
        					.addGap(34)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel_deadline)
        						.addComponent(jLabelDeadline))
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel_price)
        						.addComponent(jLabelPrice))
        					.addGap(0, 138, Short.MAX_VALUE))
        				.addComponent(jLabel_image, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jLabel3)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jCheck_GTC)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jCheck_directDebitPayment)
        			.addGap(22)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jButton_buy)
        				.addComponent(jButton_return))
        			.addGap(21))
        );
        getContentPane().setLayout(layout);

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

    private void jButton_buyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_rentActionPerformed

        Verbindung db = new Verbindung();
        db.start();
        Connection conn = db.getVerbindung();
        Object[] options = {"Yes", "No"};
        
        if (jCheck_directDebitPayment.isSelected() && jCheck_GTC.isSelected()) {
            JOptionPane.setDefaultLocale(Locale.ENGLISH);
            try {
                Statement stmt = conn.createStatement();
                Statement update = conn.createStatement();
                
                System.out.println("Line-210; Bought.java -> " +user.getUid() + " " +book.getMid());
                
                ResultSet rs = stmt.executeQuery("Select * from bought natural join user where uid = '" + user.getUid() + "' and mid = '" + book.getMid() + "'");
              
                if (rs.next()) {
                    
                		JOptionPane.showMessageDialog(null, "You already have bought bought this book at " + rs.getString("time"));
                        dispose();
                        // new BookLibrary(user).setVisible(true);
               } else {
            	   
            	   
            	   
            	   int n = JOptionPane.showOptionDialog(this,
                           "Do you want buy the book?", 
                           "", 
                           JOptionPane.YES_NO_OPTION, 
                           JOptionPane.QUESTION_MESSAGE, 
                           null,           //do not use a custiom Icon
                           options, 
                           options[1]);
            	   
            	   Statement stmInsert = conn.createStatement();
            	   String insert = "Insert into bought (uid, mid, time) VALUES ('" +user.getUid() + "', '" +book.getMid() + "', now())";
            	   String upd = "Update book set bought = bought + 1 where mid = "+book.getMid();
					switch(n) {
						case 0: stmInsert.executeUpdate(insert);
								stmInsert.executeUpdate(upd);
								JOptionPane.showMessageDialog(null, "Congratulations! You can now get the book in your book library.");
								dispose();
								new BookLibrary(user).setVisible(true);
								break;
						case 1: setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); break;
						default : break;
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
    private javax.swing.JButton jButton_buy;
    private javax.swing.JButton jButton_return;
    private javax.swing.JCheckBox jCheck_GTC;
    private javax.swing.JCheckBox jCheck_directDebitPayment;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelDeadline;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabel_deadline;
    private javax.swing.JLabel jLabel_image;
    private javax.swing.JLabel jLabel_price;
    private javax.swing.JLabel jLabel_buy;
    private javax.swing.JLabel jLabel_title;
    private JLabel lblNewLabel;
    // End of variables declaration//GEN-END:variables
}
