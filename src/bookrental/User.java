package bookrental;

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
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class User extends javax.swing.JFrame {
    User user;
    String uid, username, password, email, isAdmin, activCode="123", activated,  lastLogin, birthday, prename, surname, street, zipcode, city, iban, bic;
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Book> books2 = new ArrayList<>();
    String suchetext,gen,pri,age,rate,lang;
    int genre,price,agerating,rating,language,pages;
    Verbindung db;
    Connection conn;
    Statement stmt,stmt2,stmt3,stmt4,stmtNewest,stmtNewest2,stmtSearch,stmtTop10,stmt2Top10;
    ResultSet rs,rs2,rs3,rsNewest,rsNewest2,rsSearch,rsTop10,rs2Top10;
    static int seitenanzahl = 0; // Checks wether the user has pressed next after searching
    
    public User(){
        initComponents();
        setLocationRelativeTo(null);
        this.uid = "0";
        this.setSize(870,700);
    }
    
    public User(User obj) throws SQLException, MalformedURLException, IOException{
        initComponents();
        this.setSize(870,700);
        setLocationRelativeTo(null);
        user = obj;
        books = Book.getNewestAndTop10(); 
        this.Newest10();
        this.Top10();
        this.setVisible(true);
        if(!(user.lastLogin == null) ) // If isn't the user's first login
            jLabelLastLogin.setText("Your last login was on the " + user.lastLogin + ".");
        else
            jLabelLastLogin.setText("This is your first login.");
        jButton_previous.setVisible(false);
        jButton_next.setVisible(false);
        jButton_return.setVisible(false);
        
    }
    
    
    //Responsible for checking wether username, email are used; insertion into database; sending the email
    public static boolean register( String username, String password, String email, String birthday, String prename, String surname, String street, String zipcode, String city, String iban, String bic) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException{
       int random = (int) (Math.round(Math.random() * 89999) + 10000);
       
       Verbindung db = new Verbindung();
       db.start();
       Connection conn = db.getVerbindung();
       Statement stmt = conn.createStatement();
       Statement stmt2 = conn.createStatement();
       Statement stmt3 = conn.createStatement();
       Statement stmt4 = conn.createStatement();
       ResultSet rs = stmt3.executeQuery("SELECT * from user WHERE username = '"+username+"'");
       ResultSet rs2 = stmt4.executeQuery("SELECT * from user WHERE email = '"+email+"'");
       if(rs.next()){
           JOptionPane.showMessageDialog(null, "Username already exists.");
           return false;
       }else if(rs2.next()){
           JOptionPane.showMessageDialog(null, "Email already exists.");       
           return false;
       }else{
       
       if(!(iban.equals("") && bic.equals(""))){
            stmt2.executeUpdate("INSERT INTO BANK (`iban`, `bic`) VALUES ('"+iban+"','"+bic+"')");
            stmt.executeUpdate("INSERT INTO user(`username`, `password`, `email`, `isAdmin`, `activCode`, `birthday`, `prename`, `surname`, `street`, `zipcode`, `city`, `bid`) VALUES "
             + "('" + username + "', '"+User.encrypt(password)+"', '" + email + "', 0, '" + random + "',\"" +  birthday + "\",'" + prename + "','" + surname + "','" + street + "','" + zipcode + "','" + city + "', (SELECT bid FROM bank where iban = '"+iban+"' and bic = '"+bic+"' LIMIT 0,1))");
       }else{
            stmt.executeUpdate("INSERT INTO user(`username`, `password`, `email`, `isAdmin`, `activCode`, `birthday`, `prename`, `surname`, `street`, `zipcode`, `city`) VALUES "
             + "('" + username + "', '"+User.encrypt(password)+"', '" + email + "', 0, '" + random + "',\"" +  birthday + "\",'" + prename + "','" + surname + "','" + street + "','" + zipcode + "','" + city + "')");
       }
    
       
       final String mailname = "booklibrary.project@gmail.com";
       final String mailpassword = "booklibrary12345";
        
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
            message.setSubject("Complete your registration");
            message.setContent("<h><img src='http://fs1.directupload.net/images/141205/qxjttzl5.png'><br><p>Congratulations! <br><br> To finish your registration please enter this activation code after logging in: "+random+"</p></h>","text/html");
        Transport.send(message);
        
       }catch(MessagingException e){
           throw new RuntimeException();
       }
       JOptionPane.showMessageDialog(null, "Registration was succesfull. Please check your emails.");
        }
       return true;
    }
    
    //Checks entered login-data and wether the user is activated or not. Returns 1 if successfull, 0 if not.
    public int login(String username, String password) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException{
       
       this.username = username;
       this.password = password; 
       Verbindung db = new Verbindung();
       db.start();
       Connection conn = db.getVerbindung();
       Statement stmt = conn.createStatement();
       rs = stmt.executeQuery("Select * from user natural left join bank where password = '" +User.encrypt(password)+ "' and username = '"+username+"'");
        
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
    
    //Insert into database if a user rents a book
    public int rentbook(String mid) throws SQLException{
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
       
       final String mailname = "bookjunkie.progex@gmail.com";
       final String mailpassword = "bookjunkie";
        
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
   public void searchResult(ArrayList<Book> books2) throws MalformedURLException{
        jLabe_img1.setVisible(false);
        jLabel_img2.setVisible(false);
        jLabel_img3.setVisible(false);
        jLabelimg4.setVisible(false);
        jLabel_img5.setVisible(false);
        jLabel_img6.setVisible(false);
        jLabel_img7.setVisible(false);
        jLabel_img8.setVisible(false);
        jLabel_img9.setVisible(false);
        jLabel_img10.setVisible(false);
        jLabel_img11.setVisible(false);
        jLabel_img19.setVisible(false);
        jLabelimg12.setVisible(false);
        jLabel_img14.setVisible(false);
        jLabel_img15.setVisible(false);
        jLabel_img17.setVisible(false);
        jLabel_img18.setVisible(false);
        jLabel_img20.setVisible(false);
        jLabel_img13.setVisible(false);
        jLabel_img16.setVisible(false);

        jLabel_top10.setVisible(false);
        jLabel_newest.setText("Search Result for '"+ suchetext +"':");

        if(!(books2.get(0+seitenanzahl).getTitle().equals(""))){
        jLabe_img1.setIcon(new ImageIcon(new URL(books2.get(0+seitenanzahl).getImglink())));
        jLabe_img1.setText(null);
        jLabe_img1.setVisible(true);
        }else{
        jLabe_img1.setVisible(false);
        }
        if(!(books2.get(1+seitenanzahl).getTitle().equals(""))){
        jLabel_img2.setIcon(new ImageIcon(new URL(books2.get(1+seitenanzahl).getImglink())));
        jLabel_img2.setText(null);
        jLabel_img2.setVisible(true);
        }else{
        jLabel_img2.setVisible(false);
        }
        if(!(books2.get(2+seitenanzahl).getTitle().equals(""))){
        jLabel_img3.setIcon(new ImageIcon(new URL(books2.get(2+seitenanzahl).getImglink())));
        jLabel_img3.setText(null);
        jLabel_img3.setVisible(true);
        }else{
        jLabel_img3.setVisible(false);
        }
        if(!(books2.get(3+seitenanzahl).getTitle().equals(""))){
        jLabelimg4.setIcon(new ImageIcon(new URL(books2.get(3+seitenanzahl).getImglink())));
        jLabelimg4.setText(null);
        jLabelimg4.setVisible(true);
        }else{
        jLabelimg4.setVisible(false);
        }
        if(!(books2.get(4+seitenanzahl).getTitle().equals(""))){
        jLabel_img5.setIcon(new ImageIcon(new URL(books2.get(4+seitenanzahl).getImglink())));
        jLabel_img5.setText(null);
        jLabel_img5.setVisible(true);
        }else{
        jLabel_img5.setVisible(false);
        }
        if(!(books2.get(5+seitenanzahl).getTitle().equals(""))){
        jLabel_img6.setIcon(new ImageIcon(new URL(books2.get(5+seitenanzahl).getImglink())));
        jLabel_img6.setText(null);
        jLabel_img6.setVisible(true);
        }else{
        jLabel_img6.setVisible(false);
        }
        if(!(books2.get(6+seitenanzahl).getTitle().equals(""))){
        jLabel_img7.setIcon(new ImageIcon(new URL(books2.get(6+seitenanzahl).getImglink())));
        jLabel_img7.setText(null);
        jLabel_img7.setVisible(true);
        }else{
        jLabel_img7.setVisible(false);
        }
        if(!(books2.get(7+seitenanzahl).getTitle().equals(""))){
        jLabel_img8.setIcon(new ImageIcon(new URL(books2.get(7+seitenanzahl).getImglink())));
        jLabel_img8.setText(null);
        jLabel_img8.setVisible(true);
        }else{
        jLabel_img8.setVisible(false);
        }
        if(!(books2.get(8+seitenanzahl).getTitle().equals(""))){
        jLabel_img9.setIcon(new ImageIcon(new URL(books2.get(8+seitenanzahl).getImglink())));
        jLabel_img9.setText(null);
        jLabel_img9.setVisible(true);
        }else{
        jLabel_img9.setVisible(false);
        }
        if(!(books2.get(9+seitenanzahl).getTitle().equals(""))){
        jLabel_img10.setIcon(new ImageIcon(new URL(books2.get(9+seitenanzahl).getImglink())));
        jLabel_img10.setText(null);
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
   public void Newest10() throws SQLException, MalformedURLException, IOException{
        MouseAdapter listener = new MouseImpl();
           
      
        jLabe_img1.setIcon(new ImageIcon(new URL(books.get(0).getImglink())));
        jLabe_img1.setText(null);
        jLabe_img1.addMouseListener(listener);
        
        jLabel_img2.setIcon(new ImageIcon(new URL(books.get(1).getImglink())));
        jLabel_img2.setText(null);
        jLabel_img2.addMouseListener(listener);
        
        jLabel_img3.setIcon(new ImageIcon(new URL(books.get(2).getImglink())));
        jLabel_img3.setText(null);
        jLabel_img3.addMouseListener(listener);

        jLabelimg4.setIcon(new ImageIcon(new URL(books.get(3).getImglink())));
        jLabelimg4.setText(null);
        jLabelimg4.addMouseListener(listener);
        
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
   public void Top10() throws SQLException, MalformedURLException{
       MouseAdapter listener = new MouseImpl();
       
        jLabel_img11.setIcon(new ImageIcon(new URL(books.get(10).getImglink())));
        jLabel_img11.setText(null);
        jLabel_img11.addMouseListener(listener);

        jLabelimg12.setIcon(new ImageIcon(new URL(books.get(11).getImglink())));
        jLabelimg12.setText(null);
        jLabelimg12.addMouseListener(listener);
        
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
        
        jLabel_img18.setIcon(new ImageIcon(new URL(books.get(17).getImglink())));
        jLabel_img18.setText(null);
        jLabel_img18.addMouseListener(listener);
        
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

        jCombo_price = new javax.swing.JComboBox();
        jCombo_genre = new javax.swing.JComboBox();
        jCombo_ageRating = new javax.swing.JComboBox();
        jCombo_language = new javax.swing.JComboBox();
        jButton_account = new javax.swing.JButton();
        jButton_bookLibrary = new javax.swing.JButton();
        jButton_logOut = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextField_search = new javax.swing.JTextPane();
        jSeparator1 = new javax.swing.JSeparator();
        jCombo_rating = new javax.swing.JComboBox();
        jLabel_search = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel_img2 = new javax.swing.JLabel();
        jLabel_img17 = new javax.swing.JLabel();
        jLabelimg12 = new javax.swing.JLabel();
        jLabel_img20 = new javax.swing.JLabel();
        jLabel_img5 = new javax.swing.JLabel();
        jLabelimg4 = new javax.swing.JLabel();
        jLabel_img9 = new javax.swing.JLabel();
        jLabel_img7 = new javax.swing.JLabel();
        jLabel_newest = new javax.swing.JLabel();
        jLabel_img10 = new javax.swing.JLabel();
        jLabel_img3 = new javax.swing.JLabel();
        jLabel_img11 = new javax.swing.JLabel();
        jButton_return = new javax.swing.JButton();
        jLabel_img18 = new javax.swing.JLabel();
        jLabe_img1 = new javax.swing.JLabel();
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
        jButton_search = new javax.swing.JButton();
        jLabelLastLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jCombo_genre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Genre", "Biographies", "Children", "Computer science ", "Cooking", "History", "Novel and Narratives", "School & Education" }));
        jCombo_genre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombo_genreActionPerformed(evt);
            }
        });

        jCombo_ageRating.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Age rating", "0", "6", "12", "16", "18" }));
        jCombo_ageRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombo_ageRatingActionPerformed(evt);
            }
        });

        jCombo_language.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Language", "English", "German", "Spanish" }));
        jCombo_language.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombo_languageActionPerformed(evt);
            }
        });

        jButton_account.setText("Account");
        jButton_account.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_accountActionPerformed(evt);
            }
        });

        jButton_bookLibrary.setText("Book  library");
        jButton_bookLibrary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_bookLibraryActionPerformed(evt);
            }
        });

        jButton_logOut.setText("Log out");
        jButton_logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_logOutActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTextField_search);

        jCombo_rating.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rating", "5 Best", "4", "3", "2", "1 Badest" }));

        jLabel_search.setText("Search:");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jLabel_img2.setText("img2");

        jLabel_img17.setText("img17");

        jLabelimg12.setText("img12");

        jLabel_img20.setText("img20");

        jLabel_img5.setText("img5");

        jLabelimg4.setText("img4");

        jLabel_img9.setText("img9");

        jLabel_img7.setText("img7");

        jLabel_newest.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel_newest.setText("Newest 10");

        jLabel_img10.setText("img10");

        jLabel_img3.setText("img3");

        jLabel_img11.setText("img11");

        jButton_return.setText("Return");
        jButton_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_returnActionPerformed(evt);
            }
        });

        jLabel_img18.setText("img18");

        jLabe_img1.setText("img1");

        jButton_previous.setText("<");
        jButton_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_previousActionPerformed(evt);
            }
        });

        jLabel_img16.setText("img16");

        jLabel_img15.setText("img15");

        jButton_next.setText(">");
        jButton_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nextActionPerformed(evt);
            }
        });

        jLabel_img6.setText("img6");

        jLabel_img8.setText("img8");

        jLabel_top10.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel_top10.setText("Top 10");

        jLabel_img19.setText("img19");

        jLabel_img13.setText("img13");

        jLabel_img14.setText("img14");

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
                                    .addComponent(jLabel_img11, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_img16, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelimg12, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel_img13, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, Short.MAX_VALUE)
                                        .addComponent(jLabel_img14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(19, 19, 19)
                                        .addComponent(jLabel_img15, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel_img17, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel_img18, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel_img19, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel_img20, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_top10)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel_img6, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                            .addComponent(jLabe_img1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel_img2)
                                            .addComponent(jLabel_img7, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel_img8, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                            .addComponent(jLabel_img3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelimg4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel_img9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel_img10, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                            .addComponent(jLabel_img5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jLabel_newest))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton_return)
                        .addGap(275, 275, 275)
                        .addComponent(jButton_previous, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButton_next, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel_newest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabe_img1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_img3)
                        .addComponent(jLabelimg4)
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
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_img11)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_img14)
                        .addComponent(jLabel_img15)
                        .addComponent(jLabel_img13)
                        .addComponent(jLabelimg12)))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_img17)
                    .addComponent(jLabel_img19)
                    .addComponent(jLabel_img18)
                    .addComponent(jLabel_img16)
                    .addComponent(jLabel_img20))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_next)
                        .addComponent(jButton_previous))
                    .addComponent(jButton_return))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        jButton_search.setText("Search");
        jButton_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jCombo_genre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jCombo_price, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jCombo_ageRating, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCombo_language, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addComponent(jCombo_rating, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(jLabel_search)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_search)))
                        .addGap(18, 18, 18))))
            .addGroup(layout.createSequentialGroup()
                .addGap(282, 282, 282)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_account)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_bookLibrary)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_logOut)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelLastLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLastLogin)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_account)
                    .addComponent(jButton_bookLibrary)
                    .addComponent(jButton_logOut))
                .addGap(90, 90, 90)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCombo_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCombo_genre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jCombo_ageRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel_search, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton_search)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCombo_rating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCombo_language, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCombo_priceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCombo_priceItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_priceItemStateChanged

    private void jCombo_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_priceActionPerformed

    private void jCombo_genreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_genreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_genreActionPerformed

    private void jCombo_ageRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_ageRatingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_ageRatingActionPerformed

    private void jCombo_languageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_languageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_languageActionPerformed

    private void jButton_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_accountActionPerformed
        if(evt.getSource() == jButton_account){
            new Account(user).setVisible(true);
        }
    }//GEN-LAST:event_jButton_accountActionPerformed

    private void jButton_bookLibraryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_bookLibraryActionPerformed
        try {
            new BookLibrary(user).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_bookLibraryActionPerformed

    private void jButton_logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_logOutActionPerformed
        setVisible(false);
        JOptionPane.showMessageDialog(null, "Successfully logged out!");
        try {
            new Login().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_logOutActionPerformed

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

        if(evt.getSource() == jButton_search){
            books = new ArrayList<>();
            try {
                gen = Admin.getGenre(genre);
                pri = Admin.getPrice(price);
                age = Admin.getAgerating(agerating);
                rate = Admin.getRating(rating);
                lang = Admin.getLanguage(language);

                if(!(rate.equals("%"))){
                stmt4 = conn.createStatement();
                rs3 = stmt4.executeQuery("SELECT *,avg(rating) as average FROM book natural left join rates natural join haslang WHERE title LIKE '%"+ suchetext +"%' and genre LIKE '%" + gen + "%' and price <= '" + pri + "' and ageRating <= '"+ age +"' and Language LIKE '%"+ lang +"%' and inactive = 0 group by mid having average >= "+rate+"");
       
                    
                stmtSearch = conn.createStatement();

                while(rs3.next()){
                rsSearch = stmtSearch.executeQuery("Select * from book natural join haslang where mid = "+rs3.getString("mid")+" ");
                rsSearch.next();
                String language1 = rsSearch.getString("Language");
                rsSearch.last();
                String language2 = rsSearch.getString("Language");

                if(language2.equals(language1)){
                    language2 = "";
                }
                Book book = new Book(rs3.getString("mid"),rs3.getString("title"),rs3.getString("picture"),rs3.getString("average"), rs3.getString("description"),rs3.getString("genre"),rs3.getString("agerating"),rs3.getString("releaseYear"),rs3.getString("duration"),rs3.getString("streamlink"),language1, language2, rs3.getString("price"),"");
                books.add(book);
                }

                while(books.size() %10 != 0){
                Book dump = new Book("","","",null,"","","","","","","","","","");
                books.add(dump);
                }
                this.searchResult(books);

                }else{
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT *,avg(rating) as average FROM book natural join haslang natural left join rates WHERE title LIKE '%"+ suchetext +"%' and genre LIKE '%" + gen + "%' and price <= '" + pri + "' and ageRating <= '"+ age +"' and Language LIKE '%"+ lang +"%' and inactive = 0 group by mid");
                stmtSearch = conn.createStatement();

                while(rs.next()){

                rsSearch = stmtSearch.executeQuery("Select * from book natural join haslang where mid = "+rs.getString("mid")+" ");
                rsSearch.next();
                String language1 = rsSearch.getString("Language");
                rsSearch.last();
                String language2 = rsSearch.getString("Language");

                if(language2.equals(language1)){
                    language2 = "";
                }
                Book book = new Book(rs.getString("mid"),rs.getString("title"),rs.getString("picture"),rs.getString("average"), rs.getString("description"),rs.getString("genre"),rs.getString("agerating"),rs.getString("releaseYear"),rs.getString("duration"),rs.getString("streamlink"),language1, language2, rs.getString("price"),"");
                books.add(book);
                }
                while(books.size() %10 != 0){
                Book dump = new Book("","","http://stefano.bplaced.net/nothing.png",null,"","","","","","","","","","");
                books.add(dump);
                }
                this.searchResult(books);
                }

            } catch (        SQLException | MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_searchActionPerformed

    private void jButton_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_returnActionPerformed
        this.dispose();
        try {
            new User(user).setVisible(true);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_returnActionPerformed

    private void jButton_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_previousActionPerformed
        if(jButton_previous == evt.getSource()){
            seitenanzahl = seitenanzahl - 10;
            try {
                this.searchResult(books);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_previousActionPerformed

    private void jButton_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextActionPerformed
        if(jButton_next == evt.getSource()){
            seitenanzahl = seitenanzahl + 10;
            try {
                this.searchResult(books);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_nextActionPerformed
    
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
                new User().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_account;
    private javax.swing.JButton jButton_bookLibrary;
    private javax.swing.JButton jButton_logOut;
    private javax.swing.JButton jButton_next;
    private javax.swing.JButton jButton_previous;
    private javax.swing.JButton jButton_return;
    private javax.swing.JButton jButton_search;
    private javax.swing.JComboBox jCombo_ageRating;
    private javax.swing.JComboBox jCombo_genre;
    private javax.swing.JComboBox jCombo_language;
    private javax.swing.JComboBox jCombo_price;
    private javax.swing.JComboBox jCombo_rating;
    private javax.swing.JLabel jLabe_img1;
    private javax.swing.JLabel jLabelLastLogin;
    private javax.swing.JLabel jLabel_img10;
    private javax.swing.JLabel jLabel_img11;
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
    private javax.swing.JLabel jLabel_img5;
    private javax.swing.JLabel jLabel_img6;
    private javax.swing.JLabel jLabel_img7;
    private javax.swing.JLabel jLabel_img8;
    private javax.swing.JLabel jLabel_img9;
    private javax.swing.JLabel jLabel_newest;
    private javax.swing.JLabel jLabel_search;
    private javax.swing.JLabel jLabel_top10;
    private javax.swing.JLabel jLabelimg12;
    private javax.swing.JLabel jLabelimg4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextField_search;
    // End of variables declaration//GEN-END:variables

    //Mouselistener
    class MouseImpl extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == jLabe_img1) {
            try {
                new BookInfo(user,books.get(0+seitenanzahl)).setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabel_img2) {
             try {
                BookInfo window = new BookInfo(user,books.get(1+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (source == jLabel_img3) {
             try {
                BookInfo window = new BookInfo(user,books.get(2+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabelimg4) {
             try {
                BookInfo window = new BookInfo(user,books.get(3+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabel_img5) {
             try {
                BookInfo window = new BookInfo(user,books.get(4+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabel_img6) {
             try {
                BookInfo window = new BookInfo(user,books.get(5+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabel_img7) {
             try {
                BookInfo window = new BookInfo(user,books.get(6+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabel_img8) {
             try {
                BookInfo window = new BookInfo(user,books.get(7+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        if (source == jLabel_img9) {
             try {
                BookInfo window = new BookInfo(user,books.get(8+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabel_img10) {
             try {
                BookInfo window = new BookInfo(user,books.get(9+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
        if (source == jLabel_img11) {
            try {
                new BookInfo(user,books.get(10+seitenanzahl)).setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabel_img19) {
             try {
                BookInfo window = new BookInfo(user,books.get(11+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (source == jLabelimg12) {
             try {
                BookInfo window = new BookInfo(user,books.get(12+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabel_img14) {
             try {
                BookInfo window = new BookInfo(user,books.get(13+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabel_img15) {
             try {
                BookInfo window = new BookInfo(user,books.get(14+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabel_img17) {
             try {
                BookInfo window = new BookInfo(user,books.get(15+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabel_img18) {
             try {
                BookInfo window = new BookInfo(user,books.get(16+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabel_img20) {
             try {
                BookInfo window = new BookInfo(user,books.get(17+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        if (source == jLabel_img13) {
             try {
                BookInfo window = new BookInfo(user,books.get(18+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == jLabel_img16) {
             try {
                BookInfo window = new BookInfo(user,books.get(19+seitenanzahl));
                window.pack();
                window.setVisible(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
        }
    }
}

