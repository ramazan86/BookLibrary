
package bookrental;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Book {
  
    /*Class-attributes*/
    
    String mid, title, imglink,author, rating, description, genre, agerating, releaseYear, link, language, language2, price, PDFLink;
    ArrayList<Book> books;
    static Verbindung db;
    static Connection conn;
    
    /*Class-Methods*/
    
    
    Book(String mid,String title,String imglink, String rating, String description,String genre,String agerating,String releaseYear,String link,String language, String language2, String price, String PDFLink, String author){ 
    
        this(mid, title, imglink, rating, description, genre, agerating, releaseYear, link, language, language2, price, PDFLink);
        this.author = author;
    }
    
    
    Book(String mid,String title,String imglink, String rating, 
         String description,String genre,String agerating,
         String releaseYear,String link, 
         String language, String language2, String price, String PDFLink){
   
        this.mid = mid;
        this.title = title;
        this.imglink = imglink;

        if(rating == null)    
            this.rating = "N/A";
        else    
            this.rating = rating.substring(0, 3);

        this.description = description;
        this.genre = genre;
        this.agerating = agerating;
        this.releaseYear = releaseYear;
        this.link = link;
        this.language = language;
        this.language2 = language2;
        this.price = price;
        this.PDFLink = PDFLink;
    }//Book(String ...) closing 

    
    public Book(String title, String imglink){
        this.title = title;
        this.imglink = imglink;
    }
    //
    //Connects to the database and inserts the new book.
    public static void addBook(String title, String genre, String agerating,String description,
                                String releaseyear, String author, String imglink,String price,
                                String language, String language2, String PDFlink) throws SQLException{
        
        System.out.println("title: " +title+ " genre: " + genre + " age: " +agerating +" description: " +description+ 
                " year:" +releaseyear +" img: "+imglink + " lang: " +language + " lang2: " + language2 + 
                " author: " +author + " PDF: " +PDFlink + " price: " +price);
        
        
        Verbindung db = new Verbindung();
        db.start();
        Connection conn = db.getVerbindung();

        Statement stmt = conn.createStatement();
        Statement stmt2 = conn.createStatement();
       
        try {
            stmt.executeUpdate("INSERT INTO book(`title`, `genre`, `ageRating`, `description`, `releaseYear`, `author`, `picture`, `price`, `pdflink`, `language`, `language2`) VALUES "
                           + "('" + title + "', '" + genre + "', '" + agerating + "', \"" + description + "\"," +  releaseyear + ",'" + author + "','" + imglink + "'," + price + ",'" + PDFlink + "', '" +language +"', " + "'" +language2 +"')");

             stmt2.executeUpdate("INSERT INTO haslang (`Mid`,`Language`) VALUES ((SELECT mid FROM book WHERE title = '"+ title +"'),'"+ language + "')");

            if(language2.equals("Second")){
               JOptionPane.showMessageDialog(null, "book was added.");
            }else{
                stmt2.executeUpdate("INSERT INTO haslang (`Mid`,`Language`) VALUES ((SELECT mid FROM book WHERE title = '"+ title +"'),'"+ language2 + "')");
                JOptionPane.showMessageDialog(null, "book was added.");
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "a book with the title: \"" + title + "\" already exists");
        }
        
        
       
    }
    
    //Connects to the database and update
    public static void changebook(String title,String genre,String agerating,String description,String releaseyear,String PDFlink, String imglink, String price, String author, String PDFLink){
        Verbindung db = new Verbindung();
        db.start();
        Connection conn = db.getVerbindung();

        try {
           Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO `book`(`title`, `genre`, `ageRating`, `description`, `releaseYear`, `author`, `picture`, `price`, `pdflink`) VALUES "
            + "('" + title + "','" + genre + "','" + agerating + "','" + description + "','" +  releaseyear + "','" + author + "','" + imglink + "','" + price + "','"+ PDFLink +"')");
           
        } catch (SQLException ex) {
            Logger.getLogger(AddBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "book was added.");
    }

    public static ArrayList<Book> getNewestAndTop10() throws SQLException{
       //Newest10 are stored in "books"
       ArrayList<Book> books = new ArrayList<>();
       db = new Verbindung();
       db.start();
       conn = db.getVerbindung();
       Statement stmt = conn.createStatement();     
       ResultSet rs = stmt.executeQuery("Select *, avg(rating) as average from book natural left join rates group by mid order by mid desc LIMIT 0,10");
       
       Statement stmt2 = conn.createStatement();
       while(rs.next()){
           
        ResultSet rs2 = stmt2.executeQuery("Select * from book natural join haslang where mid = "+rs.getString("mid")+" ");
        rs2.next();
        String lang = rs2.getString("language");
        rs2.last();
        String lang2 = rs2.getString("language");
        
        if(lang2.equals(lang))
            lang2 = "";
        
       Book book = new Book(rs.getString("mid"),
                            rs.getString("title"),rs.getString("picture"),
                            rs.getString("average"), rs.getString("description"),
                            rs.getString("genre"),rs.getString("agerating"),
                            rs.getString("releaseyear"),
                            rs.getString("pdflink"),lang, lang2, rs.getString("price"), rs.getString("pdflink"));
        books.add(book);
       }
       
       //Top10 are stored in "books"
   
       Statement stmt3 = conn.createStatement();     
       ResultSet rs2 = stmt3.executeQuery("Select *, avg(rating) as average from book natural left join rates group by mid order by average desc LIMIT 0,10");
       
       Statement stmt4 = conn.createStatement();
       while(rs2.next()){
           
        ResultSet rs3 = stmt4.executeQuery("Select * from book natural join haslang where mid = "+rs2.getString("mid")+" ");
        rs3.next();
        String lang = rs3.getString("Language");
        rs3.last();
        String lang2 = rs3.getString("Language");
    
        if(lang2.equals(lang))
            lang2 = "";
        
        Book book = new Book(rs.getString("mid"),
                            rs.getString("title"),rs.getString("picture"),
                            rs.getString("average"), rs.getString("description"),
                            rs.getString("genre"),rs.getString("agerating"),
                            rs.getString("releaseyear"),
                            rs.getString("pdflink"),lang, lang2, rs.getString("price"), rs.getString("pdflink"));
        books.add(book);
       }
       return books;
    }
    
    public String getTitle() {
        return title;
    }

    public String getImglink() {
        return imglink;
    }

    public String getMid() {
        return mid;
    }

    public String getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public String getAgerating() {
        return agerating;
    }

    public String getreleaseYear() {
        return releaseYear;
    }

    public String getPDFLink() {
        return PDFLink;
    }

    public String getLink() {
        return link;
    }

    public String getLanguage() {
        return language;
    }
    
    public String getLanguage2() {
        return language2;
    }
    
    public String getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }
    
}
