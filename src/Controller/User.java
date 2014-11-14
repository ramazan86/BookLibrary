/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;



import java.util.ArrayList;

import Model.*;
import View.UserV;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Ramazan Cinardere & Ali Hannoun
 */
public class User {
    
    
    /* class-attributes */
    
    private User user;
    public String uid, username, password, email, isAdmin, activCode="123", activated,  lastLogin, birthday, prename, surname, street, zipcode, city, iban, bic;
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Book> books2 = new ArrayList<>();
    private String suchetext,gen,pri,age,rate,lang;
    private int genre,price,agerating,rating,language,pages;
    private Verbindung db;
    private Connection conn;
    private Statement stmt,stmt2,stmt3,stmt4,stmtNewest,stmtNewest2,stmtSearch,stmtTop10,stmt2Top10;
    private ResultSet rs,rs2,rs3,rsNewest,rsNewest2,rsSearch,rsTop10,rs2Top10;
    private static int seitenanzahl = 0; // Checks wether the user has pressed next after searching
    
    private UserV userView = null;
    
    
    /* class-methods */

    public User() {
        
    }
    
    public User(UserV obj) {
    
        this.uid = "0";
        this.userView = obj;
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
             + "('" + username + "', '"+UserV.encrypt(password)+"', '" + email + "', 0, '" + random + "',\"" +  birthday + "\",'" + prename + "','" + surname + "','" + street + "','" + zipcode + "','" + city + "', (SELECT bid FROM bank where iban = '"+iban+"' and bic = '"+bic+"' LIMIT 0,1))");
       }else{
            stmt.executeUpdate("INSERT INTO user(`username`, `password`, `email`, `isAdmin`, `activCode`, `birthday`, `prename`, `surname`, `street`, `zipcode`, `city`) VALUES "
             + "('" + username + "', '"+UserV.encrypt(password)+"', '" + email + "', 0, '" + random + "',\"" +  birthday + "\",'" + prename + "','" + surname + "','" + street + "','" + zipcode + "','" + city + "')");
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
        message.setContent("<h><img src='http://s14.directupload.net/images/141113/3uduuobu.jpg'><br><p>Congratulations! <br><br> To finish your registration please enter this activation code after logging in: "+random+"</p></h>","text/html");
        Transport.send(message);
       }catch(MessagingException e){
           throw new RuntimeException();
       }
       JOptionPane.showMessageDialog(null, "Registration was succesfull. Please check your emails.");
        }
       return true;
    }
    
    
    
}
