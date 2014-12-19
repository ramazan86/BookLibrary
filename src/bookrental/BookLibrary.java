package bookrental;

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class BookLibrary extends javax.swing.JFrame {

    User user;
    static int count = 0; // Counts at which page the user is
    ArrayList<Book> books;
    
    
    
    
    
    public BookLibrary(User obj) throws SQLException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        user = obj;

        this.listBooks();
    }

    public void openBrowser(String bookTitle) {
    
        try { 
            URI uri = new URI("http://ramazan.bplaced.net/BookLibrary/Test.pdf");
            
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
           
            if(desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(uri);
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    //Connects to the database, get's all bought Books of this user and shows them.
    public void listBooks() throws SQLException {
        
        jButton_next.setVisible(false);
        jButton_previous.setVisible(false);
        
        books = new ArrayList<>();
        
        //Connection to database 
        Verbindung db = new Verbindung();
        db.start();
        Connection conn = db.getVerbindung();
        
        Statement stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery("Select * from bought natural join book natural join user where uid = '" + user.getUid() + "'");

        while (rs.next()) {
    	   
        	System.out.println(rs.getCursorName());
        	
        	String mid   = rs.getString("mid");
            String date  = rs.getDate("time").toString();
            String title = rs.getString("title");
            String genre =  rs.getString("genre");
            String ageRating = rs.getString("agerating");
            String description = rs.getString("description");
            String releaseYear = rs.getString("releaseYear");
            String author = rs.getString("author");
            String picture =  rs.getString("picture");
            String price = rs.getString("price");
            String pdfLink = rs.getString("pdflink");
            String language = rs.getString("language");
            String language2 = rs.getString("language2");
            
            Book book = new Book(mid, title,picture, 
                                 null, description ,genre, ageRating, 
                                 releaseYear, "", 
                                 language, language2,price, pdfLink, author,date);
            
            
            books.add(book);
        }
           
        //Fill up the Arraylist with dump books
        while (books.size() % 5 != 0 || books.isEmpty()) {
            Book dump = new Book("", "", "", null, "", "", "", "", "", "", "", "", "", "");
            books.add(dump);
        }
        
        
       // rs.getString(null)

        jLabel_title1.setVisible(true);
        jLabel_dateOfBought.setVisible(true);
        jButton_readBook1.setVisible(true);
        jLabel_title2.setVisible(true);
        jLabel_dayOfBought2.setVisible(true);
        jButton_Download2.setVisible(true);
        jLabel_title3.setVisible(true);
        jLabel_dayOfBought3.setVisible(true);
        jButton_Download3.setVisible(true);
        jLabel_title4.setVisible(true);
        jLabel_dayOfBought4.setVisible(true);
        jButton_Download4.setVisible(true);
        jLabel_title5.setVisible(true);
        jLabel_dayOfBought5.setVisible(true);
        jButton_Download5.setVisible(true);

        
        System.out.println(books.get(0).getSaleDate());
        
        
        if (books.get(0 + count).getTitle().isEmpty()) {
            jLabel_title1.setVisible(false);
            jLabel_dateOfBought.setVisible(false);
            jButton_readBook1.setVisible(false);
        } 
        else {
            jLabel_title1.setText(books.get(0 + count).getTitle());
            jLabel_dateOfBought.setText(books.get(0 + count).getSaleDate());
            
        }

        if (books.get(1 + count).getTitle().isEmpty()) {
            jLabel_title2.setVisible(false);
            jLabel_dayOfBought2.setVisible(false);
            jButton_Download2.setVisible(false);
        } else {
            jLabel_title2.setText(books.get(1 + count).getTitle());
            jLabel_dayOfBought2.setText(books.get(1 + count).getSaleDate());
        }

        if (books.get(2 + count).getTitle().isEmpty()) {
            jLabel_title3.setVisible(false);
            jLabel_dayOfBought3.setVisible(false);
            jButton_Download3.setVisible(false);
        } else {
            jLabel_title3.setText(books.get(2 + count).getTitle());
            jLabel_dayOfBought3.setText(books.get(2 + count).getSaleDate());
        }
        
        if (books.get(3 + count).getTitle().isEmpty()) {
            jLabel_title4.setVisible(false);
            jLabel_dayOfBought4.setVisible(false);
            jButton_Download4.setVisible(false);
        } else {
            jLabel_title4.setText(books.get(3 + count).getTitle());
            jLabel_dayOfBought4.setText(books.get(3 + count).getSaleDate());
        }

        if (books.get(4 + count).getTitle().isEmpty()) {
            jLabel_title5.setVisible(false);
            jLabel_dayOfBought5.setVisible(false);
            jButton_Download5.setVisible(false);
        } else {
            jLabel_title5.setText(books.get(4 + count).getTitle());
            jLabel_dayOfBought5.setText(books.get(4 + count).getSaleDate());
        }

        if (books.size() - count > 5) {
            jButton_next.setVisible(true);
        } else {
            jButton_next.setVisible(false);
        }

        if (count != 0) {
            jButton_previous.setVisible(true);
        } else {
            jButton_previous.setVisible(false);
        }

    }//listBooks closing

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton_readBook8 = new javax.swing.JButton();
        jLabel_personalLibrary = new javax.swing.JLabel();
        jLabel_rentedBooks = new javax.swing.JLabel();
        jButton_Download5 = new javax.swing.JButton();
        jLabel_title5 = new javax.swing.JLabel();
        jLabel_dayOfBought5 = new javax.swing.JLabel();
        jButton_return = new javax.swing.JButton();
        jButton_readBook1 = new javax.swing.JButton();
        jLabel_title1 = new javax.swing.JLabel();
        jLabel_dateOfBought = new javax.swing.JLabel();
        jButton_Download2 = new javax.swing.JButton();
        jLabel_title2 = new javax.swing.JLabel();
        jLabel_dayOfBought2 = new javax.swing.JLabel();
        jButton_Download3 = new javax.swing.JButton();
        jLabel_title3 = new javax.swing.JLabel();
        jLabel_dayOfBought3 = new javax.swing.JLabel();
        jButton_Download4 = new javax.swing.JButton();
        jLabel_title4 = new javax.swing.JLabel();
        jLabel_dayOfBought4 = new javax.swing.JLabel();
        jButton_next = new javax.swing.JButton();
        jButton_previous = new javax.swing.JButton();
        jButton_Download = new javax.swing.JButton();
        jButton_readBook3 = new javax.swing.JButton();
        jButton_readBook2 = new javax.swing.JButton();
        jButton_readBook4 = new javax.swing.JButton();
        jButton_readBook5 = new javax.swing.JButton();

        jButton_readBook8.setText("Read in Browser");
        jButton_readBook8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_readBook8ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_personalLibrary.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel_personalLibrary.setText("Personal  Library");

        jLabel_rentedBooks.setText("Bought Books:");

        jButton_Download5.setText("Download");
        jButton_Download5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Download5ActionPerformed(evt);
            }
        });

        jLabel_title5.setText("Graphics under C");

        jLabel_dayOfBought5.setText("day of bought");

        jButton_return.setText("Return");
        jButton_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_returnActionPerformed(evt);
            }
        });

        jButton_readBook1.setText("Read in Browser");
        jButton_readBook1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_readBook1ActionPerformed(evt);
            }
        });

        jLabel_title1.setText("Graphics under C");

        jLabel_dateOfBought.setText("date of bought");

        jButton_Download2.setText("Download");
        jButton_Download2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Download2ActionPerformed(evt);
            }
        });

        jLabel_title2.setText("Graphics under C");

        jLabel_dayOfBought2.setText("day of bought");

        jButton_Download3.setText("Download");
        jButton_Download3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Download3ActionPerformed(evt);
            }
        });

        jLabel_title3.setText("Graphics under C");

        jLabel_dayOfBought3.setText("day of bought");

        jButton_Download4.setText("Download");
        jButton_Download4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Download4ActionPerformed(evt);
            }
        });

        jLabel_title4.setText("Graphics under C");

        jLabel_dayOfBought4.setText("day of bought");

        jButton_next.setText("Next");
        jButton_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nextActionPerformed(evt);
            }
        });

        jButton_previous.setText("Previous");
        jButton_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_previousActionPerformed(evt);
            }
        });

        jButton_Download.setText("Download ");
        jButton_Download.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DownloadActionPerformed(evt);
            }
        });

        jButton_readBook3.setText("Read in Browser");
        jButton_readBook3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_readBook3ActionPerformed(evt);
            }
        });

        jButton_readBook2.setText("Read in Browser");
        jButton_readBook2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_readBook2ActionPerformed(evt);
            }
        });

        jButton_readBook4.setText("Read in Browser");
        jButton_readBook4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_readBook4ActionPerformed(evt);
            }
        });

        jButton_readBook5.setText("Read in Browser");
        jButton_readBook5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_readBook5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_rentedBooks)
                            .addComponent(jLabel_personalLibrary))
                        .addGap(45, 45, 45))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_return)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_previous)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_next)
                                .addGap(8, 8, 8))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel_title1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                        .addComponent(jLabel_title2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel_title3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel_title4, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                    .addComponent(jLabel_title5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel_dayOfBought2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel_dayOfBought3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(20, 20, 20)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton_readBook3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton_Download3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton_readBook2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton_Download2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel_dayOfBought5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel_dayOfBought4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButton_readBook5, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                            .addComponent(jButton_readBook4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton_Download4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton_Download5))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel_dateOfBought, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(32, 32, 32)
                                        .addComponent(jButton_readBook1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton_Download, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(109, 109, 109))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jLabel_personalLibrary)
                .addGap(42, 42, 42)
                .addComponent(jLabel_rentedBooks)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_readBook1)
                    .addComponent(jLabel_title1)
                    .addComponent(jLabel_dateOfBought)
                    .addComponent(jButton_Download))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Download2)
                    .addComponent(jLabel_title2)
                    .addComponent(jLabel_dayOfBought2)
                    .addComponent(jButton_readBook2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Download3)
                    .addComponent(jLabel_title3)
                    .addComponent(jLabel_dayOfBought3)
                    .addComponent(jButton_readBook3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Download4)
                    .addComponent(jLabel_title4)
                    .addComponent(jLabel_dayOfBought4)
                    .addComponent(jButton_readBook4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Download5)
                    .addComponent(jLabel_title5)
                    .addComponent(jLabel_dayOfBought5)
                    .addComponent(jButton_readBook5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_return)
                    .addComponent(jButton_next)
                    .addComponent(jButton_previous))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextActionPerformed
        count += 5;
        try {
            listBooks();
        } catch (SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_nextActionPerformed

    private void jButton_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_previousActionPerformed
        count -= 5;
        try {
            listBooks();
        } catch (SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_previousActionPerformed

    private void jButton_readBook1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_readBook1ActionPerformed
        
        this.openBrowser("Hier kommt der Titel vom Buch");
        
        
    }//GEN-LAST:event_jButton_readBook1ActionPerformed

    private void jButton_Download2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Download2ActionPerformed
        JOptionPane.showMessageDialog(null, "This function is not included in this version.");
    }//GEN-LAST:event_jButton_Download2ActionPerformed

    private void jButton_Download3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Download3ActionPerformed
        JOptionPane.showMessageDialog(null, "This function is not included in this version.");
    }//GEN-LAST:event_jButton_Download3ActionPerformed

    private void jButton_Download4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Download4ActionPerformed
        JOptionPane.showMessageDialog(null, "This function is not included in this version.");
    }//GEN-LAST:event_jButton_Download4ActionPerformed

    private void jButton_Download5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Download5ActionPerformed
        JOptionPane.showMessageDialog(null, "This function is not included in this version.");
    }//GEN-LAST:event_jButton_Download5ActionPerformed

    private void jButton_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_returnActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton_returnActionPerformed

    private void jButton_DownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DownloadActionPerformed
       
        try {
            URL url = new URL("http://ramazan.bplaced.net/BookLibrary/Test.pdf");
            url.openConnection();
            InputStream in = url.openStream();
            
            in.close();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_DownloadActionPerformed

    private void jButton_readBook3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_readBook3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_readBook3ActionPerformed

    private void jButton_readBook2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_readBook2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_readBook2ActionPerformed

    private void jButton_readBook8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_readBook8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_readBook8ActionPerformed

    private void jButton_readBook4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_readBook4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_readBook4ActionPerformed

    private void jButton_readBook5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_readBook5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_readBook5ActionPerformed

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
            java.util.logging.Logger.getLogger(BookLibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookLibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookLibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookLibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new BookLibrary(new User()).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton_Download;
    private javax.swing.JButton jButton_Download2;
    private javax.swing.JButton jButton_Download3;
    private javax.swing.JButton jButton_Download4;
    private javax.swing.JButton jButton_Download5;
    private javax.swing.JButton jButton_next;
    private javax.swing.JButton jButton_previous;
    private javax.swing.JButton jButton_readBook1;
    private javax.swing.JButton jButton_readBook2;
    private javax.swing.JButton jButton_readBook3;
    private javax.swing.JButton jButton_readBook4;
    private javax.swing.JButton jButton_readBook5;
    private javax.swing.JButton jButton_readBook8;
    private javax.swing.JButton jButton_return;
    private javax.swing.JLabel jLabel_dateOfBought;
    private javax.swing.JLabel jLabel_dayOfBought2;
    private javax.swing.JLabel jLabel_dayOfBought3;
    private javax.swing.JLabel jLabel_dayOfBought4;
    private javax.swing.JLabel jLabel_dayOfBought5;
    private javax.swing.JLabel jLabel_personalLibrary;
    private javax.swing.JLabel jLabel_rentedBooks;
    private javax.swing.JLabel jLabel_title1;
    private javax.swing.JLabel jLabel_title2;
    private javax.swing.JLabel jLabel_title3;
    private javax.swing.JLabel jLabel_title4;
    private javax.swing.JLabel jLabel_title5;
    // End of variables declaration//GEN-END:variables

}
