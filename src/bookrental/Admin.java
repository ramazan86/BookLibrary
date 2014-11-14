package bookrental;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Admin extends javax.swing.JFrame {
        User user;
        ArrayList<Book> movies,movies2;
        String suchetext,gen,pri,age,rate,lang;
        int genre,price,agerating,rating,language,pages;
        Verbindung db;
        Connection conn;
        Statement stmt,stmt2,stmt3,stmt4,stmtNewest,stmtNewest2,stmtSearch,stmtTop10,stmt2Top10;
        ResultSet rs,rs2,rs3,rsNewest,rsNewest2,rsSearch,rsTop10,rs2Top10;
        static int seitenanzahl = 0;
       
    public Admin(User user) throws SQLException, MalformedURLException, IOException {
        this.user = user;
        initComponents();
        this.setSize(870,700);
        movies = Book.getNewestAndTop10();
        this.Newest10();
        this.Top10();
        setLocationRelativeTo(null);
        this.setVisible(true);
        jButtonPrevious.setVisible(false);
        jButtonNext.setVisible(false);
        jButtonReturn.setVisible(false);
        jLabelLastLogin.setText("Your last login was on the " + user.lastLogin);
    }
    
    public static String getGenre(int g){
        switch(g){
            case 1:
                return "Action";
            case 2:
                return "Adventure";
            case 3:
                return "Thriller";
            case 4:
                return "Fantasy";
            case 5:
                return "Animation";
            case 6:
                return "Comedy";
            default:
                return "%";
        }
    }
    public static String getPrice(int p){
        if(p == 0 || p == 1){
        return "3.99";
        }else if(p == 2){
        return "2.99";
        }else{
        return "1.99";
        }
    }
    public static String getAgerating(int a){
        switch(a){
            case 1:
                return "0";
            case 2:
                return "6";
            case 3:
                return "12";
            case 4:
                return "16";
            case 5:
                return "18";
            default:
                return "18";
        }
    }
    public static String getRating(int r){
        switch(r){
            case 1:
                return "5";
            case 2:
                return "4";
            case 3:
                return "3";
            case 4:
                return "2";
            case 5:
                return "1";
            default:
                return "%";
        }
    }
    public static String getLanguage(int l){
        switch(l){
            case 1:
                return "English";
            case 2:
                return "German";
            case 3:
                return "Spanish";
            default:
                return "%";
        
        }
    }
    public void searchResult(ArrayList<Book> movies2) throws MalformedURLException{
        
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
        jLabelNewest.setText("Search Result for '"+ suchetext +"':");
        
        if(!(movies2.get(0+seitenanzahl).getTitle().equals(""))){
        jLabelBild1.setIcon(new ImageIcon(new URL(movies2.get(0+seitenanzahl).getImglink())));
        jLabelBild1.setText(null);
        jLabelBild1.setVisible(true);
        }else{
        jLabelBild1.setVisible(false);
        }
        if(!(movies2.get(1+seitenanzahl).getTitle().equals(""))){
        jLabelBild2.setIcon(new ImageIcon(new URL(movies2.get(1+seitenanzahl).getImglink())));
        jLabelBild2.setText(null);
        jLabelBild2.setVisible(true);
        }else{
        jLabelBild2.setVisible(false);
        }
        if(!(movies2.get(2+seitenanzahl).getTitle().equals(""))){
        jLabelBild3.setIcon(new ImageIcon(new URL(movies2.get(2+seitenanzahl).getImglink())));
        jLabelBild3.setText(null);
        jLabelBild3.setVisible(true);
        }else{
        jLabelBild3.setVisible(false);
        }
        if(!(movies2.get(3+seitenanzahl).getTitle().equals(""))){
        jLabelBild4.setIcon(new ImageIcon(new URL(movies2.get(3+seitenanzahl).getImglink())));
        jLabelBild4.setText(null);
        jLabelBild4.setVisible(true);
        }else{
        jLabelBild4.setVisible(false);
        }
        if(!(movies2.get(4+seitenanzahl).getTitle().equals(""))){
        jLabelBild5.setIcon(new ImageIcon(new URL(movies2.get(4+seitenanzahl).getImglink())));
        jLabelBild5.setText(null);
        jLabelBild5.setVisible(true);
        }else{
        jLabelBild5.setVisible(false);
        }
        if(!(movies2.get(5+seitenanzahl).getTitle().equals(""))){
        jLabelBild6.setIcon(new ImageIcon(new URL(movies2.get(5+seitenanzahl).getImglink())));
        jLabelBild6.setText(null);
        jLabelBild6.setVisible(true);
        }else{
        jLabelBild6.setVisible(false);
        }
        if(!(movies2.get(6+seitenanzahl).getTitle().equals(""))){
        jLabelBild7.setIcon(new ImageIcon(new URL(movies2.get(6+seitenanzahl).getImglink())));
        jLabelBild7.setText(null);
        jLabelBild7.setVisible(true);
        }else{
        jLabelBild7.setVisible(false);
        }
        if(!(movies2.get(7+seitenanzahl).getTitle().equals(""))){
        jLabelBild8.setIcon(new ImageIcon(new URL(movies2.get(7+seitenanzahl).getImglink())));
        jLabelBild8.setText(null);
        jLabelBild8.setVisible(true);
        }else{
        jLabelBild8.setVisible(false);
        }
        if(!(movies2.get(8+seitenanzahl).getTitle().equals(""))){
        jLabelBild9.setIcon(new ImageIcon(new URL(movies2.get(8+seitenanzahl).getImglink())));
        jLabelBild9.setText(null);
        jLabelBild9.setVisible(true);
        }else{
        jLabelBild9.setVisible(false);
        }
        if(!(movies2.get(9+seitenanzahl).getTitle().equals(""))){
        jLabelBild10.setIcon(new ImageIcon(new URL(movies2.get(9+seitenanzahl).getImglink())));
        jLabelBild10.setText(null);
        jLabelBild10.setVisible(true);
        }else{
        jLabelBild10.setVisible(false);
        }
         if(movies2.size() > 10){
            jButtonNext.setVisible(true);
         }
         if(movies2.size() == seitenanzahl+10){
            jButtonNext.setVisible(false);
         }
         if(seitenanzahl != 0){
             jButtonPrevious.setVisible(true);

         }else{
             jButtonPrevious.setVisible(false);
         }
     }
    public void Newest10() throws SQLException, MalformedURLException, IOException{
       MouseAdapter listener = new MouseImpl();
        
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
     
    public void Top10() throws SQLException, MalformedURLException{
       MouseAdapter listener = new MouseImpl();
       
        jLabelBild11.setIcon(new ImageIcon(new URL(movies.get(10).getImglink())));
        jLabelBild11.setText(null);
        jLabelBild11.addMouseListener(listener);

        jLabelBild19.setIcon(new ImageIcon(new URL(movies.get(11).getImglink())));
        jLabelBild19.setText(null);
        jLabelBild19.addMouseListener(listener);
        
        jLabelBild12.setIcon(new ImageIcon(new URL(movies.get(12).getImglink())));
        jLabelBild12.setText(null);
        jLabelBild12.addMouseListener(listener);
        
        jLabelBild14.setIcon(new ImageIcon(new URL(movies.get(13).getImglink())));
        jLabelBild14.setText(null);
        jLabelBild14.addMouseListener(listener);
        
        jLabelBild15.setIcon(new ImageIcon(new URL(movies.get(14).getImglink())));
        jLabelBild15.setText(null);
        jLabelBild15.addMouseListener(listener);
        
        jLabelBild17.setIcon(new ImageIcon(new URL(movies.get(15).getImglink())));
        jLabelBild17.setText(null);
        jLabelBild17.addMouseListener(listener);
        
        jLabelBild18.setIcon(new ImageIcon(new URL(movies.get(16).getImglink())));
        jLabelBild18.setText(null);
        jLabelBild18.addMouseListener(listener);
        
        jLabelBild20.setIcon(new ImageIcon(new URL(movies.get(17).getImglink())));
        jLabelBild20.setText(null);
        jLabelBild20.addMouseListener(listener);
        
        jLabelBild13.setIcon(new ImageIcon(new URL(movies.get(18).getImglink())));
        jLabelBild13.setText(null);
        jLabelBild13.addMouseListener(listener);
        
        jLabelBild16.setIcon(new ImageIcon(new URL(movies.get(19).getImglink())));
        jLabelBild16.setText(null);
        jLabelBild16.addMouseListener(listener);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonrentedMovies = new javax.swing.JButton();
        jComboPrice = new javax.swing.JComboBox();
        jComboLanguage = new javax.swing.JComboBox();
        jComboRating = new javax.swing.JComboBox();
        jComboAgeRating = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextSearch = new javax.swing.JTextPane();
        jButtonLogOut = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jComboGenre = new javax.swing.JComboBox();
        jLabel_logo = new javax.swing.JLabel();
        jButtonChangeMovie = new javax.swing.JButton();
        jButtonAddMovie = new javax.swing.JButton();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanelMain = new javax.swing.JPanel();
        jLabelBild5 = new javax.swing.JLabel();
        jLabelBild9 = new javax.swing.JLabel();
        jLabelBild4 = new javax.swing.JLabel();
        jLabelBild18 = new javax.swing.JLabel();
        jLabelBild2 = new javax.swing.JLabel();
        jLabelBild16 = new javax.swing.JLabel();
        jLabelBild19 = new javax.swing.JLabel();
        jLabelBild14 = new javax.swing.JLabel();
        jLabelBild13 = new javax.swing.JLabel();
        jLabelBild12 = new javax.swing.JLabel();
        jButtonNext = new javax.swing.JButton();
        jLabelBild15 = new javax.swing.JLabel();
        jLabelBild17 = new javax.swing.JLabel();
        jButtonPrevious = new javax.swing.JButton();
        jLabelTop10 = new javax.swing.JLabel();
        jLabelBild8 = new javax.swing.JLabel();
        jLabelBild6 = new javax.swing.JLabel();
        jLabelBild7 = new javax.swing.JLabel();
        jLabelBild3 = new javax.swing.JLabel();
        jLabelBild11 = new javax.swing.JLabel();
        jLabelNewest = new javax.swing.JLabel();
        jLabelBild10 = new javax.swing.JLabel();
        jLabelBild20 = new javax.swing.JLabel();
        jLabelBild1 = new javax.swing.JLabel();
        jButtonReturn = new javax.swing.JButton();
        jLabelLastLogin = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButtonrentedMovies.setText("Rented Movies");
        jButtonrentedMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonrentedMoviesActionPerformed(evt);
            }
        });

        jComboPrice.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Price", "3.99", "2.99", "1.99" }));
        jComboPrice.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboPriceItemStateChanged(evt);
            }
        });
        jComboPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPriceActionPerformed(evt);
            }
        });

        jComboLanguage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Language", "English", "German", "Spanish" }));
        jComboLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboLanguageActionPerformed(evt);
            }
        });

        jComboRating.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rating", "5 Best", "4", "3", "2", "1 Badest" }));

        jComboAgeRating.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Age rating", "0", "6", "12", "16", "18" }));
        jComboAgeRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboAgeRatingActionPerformed(evt);
            }
        });

        jLabel1.setText("Search:");

        jScrollPane1.setViewportView(jTextSearch);

        jButtonLogOut.setText("Log Out");
        jButtonLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogOutActionPerformed(evt);
            }
        });

        jComboGenre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Genre", "Action", "Adventure", "Thriller", "Fantasy", "Animation", "Comedy" }));
        jComboGenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboGenreActionPerformed(evt);
            }
        });

        jLabel_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movierental/Logo.png"))); // NOI18N
        jLabel_logo.setText("jLabel2");

        jButtonChangeMovie.setText("Change Movie");
        jButtonChangeMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeMovieActionPerformed(evt);
            }
        });

        jButtonAddMovie.setText("Add Movie");
        jButtonAddMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddMovieActionPerformed(evt);
            }
        });

        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabelBild5.setText("jLabel19");

        jLabelBild9.setText("jLabel9");

        jLabelBild4.setText("jLabel4");

        jLabelBild18.setText("jLabel8");

        jLabelBild2.setText("jLabel18");

        jLabelBild16.setText("jLabel6");

        jLabelBild19.setText("jLabel9");

        jLabelBild14.setText("jLabel4");

        jLabelBild13.setText("jLabel4");

        jLabelBild12.setText("jLabel18");

        jButtonNext.setText(">");
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        jLabelBild15.setText("jLabel19");

        jLabelBild17.setText("jLabel7");

        jButtonPrevious.setText("<");
        jButtonPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviousActionPerformed(evt);
            }
        });

        jLabelTop10.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabelTop10.setText("Top 10");

        jLabelBild8.setText("jLabel8");

        jLabelBild6.setText("jLabel6");

        jLabelBild7.setText("jLabel7");

        jLabelBild3.setText("jLabel4");

        jLabelBild11.setText("jLabel17");

        jLabelNewest.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabelNewest.setText("Newest 10");

        jLabelBild10.setText("jLabel14");

        jLabelBild20.setText("jLabel14");

        jLabelBild1.setText("jLabel17");

        jButtonReturn.setText("Return");
        jButtonReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTop10)
                            .addComponent(jLabelNewest)
                            .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelBild16, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(jLabelBild11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelBild17, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(jLabelBild12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelBild18, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(jLabelBild13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelBild14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelBild19, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelBild20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelBild15, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelBild6, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(jLabelBild1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelBild7, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(jLabelBild2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelMainLayout.createSequentialGroup()
                                        .addComponent(jLabelBild8, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelBild9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabelBild10, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelMainLayout.createSequentialGroup()
                                        .addComponent(jLabelBild3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelBild4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelBild5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButtonReturn)
                        .addGap(275, 275, 275)
                        .addComponent(jButtonPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabelNewest)
                .addGap(30, 30, 30)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBild1)
                    .addComponent(jLabelBild2)
                    .addComponent(jLabelBild3)
                    .addComponent(jLabelBild4)
                    .addComponent(jLabelBild5))
                .addGap(20, 20, 20)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBild8)
                        .addComponent(jLabelBild9)
                        .addComponent(jLabelBild10))
                    .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBild6)
                        .addComponent(jLabelBild7)))
                .addGap(18, 18, 18)
                .addComponent(jLabelTop10)
                .addGap(30, 30, 30)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelBild11)
                    .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBild13)
                        .addComponent(jLabelBild14)
                        .addComponent(jLabelBild15)
                        .addComponent(jLabelBild12)))
                .addGap(20, 20, 20)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBild18)
                        .addComponent(jLabelBild19)
                        .addComponent(jLabelBild20))
                    .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBild16)
                        .addComponent(jLabelBild17)))
                .addGap(1, 1, 1)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonNext)
                        .addComponent(jButtonPrevious))
                    .addComponent(jButtonReturn))
                .addGap(1, 1, 1))
        );

        jScrollPane2.setViewportView(jPanelMain);

        jButton1.setText("Overview");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonAddMovie)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonChangeMovie)
                                .addGap(17, 17, 17)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonrentedMovies)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonLogOut))
                            .addComponent(jLabelLastLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jComboGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboAgeRating, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSearch))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jComboLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboRating, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAddMovie)
                        .addComponent(jButtonChangeMovie)
                        .addComponent(jButtonrentedMovies)
                        .addComponent(jButtonLogOut)
                        .addComponent(jButton1))
                    .addComponent(jLabelLastLogin))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboAgeRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonrentedMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonrentedMoviesActionPerformed
        try {
            new RentedBook().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonrentedMoviesActionPerformed

    private void jComboPriceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboPriceItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboPriceItemStateChanged

    private void jComboPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboPriceActionPerformed

    private void jComboLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboLanguageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboLanguageActionPerformed

    private void jComboAgeRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboAgeRatingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboAgeRatingActionPerformed

    private void jComboGenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboGenreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboGenreActionPerformed

    private void jButtonChangeMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeMovieActionPerformed
        new ChangeBook().setVisible(true);
    }//GEN-LAST:event_jButtonChangeMovieActionPerformed

    private void jButtonAddMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddMovieActionPerformed
        new AddBook().setVisible(true);
    }//GEN-LAST:event_jButtonAddMovieActionPerformed

    private void jButtonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogOutActionPerformed
        JOptionPane.showMessageDialog(null, "Successfully logged out!");
        setVisible(false);
            try {
                new Login().setVisible(true);
            } catch (    IOException | SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButtonLogOutActionPerformed
    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
            
            db = new Verbindung();
            db.start();
            conn = db.getVerbindung();
            
            jButtonReturn.setVisible(true);
            suchetext = jTextSearch.getText();
            genre = jComboGenre.getSelectedIndex();
            price = jComboPrice.getSelectedIndex();
            agerating = jComboAgeRating.getSelectedIndex();
            rating = jComboRating.getSelectedIndex();
            language = jComboLanguage.getSelectedIndex();
     
            if(evt.getSource() == jButtonSearch){
                movies = new ArrayList<>();
                try {
                    gen = getGenre(genre);
                    pri = getPrice(price);
                    age = getAgerating(agerating);
                    rate = getRating(rating);
                    lang = getLanguage(language);

                    if(!(rate.equals("%"))){
                    stmt4 = conn.createStatement();
                    rs3 = stmt4.executeQuery("SELECT *,avg(rating) as average FROM movie natural left join rates natural join haslang WHERE title LIKE '%"+ suchetext +"%' and genre LIKE '%" + gen + "%' and price <= '" + pri + "'  and ageRating <= '"+ age +"' and Language LIKE '%"+ lang +"%' and inactive = 0 group by mid having average >= "+rate+"");
                    stmtSearch = conn.createStatement();

                    while(rs3.next()){
                    rsSearch = stmtSearch.executeQuery("Select * from movie natural join haslang where mid = "+rs3.getString("mid")+" ");
                    rsSearch.next();
                    String language1 = rsSearch.getString("Language");
                    rsSearch.last();
                    String language2 = rsSearch.getString("Language");

                    if(language2.equals(language1)){
                        language2 = "";
                    }
                    Book movie = new Book(rs3.getString("mid"),rs3.getString("title"),rs3.getString("picture"),rs3.getString("average"), rs3.getString("description"),rs3.getString("genre"),rs3.getString("agerating"),rs3.getString("releaseYear"),rs3.getString("duration"),rs3.getString("streamlink"),language1, language2, rs3.getString("price"),"");
                    movies.add(movie);
                    }

                    while(movies.size() %10 != 0){
                    Book dump = new Book("","","http://stefano.bplaced.net/nothing.png",null,"","","","","","","","","","");
                    movies.add(dump);
                    }
                    this.searchResult(movies);

                    }else{
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery("SELECT *,avg(rating) as average FROM movie natural join haslang natural left join rates WHERE title LIKE '%"+ suchetext +"%' and genre LIKE '%" + gen + "%' and price <= '" + pri + "'  and ageRating <= '"+ age +"' and Language LIKE '%"+ lang +"%' and inactive = 0 group by mid");
                    stmtSearch = conn.createStatement();

                    while(rs.next()){

                    rsSearch = stmtSearch.executeQuery("Select * from movie natural join haslang where mid = "+rs.getString("mid")+" ");
                    rsSearch.next();
                    String language1 = rsSearch.getString("Language");
                    rsSearch.last();
                    String language2 = rsSearch.getString("Language");

                    if(language2.equals(language1)){
                        language2 = "";
                    }
                    Book movie = new Book(rs.getString("mid"),rs.getString("title"),rs.getString("picture"),rs.getString("average"), rs.getString("description"),rs.getString("genre"),rs.getString("agerating"),rs.getString("releaseYear"),rs.getString("duration"),rs.getString("streamlink"),language1, language2, rs.getString("price"),"");
                    movies.add(movie);
                    }
                    while(movies.size() %10 != 0){
                    Book dump = new Book("","","http://stefano.bplaced.net/nothing.png",null,"","","","","","","","","","");
                    movies.add(dump);
                    }
                    this.searchResult(movies);
                    }

                } catch (        SQLException | MalformedURLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        if(jButtonNext == evt.getSource()){
            seitenanzahl = seitenanzahl + 10;
                try {
                    this.searchResult(movies);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jButtonPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviousActionPerformed
        if(jButtonPrevious == evt.getSource()){
            seitenanzahl = seitenanzahl - 10;
           try {
                    this.searchResult(movies);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
       }
    }//GEN-LAST:event_jButtonPreviousActionPerformed

    
    private void jButtonReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturnActionPerformed
       this.dispose();
        try {
            new Admin(user).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jButtonReturnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            try {
                new Overview().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Admin(new User());
                } catch (SQLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAddMovie;
    private javax.swing.JButton jButtonChangeMovie;
    private javax.swing.JButton jButtonLogOut;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrevious;
    private javax.swing.JButton jButtonReturn;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonrentedMovies;
    private javax.swing.JComboBox jComboAgeRating;
    private javax.swing.JComboBox jComboGenre;
    private javax.swing.JComboBox jComboLanguage;
    private javax.swing.JComboBox jComboPrice;
    private javax.swing.JComboBox jComboRating;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelBild1;
    private javax.swing.JLabel jLabelBild10;
    private javax.swing.JLabel jLabelBild11;
    private javax.swing.JLabel jLabelBild12;
    private javax.swing.JLabel jLabelBild13;
    private javax.swing.JLabel jLabelBild14;
    private javax.swing.JLabel jLabelBild15;
    private javax.swing.JLabel jLabelBild16;
    private javax.swing.JLabel jLabelBild17;
    private javax.swing.JLabel jLabelBild18;
    private javax.swing.JLabel jLabelBild19;
    private javax.swing.JLabel jLabelBild2;
    private javax.swing.JLabel jLabelBild20;
    private javax.swing.JLabel jLabelBild3;
    private javax.swing.JLabel jLabelBild4;
    private javax.swing.JLabel jLabelBild5;
    private javax.swing.JLabel jLabelBild6;
    private javax.swing.JLabel jLabelBild7;
    private javax.swing.JLabel jLabelBild8;
    private javax.swing.JLabel jLabelBild9;
    private javax.swing.JLabel jLabelLastLogin;
    private javax.swing.JLabel jLabelNewest;
    private javax.swing.JLabel jLabelTop10;
    private javax.swing.JLabel jLabel_logo;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextSearch;
    // End of variables declaration//GEN-END:variables

class MouseImpl extends MouseAdapter {
         @Override
        public void mouseClicked(MouseEvent e) {
            Object source = e.getSource();
            if (source == jLabelBild1) {
            try {
                new BookInfo(user,movies.get(0+seitenanzahl)).setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild2) {
             try {
                BookInfo window = new BookInfo(user,movies.get(1+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (source == jLabelBild3) {
             try {
                BookInfo window = new BookInfo(user,movies.get(2+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild4) {
             try {
                BookInfo window = new BookInfo(user,movies.get(3+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild5) {
             try {
                BookInfo window = new BookInfo(user,movies.get(4+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild6) {
             try {
                BookInfo window = new BookInfo(user,movies.get(5+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild7) {
             try {
                BookInfo window = new BookInfo(user,movies.get(6+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild8) {
             try {
                BookInfo window = new BookInfo(user,movies.get(7+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        if (source == jLabelBild9) {
             try {
                BookInfo window = new BookInfo(user,movies.get(8+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild10) {
             try {
                BookInfo window = new BookInfo(user,movies.get(9+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
        if (source == jLabelBild11) {
            try {
                new BookInfo(user,movies.get(10+seitenanzahl)).setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild19) {
             try {
                BookInfo window = new BookInfo(user,movies.get(11+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (source == jLabelBild12) {
             try {
                BookInfo window = new BookInfo(user,movies.get(12+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild14) {
             try {
                BookInfo window = new BookInfo(user,movies.get(13+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild15) {
             try {
                BookInfo window = new BookInfo(user,movies.get(14+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild17) {
             try {
                BookInfo window = new BookInfo(user,movies.get(15+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild18) {
             try {
                BookInfo window = new BookInfo(user,movies.get(16+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild20) {
             try {
                BookInfo window = new BookInfo(user,movies.get(17+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        if (source == jLabelBild13) {
             try {
                BookInfo window = new BookInfo(user,movies.get(18+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild16) {
             try {
                BookInfo window = new BookInfo(user,movies.get(19+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
    }
    }
}



