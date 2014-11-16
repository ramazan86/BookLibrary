
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
    String mid, title, imglink, rating, description, genre, agerating, releaseYear, duration, link, language, language2, price, deadline;
    ArrayList<Book> books;
    static Verbindung db;
    static Connection conn;
    Book(String mid,String title,String imglink, String rating, String description,String genre,String agerating,String releaseYear,String duration,String link, String language, String language2, String price, String deadline){
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
    this.duration = duration;
    this.link = link;
    this.language = language;
    this.language2 = language2;
    this.price = price;
    this.deadline = deadline;
    }

    
    public Book(String title, String imglink){
        this.title = title;
        this.imglink = imglink;
    }
    //
    //Connects to the database and inserts the new book.
    public static void addBook(String title,String genre,String agerating,String description,String releaseyear,String duration,String streamlink,String imglink,String price,String language,String language2) throws SQLException{
        
        Verbindung db = new Verbindung();
        db.start();
        Connection conn = db.getVerbindung();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO book(`title`, `genre`, `ageRating`, `description`, `releaseYear`, `duration`, `picture`, `price`) VALUES "
                           + "('" + title + "','" + genre + "','" + agerating + "',\"" + description + "\",'" +  releaseyear + "','" + duration + "','" + imglink + "','" + price +"')");

        Statement stmt2 = conn.createStatement();
        stmt2.executeUpdate("INSERT INTO haslang (`Mid`,`Language`) VALUES ((SELECT mid FROM book WHERE title = '"+ title +"'),'"+ language + "')");

        if(language2.equals("Second")){
        JOptionPane.showMessageDialog(null, "book was added.");
        }else{
        stmt2.executeUpdate("INSERT INTO haslang (`Mid`,`Language`) VALUES ((SELECT mid FROM book WHERE title = '"+ title +"'),'"+ language2 + "')");
        JOptionPane.showMessageDialog(null, "book was added.");
        }
    }
    
    //Connects to the database and 
    public static void changebook(String title,String genre,String agerating,String description,String releaseyear,String duration,String streamlink,String imglink,String price){
        Verbindung db = new Verbindung();
        db.start();
        Connection conn = db.getVerbindung();

        try {
           Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO `book`(`title`, `genre`, `ageRating`, `description`, `releaseYear`, `duration`, `picture`, `price`) VALUES "
            + "('" + title + "','" + genre + "','" + agerating + "','" + description + "','" +  releaseyear + "','" + duration + "','" + imglink + "','" + price +"')");
           
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
       ResultSet rs = stmt.executeQuery("Select *, avg(rating) as average from book natural left join rates where inactive = 0 group by mid order by mid desc LIMIT 0,10");
       
       Statement stmt2 = conn.createStatement();
       while(rs.next()){
           
        ResultSet rs2 = stmt2.executeQuery("Select * from book natural join haslang where mid = "+rs.getString("mid")+" ");
        rs2.next();
        String lang = rs2.getString("Language");
        rs2.last();
        String lang2 = rs2.getString("Language");
        
        if(lang2.equals(lang))
            lang2 = "";
        
        Book book = new Book(rs.getString("mid"),rs.getString("title"),rs.getString("picture"),rs.getString("average"), rs.getString("description"),rs.getString("genre"),rs.getString("agerating"),rs.getString("releaseyear"),rs.getString("duration"),rs.getString("streamlink"),lang, lang2, rs.getString("price"),"");
        books.add(book);
       }
       
       //Top10 are stored in "books"
   
       Statement stmt3 = conn.createStatement();     
       ResultSet rs2 = stmt3.executeQuery("Select *, avg(rating) as average from book natural left join rates where inactive = 0 group by mid order by average desc LIMIT 0,10");
       
       Statement stmt4 = conn.createStatement();
       while(rs2.next()){
           
        ResultSet rs3 = stmt4.executeQuery("Select * from book natural join haslang where mid = "+rs2.getString("mid")+" ");
        rs3.next();
        String lang = rs3.getString("Language");
        rs3.last();
        String lang2 = rs3.getString("Language");
    
        if(lang2.equals(lang))
            lang2 = "";
        
        Book book = new Book(rs2.getString("mid"),rs2.getString("title"),rs2.getString("picture"),rs2.getString("average"), rs2.getString("description"),rs2.getString("genre"),rs2.getString("agerating"),rs2.getString("releaseyear"),rs2.getString("duration"),rs2.getString("streamlink"),lang, lang2, rs2.getString("price"),"");
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

    public String getDuration() {
        return duration;
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

    public String getDeadline() {
        return deadline;
    }
    
}
