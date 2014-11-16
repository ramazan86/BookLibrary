package bookrental;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Login extends javax.swing.JFrame { // Login, Register and ForgottenPassword Function

    Login login;
    User user = new User();
    String uid, username, password, email, isAdmin, activCode, activated, lastLogin, birthday, prename, surname, address, zipcode, city, iban, bic;
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Book> books2 = new ArrayList<>();
    String suchetext, gen, pri, age, rate, lang;
    int genre, price, agerating, rating, language, pages;
    Verbindung db;
    Connection conn;
    Statement stmt, stmt2, stmt3, stmt4, stmtNewest, stmtNewest2, stmtSearch, stmtTop10, stmt2Top10;
    ResultSet rs, rs2, rs3, rsNewest, rsNewest2, rsSearch, rsTop10, rs2Top10;
    static int seitenanzahl = 0; // Checks wether the user has pressed next after searching

    public Login() throws IOException, SQLException {
        user.setUid("0");
        initComponents();
        this.setSize(870, 700);
        setLocationRelativeTo(null);
        this.Newest10();
        this.Top10();
        this.setVisible(true);
        getRootPane().setDefaultButton(jButton_login);
        jButton_previous.setVisible(false);
        jButton_next.setVisible(false);
        jButton_return.setVisible(false);
    }

    // Shows the result of the search
    public void searchResult(ArrayList<Book> books2) throws MalformedURLException {

        jLabel_img1.setVisible(false);
        jLabel_img2.setVisible(false);
        jLabel_img3.setVisible(false);
        jLabel_img4.setVisible(false);
        jLabel_img5.setVisible(false);
        jLabel_img6.setVisible(false);
        jLabel_img7.setVisible(false);
        jLabel_img8.setVisible(false);
        jLabel_img9.setVisible(false);
        jLabel_img10.setVisible(false);
        jLabel_img11.setVisible(false);
        jLabel_img19.setVisible(false);
        jLabel_img12.setVisible(false);
        jLabel_img14.setVisible(false);
        jLabel_img15.setVisible(false);
        jLabel_img17.setVisible(false);
        jLabe_img18.setVisible(false);
        jLabel_img20.setVisible(false);
        jLabel_img13.setVisible(false);
        jLabel_img16.setVisible(false);

        jLabel_top10.setVisible(false);
        jLabel_newest.setText("Search Result for '" + suchetext + "':");

        if (!(books2.get(0 + seitenanzahl).getTitle().equals(""))) {
            jLabel_img1.setIcon(new ImageIcon(new URL(books2.get(0 + seitenanzahl).getImglink())));
            jLabel_img1.setText(null);
            jLabel_img1.setVisible(true);
        } else {
            jLabel_img1.setVisible(false);
        }
        if (!(books2.get(1 + seitenanzahl).getTitle().equals(""))) {
            jLabel_img2.setIcon(new ImageIcon(new URL(books2.get(1 + seitenanzahl).getImglink())));
            jLabel_img2.setText(null);
            jLabel_img2.setVisible(true);
        } else {
            jLabel_img2.setVisible(false);
        }
        if (!(books2.get(2 + seitenanzahl).getTitle().equals(""))) {
            jLabel_img3.setIcon(new ImageIcon(new URL(books2.get(2 + seitenanzahl).getImglink())));
            jLabel_img3.setText(null);
            jLabel_img3.setVisible(true);
        } else {
            jLabel_img3.setVisible(false);
        }
        if (!(books2.get(3 + seitenanzahl).getTitle().equals(""))) {
            jLabel_img4.setIcon(new ImageIcon(new URL(books2.get(3 + seitenanzahl).getImglink())));
            jLabel_img4.setText(null);
            jLabel_img4.setVisible(true);
        } else {
            jLabel_img4.setVisible(false);
        }
        if (!(books2.get(4 + seitenanzahl).getTitle().equals(""))) {
            jLabel_img5.setIcon(new ImageIcon(new URL(books2.get(4 + seitenanzahl).getImglink())));
            jLabel_img5.setText(null);
            jLabel_img5.setVisible(true);
        } else {
            jLabel_img5.setVisible(false);
        }
        if (!(books2.get(5 + seitenanzahl).getTitle().equals(""))) {
            jLabel_img6.setIcon(new ImageIcon(new URL(books2.get(5 + seitenanzahl).getImglink())));
            jLabel_img6.setText(null);
            jLabel_img6.setVisible(true);
        } else {
            jLabel_img6.setVisible(false);
        }
        if (!(books2.get(6 + seitenanzahl).getTitle().equals(""))) {
            jLabel_img7.setIcon(new ImageIcon(new URL(books2.get(6 + seitenanzahl).getImglink())));
            jLabel_img7.setText(null);
            jLabel_img7.setVisible(true);
        } else {
            jLabel_img7.setVisible(false);
        }
        if (!(books2.get(7 + seitenanzahl).getTitle().equals(""))) {
            jLabel_img8.setIcon(new ImageIcon(new URL(books2.get(7 + seitenanzahl).getImglink())));
            jLabel_img8.setText(null);
            jLabel_img8.setVisible(true);
        } else {
            jLabel_img8.setVisible(false);
        }
        if (!(books2.get(8 + seitenanzahl).getTitle().equals(""))) {
            jLabel_img9.setIcon(new ImageIcon(new URL(books2.get(8 + seitenanzahl).getImglink())));
            jLabel_img9.setText(null);
            jLabel_img9.setVisible(true);
        } else {
            jLabel_img9.setVisible(false);
        }
        if (!(books2.get(9 + seitenanzahl).getTitle().equals(""))) {
            jLabel_img10.setIcon(new ImageIcon(new URL(books2.get(9 + seitenanzahl).getImglink())));
            jLabel_img10.setText(null);
            jLabel_img10.setVisible(true);
        } else {
            jLabel_img10.setVisible(false);
        }
        if (books2.size() > 10) {
            jButton_next.setVisible(true);
        }
        if (books2.size() == seitenanzahl + 10) {
            jButton_next.setVisible(false);
        }
        if (seitenanzahl != 0) {
            jButton_previous.setVisible(true);

        } else {
            jButton_previous.setVisible(false);
        }
    }

    // Shows the Newest10 books

    public void Newest10() throws SQLException, MalformedURLException, IOException {
        MouseAdapter listener = new MouseImpl();

        books = Book.getNewestAndTop10();

        jLabel_img1.setIcon(new ImageIcon(new URL(books.get(0).getImglink())));
        jLabel_img1.setText(null);
        jLabel_img1.addMouseListener(listener);

        jLabel_img2.setIcon(new ImageIcon(new URL(books.get(1).getImglink())));
        jLabel_img2.setText(null);
        jLabel_img2.addMouseListener(listener);

        jLabel_img3.setIcon(new ImageIcon(new URL(books.get(2).getImglink())));
        jLabel_img3.setText(null);
        jLabel_img3.addMouseListener(listener);

        jLabel_img4.setIcon(new ImageIcon(new URL(books.get(3).getImglink())));
        jLabel_img4.setText(null);
        jLabel_img4.addMouseListener(listener);

        jLabel_img5.setIcon(new ImageIcon(new URL(books.get(4).getImglink())));
        jLabel_img5.setText(null);
        jLabel_img5.addMouseListener(listener);

        jLabel_img6.setIcon(new ImageIcon(new URL(books.get(5).getImglink())));
        jLabel_img6.setText(null);
        jLabel_img6.addMouseListener(listener);

        jLabel_img7.setIcon(new ImageIcon(new URL(books.get(6).getImglink())));
        jLabel_img7.setText(null);
        jLabel_img7.addMouseListener(listener);

        jLabel_img8.setIcon(new ImageIcon(new URL(books.get(7).getImglink())));
        jLabel_img8.setText(null);
        jLabel_img8.addMouseListener(listener);

        jLabel_img9.setIcon(new ImageIcon(new URL(books.get(8).getImglink())));
        jLabel_img9.setText(null);
        jLabel_img9.addMouseListener(listener);

        jLabel_img10.setIcon(new ImageIcon(new URL(books.get(9).getImglink())));
        jLabel_img10.setText(null);
        jLabel_img10.addMouseListener(listener);
    }

    // Shows the Top10 books

    public void Top10() throws SQLException, MalformedURLException {
        MouseAdapter listener = new MouseImpl();

        jLabel_img11.setIcon(new ImageIcon(new URL(books.get(10).getImglink())));
        jLabel_img11.setText(null);
        jLabel_img11.addMouseListener(listener);

        jLabel_img12.setIcon(new ImageIcon(new URL(books.get(11).getImglink())));
        jLabel_img12.setText(null);
        jLabel_img12.addMouseListener(listener);

        jLabel_img13.setIcon(new ImageIcon(new URL(books.get(12).getImglink())));
        jLabel_img13.setText(null);
        jLabel_img13.addMouseListener(listener);

        jLabel_img14.setIcon(new ImageIcon(new URL(books.get(13).getImglink())));
        jLabel_img14.setText(null);
        jLabel_img14.addMouseListener(listener);

        jLabel_img15.setIcon(new ImageIcon(new URL(books.get(14).getImglink())));
        jLabel_img15.setText(null);
        jLabel_img15.addMouseListener(listener);

        jLabel_img16.setIcon(new ImageIcon(new URL(books.get(15).getImglink())));
        jLabel_img16.setText(null);
        jLabel_img16.addMouseListener(listener);

        jLabel_img17.setIcon(new ImageIcon(new URL(books.get(16).getImglink())));
        jLabel_img17.setText(null);
        jLabel_img17.addMouseListener(listener);

        jLabe_img18.setIcon(new ImageIcon(new URL(books.get(17).getImglink())));
        jLabe_img18.setText(null);
        jLabe_img18.addMouseListener(listener);

        jLabel_img19.setIcon(new ImageIcon(new URL(books.get(18).getImglink())));
        jLabel_img19.setText(null);
        jLabel_img19.addMouseListener(listener);

        jLabel_img20.setIcon(new ImageIcon(new URL(books.get(19).getImglink())));
        jLabel_img20.setText(null);
        jLabel_img20.addMouseListener(listener);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();
        jButton_pswForgotten = new javax.swing.JButton();
        jButton_registry = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jButton_login = new javax.swing.JButton();
        jTextField_userName = new javax.swing.JTextField();
        jLabel_userName = new javax.swing.JLabel();
        jCombo_genre = new javax.swing.JComboBox();
        jCombo_language = new javax.swing.JComboBox();
        jCombo_ageRating = new javax.swing.JComboBox();
        jCombo_rating = new javax.swing.JComboBox();
        jLabel_search = new javax.swing.JLabel();
        jCombo_price = new javax.swing.JComboBox();
        jPassword = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextField_search = new javax.swing.JTextPane();
        jLabel_logo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton_search = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel_img2 = new javax.swing.JLabel();
        jLabel_img17 = new javax.swing.JLabel();
        jLabel_img12 = new javax.swing.JLabel();
        jLabel_img20 = new javax.swing.JLabel();
        jLabel_img5 = new javax.swing.JLabel();
        jLabel_img4 = new javax.swing.JLabel();
        jLabel_img9 = new javax.swing.JLabel();
        jLabel_img7 = new javax.swing.JLabel();
        jLabel_newest = new javax.swing.JLabel();
        jLabel_img10 = new javax.swing.JLabel();
        jLabel_img3 = new javax.swing.JLabel();
        jLabel_img11 = new javax.swing.JLabel();
        jButton_return = new javax.swing.JButton();
        jLabe_img18 = new javax.swing.JLabel();
        jLabel_img1 = new javax.swing.JLabel();
        jButton_previous = new javax.swing.JButton();
        jLabel_img16 = new javax.swing.JLabel();
        jLabel_img15 = new javax.swing.JLabel();
        jButton_next = new javax.swing.JButton();
        jLabel_img6 = new javax.swing.JLabel();
        jLabel_img8 = new javax.swing.JLabel();
        jLabel_top10 = new javax.swing.JLabel();
        jLabel_img19 = new javax.swing.JLabel();
        jLabel_img13 = new javax.swing.JLabel();
        jLabel_img14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton_pswForgotten.setText("Forgotten Password");
        jButton_pswForgotten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_pswForgottenActionPerformed(evt);
            }
        });

        jButton_registry.setText("Registry");
        jButton_registry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_registryActionPerformed(evt);
            }
        });

        jLabel16.setText("Password:");

        jButton_login.setText("Login");
        jButton_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_loginActionPerformed(evt);
            }
        });

        jLabel_userName.setText("Username:");

        jCombo_genre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Biographies", "Children", "Computer science ", "Cooking", "History", "Novel and Narratives", "School & Education" }));
        jCombo_genre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombo_genreActionPerformed(evt);
            }
        });

        jCombo_language.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Language", "English", "German", "Spanish" }));
        jCombo_language.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombo_languageActionPerformed(evt);
            }
        });

        jCombo_ageRating.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Age rating", "0", "6", "12", "16", "18" }));
        jCombo_ageRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombo_ageRatingActionPerformed(evt);
            }
        });

        jCombo_rating.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rating", "5 Best", "4", "3", "2", "1 Badest" }));

        jLabel_search.setText("Search:");

        jCombo_price.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Price", "3.99", "2.99", "1.99" }));
        jCombo_price.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCombo_priceItemStateChanged(evt);
            }
        });
        jCombo_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombo_priceActionPerformed(evt);
            }
        });

        jPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTextField_search);

        jLabel_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png"))); // NOI18N
        jLabel_logo.setText("jLabel2");

        jButton_search.setText("Search");
        jButton_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_searchActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(893, 468));

        jLabel_img2.setText("jLabel18");

        jLabel_img17.setText("jLabel8");

        jLabel_img12.setText("jLabel9");

        jLabel_img20.setText("jLabel6");

        jLabel_img5.setText("jLabel19");

        jLabel_img4.setText("jLabel4");

        jLabel_img9.setText("jLabel9");

        jLabel_img7.setText("jLabel7");

        jLabel_newest.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel_newest.setText("Newest 10");

        jLabel_img10.setText("jLabel14");

        jLabel_img3.setText("jLabel4");

        jLabel_img11.setText("jLabel17");

        jButton_return.setText("Return");
        jButton_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_returnActionPerformed(evt);
            }
        });

        jLabe_img18.setText("jLabel14");

        jLabel_img1.setText("jLabel17");

        jButton_previous.setText("<");
        jButton_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_previousActionPerformed(evt);
            }
        });

        jLabel_img16.setText("jLabel7");

        jLabel_img15.setText("jLabel19");

        jButton_next.setText(">");
        jButton_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nextActionPerformed(evt);
            }
        });

        jLabel_img6.setText("jLabel6");

        jLabel_img8.setText("jLabel8");

        jLabel_top10.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel_top10.setText("Top 10");

        jLabel_img19.setText("jLabel4");

        jLabel_img13.setText("jLabel18");

        jLabel_img14.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_img11, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_img16, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel_img12, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel_img13, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel_img17, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabe_img18, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel_img14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_img15, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel_img19, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_img20, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_img6, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(jLabel_img1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_img7, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(jLabel_img2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_img8, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(jLabel_img3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_img4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_img9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_img10, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                    .addComponent(jLabel_img5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel_newest))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel_top10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton_return)
                .addGap(275, 275, 275)
                .addComponent(jButton_previous, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton_next, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel_newest)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_img1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_img3)
                        .addComponent(jLabel_img4)
                        .addComponent(jLabel_img5)
                        .addComponent(jLabel_img2)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_img8)
                        .addComponent(jLabel_img9)
                        .addComponent(jLabel_img10))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_img6)
                        .addComponent(jLabel_img7)))
                .addGap(18, 18, 18)
                .addComponent(jLabel_top10)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_img11)
                        .addComponent(jLabel_img12))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_img14)
                        .addComponent(jLabel_img13)
                        .addComponent(jLabel_img15)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_img17)
                    .addComponent(jLabel_img19)
                    .addComponent(jLabel_img16)
                    .addComponent(jLabe_img18)
                    .addComponent(jLabel_img20))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_next)
                        .addComponent(jButton_previous))
                    .addComponent(jButton_return))
                .addGap(26, 26, 26))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel_userName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_login)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_userName, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_pswForgotten, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_registry))
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jCombo_genre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jCombo_price, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jCombo_ageRating, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_search)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_search))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jCombo_language, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jCombo_rating, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_registry)
                            .addComponent(jLabel_userName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_pswForgotten)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_login))
                    .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_search)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel_search, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCombo_rating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCombo_ageRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCombo_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCombo_genre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCombo_language, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_loginActionPerformed
        if (evt.getSource() == jButton_login) {
            User user = new User();
            try {
                if ((user.login(jTextField_userName.getText(), new String(jPassword.getPassword()))) == 1) {
                    dispose();
                    if (user.checkAdmin() == 1) {
                        new Admin(user).setVisible(true);
                    } else {
                        setVisible(false);
                        new User(user).setVisible(true);
                    }
                }
            } catch (SQLException | UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_loginActionPerformed

    private void jCombo_genreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_genreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_genreActionPerformed

    private void jCombo_languageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_languageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_languageActionPerformed

    private void jCombo_ageRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_ageRatingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_ageRatingActionPerformed

    private void jCombo_priceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCombo_priceItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_priceItemStateChanged

    private void jCombo_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_priceActionPerformed

    private void jPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordActionPerformed
        // TODO add your handling code here:**
    }//GEN-LAST:event_jPasswordActionPerformed

    private void jButton_registryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_registryActionPerformed
        new Registry().setVisible(true);
    }//GEN-LAST:event_jButton_registryActionPerformed

    private void jButton_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_searchActionPerformed
        db = new Verbindung();
        db.start();
        conn = db.getVerbindung();

        jButton_return.setVisible(true);
        suchetext = jTextField_search.getText();
        genre = jCombo_genre.getSelectedIndex();
        price = jCombo_price.getSelectedIndex();
        agerating = jCombo_ageRating.getSelectedIndex();
        rating = jCombo_rating.getSelectedIndex();
        language = jCombo_language.getSelectedIndex();

        if (evt.getSource() == jButton_search) {
            books = new ArrayList<>();
            try {
                gen = Admin.getGenre(genre);
                pri = Admin.getPrice(price);
                age = Admin.getAgerating(agerating);
                rate = Admin.getRating(rating);
                lang = Admin.getLanguage(language);

                if (!(rate.equals("%"))) {
                    stmt4 = conn.createStatement();
                    rs3 = stmt4.executeQuery("SELECT *,avg(rating) as average FROM book natural left join rates natural join haslang WHERE title LIKE '%" + suchetext + "%' and genre LIKE '%" + gen + "%' and price <= '" + pri + "' and ageRating <= '" + age + "' and Language LIKE '%" + lang + "%' and inactive = 0 group by mid having average >= " + rate + "");
                    stmtSearch = conn.createStatement();

                    while (rs3.next()) {
                        rsSearch = stmtSearch.executeQuery("Select * from book natural join haslang where mid = " + rs3.getString("mid") + " ");
                        rsSearch.next();
                        String language1 = rsSearch.getString("Language");
                        rsSearch.last();
                        String language2 = rsSearch.getString("Language");

                        if (language2.equals(language1)) {
                            language2 = "";
                        }
                        Book book = new Book(rs3.getString("mid"), rs3.getString("title"), rs3.getString("picture"), rs3.getString("average"), rs3.getString("description"), rs3.getString("genre"), rs3.getString("agerating"), rs3.getString("releaseYear"), rs3.getString("duration"), rs3.getString("streamlink"), language1, language2, rs3.getString("price"), "");
                        books.add(book);
                    }

                    while (books.size() % 10 != 0) {
                        Book dump = new Book("", "", "", null, "", "", "", "", "", "", "", "", "", "");
                        books.add(dump);
                    }
                    this.searchResult(books);

                } else {
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery("SELECT *,avg(rating) as average FROM book natural join haslang natural left join rates WHERE title LIKE '%" + suchetext + "%' and genre LIKE '%" + gen + "%' and price <= '" + pri + "' and ageRating <= '" + age + "' and Language LIKE '%" + lang + "%' and inactive = 0 group by mid");
                    stmtSearch = conn.createStatement();

                    while (rs.next()) {

                        rsSearch = stmtSearch.executeQuery("Select * from book natural join haslang where mid = " + rs.getString("mid") + " ");
                        rsSearch.next();
                        String language1 = rsSearch.getString("Language");
                        rsSearch.last();
                        String language2 = rsSearch.getString("Language");

                        if (language2.equals(language1)) {
                            language2 = "";
                        }
                        Book book = new Book(rs.getString("mid"), rs.getString("title"), rs.getString("picture"), rs.getString("average"), rs.getString("description"), rs.getString("genre"), rs.getString("agerating"), rs.getString("releaseYear"), rs.getString("duration"), rs.getString("streamlink"), language1, language2, rs.getString("price"), "");
                        books.add(book);
                    }
                    while (books.size() % 10 != 0) {
                        Book dump = new Book("", "", "http://stefano.bplaced.net/nothing.png", null, "", "", "", "", "", "", "", "", "", "");
                        books.add(dump);
                    }
                    this.searchResult(books);
                }

            } catch (SQLException | MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_searchActionPerformed

    private void jButton_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_returnActionPerformed
        this.dispose();
        try {
            new Login().setVisible(true);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton_returnActionPerformed

    private void jButton_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_previousActionPerformed
        if (jButton_previous == evt.getSource()) {
            seitenanzahl = seitenanzahl - 10;
            try {
                this.searchResult(books);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_previousActionPerformed

    private void jButton_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextActionPerformed
        if (jButton_next == evt.getSource()) {
            seitenanzahl = seitenanzahl + 10;
            try {
                this.searchResult(books);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_nextActionPerformed

    private void jButton_pswForgottenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_pswForgottenActionPerformed
        new ForgottenPassword();
    }//GEN-LAST:event_jButton_pswForgottenActionPerformed

    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        /* Create and display the form */
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login login = new Login();
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_login;
    private javax.swing.JButton jButton_next;
    private javax.swing.JButton jButton_previous;
    private javax.swing.JButton jButton_pswForgotten;
    private javax.swing.JButton jButton_registry;
    private javax.swing.JButton jButton_return;
    private javax.swing.JButton jButton_search;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JComboBox jCombo_ageRating;
    private javax.swing.JComboBox jCombo_genre;
    private javax.swing.JComboBox jCombo_language;
    private javax.swing.JComboBox jCombo_price;
    private javax.swing.JComboBox jCombo_rating;
    private javax.swing.JLabel jLabe_img18;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel_img1;
    private javax.swing.JLabel jLabel_img10;
    private javax.swing.JLabel jLabel_img11;
    private javax.swing.JLabel jLabel_img12;
    private javax.swing.JLabel jLabel_img13;
    private javax.swing.JLabel jLabel_img14;
    private javax.swing.JLabel jLabel_img15;
    private javax.swing.JLabel jLabel_img16;
    private javax.swing.JLabel jLabel_img17;
    private javax.swing.JLabel jLabel_img19;
    private javax.swing.JLabel jLabel_img2;
    private javax.swing.JLabel jLabel_img20;
    private javax.swing.JLabel jLabel_img3;
    private javax.swing.JLabel jLabel_img4;
    private javax.swing.JLabel jLabel_img5;
    private javax.swing.JLabel jLabel_img6;
    private javax.swing.JLabel jLabel_img7;
    private javax.swing.JLabel jLabel_img8;
    private javax.swing.JLabel jLabel_img9;
    private javax.swing.JLabel jLabel_logo;
    private javax.swing.JLabel jLabel_newest;
    private javax.swing.JLabel jLabel_search;
    private javax.swing.JLabel jLabel_top10;
    private javax.swing.JLabel jLabel_userName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextField_search;
    private javax.swing.JTextField jTextField_userName;
    // End of variables declaration//GEN-END:variables

    //Class MouseImpl is responsible for the Mouselistener
    class MouseImpl extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Object source = e.getSource();
            if (source == jLabel_img1) {
                try {
                    new BookInfo(user, books.get(0 + seitenanzahl)).setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img2) {
                try {
                    BookInfo window = new BookInfo(user, books.get(1 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (source == jLabel_img3) {
                try {
                    BookInfo window = new BookInfo(user, books.get(2 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img4) {
                try {
                    BookInfo window = new BookInfo(user, books.get(3 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img5) {
                try {
                    BookInfo window = new BookInfo(user, books.get(4 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img6) {
                try {
                    new BookInfo(user, books.get(5 + seitenanzahl)).setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img7) {
                try {
                    BookInfo window = new BookInfo(user, books.get(6 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (source == jLabel_img8) {
                try {
                    BookInfo window = new BookInfo(user, books.get(7 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img9) {
                try {
                    BookInfo window = new BookInfo(user, books.get(8 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img10) {
                try {
                    BookInfo window = new BookInfo(user, books.get(9 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img11) {
                try {
                    new BookInfo(user, books.get(10 + seitenanzahl)).setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img12) {
                try {
                    BookInfo window = new BookInfo(user, books.get(11 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (source == jLabel_img13) {
                try {
                    BookInfo window = new BookInfo(user, books.get(12 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img14) {
                try {
                    BookInfo window = new BookInfo(user,books.get(13 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img15) {
                try {
                    BookInfo window = new BookInfo(user, books.get(14 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img16) {
                try {
                    new BookInfo(user, books.get(15 + seitenanzahl)).setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img17) {
                try {
                    BookInfo window = new BookInfo(user, books.get(16 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (source == jLabe_img18) {
                try {
                    BookInfo window = new BookInfo(user, books.get(17 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img19) {
                try {
                    BookInfo window = new BookInfo(user, books.get(18 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabel_img20) {
                try {
                    BookInfo window = new BookInfo(user, books.get(19 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
