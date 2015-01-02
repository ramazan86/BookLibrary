package bookrental;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class BookLibrary extends javax.swing.JFrame implements MouseListener {

    User user;
    static int count = 0; // Counts at which page the user is
    ArrayList<Book> books;
    
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton_next;
    private javax.swing.JButton jButton_previous;
    private javax.swing.JButton jButton_return;
    private javax.swing.JLabel jLabel_dateOfBought;
    private javax.swing.JLabel jLabel_dayOfBought2;
    private javax.swing.JLabel jLabel_dayOfBought3;
    private javax.swing.JLabel jLabel_dayOfBought4;
    private javax.swing.JLabel jLabel_dayOfBought5;
    private javax.swing.JLabel jLabel_personalLibrary;
    private javax.swing.JLabel jLabel_boughtBooks;
    private javax.swing.JLabel jLabel_title1;
    private javax.swing.JLabel jLabel_title2;
    private javax.swing.JLabel jLabel_title3;
    private javax.swing.JLabel jLabel_title4;
    private javax.swing.JLabel jLabel_title5;
    private JLabel jLabel_Browser;
    private JLabel jLabel_Browser_2;
    private JLabel jLabel_Browser_3;
    private JLabel jLabel_Browser_4;
    private JLabel jLabel_Browser_5;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    
    public BookLibrary(User obj) throws SQLException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        user = obj;

        listBooks();
    }


	public void openBrowser(String bookTitle) {
    
        try { 
            URI uri = new URI("http://ramazan.bplaced.net/BookLibrary/Test.pdf");
            
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
           
            if(desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(uri);
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    //Connects to the database, get's all bought Books of this user and shows them.
    public void listBooks() throws SQLException {
        
        jButton_next.setVisible(false);
        jButton_previous.setVisible(false);
        
        books = new ArrayList<>();
        
        //Connection to database 
        Verbindung db = new Verbindung();
        db.start();
        Connection conn = db.getVerbindung();
        
        Statement stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery("Select * from bought natural join book natural join user where uid = '" + user.getUid() + "'");

        while (rs.next()) {
    	   
        	
        	String mid   = rs.getString("mid");
            String date  = rs.getDate("time").toString();
            String title = rs.getString("title");
            String genre =  rs.getString("genre");
            String ageRating = rs.getString("agerating");
            String description = rs.getString("description");
            String releaseYear = rs.getString("releaseYear");
            String author = rs.getString("author");
            String picture =  rs.getString("picture");
            String price = rs.getString("price");
            String pdfLink = rs.getString("pdflink");
            String language = rs.getString("language");
            String language2 = rs.getString("language2");
            
            Book book = new Book(mid, title,picture, 
                                 null, description ,genre, ageRating, 
                                 releaseYear, "", 
                                 language, language2,price, pdfLink, author,date);
            
            
            books.add(book);
        }
           
        
       // rs.getString(null)

        
        JLabel[] labelsTitle = new JLabel[5];
        	labelsTitle[0] = jLabel_title1;
        	labelsTitle[1] = jLabel_title2;
        	labelsTitle[2] = jLabel_title3;
        	labelsTitle[3] = jLabel_title4;
        	labelsTitle[4] = jLabel_title5;
        	
        JLabel[] labelsDate = new JLabel[5];
        	labelsDate[0] = jLabel_dateOfBought;
        	labelsDate[1] = jLabel_dayOfBought2;
        	labelsDate[2] = jLabel_dayOfBought3;
        	labelsDate[3] = jLabel_dayOfBought4;
        	labelsDate[4] = jLabel_dayOfBought5;
    
        JLabel[] jlabels = new JLabel[5];
			
	        jlabels[0] = jLabel_Browser;
			jlabels[1] = jLabel_Browser_2;
			jlabels[2] = jLabel_Browser_3;
	        jlabels[3] = jLabel_Browser_4;
	        jlabels[4] = jLabel_Browser_5;
	        
        	System.out.println("BookLibrary/Line 172 : " +books.size());
        
        for(int i = 0; i<books.size(); i++) {
        	
        	if(books.get(i+count).getTitle().isEmpty()) {
        		labelsTitle[i].setVisible(false);
        		labelsDate [i].setVisible(false);
        	}
        	else {
        		labelsTitle[i].setText(books.get(i+count).getTitle());
        		labelsDate [i].setText(books.get(i + count).getSaleDate());
        	}
        	
        		System.out.println(i);
        		jlabels[i].setVisible(true);
        }
        

        if (books.size() - count > 5) {
            jButton_next.setVisible(true);
        } else {
            jButton_next.setVisible(false);
        }

        if (count != 0) {
            jButton_previous.setVisible(true);
        } else {
            jButton_previous.setVisible(false);
        }
        
        

    }//listBooks closing

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel_personalLibrary = new javax.swing.JLabel();
        jLabel_boughtBooks = new javax.swing.JLabel();
        jLabel_boughtBooks.setFont(new Font("Tahoma", Font.BOLD, 11));
        jLabel_title5 = new javax.swing.JLabel();
        jLabel_dayOfBought5 = new javax.swing.JLabel();
        jButton_return = new javax.swing.JButton();
        jLabel_title1 = new javax.swing.JLabel();
        jLabel_dateOfBought = new javax.swing.JLabel();
        jLabel_title2 = new javax.swing.JLabel();
        jLabel_dayOfBought2 = new javax.swing.JLabel();
        jLabel_title3 = new javax.swing.JLabel();
        jLabel_dayOfBought3 = new javax.swing.JLabel();
        jLabel_title4 = new javax.swing.JLabel();
        jLabel_dayOfBought4 = new javax.swing.JLabel();
        jButton_next = new javax.swing.JButton();
        jButton_previous = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_personalLibrary.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel_personalLibrary.setText("Personal  Library");

        jLabel_boughtBooks.setText("Bought Books:");

        jButton_return.setText("Return");
        jButton_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_returnActionPerformed(evt);
            }
        });

        
        jLabel_title5.setText("");
        
        jLabel_dayOfBought5.setText("");
        
        jButton_next.setText("Next");
        jButton_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nextActionPerformed(evt);
            }
        });

        jButton_previous.setText("Previous");
        jButton_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_previousActionPerformed(evt);
            }
        });
        
        jLabel_Browser = new JLabel("");
        jLabel_Browser.addMouseListener(this);
        jLabel_Browser.setIcon(new ImageIcon("D:\\IT\\Eclipse\\WorkSpace\\BookLibrary\\src\\Images\\read_24.png"));
        jLabel_Browser.setVisible(false);
        
        jLabel_Browser_2 = new JLabel("");
        jLabel_Browser_2.addMouseListener(this);
        jLabel_Browser_2.setIcon(new ImageIcon("D:\\IT\\Eclipse\\WorkSpace\\BookLibrary\\src\\Images\\read_24.png"));
        jLabel_Browser_2.setVisible(false);

        jLabel_Browser_3 = new JLabel("");
        jLabel_Browser_3.addMouseListener(this);
        jLabel_Browser_3.setIcon(new ImageIcon("D:\\IT\\Eclipse\\WorkSpace\\BookLibrary\\src\\Images\\read_24.png"));
        jLabel_Browser_3.setVisible(false);

        jLabel_Browser_4 = new JLabel("");
        jLabel_Browser_4.addMouseListener(this);
        jLabel_Browser_4.setIcon(new ImageIcon("D:\\IT\\Eclipse\\WorkSpace\\BookLibrary\\src\\Images\\read_24.png"));
        jLabel_Browser_4.setVisible(false);

        jLabel_Browser_5 = new JLabel("");
        jLabel_Browser_5.addMouseListener(this);
        jLabel_Browser_5.setIcon(new ImageIcon("D:\\IT\\Eclipse\\WorkSpace\\BookLibrary\\src\\Images\\read_24.png"));
        jLabel_Browser_5.setVisible(false);
        
        lblNewLabel = new JLabel("");
        
        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("D:\\IT\\Eclipse\\WorkSpace\\BookLibrary\\src\\Images\\mylog2.png"));

        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(35)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(jLabel_boughtBooks)
        								.addComponent(jLabel_personalLibrary)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(jButton_return)
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addComponent(jButton_previous)))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(jButton_next))
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        									.addComponent(jLabel_title1, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
        									.addComponent(jLabel_title2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        									.addComponent(jLabel_title3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        								.addComponent(jLabel_title4, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
        								.addComponent(jLabel_title5, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createSequentialGroup()
        									.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        										.addComponent(jLabel_dayOfBought3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        										.addComponent(jLabel_dayOfBought2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        										.addComponent(jLabel_dateOfBought, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
        									.addGap(31)
        									.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        										.addComponent(jLabel_Browser_2)
        										.addComponent(jLabel_Browser)
        										.addComponent(jLabel_Browser_3)))
        								.addGroup(layout.createSequentialGroup()
        									.addGroup(layout.createParallelGroup(Alignment.LEADING)
        										.addComponent(jLabel_dayOfBought4, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
        										.addComponent(jLabel_dayOfBought5, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
        									.addGap(18)
        									.addGroup(layout.createParallelGroup(Alignment.LEADING)
        										.addComponent(jLabel_Browser_5)
        										.addComponent(jLabel_Browser_4)))))))
        				.addComponent(lblNewLabel)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblNewLabel_1)))
        			.addContainerGap(367, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(lblNewLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lblNewLabel_1)
        			.addGap(98)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel_personalLibrary)
        					.addGap(42)
        					.addComponent(jLabel_boughtBooks)
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel_title1)
        						.addComponent(jLabel_dateOfBought)))
        				.addComponent(jLabel_Browser))
        			.addGap(11)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jLabel_title2)
        					.addComponent(jLabel_dayOfBought2))
        				.addComponent(jLabel_Browser_2))
        			.addGap(10)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabel_Browser_3)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jLabel_title3)
        					.addComponent(jLabel_dayOfBought3)))
        			.addGap(12)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jLabel_title4)
        					.addComponent(jLabel_dayOfBought4))
        				.addComponent(jLabel_Browser_4))
        			.addGap(10)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel_title5)
        						.addComponent(jLabel_dayOfBought5))
        					.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jButton_return)
        						.addComponent(jButton_previous)
        						.addComponent(jButton_next))
        					.addGap(40))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel_Browser_5)
        					.addGap(122))))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextActionPerformed
        count += 5;
        try {
            listBooks();
        } catch (SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_nextActionPerformed

    private void jButton_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_previousActionPerformed
        count -= 5;
        try {
            listBooks();
        } catch (SQLException ex) {
            Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_previousActionPerformed

    private void jButton_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_returnActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton_returnActionPerformed


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
            java.util.logging.Logger.getLogger(BookLibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookLibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookLibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookLibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new BookLibrary(new User()).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(BookLibrary.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    // End of variables declaration//GEN-END:variables


	@Override
	public void mouseClicked(MouseEvent e) {
		openBrowser("");
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




}