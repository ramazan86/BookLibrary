package bookrental;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.views.AbstractView;

import bookrental.Admin.MyListener;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Login extends javax.swing.JFrame implements WindowListener { // Login, Register and ForgottenPassword Function

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
    Object[] options = {"Yes", "Abort"};
    
    boolean searchResult;
    int checkFlag = 0;
    
    public Login() throws IOException, SQLException {
        user.setUid("0");
        initComponents();
        this.setSize(870, 700);
        setLocationRelativeTo(null);
        
        //Check if Internet Connection is established
      try {
    	  this.Newest10();
    	  this.Top10();
      }catch (Exception e) {
    	  System.out.println(e.getClass().getTypeName() + " " +e.getMessage());
      }  
       this.Top10();
        this.setVisible(true);
        getRootPane().setDefaultButton(jButton_login);
        jButton_previous.setVisible(false);
        jButton_next.setVisible(false);
        jButton_return.setVisible(false);
        
        this.addWindowListener(this);
    }

    // Shows the result of the search
    public void searchResult(ArrayList<Book> books2) throws MalformedURLException {

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
	        jLabel_img1.addMouseListener(new MyMouseListener(books2.get(0+seitenanzahl),methodName));
	        jLabel_img1.setVisible(true);
        }else{
        	jLabel_img1.setVisible(false);
        }
        if(!(books2.get(1+seitenanzahl).getTitle().equals(""))){
	        jLabel_img2.setIcon(new ImageIcon(new URL(books2.get(1+seitenanzahl).getImglink())));
	        jLabel_img2.setText(null);
	        jLabel_img2.addMouseListener(new MyMouseListener(books2.get(1+seitenanzahl),methodName));
	        jLabel_img2.setVisible(true);
        }else{
        	jLabel_img2.setVisible(false);
        }
        if(!(books2.get(2+seitenanzahl).getTitle().equals(""))){
	        jLabel_img3.setIcon(new ImageIcon(new URL(books2.get(2+seitenanzahl).getImglink())));
	        jLabel_img3.setText(null);
	        jLabel_img3.addMouseListener(new MyMouseListener(books2.get(2+seitenanzahl),methodName));
	        jLabel_img3.setVisible(true);
        }else{
        	jLabel_img3.setVisible(false);
        }
        if(!(books2.get(3+seitenanzahl).getTitle().equals(""))){
	        jLabel_img4.setIcon(new ImageIcon(new URL(books2.get(3+seitenanzahl).getImglink())));
	        jLabel_img4.setText(null);
	        jLabel_img4.addMouseListener(new MyMouseListener(books2.get(3+seitenanzahl),methodName));
	        jLabel_img4.setVisible(true);
        }else{
        	jLabel_img4.setVisible(false);
        }
        if(!(books2.get(4+seitenanzahl).getTitle().equals(""))){
	        jLabel_img5.setIcon(new ImageIcon(new URL(books2.get(4+seitenanzahl).getImglink())));
	        jLabel_img5.setText(null);
	        jLabel_img5.addMouseListener(new MyMouseListener(books2.get(4+seitenanzahl),methodName));
	        jLabel_img5.setVisible(true);
        }else{
        	jLabel_img5.setVisible(false);
        }
        if(!(books2.get(5+seitenanzahl).getTitle().equals(""))){
	        jLabel_img6.setIcon(new ImageIcon(new URL(books2.get(5+seitenanzahl).getImglink())));
	        jLabel_img6.setText(null);
	        jLabel_img6.addMouseListener(new MyMouseListener(books2.get(5+seitenanzahl),methodName));
	        jLabel_img6.setVisible(true);
        }else{
        	jLabel_img6.setVisible(false);
        }
        if(!(books2.get(6+seitenanzahl).getTitle().equals(""))){
	        jLabel_img7.setIcon(new ImageIcon(new URL(books2.get(6+seitenanzahl).getImglink())));
	        jLabel_img7.setText(null);
	        jLabel_img7.addMouseListener(new MyMouseListener(books2.get(6+seitenanzahl),methodName));
	        jLabel_img7.setVisible(true);
        }else{
        	jLabel_img7.setVisible(false);
        }
        if(!(books2.get(7+seitenanzahl).getTitle().equals(""))){
	        jLabel_img8.setIcon(new ImageIcon(new URL(books2.get(7+seitenanzahl).getImglink())));
	        jLabel_img8.setText(null);
	        jLabel_img8.addMouseListener(new MyMouseListener(books2.get(7+seitenanzahl),methodName));
	        jLabel_img8.setVisible(true);
        }else{
        	jLabel_img8.setVisible(false);
        }
        if(!(books2.get(8+seitenanzahl).getTitle().equals(""))){
	        jLabel_img9.setIcon(new ImageIcon(new URL(books2.get(8+seitenanzahl).getImglink())));
	        jLabel_img9.setText(null);
	        jLabel_img9.addMouseListener(new MyMouseListener(books2.get(8+seitenanzahl),methodName));
	        jLabel_img9.setVisible(true);
        }else{
        	jLabel_img9.setVisible(false);
        }
        if(!(books2.get(9+seitenanzahl).getTitle().equals(""))){
	        jLabel_img10.setIcon(new ImageIcon(new URL(books2.get(9+seitenanzahl).getImglink())));
	        jLabel_img10.setText(null);
	        jLabel_img10.addMouseListener(new MyMouseListener(books2.get(9+seitenanzahl),methodName));
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

    // Shows the Newest10 books

    public void Newest10() throws SQLException, MalformedURLException, IOException {

        books = Book.getNewest();
        
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
	    	j[i].addMouseListener(new MyMouseListener(books.get(i)));
	    }
        
	    for(int i = books.size(); i<j.length; i++) {
	    	j[i].setVisible(false);
	    }
    }

    // Shows the Top10 books

    public void Top10() throws SQLException, MalformedURLException {
        
    	books = Book.getTop10();	
    	
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
		    j[i].addMouseListener(new MyMouseListener(books.get(i)));
		}
	        
	    for(int i = books.size(); i<j.length; i++) {
		    j[i].setVisible(false);
	    }   
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
        jLabel_img18 = new javax.swing.JLabel();
        jLabel_img1 = new javax.swing.JLabel();
        jButton_previous = new javax.swing.JButton();
        jLabel_img16 = new javax.swing.JLabel();
        jLabel_img15 = new javax.swing.JLabel();
        jButton_next = new javax.swing.JButton();
        jLabel_img6  = new javax.swing.JLabel();
        jLabel_img8  = new javax.swing.JLabel();
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

        jCombo_genre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Genre", "Biographies", "Children", "Computer science ", "Cooking", "History", "Novel and Narratives", " " }));
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

        jButton_search.setText("Search");
        jButton_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_searchActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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

        jLabel_img18.setText("jLabel14");

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
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(35)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel_img11, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel_img16, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
        					.addGap(18)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addComponent(jLabel_img12, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
        							.addGap(23)
        							.addComponent(jLabel_img13, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addComponent(jLabel_img17, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(jLabel_img18, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
        					.addPreferredGap(ComponentPlacement.RELATED, -2, Short.MAX_VALUE)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addComponent(jLabel_img14, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(jLabel_img15, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addComponent(jLabel_img19, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(jLabel_img20, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
        					.addGap(35))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        								.addComponent(jLabel_img6, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        								.addComponent(jLabel_img1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        								.addComponent(jLabel_img7, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        								.addComponent(jLabel_img2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        								.addComponent(jLabel_img8, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        								.addComponent(jLabel_img3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(jLabel_img4, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
        								.addComponent(jLabel_img9, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        								.addComponent(jLabel_img10, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
        								.addComponent(jLabel_img5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        						.addComponent(jLabel_newest))
        					.addGap(0, 61, Short.MAX_VALUE))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(jLabel_top10)
        					.addContainerGap(764, Short.MAX_VALUE))))
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(18)
        			.addComponent(jButton_return)
        			.addGap(275)
        			.addComponent(jButton_previous, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
        			.addGap(41)
        			.addComponent(jButton_next, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        			.addGap(35))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(36)
        			.addComponent(jLabel_newest)
        			.addGap(30)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jLabel_img1)
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jLabel_img3)
        					.addComponent(jLabel_img4)
        					.addComponent(jLabel_img5)
        					.addComponent(jLabel_img2)))
        			.addGap(20)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jLabel_img8)
        					.addComponent(jLabel_img9)
        					.addComponent(jLabel_img10))
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jLabel_img6)
        					.addComponent(jLabel_img7)))
        			.addGap(18)
        			.addComponent(jLabel_top10)
        			.addGap(30)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jLabel_img11)
        					.addComponent(jLabel_img12))
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jLabel_img14)
        					.addComponent(jLabel_img13)
        					.addComponent(jLabel_img15)))
        			.addGap(20)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel_img17)
        				.addComponent(jLabel_img19)
        				.addComponent(jLabel_img16)
        				.addComponent(jLabel_img18)
        				.addComponent(jLabel_img20))
        			.addGap(1)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jButton_next)
        					.addComponent(jButton_previous))
        				.addComponent(jButton_return))
        			.addGap(26))
        );
        jPanel1.setLayout(jPanel1Layout);

        jScrollPane2.setViewportView(jPanel1);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("D:\\IT\\Eclipse\\WorkSpace\\BookLibrary\\src\\Images\\mylog2.png"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jSeparator1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(0, 422, Short.MAX_VALUE)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel16)
        						.addComponent(jLabel_userName))
        					.addPreferredGap(ComponentPlacement.RELATED))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(lblNewLabel)
        					.addPreferredGap(ComponentPlacement.RELATED)))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jButton_login)
        				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        					.addComponent(jPassword, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
        					.addComponent(jTextField_userName, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jButton_pswForgotten, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jButton_registry))
        			.addGap(21))
        		.addGroup(layout.createSequentialGroup()
        			.addGap(20)
        			.addComponent(jCombo_genre, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
        			.addComponent(jCombo_price, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
        			.addComponent(jCombo_ageRating, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
        					.addComponent(jLabel_search)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(jButton_search))
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
        					.addComponent(jCombo_language, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
        					.addComponent(jCombo_rating, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)))
        			.addGap(18))
        		.addComponent(jScrollPane2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jTextField_userName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jButton_registry)
        						.addComponent(jLabel_userName))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jButton_pswForgotten)
        						.addComponent(jLabel16))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jButton_login))
        				.addComponent(lblNewLabel))
        			.addGap(48)
        			.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jButton_search)
        				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        					.addComponent(jLabel_search, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jCombo_rating, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jCombo_ageRating, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addComponent(jCombo_price, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addComponent(jCombo_genre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addComponent(jCombo_language, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

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
               
        	gen = Admin.getGenre(genre);
        	pri = Admin.getPrice(price);
        	age = Admin.getAgerating(agerating);
        	rate = Admin.getRating(rating);
        	lang = Admin.getLanguage(language);

            //Hier werden alle Bcher angezeigt        
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
		                            		 
        				books.add(book);
        			}//while(... closing
                        
                		
                	while(books.size() %10 != 0){
                        Book dump = new Book("","","http://booklibrary.bplaced.net/nothing.png",null,"","","","","","","","","","");
                        books.add(dump);
                	}
                	
                	//show Books on Frame
                	searchResult(books);
                	//books.clear();
                	
                	}catch (Exception e) {
        					System.out.println(e.getClass().getName() + "Line-806 " +e.getCause());
                	}
        			//Hier werden Bch nach wunsch bzw. auswahl angezeigt
                 }else {        
                    
                	 try{ 
                		 //berprfung der Auswahlen von Kategorien
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
                    		  
                    		  books2.add(book);
                    	 }
                    	 
                    	 System.out.println("Line-857 listSize: " +books2.size());
                    	 
                    	 while(books2.size() %10 != 0){
                               Book dump = new Book("","","http://booklibrary.bplaced.net/nothing.png",null,"","","","","","","","","","");
                               books2.add(dump);
                    	}
                    	
                    	 System.out.println("Line-864 listSize: " +books2.size());
                    	 
                    	 for(int i = 0; i<books2.size(); i++) {
                    		 System.out.println(books2.get(i).getTitle());
                    	 }
                    	 searchResult(books2);
                    	 books2.clear(); 
                    	 System.out.println();
                      }catch(Exception e) {
                    	  System.out.println(e.getCause() + " " +e.getMessage());
                      } 
                }//else closing         
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
    private javax.swing.JLabel jLabel_img18;
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
    private JLabel lblNewLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
        
        int n = JOptionPane.showOptionDialog(this,
                                                "Are you sure to terminating the program?", 
                                                "Program finishing", 
                                                JOptionPane.YES_NO_OPTION, 
                                                JOptionPane.QUESTION_MESSAGE, 
                                                null,           //do not use a custiom Icon
                                                options, 
                                                options[1]);
        switch(n) {
            case 0: setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); break;
            case 1: setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); break;
            default : break;
        }
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

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

            if (source == jLabel_img18) {
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

    public class MyMouseListener implements MouseListener {
    	
    	Book objBook = null;
    	String methodName;
    	
    	public MyMouseListener(Book objBook) {
    		this.objBook = objBook;
    	}
    	
    	public MyMouseListener(Book objBook, String methodName) {
    		this(objBook);
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
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
		

		
		
	}//MyMouseListener closing
    
}
