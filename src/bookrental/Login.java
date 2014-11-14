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
    ArrayList<Book> movies = new ArrayList<>();
    ArrayList<Book> movies2 = new ArrayList<>();
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
        jButtonPrevious.setVisible(false);
        jButtonNext.setVisible(false);
        jButtonReturn.setVisible(false);
    }

    // Shows the result of the search
    public void searchResult(ArrayList<Book> movies2) throws MalformedURLException {

        jLabelBild1.setVisible(false);
        jLabelBild2.setVisible(false);
        jLabelBild3.setVisible(false);
        jLabelBild4.setVisible(false);
        jLabelBild5.setVisible(false);
        jLabelBild6.setVisible(false);
        jLabelBild7.setVisible(false);
        jLabelBild8.setVisible(false);
        jLabelBild9.setVisible(false);
        jLabelBild10.setVisible(false);
        jLabelBild11.setVisible(false);
        jLabelBild19.setVisible(false);
        jLabelBild12.setVisible(false);
        jLabelBild14.setVisible(false);
        jLabelBild15.setVisible(false);
        jLabelBild17.setVisible(false);
        jLabelBild18.setVisible(false);
        jLabelBild20.setVisible(false);
        jLabelBild13.setVisible(false);
        jLabelBild16.setVisible(false);

        jLabelTop10.setVisible(false);
        jLabelNewest.setText("Search Result for '" + suchetext + "':");

        if (!(movies2.get(0 + seitenanzahl).getTitle().equals(""))) {
            jLabelBild1.setIcon(new ImageIcon(new URL(movies2.get(0 + seitenanzahl).getImglink())));
            jLabelBild1.setText(null);
            jLabelBild1.setVisible(true);
        } else {
            jLabelBild1.setVisible(false);
        }
        if (!(movies2.get(1 + seitenanzahl).getTitle().equals(""))) {
            jLabelBild2.setIcon(new ImageIcon(new URL(movies2.get(1 + seitenanzahl).getImglink())));
            jLabelBild2.setText(null);
            jLabelBild2.setVisible(true);
        } else {
            jLabelBild2.setVisible(false);
        }
        if (!(movies2.get(2 + seitenanzahl).getTitle().equals(""))) {
            jLabelBild3.setIcon(new ImageIcon(new URL(movies2.get(2 + seitenanzahl).getImglink())));
            jLabelBild3.setText(null);
            jLabelBild3.setVisible(true);
        } else {
            jLabelBild3.setVisible(false);
        }
        if (!(movies2.get(3 + seitenanzahl).getTitle().equals(""))) {
            jLabelBild4.setIcon(new ImageIcon(new URL(movies2.get(3 + seitenanzahl).getImglink())));
            jLabelBild4.setText(null);
            jLabelBild4.setVisible(true);
        } else {
            jLabelBild4.setVisible(false);
        }
        if (!(movies2.get(4 + seitenanzahl).getTitle().equals(""))) {
            jLabelBild5.setIcon(new ImageIcon(new URL(movies2.get(4 + seitenanzahl).getImglink())));
            jLabelBild5.setText(null);
            jLabelBild5.setVisible(true);
        } else {
            jLabelBild5.setVisible(false);
        }
        if (!(movies2.get(5 + seitenanzahl).getTitle().equals(""))) {
            jLabelBild6.setIcon(new ImageIcon(new URL(movies2.get(5 + seitenanzahl).getImglink())));
            jLabelBild6.setText(null);
            jLabelBild6.setVisible(true);
        } else {
            jLabelBild6.setVisible(false);
        }
        if (!(movies2.get(6 + seitenanzahl).getTitle().equals(""))) {
            jLabelBild7.setIcon(new ImageIcon(new URL(movies2.get(6 + seitenanzahl).getImglink())));
            jLabelBild7.setText(null);
            jLabelBild7.setVisible(true);
        } else {
            jLabelBild7.setVisible(false);
        }
        if (!(movies2.get(7 + seitenanzahl).getTitle().equals(""))) {
            jLabelBild8.setIcon(new ImageIcon(new URL(movies2.get(7 + seitenanzahl).getImglink())));
            jLabelBild8.setText(null);
            jLabelBild8.setVisible(true);
        } else {
            jLabelBild8.setVisible(false);
        }
        if (!(movies2.get(8 + seitenanzahl).getTitle().equals(""))) {
            jLabelBild9.setIcon(new ImageIcon(new URL(movies2.get(8 + seitenanzahl).getImglink())));
            jLabelBild9.setText(null);
            jLabelBild9.setVisible(true);
        } else {
            jLabelBild9.setVisible(false);
        }
        if (!(movies2.get(9 + seitenanzahl).getTitle().equals(""))) {
            jLabelBild10.setIcon(new ImageIcon(new URL(movies2.get(9 + seitenanzahl).getImglink())));
            jLabelBild10.setText(null);
            jLabelBild10.setVisible(true);
        } else {
            jLabelBild10.setVisible(false);
        }
        if (movies2.size() > 10) {
            jButtonNext.setVisible(true);
        }
        if (movies2.size() == seitenanzahl + 10) {
            jButtonNext.setVisible(false);
        }
        if (seitenanzahl != 0) {
            jButtonPrevious.setVisible(true);

        } else {
            jButtonPrevious.setVisible(false);
        }
    }

    // Shows the Newest10 Movies

    public void Newest10() throws SQLException, MalformedURLException, IOException {
        MouseAdapter listener = new MouseImpl();

        movies = Book.getNewestAndTop10();

        jLabelBild1.setIcon(new ImageIcon(new URL(movies.get(0).getImglink())));
        jLabelBild1.setText(null);
        jLabelBild1.addMouseListener(listener);

        jLabelBild2.setIcon(new ImageIcon(new URL(movies.get(1).getImglink())));
        jLabelBild2.setText(null);
        jLabelBild2.addMouseListener(listener);

        jLabelBild3.setIcon(new ImageIcon(new URL(movies.get(2).getImglink())));
        jLabelBild3.setText(null);
        jLabelBild3.addMouseListener(listener);

        jLabelBild4.setIcon(new ImageIcon(new URL(movies.get(3).getImglink())));
        jLabelBild4.setText(null);
        jLabelBild4.addMouseListener(listener);

        jLabelBild5.setIcon(new ImageIcon(new URL(movies.get(4).getImglink())));
        jLabelBild5.setText(null);
        jLabelBild5.addMouseListener(listener);

        jLabelBild6.setIcon(new ImageIcon(new URL(movies.get(5).getImglink())));
        jLabelBild6.setText(null);
        jLabelBild6.addMouseListener(listener);

        jLabelBild7.setIcon(new ImageIcon(new URL(movies.get(6).getImglink())));
        jLabelBild7.setText(null);
        jLabelBild7.addMouseListener(listener);

        jLabelBild8.setIcon(new ImageIcon(new URL(movies.get(7).getImglink())));
        jLabelBild8.setText(null);
        jLabelBild8.addMouseListener(listener);

        jLabelBild9.setIcon(new ImageIcon(new URL(movies.get(8).getImglink())));
        jLabelBild9.setText(null);
        jLabelBild9.addMouseListener(listener);

        jLabelBild10.setIcon(new ImageIcon(new URL(movies.get(9).getImglink())));
        jLabelBild10.setText(null);
        jLabelBild10.addMouseListener(listener);
    }

    // Shows the Top10 Movies

    public void Top10() throws SQLException, MalformedURLException {
        MouseAdapter listener = new MouseImpl();

        jLabelBild11.setIcon(new ImageIcon(new URL(movies.get(10).getImglink())));
        jLabelBild11.setText(null);
        jLabelBild11.addMouseListener(listener);

        jLabelBild12.setIcon(new ImageIcon(new URL(movies.get(11).getImglink())));
        jLabelBild12.setText(null);
        jLabelBild12.addMouseListener(listener);

        jLabelBild13.setIcon(new ImageIcon(new URL(movies.get(12).getImglink())));
        jLabelBild13.setText(null);
        jLabelBild13.addMouseListener(listener);

        jLabelBild14.setIcon(new ImageIcon(new URL(movies.get(13).getImglink())));
        jLabelBild14.setText(null);
        jLabelBild14.addMouseListener(listener);

        jLabelBild15.setIcon(new ImageIcon(new URL(movies.get(14).getImglink())));
        jLabelBild15.setText(null);
        jLabelBild15.addMouseListener(listener);

        jLabelBild16.setIcon(new ImageIcon(new URL(movies.get(15).getImglink())));
        jLabelBild16.setText(null);
        jLabelBild16.addMouseListener(listener);

        jLabelBild17.setIcon(new ImageIcon(new URL(movies.get(16).getImglink())));
        jLabelBild17.setText(null);
        jLabelBild17.addMouseListener(listener);

        jLabelBild18.setIcon(new ImageIcon(new URL(movies.get(17).getImglink())));
        jLabelBild18.setText(null);
        jLabelBild18.addMouseListener(listener);

        jLabelBild19.setIcon(new ImageIcon(new URL(movies.get(18).getImglink())));
        jLabelBild19.setText(null);
        jLabelBild19.addMouseListener(listener);

        jLabelBild20.setIcon(new ImageIcon(new URL(movies.get(19).getImglink())));
        jLabelBild20.setText(null);
        jLabelBild20.addMouseListener(listener);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();
        jButton_forgottenPass = new javax.swing.JButton();
        jButton_registry = new javax.swing.JButton();
        jLabel_pasword = new javax.swing.JLabel();
        jButton_login = new javax.swing.JButton();
        jTextUsername = new javax.swing.JTextField();
        jLabel_username = new javax.swing.JLabel();
        jComboGenre = new javax.swing.JComboBox();
        jComboLanguage = new javax.swing.JComboBox();
        jCombo_AgeRating = new javax.swing.JComboBox();
        jComboRating = new javax.swing.JComboBox();
        jLabel_search = new javax.swing.JLabel();
        jCombo_price = new javax.swing.JComboBox();
        jPassword = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jText_search = new javax.swing.JTextPane();
        jLabel_logo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabelBild2 = new javax.swing.JLabel();
        jLabelBild5 = new javax.swing.JLabel();
        jLabelBild4 = new javax.swing.JLabel();
        jLabelBild9 = new javax.swing.JLabel();
        jLabelBild7 = new javax.swing.JLabel();
        jLabelBild10 = new javax.swing.JLabel();
        jLabelBild3 = new javax.swing.JLabel();
        jButtonReturn = new javax.swing.JButton();
        jLabelBild1 = new javax.swing.JLabel();
        jButtonPrevious = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jLabelBild6 = new javax.swing.JLabel();
        jLabelBild8 = new javax.swing.JLabel();
        jLabelTop10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton_forgottenPass.setText("Forgotten Password");
        jButton_forgottenPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_forgottenPassActionPerformed(evt);
            }
        });

        jButton_registry.setText("Registry");
        jButton_registry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_registryActionPerformed(evt);
            }
        });

        jLabel_pasword.setText("Password:");

        jButton_login.setText("Login");
        jButton_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_loginActionPerformed(evt);
            }
        });

        jLabel_username.setText("Username:");

        jComboGenre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Genre", "Action", "Adventure", "Thriller", "Fantasy", "Animation", "Comedy" }));
        jComboGenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboGenreActionPerformed(evt);
            }
        });

        jComboLanguage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Language", "English", "German", "Spanish" }));
        jComboLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboLanguageActionPerformed(evt);
            }
        });

        jCombo_AgeRating.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Age rating", "0", "6", "12", "16", "18" }));
        jCombo_AgeRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombo_AgeRatingActionPerformed(evt);
            }
        });

        jComboRating.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rating", "5 Best", "4", "3", "2", "1 Badest" }));

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

        jScrollPane1.setViewportView(jText_search);

        jLabel_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movierental/Logo.png"))); // NOI18N
        jLabel_logo.setText("jLabel2");

        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(893, 468));

        jLabelBild2.setText("jLabel18");

        jLabelBild5.setText("jLabel19");

        jLabelBild4.setText("jLabel4");

        jLabelBild9.setText("jLabel9");

        jLabelBild7.setText("jLabel7");

        jLabelBild10.setText("jLabel14");

        jLabelBild3.setText("jLabel4");

        jButtonReturn.setText("Return");
        jButtonReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturnActionPerformed(evt);
            }
        });

        jLabelBild1.setText("jLabel17");

        jButtonPrevious.setText("<");
        jButtonPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviousActionPerformed(evt);
            }
        });

        jButtonNext.setText(">");
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        jLabelBild6.setText("jLabel6");

        jLabelBild8.setText("jLabel8");

        jLabelTop10.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabelTop10.setText("Top 10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelBild6, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(jLabelBild1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelBild7, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(jLabelBild2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelBild8, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(jLabelBild3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBild4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelBild9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelBild10, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(jLabelBild5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelTop10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButtonReturn)
                .addGap(275, 275, 275)
                .addComponent(jButtonPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabelTop10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelBild1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBild3)
                        .addComponent(jLabelBild4)
                        .addComponent(jLabelBild5)
                        .addComponent(jLabelBild2)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBild8)
                        .addComponent(jLabelBild9)
                        .addComponent(jLabelBild10))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBild6)
                        .addComponent(jLabelBild7)))
                .addGap(123, 123, 123)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonNext)
                        .addComponent(jButtonPrevious))
                    .addComponent(jButtonReturn))
                .addGap(26, 26, 26))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_pasword)
                    .addComponent(jLabel_username))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_login)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_forgottenPass, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_registry))
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jComboGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jCombo_price, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jCombo_AgeRating, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_search)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSearch))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jComboLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jComboRating, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_registry)
                            .addComponent(jLabel_username))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_forgottenPass)
                            .addComponent(jLabel_pasword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_login)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSearch)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel_search, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCombo_AgeRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCombo_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_loginActionPerformed
        if (evt.getSource() == jButton_login) {
            User user = new User();
            try {
                if ((user.login(jTextUsername.getText(), new String(jPassword.getPassword()))) == 1) {
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

    private void jComboGenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboGenreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboGenreActionPerformed

    private void jComboLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboLanguageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboLanguageActionPerformed

    private void jCombo_AgeRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_AgeRatingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_AgeRatingActionPerformed

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

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        db = new Verbindung();
        db.start();
        conn = db.getVerbindung();

        jButtonReturn.setVisible(true);
        suchetext = jText_search.getText();
        genre = jComboGenre.getSelectedIndex();
        price = jCombo_price.getSelectedIndex();
        agerating = jCombo_AgeRating.getSelectedIndex();
        rating = jComboRating.getSelectedIndex();
        language = jComboLanguage.getSelectedIndex();

        if (evt.getSource() == jButtonSearch) {
            movies = new ArrayList<>();
            try {
                gen = Admin.getGenre(genre);
                pri = Admin.getPrice(price);
                age = Admin.getAgerating(agerating);
                rate = Admin.getRating(rating);
                lang = Admin.getLanguage(language);

                if (!(rate.equals("%"))) {
                    stmt4 = conn.createStatement();
                    rs3 = stmt4.executeQuery("SELECT *,avg(rating) as average FROM movie natural left join rates natural join haslang WHERE title LIKE '%" + suchetext + "%' and genre LIKE '%" + gen + "%' and price <= '" + pri + "' and ageRating <= '" + age + "' and Language LIKE '%" + lang + "%' and inactive = 0 group by mid having average >= " + rate + "");
                    stmtSearch = conn.createStatement();

                    while (rs3.next()) {
                        rsSearch = stmtSearch.executeQuery("Select * from movie natural join haslang where mid = " + rs3.getString("mid") + " ");
                        rsSearch.next();
                        String language1 = rsSearch.getString("Language");
                        rsSearch.last();
                        String language2 = rsSearch.getString("Language");

                        if (language2.equals(language1)) {
                            language2 = "";
                        }
                        Book movie = new Book(rs3.getString("mid"), rs3.getString("title"), rs3.getString("picture"), rs3.getString("average"), rs3.getString("description"), rs3.getString("genre"), rs3.getString("agerating"), rs3.getString("releaseYear"), rs3.getString("duration"), rs3.getString("streamlink"), language1, language2, rs3.getString("price"), "");
                        movies.add(movie);
                    }

                    while (movies.size() % 10 != 0) {
                        Book dump = new Book("", "", "", null, "", "", "", "", "", "", "", "", "", "");
                        movies.add(dump);
                    }
                    this.searchResult(movies);

                } else {
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery("SELECT *,avg(rating) as average FROM movie natural join haslang natural left join rates WHERE title LIKE '%" + suchetext + "%' and genre LIKE '%" + gen + "%' and price <= '" + pri + "' and ageRating <= '" + age + "' and Language LIKE '%" + lang + "%' and inactive = 0 group by mid");
                    stmtSearch = conn.createStatement();

                    while (rs.next()) {

                        rsSearch = stmtSearch.executeQuery("Select * from movie natural join haslang where mid = " + rs.getString("mid") + " ");
                        rsSearch.next();
                        String language1 = rsSearch.getString("Language");
                        rsSearch.last();
                        String language2 = rsSearch.getString("Language");

                        if (language2.equals(language1)) {
                            language2 = "";
                        }
                        Book movie = new Book(rs.getString("mid"), rs.getString("title"), rs.getString("picture"), rs.getString("average"), rs.getString("description"), rs.getString("genre"), rs.getString("agerating"), rs.getString("releaseYear"), rs.getString("duration"), rs.getString("streamlink"), language1, language2, rs.getString("price"), "");
                        movies.add(movie);
                    }
                    while (movies.size() % 10 != 0) {
                        Book dump = new Book("", "", "http://stefano.bplaced.net/nothing.png", null, "", "", "", "", "", "", "", "", "", "");
                        movies.add(dump);
                    }
                    this.searchResult(movies);
                }

            } catch (SQLException | MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jButtonReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturnActionPerformed
        this.dispose();
        try {
            new Login().setVisible(true);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonReturnActionPerformed

    private void jButtonPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviousActionPerformed
        if (jButtonPrevious == evt.getSource()) {
            seitenanzahl = seitenanzahl - 10;
            try {
                this.searchResult(movies);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonPreviousActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        if (jButtonNext == evt.getSource()) {
            seitenanzahl = seitenanzahl + 10;
            try {
                this.searchResult(movies);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jButton_forgottenPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_forgottenPassActionPerformed
        new ForgottenPassword();
    }//GEN-LAST:event_jButton_forgottenPassActionPerformed

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
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrevious;
    private javax.swing.JButton jButtonReturn;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButton_forgottenPass;
    private javax.swing.JButton jButton_login;
    private javax.swing.JButton jButton_registry;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JComboBox jComboGenre;
    private javax.swing.JComboBox jComboLanguage;
    private javax.swing.JComboBox jComboRating;
    private javax.swing.JComboBox jCombo_AgeRating;
    private javax.swing.JComboBox jCombo_price;
    private javax.swing.JLabel jLabelBild1;
    private javax.swing.JLabel jLabelBild10;
    private javax.swing.JLabel jLabelBild2;
    private javax.swing.JLabel jLabelBild3;
    private javax.swing.JLabel jLabelBild4;
    private javax.swing.JLabel jLabelBild5;
    private javax.swing.JLabel jLabelBild6;
    private javax.swing.JLabel jLabelBild7;
    private javax.swing.JLabel jLabelBild8;
    private javax.swing.JLabel jLabelBild9;
    private javax.swing.JLabel jLabelTop10;
    private javax.swing.JLabel jLabel_logo;
    private javax.swing.JLabel jLabel_pasword;
    private javax.swing.JLabel jLabel_search;
    private javax.swing.JLabel jLabel_username;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextUsername;
    private javax.swing.JTextPane jText_search;
    // End of variables declaration//GEN-END:variables

    //Class MouseImpl is responsible for the Mouselistener
    class MouseImpl extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Object source = e.getSource();
            if (source == jLabelBild1) {
                try {
                    new BookInfo(user, movies.get(0 + seitenanzahl)).setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild2) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(1 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (source == jLabelBild3) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(2 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild4) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(3 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild5) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(4 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild6) {
                try {
                    new BookInfo(user, movies.get(5 + seitenanzahl)).setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild7) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(6 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (source == jLabelBild8) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(7 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild9) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(8 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild10) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(9 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild11) {
                try {
                    new BookInfo(user, movies.get(10 + seitenanzahl)).setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild12) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(11 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (source == jLabelBild13) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(12 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild14) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(13 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild15) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(14 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild16) {
                try {
                    new BookInfo(user, movies.get(15 + seitenanzahl)).setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild17) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(16 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (source == jLabelBild18) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(17 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild19) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(18 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (source == jLabelBild20) {
                try {
                    BookInfo window = new BookInfo(user, movies.get(19 + seitenanzahl));
                    window.pack();
                    window.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
