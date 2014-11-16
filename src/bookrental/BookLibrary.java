package bookrental;

import java.net.MalformedURLException;
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

    //Connects to the database, get's all rented Books of this user and shows them.
    public void listBooks() throws SQLException {
        jButton_next.setVisible(false);
        jButton_previous.setVisible(false);
        books = new ArrayList<>();
        Verbindung db = new Verbindung();
        db.start();
        Connection conn = db.getVerbindung();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select *,DATEDIFF(deadline,now()) as deadlinex from rents natural join book natural join user where uid = '" + user.getUid() + "' having deadlinex >= 0 order by deadlinex");

        while (rs.next()) {
            Book book = new Book(rs.getString("mid"), rs.getString("title"), rs.getString("picture"), null, rs.getString("description"), rs.getString("genre"), rs.getString("agerating"), rs.getString("releaseYear"), rs.getString("duration"), rs.getString("streamlink"), "", "", rs.getString("price"), rs.getString("deadlinex"));
            books.add(book);
        }

        //Fill up the Arraylist with dump books
        while (books.size() % 5 != 0 || books.isEmpty()) {
            Book dump = new Book("", "", "", null, "", "", "", "", "", "", "", "", "", "");
            books.add(dump);
        }

        jLabel_title1.setVisible(true);
        jLabel_deadline1.setVisible(true);
        jButton_extend1.setVisible(true);
        jButton_readBook1.setVisible(true);
        jLabel_title2.setVisible(true);
        jLabel_deadline2.setVisible(true);
        jButton_extend2.setVisible(true);
        jButton_readBook2.setVisible(true);
        jLabel_title3.setVisible(true);
        jLabel_deadline3.setVisible(true);
        jButton_extend3.setVisible(true);
        jButton_readBook3.setVisible(true);
        jLabel_title4.setVisible(true);
        jLabel_deadline4.setVisible(true);
        jButton_extend4.setVisible(true);
        jButton_readBook4.setVisible(true);
        jLabel_title5.setVisible(true);
        jLabel_deadline5.setVisible(true);
        jButton_extend5.setVisible(true);
        jButton_readBook5.setVisible(true);

        if (books.get(0 + count).getTitle().isEmpty()) {
            jLabel_title1.setVisible(false);
            jLabel_deadline1.setVisible(false);
            jButton_extend1.setVisible(false);
            jButton_readBook1.setVisible(false);
        } else {
            jLabel_title1.setText(books.get(0 + count).getTitle());
            if (books.get(0 + count).getDeadline().equals("1")) {
                jLabel_deadline1.setText(books.get(0 + count).getDeadline() + " Day left");
            } else {
                jLabel_deadline1.setText(books.get(0 + count).getDeadline() + " Days left");
            }
        }

        if (books.get(1 + count).getTitle().isEmpty()) {
            jLabel_title2.setVisible(false);
            jLabel_deadline2.setVisible(false);
            jButton_extend2.setVisible(false);
            jButton_readBook2.setVisible(false);
        } else {
            jLabel_title2.setText(books.get(1 + count).getTitle());
            if (books.get(1 + count).getDeadline().equals("1")) {
                jLabel_deadline2.setText(books.get(1 + count).getDeadline() + " Day left");
            } else {
                jLabel_deadline2.setText(books.get(1 + count).getDeadline() + " Days left");
            }
        }

        if (books.get(2 + count).getTitle().isEmpty()) {
            jLabel_title3.setVisible(false);
            jLabel_deadline3.setVisible(false);
            jButton_extend3.setVisible(false);
            jButton_readBook3.setVisible(false);
        } else {
            jLabel_title3.setText(books.get(2 + count).getTitle());
            if (books.get(2 + count).getDeadline().equals("1")) {
                jLabel_deadline3.setText(books.get(2 + count).getDeadline() + " Day left");
            } else {
                jLabel_deadline3.setText(books.get(2 + count).getDeadline() + " Days left");
            }
        }

        if (books.get(3 + count).getTitle().isEmpty()) {
            jLabel_title4.setVisible(false);
            jLabel_deadline4.setVisible(false);
            jButton_extend4.setVisible(false);
            jButton_readBook4.setVisible(false);
        } else {
            jLabel_title4.setText(books.get(3 + count).getTitle());
            if (books.get(3 + count).getDeadline().equals("1")) {
                jLabel_deadline4.setText(books.get(3 + count).getDeadline() + " Day left");
            } else {
                jLabel_deadline4.setText(books.get(3 + count).getDeadline() + " Days left");
            }
        }

        if (books.get(4 + count).getTitle().isEmpty()) {
            jLabel_title5.setVisible(false);
            jLabel_deadline5.setVisible(false);
            jButton_extend5.setVisible(false);
            jButton_readBook5.setVisible(false);
        } else {
            jLabel_title5.setText(books.get(4 + count).getTitle());
            if (books.get(4 + count).getDeadline().equals("1")) {
                jLabel_deadline5.setText(books.get(4 + count).getDeadline() + " Day left");
            } else {
                jLabel_deadline5.setText(books.get(4 + count).getDeadline() + " Days left");
            }
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

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel_logo = new javax.swing.JLabel();
        jLabel_personalLibrary = new javax.swing.JLabel();
        jLabel_rentedBooks = new javax.swing.JLabel();
        jButton_readBook5 = new javax.swing.JButton();
        jLabel_title5 = new javax.swing.JLabel();
        jLabel_deadline5 = new javax.swing.JLabel();
        jButton_extend5 = new javax.swing.JButton();
        jButton_return = new javax.swing.JButton();
        jButton_readBook1 = new javax.swing.JButton();
        jLabel_title1 = new javax.swing.JLabel();
        jLabel_deadline1 = new javax.swing.JLabel();
        jButton_extend1 = new javax.swing.JButton();
        jButton_readBook2 = new javax.swing.JButton();
        jLabel_title2 = new javax.swing.JLabel();
        jLabel_deadline2 = new javax.swing.JLabel();
        jButton_extend2 = new javax.swing.JButton();
        jButton_readBook3 = new javax.swing.JButton();
        jLabel_title3 = new javax.swing.JLabel();
        jLabel_deadline3 = new javax.swing.JLabel();
        jButton_extend3 = new javax.swing.JButton();
        jButton_readBook4 = new javax.swing.JButton();
        jLabel_title4 = new javax.swing.JLabel();
        jLabel_deadline4 = new javax.swing.JLabel();
        jButton_extend4 = new javax.swing.JButton();
        jButton_next = new javax.swing.JButton();
        jButton_previous = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png"))); // NOI18N
        jLabel_logo.setText("jLabel2");

        jLabel_personalLibrary.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel_personalLibrary.setText("Personal  Library");

        jLabel_rentedBooks.setText("Rented Books:");

        jButton_readBook5.setText("Read Book");
        jButton_readBook5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_readBook5ActionPerformed(evt);
            }
        });

        jLabel_title5.setText("Graphics under C");

        jLabel_deadline5.setText("1 day left");

        jButton_extend5.setText("Extend");
        jButton_extend5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_extend5ActionPerformed(evt);
            }
        });

        jButton_return.setText("Return");
        jButton_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_returnActionPerformed(evt);
            }
        });

        jButton_readBook1.setText("Read Book");
        jButton_readBook1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_readBook1ActionPerformed(evt);
            }
        });

        jLabel_title1.setText("Graphics under C");

        jLabel_deadline1.setText("1 day left");

        jButton_extend1.setText("Extend");
        jButton_extend1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_extend1ActionPerformed(evt);
            }
        });

        jButton_readBook2.setText("Read Book");
        jButton_readBook2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_readBook2ActionPerformed(evt);
            }
        });

        jLabel_title2.setText("Graphics under C");

        jLabel_deadline2.setText("1 day left");

        jButton_extend2.setText("Extend");
        jButton_extend2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_extend2ActionPerformed(evt);
            }
        });

        jButton_readBook3.setText("Read Book");
        jButton_readBook3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_readBook3ActionPerformed(evt);
            }
        });

        jLabel_title3.setText("Graphics under C");

        jLabel_deadline3.setText("1 day left");

        jButton_extend3.setText("Extend");
        jButton_extend3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_extend3ActionPerformed(evt);
            }
        });

        jButton_readBook4.setText("Read Book");
        jButton_readBook4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_readBook4ActionPerformed(evt);
            }
        });

        jLabel_title4.setText("Graphics under C");

        jLabel_deadline4.setText("1 day left");

        jButton_extend4.setText("Extend");
        jButton_extend4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_extend4ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_rentedBooks)
                    .addComponent(jLabel_personalLibrary)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_return)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_previous))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_title1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                    .addComponent(jLabel_title2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel_title3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel_deadline1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton_readBook1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel_deadline3, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                            .addComponent(jLabel_deadline2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton_readBook2, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton_readBook3, javax.swing.GroupLayout.Alignment.TRAILING)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_title4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel_title5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel_deadline4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton_readBook4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel_deadline5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton_readBook5)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jButton_next))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton_extend2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton_extend1)
                                .addComponent(jButton_extend3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton_extend4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton_extend5, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_personalLibrary)
                .addGap(42, 42, 42)
                .addComponent(jLabel_rentedBooks)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_readBook1)
                    .addComponent(jLabel_title1)
                    .addComponent(jLabel_deadline1)
                    .addComponent(jButton_extend1))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_readBook2)
                    .addComponent(jLabel_title2)
                    .addComponent(jLabel_deadline2)
                    .addComponent(jButton_extend2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_readBook3)
                    .addComponent(jLabel_title3)
                    .addComponent(jLabel_deadline3)
                    .addComponent(jButton_extend3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_readBook4)
                    .addComponent(jLabel_title4)
                    .addComponent(jLabel_deadline4)
                    .addComponent(jButton_extend4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_readBook5)
                    .addComponent(jLabel_title5)
                    .addComponent(jLabel_deadline5)
                    .addComponent(jButton_extend5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_return)
                    .addComponent(jButton_next)
                    .addComponent(jButton_previous))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_extend1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_extend1ActionPerformed
        try {
            if (user.getPrename().equals("") || user.getSurname().equals("") || user.getStreet().equals("") || user.getZipcode().equals("") || user.getCity().equals("") || user.getIban() == null || user.getBic() == null || user.getIban().equals("") || user.getBic().equals("")) {
                JOptionPane.showMessageDialog(null, "You have to change your account information and fill in all fields.");
            } else {
                dispose();
                Rent rent = new Rent(user, books.get(0 + count));
                rent.setPrevious(1);
                rent.setVisible(true);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_extend1ActionPerformed

    private void jButton_extend2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_extend2ActionPerformed
        try {
            if (user.getPrename().equals("") || user.getSurname().equals("") || user.getStreet().equals("") || user.getZipcode().equals("") || user.getCity().equals("") || user.getIban() == null || user.getBic() == null || user.getIban().equals("") || user.getBic().equals("")) {
                JOptionPane.showMessageDialog(null, "You have to change your account information and fill in all fields.");
            } else {
                dispose();
                Rent rent = new Rent(user,books.get(1 + count));
                rent.setPrevious(1);
                rent.setVisible(true);
            }
        } catch (MalformedURLException ex) {
        }  catch (SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_extend2ActionPerformed

    private void jButton_extend3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_extend3ActionPerformed
        try {
            if (user.getPrename().equals("") || user.getSurname().equals("") || user.getStreet().equals("") || user.getZipcode().equals("") || user.getCity().equals("") || user.getIban() == null || user.getBic() == null || user.getIban().equals("") || user.getBic().equals("")) {
                JOptionPane.showMessageDialog(null, "You have to change your account information and fill in all fields.");
            } else {
                dispose();
                Rent rent = new Rent(user, books.get(2 + count));
                rent.setPrevious(1);
                rent.setVisible(true);
            }
        } catch (MalformedURLException | SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }    }//GEN-LAST:event_jButton_extend3ActionPerformed

    private void jButton_extend4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_extend4ActionPerformed
        try {
            if (user.getPrename().equals("") || user.getSurname().equals("") || user.getStreet().equals("") || user.getZipcode().equals("") || user.getCity().equals("") || user.getIban() == null || user.getBic() == null || user.getIban().equals("") || user.getBic().equals("")) {
                JOptionPane.showMessageDialog(null, "You have to change your account information and fill in all fields.");
            } else {
                dispose();
                Rent rent = new Rent(user, books.get(3 + count));
                rent.setPrevious(1);
                rent.setVisible(true);
            }
        } catch (MalformedURLException | SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_extend4ActionPerformed

    private void jButton_extend5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_extend5ActionPerformed
        try {
            if (user.getPrename().equals("") || user.getSurname().equals("") || user.getStreet().equals("") || user.getZipcode().equals("") || user.getCity().equals("") || user.getIban() == null || user.getBic() == null || user.getIban().equals("") || user.getBic().equals("")) {
                JOptionPane.showMessageDialog(null, "You have to change your account information and fill in all fields.");
            } else {
                dispose();
                Rent rent = new Rent(user, books.get(4 + count));
                rent.setPrevious(1);
                rent.setVisible(true);
            }
        } catch (MalformedURLException | SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_extend5ActionPerformed

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
        JOptionPane.showMessageDialog(null, "This function is not included in this version.");
    }//GEN-LAST:event_jButton_readBook1ActionPerformed

    private void jButton_readBook2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_readBook2ActionPerformed
        JOptionPane.showMessageDialog(null, "This function is not included in this version.");
    }//GEN-LAST:event_jButton_readBook2ActionPerformed

    private void jButton_readBook3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_readBook3ActionPerformed
        JOptionPane.showMessageDialog(null, "This function is not included in this version.");
    }//GEN-LAST:event_jButton_readBook3ActionPerformed

    private void jButton_readBook4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_readBook4ActionPerformed
        JOptionPane.showMessageDialog(null, "This function is not included in this version.");
    }//GEN-LAST:event_jButton_readBook4ActionPerformed

    private void jButton_readBook5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_readBook5ActionPerformed
        JOptionPane.showMessageDialog(null, "This function is not included in this version.");
    }//GEN-LAST:event_jButton_readBook5ActionPerformed

    private void jButton_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_returnActionPerformed
        this.dispose();
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
    private javax.swing.JButton jButton_extend1;
    private javax.swing.JButton jButton_extend2;
    private javax.swing.JButton jButton_extend3;
    private javax.swing.JButton jButton_extend4;
    private javax.swing.JButton jButton_extend5;
    private javax.swing.JButton jButton_next;
    private javax.swing.JButton jButton_previous;
    private javax.swing.JButton jButton_readBook1;
    private javax.swing.JButton jButton_readBook2;
    private javax.swing.JButton jButton_readBook3;
    private javax.swing.JButton jButton_readBook4;
    private javax.swing.JButton jButton_readBook5;
    private javax.swing.JButton jButton_return;
    private javax.swing.JLabel jLabel_deadline1;
    private javax.swing.JLabel jLabel_deadline2;
    private javax.swing.JLabel jLabel_deadline3;
    private javax.swing.JLabel jLabel_deadline4;
    private javax.swing.JLabel jLabel_deadline5;
    private javax.swing.JLabel jLabel_logo;
    private javax.swing.JLabel jLabel_personalLibrary;
    private javax.swing.JLabel jLabel_rentedBooks;
    private javax.swing.JLabel jLabel_title1;
    private javax.swing.JLabel jLabel_title2;
    private javax.swing.JLabel jLabel_title3;
    private javax.swing.JLabel jLabel_title4;
    private javax.swing.JLabel jLabel_title5;
    // End of variables declaration//GEN-END:variables

}
