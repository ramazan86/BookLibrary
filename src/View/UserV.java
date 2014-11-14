package View;

import Controller.User;
import Model.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *@author Created by Ramazan Cinardere & Ali Hannoun
 */

public class UserV extends javax.swing.JFrame {
    public User user;
    private String uid, username, password, email, isAdmin, activCode="123", activated,  lastLogin, birthday, prename, surname, street, zipcode, city, iban, bic;
    private ArrayList<Book> movies = new ArrayList<>();
    private ArrayList<Book> movies2 = new ArrayList<>();
    private String suchetext,gen,pri,age,rate,lang;
    private int genre,price,agerating,rating,language,pages;
    public Verbindung db;
    private Connection conn;
    private Statement stmt,stmt2,stmt3,stmt4,stmtNewest,stmtNewest2,stmtSearch,stmtTop10,stmt2Top10;
    private ResultSet rs,rs2,rs3,rsNewest,rsNewest2,rsSearch,rsTop10,rs2Top10;
    public static int seitenanzahl = 0; // Checks wether the user has pressed next after searching
    
    public UserV(){
        initComponents();
        setLocationRelativeTo(null);
        this.uid = "0";
        this.setSize(870,700);
    }
    
    public UserV(User obj) throws SQLException, MalformedURLException, IOException{
        initComponents();
        this.setSize(870,700);
        setLocationRelativeTo(null);
        user = obj;
        
    /*   ************************
        movies = Book.getNewestAndTop10(); 
        this.Newest10();
        this.Top10(); */
        
        this.setVisible(true);
        if(!(user.lastLogin == null) ) // If isn't the user's first login
            jLabelLastLogin.setText("Your last login was on the " + user.lastLogin + ".");
        else
            jLabelLastLogin.setText("This is your first login.");
        jButtonPrevious.setVisible(false);
        jButtonNext.setVisible(false);
        jButtonReturn.setVisible(false);
        
    }
    
    
    //Checks entered login-data and wether the user is activated or not. Returns 1 if successfull, 0 if not.
    public int login(String username, String password) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException{
       
       this.username = username;
       this.password = password; 
       Verbindung db = new Verbindung();
       db.start();
       Connection conn = db.getVerbindung();
       Statement stmt = conn.createStatement();
       rs = stmt.executeQuery("Select * from user natural left join bank where password = '" +UserV.encrypt(password)+ "' and username = '"+username+"'");
        
       if(rs.next()){
        uid = String.valueOf(rs.getInt("uid"));
        email = rs.getString("email");
        isAdmin = rs.getString("isAdmin");
        lastLogin = rs.getString("lastLogin");
        activated = String.valueOf(rs.getInt("activated"));
        activCode = rs.getString("activCode");
        birthday = rs.getString("birthday");
        prename = rs.getString("prename");
        surname = rs.getString("surname");
        street = rs.getString("street");
        zipcode = rs.getString("zipcode");
        city = rs.getString("city");
        iban = rs.getString("iban");
        bic = rs.getString("bic");
        lastLogin = rs.getString("lastLogin");
       
       if(activated.equals("1")){
           JOptionPane.showMessageDialog(null,"Successfully logged in.");
           stmt.executeUpdate("UPDATE user SET lastLogin = now() where username ='"+username+"'");
           return 1;
       }else{
           if(activCode.equals(JOptionPane.showInputDialog("You aren't activated.\n Please enter your activation code: "))){
               stmt.executeUpdate("UPDATE user SET activated = 1 where username = '"+username+"' ");
               JOptionPane.showMessageDialog(null, "Activation successfull! You can now login.");
           }else{
               JOptionPane.showMessageDialog(null, "Wrong activation code.");
           }
               
           return 0;
       
       }
      }else{
           JOptionPane.showMessageDialog(null,"Wrong username or password!");
           return 0;
       }  
              
}
    
    public int checkAdmin(){
        return Integer.parseInt(this.isAdmin);
    }
    
    //Insert into database if a user rents a movie
    public int rentMovie(String mid) throws SQLException{
        Verbindung db = new Verbindung();
        db.start();
        Connection conn = db.getVerbindung();
        Statement stmt = conn.createStatement();
        if(stmt.executeUpdate("INSERT INTO rents VALUES ('"+this.uid+"','"+mid+"',now())") == 1)
            return 1;
        else
            return 0;
    }
    
    //Changing of user's account information
    public int changeInformation(String password, String password2, String email, String prename, String surname, String address, String zipcode, String city, String iban, String bic) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException{
        Verbindung db = new Verbindung();
        db.start();
        Connection conn = db.getVerbindung();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from user where email = '"+email+"' and uid != '"+this.uid+"'"); //Überprüfung ob Email bereits vorhanden
        Statement stmt2 = conn.createStatement();

        if(rs.next()){
            JOptionPane.showMessageDialog(null,"Email already used.");
            return 0;
        }else{
            stmt2.executeUpdate("INSERT INTO bank (iban,bic) values ('"+iban+"', '"+bic+"') ON DUPLICATE KEY UPDATE iban = '"+iban+"'");
            String query = "UPDATE user SET password = '"+encrypt(password)+"' , email = '"+email+"', prename = '"+prename+"' , surname = '" + surname + "' , street = '" + address + "' , zipcode = '" + zipcode + "', city = '" + city + "', bid = (SELECT bid from bank where iban = '"+iban+"') WHERE uid = '" + this.uid + "' ";
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Account information successfully changed.");
            return 1;
        }   
    }
   
    //Checks wether user and email exissts and then sends an email with a new random password to email-address
    public static boolean forgottenPassword(String username, String email) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException{
       Verbindung db = new Verbindung();
       db.start();
       Connection conn = db.getVerbindung();
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery("Select * from user where username = '"+username+"' and email = '"+email+"' ");
       
       if(rs.next()){
       
       final String mailname = "moviejunkie.progex@gmail.com";
       final String mailpassword = "moviejunkie";
        
        Properties props = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        
        Session session = Session.getInstance(props,new javax.mail.Authenticator(){
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(mailname, mailpassword);
           } 
        });
       try{
       Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
        message.setSubject("New Password");
        String rand = randomString();
        message.setContent("<h>Your new password: </h>"+rand,"text/html");
        Transport.send(message);
        Statement stmt2 = conn.createStatement();
        stmt2.executeUpdate("UPDATE user SET password = '"+encrypt(rand)+"' WHERE username = '"+username+"'");
        
       }catch(MessagingException e){
           throw new RuntimeException();
       }
           
           JOptionPane.showMessageDialog(null, "A new password has been sent to your email address.");
           return true;
       }else{
           JOptionPane.showMessageDialog(null, "Wrong username or email.");        
           return false;
       }
    }
    
    public static String randomString(){
        int random = (int) (Math.round(Math.random() * 89999) + 10000);
      
        StringBuffer buffer = new StringBuffer();

        String characters ="!$%&?#abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!$%&?#";
        
        int charactersLength = characters.length();
        for (int i = 0; i < 8; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
                        double index = Math.random() * charactersLength;
                        //buffer.append(sonder.charAt((int) index));
       return buffer.toString();
    
    }
    
    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPrename() {
        return prename;
    }

    public String getSurname() {
        return surname;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getIban() {
        return iban;
    }

    public String getBic() {
        return bic;
    }

    public String getPassword() {
        return password;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }
   
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }
    
    
    
   // Shows the result of the search
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
   // Shows the Newest10 Movies
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
   // Shows the Top10 Movies
   public void Top10() throws SQLException, MalformedURLException{
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

        jLabel2 = new javax.swing.JLabel();
        jComboPrice = new javax.swing.JComboBox();
        jComboGenre = new javax.swing.JComboBox();
        jComboAgeRating = new javax.swing.JComboBox();
        jComboLanguage = new javax.swing.JComboBox();
        jButtonAccount = new javax.swing.JButton();
        jButtonVideoLibrary = new javax.swing.JButton();
        jButtonLogOut = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextSearch = new javax.swing.JTextPane();
        jSeparator1 = new javax.swing.JSeparator();
        jComboRating = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabelBild2 = new javax.swing.JLabel();
        jLabelBild17 = new javax.swing.JLabel();
        jLabelBild12 = new javax.swing.JLabel();
        jLabelBild20 = new javax.swing.JLabel();
        jLabelBild5 = new javax.swing.JLabel();
        jLabelBild4 = new javax.swing.JLabel();
        jLabelBild9 = new javax.swing.JLabel();
        jLabelBild7 = new javax.swing.JLabel();
        jLabelNewest = new javax.swing.JLabel();
        jLabelBild10 = new javax.swing.JLabel();
        jLabelBild3 = new javax.swing.JLabel();
        jLabelBild11 = new javax.swing.JLabel();
        jButtonReturn = new javax.swing.JButton();
        jLabelBild18 = new javax.swing.JLabel();
        jLabelBild1 = new javax.swing.JLabel();
        jButtonPrevious = new javax.swing.JButton();
        jLabelBild16 = new javax.swing.JLabel();
        jLabelBild15 = new javax.swing.JLabel();
        jButtonNext = new javax.swing.JButton();
        jLabelBild6 = new javax.swing.JLabel();
        jLabelBild8 = new javax.swing.JLabel();
        jLabelTop10 = new javax.swing.JLabel();
        jLabelBild19 = new javax.swing.JLabel();
        jLabelBild13 = new javax.swing.JLabel();
        jLabelBild14 = new javax.swing.JLabel();
        jButtonSearch = new javax.swing.JButton();
        jLabelLastLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/movierental/Logo.png"))); // NOI18N
        jLabel2.setText("jLabel2");

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

        jComboGenre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Genre", "Action", "Adventure", "Thriller", "Fantasy", "Animation", "Comedy" }));
        jComboGenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboGenreActionPerformed(evt);
            }
        });

        jComboAgeRating.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Age rating", "0", "6", "12", "16", "18" }));
        jComboAgeRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboAgeRatingActionPerformed(evt);
            }
        });

        jComboLanguage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Language", "English", "German", "Spanish" }));
        jComboLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboLanguageActionPerformed(evt);
            }
        });

        jButtonAccount.setText("Account");
        jButtonAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAccountActionPerformed(evt);
            }
        });

        jButtonVideoLibrary.setText("Video library");
        jButtonVideoLibrary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVideoLibraryActionPerformed(evt);
            }
        });

        jButtonLogOut.setText("Log out");
        jButtonLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogOutActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTextSearch);

        jComboRating.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rating", "5 Best", "4", "3", "2", "1 Badest" }));

        jLabel1.setText("Search:");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jLabelBild2.setText("jLabel18");

        jLabelBild17.setText("jLabel8");

        jLabelBild12.setText("jLabel9");

        jLabelBild20.setText("jLabel6");

        jLabelBild5.setText("jLabel19");

        jLabelBild4.setText("jLabel4");

        jLabelBild9.setText("jLabel9");

        jLabelBild7.setText("jLabel7");

        jLabelNewest.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabelNewest.setText("Newest 10");

        jLabelBild10.setText("jLabel14");

        jLabelBild3.setText("jLabel4");

        jLabelBild11.setText("jLabel17");

        jButtonReturn.setText("Return");
        jButtonReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturnActionPerformed(evt);
            }
        });

        jLabelBild18.setText("jLabel14");

        jLabelBild1.setText("jLabel17");

        jButtonPrevious.setText("<");
        jButtonPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviousActionPerformed(evt);
            }
        });

        jLabelBild16.setText("jLabel7");

        jLabelBild15.setText("jLabel19");

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

        jLabelBild19.setText("jLabel4");

        jLabelBild13.setText("jLabel18");

        jLabelBild14.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelBild11, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelBild16, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelBild12, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabelBild13, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, Short.MAX_VALUE)
                                        .addComponent(jLabelBild14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(19, 19, 19)
                                        .addComponent(jLabelBild15, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelBild17, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelBild18, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelBild19, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelBild20, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTop10)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabelBild6, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                            .addComponent(jLabelBild1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelBild2)
                                            .addComponent(jLabelBild7, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                            .addComponent(jLabelBild5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jLabelNewest))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButtonReturn)
                        .addGap(275, 275, 275)
                        .addComponent(jButtonPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabelNewest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addGap(18, 18, 18)
                .addComponent(jLabelTop10)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelBild11)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBild14)
                        .addComponent(jLabelBild15)
                        .addComponent(jLabelBild13)
                        .addComponent(jLabelBild12)))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBild17)
                    .addComponent(jLabelBild19)
                    .addComponent(jLabelBild18)
                    .addComponent(jLabelBild16)
                    .addComponent(jLabelBild20))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonNext)
                        .addComponent(jButtonPrevious))
                    .addComponent(jButtonReturn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAccount)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonVideoLibrary)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLogOut)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLastLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jComboGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jComboPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jComboAgeRating, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addComponent(jComboRating, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSearch)))
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelLastLogin)
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAccount)
                            .addComponent(jButtonVideoLibrary)
                            .addComponent(jButtonLogOut)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jComboAgeRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonSearch)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboPriceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboPriceItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboPriceItemStateChanged

    private void jComboPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboPriceActionPerformed

    private void jComboGenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboGenreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboGenreActionPerformed

    private void jComboAgeRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboAgeRatingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboAgeRatingActionPerformed

    private void jComboLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboLanguageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboLanguageActionPerformed

    private void jButtonAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAccountActionPerformed
        if(evt.getSource() == jButtonAccount){
            new Account(user).setVisible(true);
        }
    }//GEN-LAST:event_jButtonAccountActionPerformed

    private void jButtonVideoLibraryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVideoLibraryActionPerformed
        try {
            new VideoLibrary(user).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(UserV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonVideoLibraryActionPerformed

    private void jButtonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogOutActionPerformed
        setVisible(false);
        JOptionPane.showMessageDialog(null, "Successfully logged out!");
        try {
            new Login().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(UserV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserV.class.getName()).log(Level.SEVERE, null, ex);
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
                gen = Admin.getGenre(genre);
                pri = Admin.getPrice(price);
                age = Admin.getAgerating(agerating);
                rate = Admin.getRating(rating);
                lang = Admin.getLanguage(language);

                if(!(rate.equals("%"))){
                stmt4 = conn.createStatement();
                rs3 = stmt4.executeQuery("SELECT *,avg(rating) as average FROM movie natural left join rates natural join haslang WHERE title LIKE '%"+ suchetext +"%' and genre LIKE '%" + gen + "%' and price <= '" + pri + "' and ageRating <= '"+ age +"' and Language LIKE '%"+ lang +"%' and inactive = 0 group by mid having average >= "+rate+"");
       
                    
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
                Movie movie = new Movie(rs3.getString("mid"),rs3.getString("title"),rs3.getString("picture"),rs3.getString("average"), rs3.getString("description"),rs3.getString("genre"),rs3.getString("agerating"),rs3.getString("releaseYear"),rs3.getString("duration"),rs3.getString("streamlink"),language1, language2, rs3.getString("price"),"");
                movies.add(movie);
                }

                while(movies.size() %10 != 0){
                Movie dump = new Movie("","","",null,"","","","","","","","","","");
                movies.add(dump);
                }
                this.searchResult(movies);

                }else{
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT *,avg(rating) as average FROM movie natural join haslang natural left join rates WHERE title LIKE '%"+ suchetext +"%' and genre LIKE '%" + gen + "%' and price <= '" + pri + "' and ageRating <= '"+ age +"' and Language LIKE '%"+ lang +"%' and inactive = 0 group by mid");
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
                Movie movie = new Movie(rs.getString("mid"),rs.getString("title"),rs.getString("picture"),rs.getString("average"), rs.getString("description"),rs.getString("genre"),rs.getString("agerating"),rs.getString("releaseYear"),rs.getString("duration"),rs.getString("streamlink"),language1, language2, rs.getString("price"),"");
                movies.add(movie);
                }
                while(movies.size() %10 != 0){
                Movie dump = new Movie("","","http://stefano.bplaced.net/nothing.png",null,"","","","","","","","","","");
                movies.add(dump);
                }
                this.searchResult(movies);
                }

            } catch (        SQLException | MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jButtonReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturnActionPerformed
        this.dispose();
        try {
            new UserV(user).setVisible(true);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonReturnActionPerformed

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
    
    //Encrypting a Password with SHA-256
    public static String encrypt(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException{
    MessageDigest md   = MessageDigest.getInstance("SHA-256"); //make sure it exists, there are other algorithms, but I prefer SHA for simple and relatively quick hashing
        
        md.update(password.getBytes("UTF-8")); //I'd rather specify the encoding. It's platform dependent otherwise. 
        byte[] digestBuff = md.digest();
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digestBuff.length; i++) {
         sb.append(Integer.toString((digestBuff[i] & 0xff) + 0x100, 16).substring(1));
        }
 
        return sb.toString();
    }
 
    
    public static void main(String args[]) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAccount;
    private javax.swing.JButton jButtonLogOut;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrevious;
    private javax.swing.JButton jButtonReturn;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonVideoLibrary;
    private javax.swing.JComboBox jComboAgeRating;
    private javax.swing.JComboBox jComboGenre;
    private javax.swing.JComboBox jComboLanguage;
    private javax.swing.JComboBox jComboPrice;
    private javax.swing.JComboBox jComboRating;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextSearch;
    // End of variables declaration//GEN-END:variables

    //Mouselistener
    class MouseImpl extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == jLabelBild1) {
            try {
                new MovieInfo(user,movies.get(0+seitenanzahl)).setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild2) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(1+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (source == jLabelBild3) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(2+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild4) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(3+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild5) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(4+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild6) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(5+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild7) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(6+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild8) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(7+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        if (source == jLabelBild9) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(8+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild10) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(9+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
        if (source == jLabelBild11) {
            try {
                new MovieInfo(user,movies.get(10+seitenanzahl)).setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild19) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(11+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (source == jLabelBild12) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(12+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild14) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(13+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild15) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(14+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild17) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(15+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild18) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(16+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild20) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(17+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        if (source == jLabelBild13) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(18+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelBild16) {
             try {
                MovieInfo window = new MovieInfo(user,movies.get(19+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
        }
    }
}

