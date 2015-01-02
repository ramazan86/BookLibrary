package bookrental;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import bookrental.Login.MyMouseListener;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.sun.mail.util.QEncoderStream;

/**
 *@author Ali Hannoun & Ramazan Cinardere 
 */
 

public class Admin extends javax.swing.JFrame {
    
    /* Class Attributes */
    
        User user;
        ArrayList<Book> books,books2;
        String suchetext,gen,pri,age,rate,lang;
        int genre,price,agerating,rating,language,pages;
        Verbindung db;
        Connection conn;
        Statement stmt,stmt2,stmt3,stmt4,stmtNewest,stmtNewest2,stmtSearch,stmtTop10,stmt2Top10;
        ResultSet rs,rs2,rs3,rsNewest,rsNewest2,rsSearch,rsTop10,rs2Top10;
        static int seitenanzahl = 0;
        ArrayList<Book> bList = new ArrayList<>();
        ArrayList<Book> bList2 = new ArrayList<Book>();
        
        int checkFlag = 0;
        boolean searchResult = false;
        
    /* Class Methods */    
        
    public Admin(User user) throws SQLException, MalformedURLException, IOException {
        this.user = user;
        initComponents();
        this.setSize(870,700);
       
       try{
    	   Newest10();
    	   Top10();
       }catch(Exception e) {
    	   Top10();
    	   System.err.println(e.getClass().getName() + " " +e.getMessage() + " " +e.getCause());
       }
       
       
       
        setLocationRelativeTo(null);
        this.setVisible(true);
        jButton_previous.setVisible(false);
        jButton_next.setVisible(false);
        jButton_return.setVisible(false);
        jLabelLastLogin.setText("Your last login was on the " + user.lastLogin);
    }
    
    public static String getGenre(int g){
        switch(g){
            case 1:
                return "Biographie"; 
            case 2:
                return "Children";
            case 3:
                return "Computer Science";
            case 4:
                return "Cooking";
            case 5:
                return "History";
            case 6:
                return "Novel and Narratives";
            case 7: 
                return "School & Education";
            default:
                return " ";
        }
    }
    
    public static String getPrice(int p){
        if(p == 1){
        	return "3.99";
        }else if(p == 2){
        	return "2.99";
        }else if(p == 3){
        	return "1.99";
        }
        else return " ";
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
                return " ";
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
                return " ";
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
                return " ";
        
        }
    }
   
    public void searchResult(ArrayList<Book> books2) throws MalformedURLException{
       
    	searchResult = true;
    	
    	
    	
    	
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
        jLabel_img18.setVisible(false);
        jLabel_img20.setVisible(false);
        jLabel_img13.setVisible(false);
        jLabel_img16.setVisible(false);

        jLabel_top10.setVisible(false);
        jLabel_newest.setText("Search Result for '"+ suchetext +"':");
        
        String methodName = "searchResult";
        
        System.out.println("Line-182: " +books2.size());
        
        if(!(books2.get(0+seitenanzahl).getTitle().equals(""))){
	        jLabel_img1.setIcon(new ImageIcon(new URL(books2.get(0+seitenanzahl).getImglink())));
	        jLabel_img1.setText(null);
	        jLabel_img1.addMouseListener(new MyListener(books2.get(0+seitenanzahl),methodName));
	        jLabel_img1.setVisible(true);
        }else{
        	jLabel_img1.setVisible(false);
        }
        if(!(books2.get(1+seitenanzahl).getTitle().equals(""))){
	        jLabel_img2.setIcon(new ImageIcon(new URL(books2.get(1+seitenanzahl).getImglink())));
	        jLabel_img2.setText(null);
	        jLabel_img2.addMouseListener(new MyListener(books2.get(1+seitenanzahl),methodName));
	        jLabel_img2.setVisible(true);
        }else{
        	jLabel_img2.setVisible(false);
        }
        if(!(books2.get(2+seitenanzahl).getTitle().equals(""))){
	        jLabel_img3.setIcon(new ImageIcon(new URL(books2.get(2+seitenanzahl).getImglink())));
	        jLabel_img3.setText(null);
	        jLabel_img3.addMouseListener(new MyListener(books2.get(2+seitenanzahl),methodName));
	        jLabel_img3.setVisible(true);
        }else{
        	jLabel_img3.setVisible(false);
        }
        if(!(books2.get(3+seitenanzahl).getTitle().equals(""))){
	        jLabel_img4.setIcon(new ImageIcon(new URL(books2.get(3+seitenanzahl).getImglink())));
	        jLabel_img4.setText(null);
	        jLabel_img4.addMouseListener(new MyListener(books2.get(3+seitenanzahl),methodName));
	        jLabel_img4.setVisible(true);
        }else{
        	jLabel_img4.setVisible(false);
        }
        if(!(books2.get(4+seitenanzahl).getTitle().equals(""))){
	        jLabel_img5.setIcon(new ImageIcon(new URL(books2.get(4+seitenanzahl).getImglink())));
	        jLabel_img5.setText(null);
	        jLabel_img5.addMouseListener(new MyListener(books2.get(4+seitenanzahl),methodName));
	        jLabel_img5.setVisible(true);
        }else{
        	jLabel_img5.setVisible(false);
        }
        if(!(books2.get(5+seitenanzahl).getTitle().equals(""))){
	        jLabel_img6.setIcon(new ImageIcon(new URL(books2.get(5+seitenanzahl).getImglink())));
	        jLabel_img6.setText(null);
	        jLabel_img6.addMouseListener(new MyListener(books2.get(5+seitenanzahl),methodName));
	        jLabel_img6.setVisible(true);
        }else{
        	jLabel_img6.setVisible(false);
        }
        if(!(books2.get(6+seitenanzahl).getTitle().equals(""))){
	        jLabel_img7.setIcon(new ImageIcon(new URL(books2.get(6+seitenanzahl).getImglink())));
	        jLabel_img7.setText(null);
	        jLabel_img7.addMouseListener(new MyListener(books2.get(6+seitenanzahl),methodName));
	        jLabel_img7.setVisible(true);
        }else{
        	jLabel_img7.setVisible(false);
        }
        if(!(books2.get(7+seitenanzahl).getTitle().equals(""))){
	        jLabel_img8.setIcon(new ImageIcon(new URL(books2.get(7+seitenanzahl).getImglink())));
	        jLabel_img8.setText(null);
	        jLabel_img8.addMouseListener(new MyListener(books2.get(7+seitenanzahl),methodName));
	        jLabel_img8.setVisible(true);
        }else{
        	jLabel_img8.setVisible(false);
        }
        if(!(books2.get(8+seitenanzahl).getTitle().equals(""))){
	        jLabel_img9.setIcon(new ImageIcon(new URL(books2.get(8+seitenanzahl).getImglink())));
	        jLabel_img9.setText(null);
	        jLabel_img9.addMouseListener(new MyListener(books2.get(8+seitenanzahl),methodName));
	        jLabel_img9.setVisible(true);
        }else{
        	jLabel_img9.setVisible(false);
        }
        if(!(books2.get(9+seitenanzahl).getTitle().equals(""))){
	        jLabel_img10.setIcon(new ImageIcon(new URL(books2.get(9+seitenanzahl).getImglink())));
	        jLabel_img10.setText(null);
	        jLabel_img10.addMouseListener(new MyListener(books2.get(9+seitenanzahl),methodName));
	        jLabel_img10.setVisible(true);
        }else{
        	jLabel_img10.setVisible(false);
        }
         if(books2.size() > 10){
            jButton_next.setVisible(true);
         }
         if(books2.size() == seitenanzahl+10){
            jButton_next.setVisible(false);
         }
         if(seitenanzahl != 0){
             jButton_previous.setVisible(true);

         }else{
             jButton_previous.setVisible(false);
         }
     }
    
    public void Newest10() throws SQLException, MalformedURLException, IOException{
        
       searchResult = false;
       books = Book.getNewest();
       String methodName = "new10";
       
       JLabel[] j = new JLabel[10];
	        j[0] = jLabel_img1;
	        j[1] = jLabel_img2;
	        j[2] = jLabel_img3;
	        j[3] = jLabel_img4;
	        j[4] = jLabel_img5;
	        j[5] = jLabel_img6;
	        j[6] = jLabel_img7;
	        j[7] = jLabel_img8;
	        j[8] = jLabel_img9;
	        j[9] = jLabel_img10;
       
    
	    for(int i = 0; i<books.size(); i++) {
	    	j[i].setIcon(new ImageIcon(new URL(books.get(i).getImglink())));
	    	j[i].setText(null);
	    	j[i].addMouseListener(new MyListener(books.get(i),methodName));
	    }
       
	    for(int i = books.size(); i<j.length; i++) {
	    	j[i].setVisible(false);
	    }
	    
	    
    }
     
    public void Top10() throws SQLException, MalformedURLException{
       
       searchResult = false;
       books = Book.getTop10();	
       String methodName = "top10";
       
  	  JLabel[] j = new JLabel[10];
	        j[0] = jLabel_img11;
	        j[1] = jLabel_img12;
	        j[2] = jLabel_img13;
	        j[3] = jLabel_img14;
	        j[4] = jLabel_img15;
	        j[5] = jLabel_img16;
	        j[6] = jLabel_img17;
	        j[7] = jLabel_img18;
	        j[8] = jLabel_img19;
	        j[9] = jLabel_img20;
  	
	       
	    for(int i = 0; i<books.size(); i++) {
	    	j[i].setIcon(new ImageIcon(new URL(books.get(i).getImglink())));
		    j[i].setText(null);
		    j[i].addMouseListener(new MyListener(books.get(i),methodName));
		}
	        
	    for(int i = books.size(); i<j.length; i++) {
		    j[i].setVisible(false);
	    }   
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_saledBook = new javax.swing.JButton();
        jCombo_price = new javax.swing.JComboBox();
        jCombo_language = new javax.swing.JComboBox();
        jCombo_rating = new javax.swing.JComboBox();
        jCombo_ageRating = new javax.swing.JComboBox();
        jLabel_search = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextField_search = new javax.swing.JTextPane();
        jButton_logOut = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jCombo_genre = new javax.swing.JComboBox();
        jButton_changeBook = new javax.swing.JButton();
        jButton_addBook = new javax.swing.JButton();
        jButton_search = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel_main = new javax.swing.JPanel();
        jLabel_img5 = new javax.swing.JLabel();
        jLabel_img9 = new javax.swing.JLabel();
        jLabel_img4 = new javax.swing.JLabel();
        jLabel_img18 = new javax.swing.JLabel();
        jLabel_img2 = new javax.swing.JLabel();
        jLabel_img16 = new javax.swing.JLabel();
        jLabel_img19 = new javax.swing.JLabel();
        jLabel_img14 = new javax.swing.JLabel();
        jLabel_img13 = new javax.swing.JLabel();
        jLabel_img12 = new javax.swing.JLabel();
        jButton_next = new javax.swing.JButton();
        jLabel_img15 = new javax.swing.JLabel();
        jLabel_img17 = new javax.swing.JLabel();
        jButton_previous = new javax.swing.JButton();
        jLabel_top10 = new javax.swing.JLabel();
        jLabel_img8 = new javax.swing.JLabel();
        jLabel_img6 = new javax.swing.JLabel();
        jLabel_img7 = new javax.swing.JLabel();
        jLabel_img3 = new javax.swing.JLabel();
        jLabel_img11 = new javax.swing.JLabel();
        jLabel_newest = new javax.swing.JLabel();
        jLabel_img10 = new javax.swing.JLabel();
        jLabel_img20 = new javax.swing.JLabel();
        jLabel_img1 = new javax.swing.JLabel();
        jButton_return = new javax.swing.JButton();
        jLabelLastLogin = new javax.swing.JLabel();
        jButton_overwiew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton_saledBook.setText("Bought Book");
        jButton_saledBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_saledBookActionPerformed(evt);
            }
        });

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

        jCombo_language.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Language", "English", "German", "Spanish" }));
        jCombo_language.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombo_languageActionPerformed(evt);
            }
        });

        jCombo_rating.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rating", "5 Best", "4", "3", "2", "1 Badest" }));

        jCombo_ageRating.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Age rating", "0", "6", "12", "16", "18" }));
        jCombo_ageRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombo_ageRatingActionPerformed(evt);
            }
        });

        jLabel_search.setText("Search:");

        jScrollPane1.setViewportView(jTextField_search);

        jButton_logOut.setText("Log Out");
        jButton_logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_logOutActionPerformed(evt);
            }
        });

        jCombo_genre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Genre", "Biographies", "Children", "Computer science ", "Cooking", "History", "Novel and Narratives", " " }));
        jCombo_genre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombo_genreActionPerformed(evt);
            }
        });

        jButton_changeBook.setText("Change Book");
        jButton_changeBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_changeBookActionPerformed(evt);
            }
        });

        jButton_addBook.setText("Add Book");
        jButton_addBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addBookActionPerformed(evt);
            }
        });

        jButton_search.setText("Search");
        jButton_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_searchActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel_img5.setText("img5");

        jLabel_img9.setText("img9");

        jLabel_img4.setText("img4");

        jLabel_img18.setText("img18");

        jLabel_img2.setText("img2");

        jLabel_img16.setText("img16");

        jLabel_img19.setText("img19");

        jLabel_img14.setText("img14");

        jLabel_img13.setText("img13");

        jLabel_img12.setText("img12");

        jButton_next.setText(">");
        jButton_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nextActionPerformed(evt);
            }
        });

        jLabel_img15.setText("im15");

        jLabel_img17.setText("img17");

        jButton_previous.setText("<");
        jButton_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_previousActionPerformed(evt);
            }
        });

        jLabel_top10.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel_top10.setText("Top 10");

        jLabel_img8.setText("img8");

        jLabel_img6.setText("img6");

        jLabel_img7.setText("img7");

        jLabel_img3.setText("img3");

        jLabel_img11.setText("img11");

        jLabel_newest.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel_newest.setText("Newest 10");

        jLabel_img10.setText("img10");

        jLabel_img20.setText("img19");

        jLabel_img1.setText("img1");

        jButton_return.setText("Return");
        jButton_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_returnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_mainLayout = new javax.swing.GroupLayout(jPanel_main);
        jPanel_main.setLayout(jPanel_mainLayout);
        jPanel_mainLayout.setHorizontalGroup(
            jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_mainLayout.createSequentialGroup()
                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_mainLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_top10)
                            .addComponent(jLabel_newest)
                            .addGroup(jPanel_mainLayout.createSequentialGroup()
                                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_img16, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(jLabel_img11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_img17, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(jLabel_img12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_img18, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(jLabel_img13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_img14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_img19, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_img20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel_img15, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel_mainLayout.createSequentialGroup()
                                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_img6, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(jLabel_img1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_img7, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(jLabel_img2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_mainLayout.createSequentialGroup()
                                        .addComponent(jLabel_img8, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel_img9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel_img10, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_mainLayout.createSequentialGroup()
                                        .addComponent(jLabel_img3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel_img4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel_img5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel_mainLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton_return)
                        .addGap(275, 275, 275)
                        .addComponent(jButton_previous, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButton_next, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        jPanel_mainLayout.setVerticalGroup(
            jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_mainLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel_newest)
                .addGap(30, 30, 30)
                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_img1)
                    .addComponent(jLabel_img2)
                    .addComponent(jLabel_img3)
                    .addComponent(jLabel_img4)
                    .addComponent(jLabel_img5))
                .addGap(20, 20, 20)
                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_img8)
                        .addComponent(jLabel_img9)
                        .addComponent(jLabel_img10))
                    .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_img6)
                        .addComponent(jLabel_img7)))
                .addGap(18, 18, 18)
                .addComponent(jLabel_top10)
                .addGap(30, 30, 30)
                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_img11)
                    .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_img13)
                        .addComponent(jLabel_img14)
                        .addComponent(jLabel_img15)
                        .addComponent(jLabel_img12)))
                .addGap(20, 20, 20)
                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_img18)
                        .addComponent(jLabel_img19)
                        .addComponent(jLabel_img20))
                    .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_img16)
                        .addComponent(jLabel_img17)))
                .addGap(1, 1, 1)
                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_next)
                        .addComponent(jButton_previous))
                    .addComponent(jButton_return))
                .addGap(1, 1, 1))
        );

        jScrollPane2.setViewportView(jPanel_main);

        jButton_overwiew.setText("Overview");
        jButton_overwiew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_overwiewActionPerformed(evt);
            }
        });
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("D:\\FH\\ProgrammingExercise\\BookLibrary\\BookLibrary\\src\\Images\\mylog2.png"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblNewLabel)
        					.addGap(33)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(0, 75, Short.MAX_VALUE)
        							.addComponent(jButton_addBook)
        							.addGap(18)
        							.addComponent(jButton_changeBook)
        							.addGap(17)
        							.addComponent(jButton_overwiew)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(jButton_saledBook)
        							.addGap(18)
        							.addComponent(jButton_logOut))
        						.addComponent(jLabelLastLogin, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(20)
        					.addComponent(jCombo_genre, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
        					.addComponent(jCombo_price, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
        					.addComponent(jCombo_ageRating, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jLabel_search)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(jButton_search))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jCombo_language, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
        							.addComponent(jCombo_rating, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))))
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        			.addGap(21))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabelLastLogin)
        					.addGap(52)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jButton_addBook)
        						.addComponent(jButton_changeBook)
        						.addComponent(jButton_saledBook)
        						.addComponent(jButton_logOut)
        						.addComponent(jButton_overwiew))
        					.addGap(85)
        					.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(jLabel_search, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jButton_search)))
        				.addComponent(lblNewLabel))
        			.addGap(17)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jCombo_ageRating, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jCombo_price, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jCombo_genre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jCombo_language, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jCombo_rating, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
        			.addGap(6))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_saledBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_saledBookActionPerformed
        try {
            new SaledBook().setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_saledBookActionPerformed

    private void jCombo_priceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCombo_priceItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_priceItemStateChanged

    private void jCombo_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_priceActionPerformed

    private void jCombo_languageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_languageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_languageActionPerformed

    private void jCombo_ageRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_ageRatingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_ageRatingActionPerformed

    private void jCombo_genreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_genreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_genreActionPerformed

    private void jButton_changeBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_changeBookActionPerformed
        new ChangeBook().setVisible(true);
    }//GEN-LAST:event_jButton_changeBookActionPerformed

    private void jButton_addBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addBookActionPerformed
        new AddBook().setVisible(true);
    }//GEN-LAST:event_jButton_addBookActionPerformed

    private void jButton_logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_logOutActionPerformed
        JOptionPane.showMessageDialog(null, "Successfully logged out!");
        setVisible(false);
            try {
                new Login().setVisible(true);
            } catch (    IOException | SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton_logOutActionPerformed
    
    private void jButton_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_searchActionPerformed
            
            db = new Verbindung();
            db.start();
            conn = db.getVerbindung();
            Statement stmt = null;
            ResultSet rs;
            String queryBegin = "SELECT * FROM book";
            String queryEnd = " GROUP BY mid";
            String query;
            checkFlag = 0;
            
            jButton_return.setVisible(true);
            suchetext = jTextField_search.getText();
            genre = jCombo_genre.getSelectedIndex();
            price = jCombo_price.getSelectedIndex();
            agerating = jCombo_ageRating.getSelectedIndex();
            rating = jCombo_rating.getSelectedIndex();
            language = jCombo_language.getSelectedIndex();
     
            
            if(evt.getSource() == jButton_search){
                   
            	gen = getGenre(genre);
            	pri = getPrice(price);
            	age = getAgerating(agerating);
            	rate = getRating(rating);
            	lang = getLanguage(language);

                        
            	if(suchetext.isEmpty() && genre == 0 && price == 0 && agerating == 0 && rating == 0 && language == 0){
                        	
            		try {
            			stmt = conn.createStatement();
            			rs = stmt.executeQuery(queryBegin);
                            	
            			while(rs.next()) {
            				Book book = new Book(rs.getString("mid"),rs.getString("title"),rs.getString("picture"),
			            						"", rs.getString("description"),rs.getString("genre"),
			            						rs.getString("agerating"),rs.getString("releaseYear"),"",rs.getString("language"), 
			            						rs.getString("language2"), rs.getString("price"),rs.getString("pdflink"),
			            						rs.getString("author"));
			                            		 
            				bList.add(book);
            			}//while(... closing
                            
                    		
                    	while(bList.size() %10 != 0){
                            Book dump = new Book("","","http://booklibrary.bplaced.net/nothing.png",null,"","","","","","","","","","");
                            bList.add(dump);
                    	}
                    	
                    	//show Books on Frame
                    	searchResult(bList);
                    	bList.clear();
                    	
                    	}catch (Exception e) {
            					System.out.println(e.getClass().getName() + "Line-806 " +e.getCause());
                    	}
                     }else {        
                        
                    	 try{  
	                    	 boolean bRate = false , bGen = false, 
	                    			 bPri = false, bAge = false, bLang = false;
	                    	 
                    		 
                    		 if(!gen.equals(" ")){
	                    		 queryBegin += " WHERE genre like '%" +gen +"%'";
	                    		 bGen = true;
	                    	 }
	                    	 if(!pri.equals(" ")) {
	                    		 
	                    		 if(!bGen) {
	                    			 queryBegin+= " WHERE price <= "+pri;
	                    		 }else{
	                    			 queryBegin += " AND price <= " +pri;
	                    		 }
	                    		 bPri = true;
	                    	 }
	                    	 if(!age.equals(" ")) {
	                    		 
	                    		 if(!bGen && !bPri) {
	                    			 queryBegin += " WHERE ageRating <= "+age;
	                    		 }else {
	                    			 queryBegin += " AND ageRating <= " +age;
	                    		 }
	                    		 bAge = true;
	                    	 }
	                    	 if(!lang.equals(" ")){
	                    		 
	                    		 if(!bGen && ! bPri && !bAge) {
	                    			 queryBegin += " WHERE language like '%"+lang+"%'";
	                    		 }else {
	                    			 queryBegin += " AND language like '%" +lang +"%'"; 
	                    		 }
	                    		 bLang = true;
	                    	 }
	                    	 if(!rate.equals(" ")) {
	                    		 queryBegin = "SELECT *, AVG(rating) as average FROM book NATURAL JOIN rates";
	                    		 queryEnd += " HAVING average <= " +rate;
	                    		 bRate = true;
	                    	 }
	                    	 
	                    	 
	                    	 bGen = bPri = bAge = bLang = bRate = false;
	                    	 
	                    	 query = queryBegin + queryEnd;
	                    	 System.out.println(query);
	                    	 
	                    	 stmt = conn.createStatement();
	                    	 rs = stmt.executeQuery(query);
	                    	  
	                    	 while(rs.next()) {
	                    		  
	                    		  Book book = new Book(rs.getString("mid"),rs.getString("title"),rs.getString("picture"),
		            						"", rs.getString("description"),rs.getString("genre"),
		            						rs.getString("agerating"),rs.getString("releaseYear"),"",rs.getString("language"), 
		            						rs.getString("language2"), rs.getString("price"),rs.getString("pdflink"),
		            						rs.getString("author"));
	                    		  
	                    		  bList2.add(book);
	                    	 }
	                    	 
	                    	 
	                    	 while(bList2.size() %10 != 0){
	                               Book dump = new Book("","","http://booklibrary.bplaced.net/nothing.png",null,"","","","","","","","","","");
	                               bList2.add(dump);
	                    	}
	                    	
	                    	 System.out.println("ListSize: " +bList2.size());
	                    	 
	                    	 for(int i = 0; i<bList2.size(); i++) {
	                    		 System.out.println(bList2.get(i).getTitle());
	                    	 }
	                    	 searchResult(bList2);
	                    	 bList2.clear(); 
	                    	 System.out.println();
	                      }catch(Exception e) {
	                    	  System.out.println(e.getCause() + " " +e.getMessage());
	                      } 
                    }//else closing         
            }
            
                                
    }//GEN-LAST:event_jButton_searchActionPerformed

    private void jButton_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextActionPerformed
        if(jButton_next == evt.getSource()){
            seitenanzahl = seitenanzahl + 10;
                try {
                    this.searchResult(bList);
                } catch (MalformedURLException ex) {
                	System.out.println(ex.getCause() + " " +ex.getMessage());
                }
            }
    }//GEN-LAST:event_jButton_nextActionPerformed

    private void jButton_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_previousActionPerformed
        if(jButton_previous == evt.getSource()){
            seitenanzahl = seitenanzahl - 10;
           try {
                    this.searchResult(bList);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
       }
    }//GEN-LAST:event_jButton_previousActionPerformed

    
    private void jButton_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_returnActionPerformed
       this.dispose();
        try {
            new Admin(user).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jButton_returnActionPerformed

    private void jButton_overwiewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_overwiewActionPerformed
            new Overview().setVisible(true);
    }//GEN-LAST:event_jButton_overwiewActionPerformed

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
    private javax.swing.JButton jButton_addBook;
    private javax.swing.JButton jButton_changeBook;
    private javax.swing.JButton jButton_logOut;
    private javax.swing.JButton jButton_next;
    private javax.swing.JButton jButton_overwiew;
    private javax.swing.JButton jButton_previous;
    private javax.swing.JButton jButton_return;
    private javax.swing.JButton jButton_saledBook;
    private javax.swing.JButton jButton_search;
    private javax.swing.JComboBox jCombo_ageRating;
    private javax.swing.JComboBox jCombo_genre;
    private javax.swing.JComboBox jCombo_language;
    private javax.swing.JComboBox jCombo_price;
    private javax.swing.JComboBox jCombo_rating;
    private javax.swing.JLabel jLabelLastLogin;
    private javax.swing.JLabel jLabel_img1;
    private javax.swing.JLabel jLabel_img10;
    private javax.swing.JLabel jLabel_img11;
    private javax.swing.JLabel jLabel_img12;
    private javax.swing.JLabel jLabel_img13;
    private javax.swing.JLabel jLabel_img14;
    private javax.swing.JLabel jLabel_img15;
    private javax.swing.JLabel jLabel_img16;
    private javax.swing.JLabel jLabel_img17;
    private javax.swing.JLabel jLabel_img18;
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
    private javax.swing.JLabel jLabel_newest;
    private javax.swing.JLabel jLabel_search;
    private javax.swing.JLabel jLabel_top10;
    private javax.swing.JPanel jPanel_main;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextField_search;
    private JLabel lblNewLabel;
    // End of variables declaration//GEN-END:variables



	public class MyListener implements MouseListener {

		Book objBook = null;
    	String methodName;
    	
    	public MyListener(Book objBook, String methodName) {
    		this.objBook = objBook;
    		this.methodName = methodName;
    	}
    	
		@Override
		public void mouseClicked(MouseEvent e) {
			
			checkFlag++;
			

			
			if(searchResult){
				System.out.println(checkFlag + " " +methodName + " state: " +searchResult);
				if(checkFlag == 2 && methodName.equals("searchResult")) {
					try {
		                 BookInfo window = new BookInfo(user, objBook);
		                 window.pack();
		                 window.setVisible(true);
		             } catch (MalformedURLException ex) {
		             }
				}	
			}
			else {
				System.out.println(checkFlag + " " +methodName + " state: " +searchResult);

				if((methodName.equals("top10") && (checkFlag == 1 || checkFlag == 2))
						|| (methodName.equals("new10") && (checkFlag == 1 || checkFlag == 2))
						&& !methodName.equals("searchResult")) {
					try {
		                 BookInfo window = new BookInfo(user, objBook);
		                 window.pack();
		                 window.setVisible(true);
		             } catch (MalformedURLException ex) {
		             }
				}
			}
			
			 if(checkFlag == 2) {
					checkFlag = 0;
			 }
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
		
	}
}



