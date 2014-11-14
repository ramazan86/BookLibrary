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
    ArrayList<Book> movies;

    public BookLibrary(User obj) throws SQLException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        user = obj;

        this.listMovies();
    }

    //Connects to the database, get's all rented movies of this user and shows them.
    public void listMovies() throws SQLException {
        jButtonNext.setVisible(false);
        jButtonPrevious.setVisible(false);
        movies = new ArrayList<>();
        Verbindung db = new Verbindung();
        db.start();
        Connection conn = db.getVerbindung();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select *,DATEDIFF(deadline,now()) as deadlinex from rents natural join movie natural join user where uid = '" + user.getUid() + "' having deadlinex >= 0 order by deadlinex");

        while (rs.next()) {
            Book movie = new Book(rs.getString("mid"), rs.getString("title"), rs.getString("picture"), null, rs.getString("description"), rs.getString("genre"), rs.getString("agerating"), rs.getString("releaseYear"), rs.getString("duration"), rs.getString("streamlink"), "", "", rs.getString("price"), rs.getString("deadlinex"));
            movies.add(movie);
        }

        //Fill up the Arraylist with dump movies
        while (movies.size() % 5 != 0 || movies.isEmpty()) {
            Book dump = new Book("", "", "", null, "", "", "", "", "", "", "", "", "", "");
            movies.add(dump);
        }

        jLabelTitle1.setVisible(true);
        jLabelDeadline1.setVisible(true);
        jButtonExtend1.setVisible(true);
        jButtonWatchMovie1.setVisible(true);
        jLabelTitle2.setVisible(true);
        jLabelDeadline2.setVisible(true);
        jButtonExtend2.setVisible(true);
        jButtonWatchMovie2.setVisible(true);
        jLabelTitle3.setVisible(true);
        jLabelDeadline3.setVisible(true);
        jButtonExtend3.setVisible(true);
        jButtonWatchMovie3.setVisible(true);
        jLabelTitle4.setVisible(true);
        jLabelDeadline4.setVisible(true);
        jButtonExtend4.setVisible(true);
        jButtonWatchMovie4.setVisible(true);
        jLabelTitle5.setVisible(true);
        jLabelDeadline5.setVisible(true);
        jButtonExtend5.setVisible(true);
        jButtonWatchMovie5.setVisible(true);

        if (movies.get(0 + count).getTitle().isEmpty()) {
            jLabelTitle1.setVisible(false);
            jLabelDeadline1.setVisible(false);
            jButtonExtend1.setVisible(false);
            jButtonWatchMovie1.setVisible(false);
        } else {
            jLabelTitle1.setText(movies.get(0 + count).getTitle());
            if (movies.get(0 + count).getDeadline().equals("1")) {
                jLabelDeadline1.setText(movies.get(0 + count).getDeadline() + " Day left");
            } else {
                jLabelDeadline1.setText(movies.get(0 + count).getDeadline() + " Days left");
            }
        }

        if (movies.get(1 + count).getTitle().isEmpty()) {
            jLabelTitle2.setVisible(false);
            jLabelDeadline2.setVisible(false);
            jButtonExtend2.setVisible(false);
            jButtonWatchMovie2.setVisible(false);
        } else {
            jLabelTitle2.setText(movies.get(1 + count).getTitle());
            if (movies.get(1 + count).getDeadline().equals("1")) {
                jLabelDeadline2.setText(movies.get(1 + count).getDeadline() + " Day left");
            } else {
                jLabelDeadline2.setText(movies.get(1 + count).getDeadline() + " Days left");
            }
        }

        if (movies.get(2 + count).getTitle().isEmpty()) {
            jLabelTitle3.setVisible(false);
            jLabelDeadline3.setVisible(false);
            jButtonExtend3.setVisible(false);
            jButtonWatchMovie3.setVisible(false);
        } else {
            jLabelTitle3.setText(movies.get(2 + count).getTitle());
            if (movies.get(2 + count).getDeadline().equals("1")) {
                jLabelDeadline3.setText(movies.get(2 + count).getDeadline() + " Day left");
            } else {
                jLabelDeadline3.setText(movies.get(2 + count).getDeadline() + " Days left");
            }
        }

        if (movies.get(3 + count).getTitle().isEmpty()) {
            jLabelTitle4.setVisible(false);
            jLabelDeadline4.setVisible(false);
            jButtonExtend4.setVisible(false);
            jButtonWatchMovie4.setVisible(false);
        } else {
            jLabelTitle4.setText(movies.get(3 + count).getTitle());
            if (movies.get(3 + count).getDeadline().equals("1")) {
                jLabelDeadline4.setText(movies.get(3 + count).getDeadline() + " Day left");
            } else {
                jLabelDeadline4.setText(movies.get(3 + count).getDeadline() + " Days left");
            }
        }

        if (movies.get(4 + count).getTitle().isEmpty()) {
            jLabelTitle5.setVisible(false);
            jLabelDeadline5.setVisible(false);
            jButtonExtend5.setVisible(false);
            jButtonWatchMovie5.setVisible(false);
        } else {
            jLabelTitle5.setText(movies.get(4 + count).getTitle());
            if (movies.get(4 + count).getDeadline().equals("1")) {
                jLabelDeadline5.setText(movies.get(4 + count).getDeadline() + " Day left");
            } else {
                jLabelDeadline5.setText(movies.get(4 + count).getDeadline() + " Days left");
            }
        }

        if (movies.size() - count > 5) {
            jButtonNext.setVisible(true);
        } else {
            jButtonNext.setVisible(false);
        }

        if (count != 0) {
            jButtonPrevious.setVisible(true);
        } else {
            jButtonPrevious.setVisible(false);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel_logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonWatchMovie5 = new javax.swing.JButton();
        jLabelTitle5 = new javax.swing.JLabel();
        jLabelDeadline5 = new javax.swing.JLabel();
        jButtonExtend5 = new javax.swing.JButton();
        jButtonReturn = new javax.swing.JButton();
        jButtonWatchMovie1 = new javax.swing.JButton();
        jLabelTitle1 = new javax.swing.JLabel();
        jLabelDeadline1 = new javax.swing.JLabel();
        jButtonExtend1 = new javax.swing.JButton();
        jButtonWatchMovie2 = new javax.swing.JButton();
        jLabelTitle2 = new javax.swing.JLabel();
        jLabelDeadline2 = new javax.swing.JLabel();
        jButtonExtend2 = new javax.swing.JButton();
        jButtonWatchMovie3 = new javax.swing.JButton();
        jLabelTitle3 = new javax.swing.JLabel();
        jLabelDeadline3 = new javax.swing.JLabel();
        jButtonExtend3 = new javax.swing.JButton();
        jButtonWatchMovie4 = new javax.swing.JButton();
        jLabelTitle4 = new javax.swing.JLabel();
        jLabelDeadline4 = new javax.swing.JLabel();
        jButtonExtend4 = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jButtonPrevious = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movierental/Logo.png"))); // NOI18N
        jLabel_logo.setText("jLabel2");

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel1.setText("Personal Video Library");

        jLabel3.setText("Rented Movies:");

        jButtonWatchMovie5.setText("Watch Movie");
        jButtonWatchMovie5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWatchMovie5ActionPerformed(evt);
            }
        });

        jLabelTitle5.setText("Turbo - Kleine Schnecke, großer Traum");

        jLabelDeadline5.setText("1 day left");

        jButtonExtend5.setText("Extend");
        jButtonExtend5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExtend5ActionPerformed(evt);
            }
        });

        jButtonReturn.setText("Return");
        jButtonReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturnActionPerformed(evt);
            }
        });

        jButtonWatchMovie1.setText("Watch Movie");
        jButtonWatchMovie1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWatchMovie1ActionPerformed(evt);
            }
        });

        jLabelTitle1.setText("Turbo - Kleine Schnecke, großer Traum");

        jLabelDeadline1.setText("1 day left");

        jButtonExtend1.setText("Extend");
        jButtonExtend1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExtend1ActionPerformed(evt);
            }
        });

        jButtonWatchMovie2.setText("Watch Movie");
        jButtonWatchMovie2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWatchMovie2ActionPerformed(evt);
            }
        });

        jLabelTitle2.setText("Turbo - Kleine Schnecke, großer Traum");

        jLabelDeadline2.setText("1 day left");

        jButtonExtend2.setText("Extend");
        jButtonExtend2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExtend2ActionPerformed(evt);
            }
        });

        jButtonWatchMovie3.setText("Watch Movie");
        jButtonWatchMovie3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWatchMovie3ActionPerformed(evt);
            }
        });

        jLabelTitle3.setText("Turbo - Kleine Schnecke, großer Traum");

        jLabelDeadline3.setText("1 day left");

        jButtonExtend3.setText("Extend");
        jButtonExtend3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExtend3ActionPerformed(evt);
            }
        });

        jButtonWatchMovie4.setText("Watch Movie");
        jButtonWatchMovie4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWatchMovie4ActionPerformed(evt);
            }
        });

        jLabelTitle4.setText("Turbo - Kleine Schnecke, großer Traum");

        jLabelDeadline4.setText("1 day left");

        jButtonExtend4.setText("Extend");
        jButtonExtend4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExtend4ActionPerformed(evt);
            }
        });

        jButtonNext.setText("Next");
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        jButtonPrevious.setText("Previous");
        jButtonPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviousActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonReturn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonPrevious))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                    .addComponent(jLabelTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelTitle3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelDeadline1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonWatchMovie1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabelDeadline3, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                            .addComponent(jLabelDeadline2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButtonWatchMovie2, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButtonWatchMovie3, javax.swing.GroupLayout.Alignment.TRAILING)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTitle4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelTitle5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelDeadline4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonWatchMovie4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelDeadline5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonWatchMovie5)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jButtonNext))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonExtend2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButtonExtend1)
                                .addComponent(jButtonExtend3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButtonExtend4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButtonExtend5, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonWatchMovie1)
                    .addComponent(jLabelTitle1)
                    .addComponent(jLabelDeadline1)
                    .addComponent(jButtonExtend1))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonWatchMovie2)
                    .addComponent(jLabelTitle2)
                    .addComponent(jLabelDeadline2)
                    .addComponent(jButtonExtend2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonWatchMovie3)
                    .addComponent(jLabelTitle3)
                    .addComponent(jLabelDeadline3)
                    .addComponent(jButtonExtend3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonWatchMovie4)
                    .addComponent(jLabelTitle4)
                    .addComponent(jLabelDeadline4)
                    .addComponent(jButtonExtend4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonWatchMovie5)
                    .addComponent(jLabelTitle5)
                    .addComponent(jLabelDeadline5)
                    .addComponent(jButtonExtend5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonReturn)
                    .addComponent(jButtonNext)
                    .addComponent(jButtonPrevious))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExtend1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExtend1ActionPerformed
        try {
            if (user.getPrename().equals("") || user.getSurname().equals("") || user.getStreet().equals("") || user.getZipcode().equals("") || user.getCity().equals("") || user.getIban() == null || user.getBic() == null || user.getIban().equals("") || user.getBic().equals("")) {
                JOptionPane.showMessageDialog(null, "You have to change your account information and fill in all fields.");
            } else {
                dispose();
                Rent rent = new Rent(user, movies.get(0 + count));
                rent.setPrevious(1);
                rent.setVisible(true);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonExtend1ActionPerformed

    private void jButtonExtend2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExtend2ActionPerformed
        try {
            if (user.getPrename().equals("") || user.getSurname().equals("") || user.getStreet().equals("") || user.getZipcode().equals("") || user.getCity().equals("") || user.getIban() == null || user.getBic() == null || user.getIban().equals("") || user.getBic().equals("")) {
                JOptionPane.showMessageDialog(null, "You have to change your account information and fill in all fields.");
            } else {
                dispose();
                Rent rent = new Rent(user, movies.get(1 + count));
                rent.setPrevious(1);
                rent.setVisible(true);
            }
        } catch (MalformedURLException ex) {
        } catch (SQLException ex) {
        } catch (SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonExtend2ActionPerformed

    private void jButtonExtend3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExtend3ActionPerformed
        try {
            if (user.getPrename().equals("") || user.getSurname().equals("") || user.getStreet().equals("") || user.getZipcode().equals("") || user.getCity().equals("") || user.getIban() == null || user.getBic() == null || user.getIban().equals("") || user.getBic().equals("")) {
                JOptionPane.showMessageDialog(null, "You have to change your account information and fill in all fields.");
            } else {
                dispose();
                Rent rent = new Rent(user, movies.get(2 + count));
                rent.setPrevious(1);
                rent.setVisible(true);
            }
        } catch (MalformedURLException | SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }    }//GEN-LAST:event_jButtonExtend3ActionPerformed

    private void jButtonExtend4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExtend4ActionPerformed
        try {
            if (user.getPrename().equals("") || user.getSurname().equals("") || user.getStreet().equals("") || user.getZipcode().equals("") || user.getCity().equals("") || user.getIban() == null || user.getBic() == null || user.getIban().equals("") || user.getBic().equals("")) {
                JOptionPane.showMessageDialog(null, "You have to change your account information and fill in all fields.");
            } else {
                dispose();
                Rent rent = new Rent(user, movies.get(3 + count));
                rent.setPrevious(1);
                rent.setVisible(true);
            }
        } catch (MalformedURLException | SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonExtend4ActionPerformed

    private void jButtonExtend5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExtend5ActionPerformed
        try {
            if (user.getPrename().equals("") || user.getSurname().equals("") || user.getStreet().equals("") || user.getZipcode().equals("") || user.getCity().equals("") || user.getIban() == null || user.getBic() == null || user.getIban().equals("") || user.getBic().equals("")) {
                JOptionPane.showMessageDialog(null, "You have to change your account information and fill in all fields.");
            } else {
                dispose();
                Rent rent = new Rent(user, movies.get(4 + count));
                rent.setPrevious(1);
                rent.setVisible(true);
            }
        } catch (MalformedURLException | SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonExtend5ActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        count += 5;
        try {
            listMovies();
        } catch (SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jButtonPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviousActionPerformed
        count -= 5;
        try {
            listMovies();
        } catch (SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonPreviousActionPerformed

    private void jButtonWatchMovie1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWatchMovie1ActionPerformed
        JOptionPane.showMessageDialog(null, "This function is not included in this version.");
    }//GEN-LAST:event_jButtonWatchMovie1ActionPerformed

    private void jButtonWatchMovie2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWatchMovie2ActionPerformed
        JOptionPane.showMessageDialog(null, "This function is not included in this version.");
    }//GEN-LAST:event_jButtonWatchMovie2ActionPerformed

    private void jButtonWatchMovie3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWatchMovie3ActionPerformed
        JOptionPane.showMessageDialog(null, "This function is not included in this version.");
    }//GEN-LAST:event_jButtonWatchMovie3ActionPerformed

    private void jButtonWatchMovie4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWatchMovie4ActionPerformed
        JOptionPane.showMessageDialog(null, "This function is not included in this version.");
    }//GEN-LAST:event_jButtonWatchMovie4ActionPerformed

    private void jButtonWatchMovie5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWatchMovie5ActionPerformed
        JOptionPane.showMessageDialog(null, "This function is not included in this version.");
    }//GEN-LAST:event_jButtonWatchMovie5ActionPerformed

    private void jButtonReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturnActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonReturnActionPerformed

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
    private javax.swing.JButton jButtonExtend1;
    private javax.swing.JButton jButtonExtend2;
    private javax.swing.JButton jButtonExtend3;
    private javax.swing.JButton jButtonExtend4;
    private javax.swing.JButton jButtonExtend5;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrevious;
    private javax.swing.JButton jButtonReturn;
    private javax.swing.JButton jButtonWatchMovie1;
    private javax.swing.JButton jButtonWatchMovie2;
    private javax.swing.JButton jButtonWatchMovie3;
    private javax.swing.JButton jButtonWatchMovie4;
    private javax.swing.JButton jButtonWatchMovie5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelDeadline1;
    private javax.swing.JLabel jLabelDeadline2;
    private javax.swing.JLabel jLabelDeadline3;
    private javax.swing.JLabel jLabelDeadline4;
    private javax.swing.JLabel jLabelDeadline5;
    private javax.swing.JLabel jLabelTitle1;
    private javax.swing.JLabel jLabelTitle2;
    private javax.swing.JLabel jLabelTitle3;
    private javax.swing.JLabel jLabelTitle4;
    private javax.swing.JLabel jLabelTitle5;
    private javax.swing.JLabel jLabel_logo;
    // End of variables declaration//GEN-END:variables

}
