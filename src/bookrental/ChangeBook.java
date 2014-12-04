
package bookrental;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.ListDataListener;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author Ali Hannoun & Ramazan Cinardere
 */
public class ChangeBook extends javax.swing.JFrame {

    /*
     ChangeBook Variablen 
     */
    String bookid, title, genre, imglink, pdflink, description,  
            releaseyear, price, suchetext, agerating, pricecat, 
            answer, language, language2, author, tmpTitle;
    
    int age, combogenre, comboagerating, combopricecat, combolanguage, combolanguage2;

    Verbindung db;
    Connection conn;
    Statement stmt, stmt2, stmt3, stmt4, stmtDelete;
    ResultSet rs, rs2, rs3, rsDelete;
    JFrame frame;
    /*
     Konstruktor von ChangeBook
     */
    
    public ChangeBook() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
    }//ende Konstruktor

    public void releaseArea() {
        jTextField_search.setText("");
        jTextField_title.setText("");
        jCombo_genre.setSelectedIndex(0);
        jText_description.setText("");
        jText_releaseYear.setText("");
        jText_Author.setText("");
        jTextField_PDFLink.setText("");
        jTextField_imgLink.setText("");
        jCombo_priceCategory.setSelectedIndex(0);
        jCombo_ageRating.setSelectedIndex(0);
        //jLabelBookid.setText("");
        //jLabelBookid.setVisible(false);
        jCombo_language1.setSelectedIndex(0);
        jCombo_language2.setSelectedIndex(0);
        
    }
    /*
     comboGenre ist eine Methode um das ausgewählte genre Objekt aus der Datenbank anzuzeigen
     */

    public static int comboGenre(String combo) {
        switch (combo) {
            case "Biographies":
                return 0;
            case "Children":
                return 1;
            case "Computer science":
                return 2;
            case "Cooking":
                return 3;
            case "History":
                return 4;
            case "Novel": 
                return 5;    
            default:
                return 6;
        }// ende switch

    }//ende methode comboGenre

    /*
     comboAgerating ist eine Methode um das ausgewählte agerating Objekt aus der Datenbank anzuzeigen
     */
    public static int comboAgerating(int combo) {
        if (combo == 0) {
            return 0;
        } else if (combo == 6) {
            return 1;
        } else if (combo == 12) {
            return 2;
        } else if (combo == 16) {
            return 3;
        } else {
            return 4;
        }// ende if bedingung
    }// ende methode comboAgerating

    /*
     comboPricecat ist eine Methode um das ausgewählte Pricecat Objekt aus der Datenbank anzuzeigen
     */
    public static int comboPricecat(String combo) {
        if (combo.equals("3.99")) {
            return 0;
        } else if (combo.equals("2.99")) {
            return 1;
        } else {
            return 2;
        }// ende if bedingung
    }// ende der methode comboPricecat

    public static int comboLanguage(String lang) {
        switch (lang) {
            case "English":
                return 1;
            case "German":
                return 2;
            case "Spanish":
                return 3;
            default:
                return 0;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     *
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField_imgLink = new javax.swing.JTextField();
        jLabel_author = new javax.swing.JLabel();
        jLabel_releaseYear = new javax.swing.JLabel();
        jLabel_ageRating = new javax.swing.JLabel();
        jText_releaseYear = new javax.swing.JTextField();
        jLabel_description = new javax.swing.JLabel();
        jLabel_changeBook = new javax.swing.JLabel();
        jText_Author = new javax.swing.JTextField();
        jLabel_genre = new javax.swing.JLabel();
        jLabel_title = new javax.swing.JLabel();
        jTextField_title = new javax.swing.JTextField();
        jLabel_img = new javax.swing.JLabel();
        jLabel_pdfLink = new javax.swing.JLabel();
        jLabel_priceCat = new javax.swing.JLabel();
        jCombo_genre = new javax.swing.JComboBox();
        jCombo_ageRating = new javax.swing.JComboBox();
        jCombo_priceCategory = new javax.swing.JComboBox();
        jButton_return = new javax.swing.JButton();
        jButton_change = new javax.swing.JButton();
        jScrollPane_discription = new javax.swing.JScrollPane();
        jText_description = new javax.swing.JTextArea();
        jTextField_search = new javax.swing.JTextField();
        jLabel_search = new javax.swing.JLabel();
        jButton_search = new javax.swing.JButton();
        jLabelMovieid = new javax.swing.JLabel();
        jLabel_language = new javax.swing.JLabel();
        jCombo_language1 = new javax.swing.JComboBox();
        jCombo_language2 = new javax.swing.JComboBox();
        jButton_delete = new javax.swing.JButton();
        jTextField_PDFLink = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_author.setText("Author :");

        jLabel_releaseYear.setText("Release Year :");

        jLabel_ageRating.setText("Age Rating :");

        jText_releaseYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_releaseYearActionPerformed(evt);
            }
        });

        jLabel_description.setText("Description :");

        jLabel_changeBook.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel_changeBook.setText("Change a Book");

        jText_Author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_AuthorActionPerformed(evt);
            }
        });

        jLabel_genre.setText("Genre :");

        jLabel_title.setText("Title :");

        jTextField_title.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_titleActionPerformed(evt);
            }
        });

        jLabel_img.setText("IMG-Link :");

        jLabel_pdfLink.setText("Pdf-Link");

        jLabel_priceCat.setText("Price Cat. :");

        jCombo_genre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Biographies", "Children", "Computer science ", "Cooking", "History", "Novel and Narratives", "School & Education" }));
        jCombo_genre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombo_genreActionPerformed(evt);
            }
        });

        jCombo_ageRating.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "6", "12", "16", "18" }));

        jCombo_priceCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3.99", "2.99", "1.99" }));
        jCombo_priceCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombo_priceCategoryActionPerformed(evt);
            }
        });

        jButton_return.setText("Return");
        jButton_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_returnActionPerformed(evt);
            }
        });

        jButton_change.setText("Change");
        jButton_change.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_changeActionPerformed(evt);
            }
        });

        jText_description.setColumns(20);
        jText_description.setRows(5);
        jText_description.setText("\n");
        jScrollPane_discription.setViewportView(jText_description);

        jLabel_search.setText("Search");

        jButton_search.setText("Search");
        jButton_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_searchActionPerformed(evt);
            }
        });

        jLabel_language.setText("Language:");

        jCombo_language1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "First", "English", "German", "Spanish" }));

        jCombo_language2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Second", "English", "German", "Spanish" }));

        jButton_delete.setText("Delete");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_changeBook)
                        .addGap(0, 710, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_releaseYear)
                                    .addComponent(jLabel_author)
                                    .addComponent(jLabel_language))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jText_releaseYear, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCombo_language1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCombo_language2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jText_Author)))
                            .addComponent(jLabelMovieid)
                            .addComponent(jButton_return)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_title, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_genre)
                                    .addComponent(jLabel_ageRating))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_title, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCombo_genre, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCombo_ageRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel_priceCat)
                                .addGap(18, 18, 18)
                                .addComponent(jCombo_priceCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton_delete)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton_change))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel_description)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane_discription, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel_search)
                                    .addGap(24, 24, 24)
                                    .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton_search, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_pdfLink)
                                        .addComponent(jLabel_img))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTextField_imgLink, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField_PDFLink, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(72, 72, 72))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_search)
                    .addComponent(jButton_search, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jLabel_changeBook)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_title))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_genre)
                            .addComponent(jCombo_genre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_ageRating)
                            .addComponent(jCombo_ageRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_releaseYear)
                            .addComponent(jText_releaseYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_author, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jText_Author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_language)
                            .addComponent(jCombo_language1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCombo_language2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelMovieid))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField_imgLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_img))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_pdfLink)
                            .addComponent(jTextField_PDFLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_priceCat, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCombo_priceCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_description)
                            .addComponent(jScrollPane_discription, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jButton_return))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_change)
                            .addComponent(jButton_delete))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jText_releaseYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_releaseYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_releaseYearActionPerformed

    private void jCombo_genreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_genreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_genreActionPerformed

    private void jButton_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_returnActionPerformed
        this.dispose();

    }//GEN-LAST:event_jButton_returnActionPerformed

    private void jTextField_titleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_titleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_titleActionPerformed

    private void jText_AuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_AuthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_AuthorActionPerformed

    private void jButton_changeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_changeActionPerformed

        if (evt.getSource() == jButton_change) {

            if(db == null) {
                db = new Verbindung();
                db.start();
                conn = db.getVerbindung();
            }
            
           
            
            title = jTextField_title.getText();
            
            
            
            
            genre = (String) jCombo_genre.getSelectedItem();
            agerating = (String) jCombo_ageRating.getSelectedItem();
            description = StringEscapeUtils.escapeJavaScript(jText_description.getText());
            releaseyear = jText_releaseYear.getText();
            author = jText_Author.getText();
            imglink = jTextField_imgLink.getText();
            pricecat = (String) jCombo_priceCategory.getSelectedItem();
            pdflink = jTextField_PDFLink.getText();
            language = (String) jCombo_language1.getSelectedItem();
            language2 = (String) jCombo_language2.getSelectedItem();

            
            
            if (suchetext != null) {

                try {
                    stmt2 = conn.createStatement();
                    stmt2.executeUpdate("UPDATE book SET title='" + title + "', genre='" + genre + "', ageRating='" + agerating + "', description='" + description
                            + "', releaseYear='" + releaseyear + "', pdflink='" + pdflink + "', author='" + author +"', Picture='" + imglink + "', price='" + pricecat + "' WHERE mid = '" + bookid + "'");
                    JOptionPane.showMessageDialog(null, "Change was succesfull.");
                    this.dispose();

                } catch (SQLException ex) {
                    Logger.getLogger(ChangeBook.class.getName()).log(Level.SEVERE, null, ex);

                }
            } else {
                JOptionPane.showMessageDialog(null, "You must search a book before you can change one. ");
            }
        }
    }//GEN-LAST:event_jButton_changeActionPerformed

    private void jButton_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_searchActionPerformed

        suchetext = jTextField_search.getText();
        
        try {
            db = new Verbindung();
            db.start();
            conn = db.getVerbindung();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        //searchButton
        if (evt.getSource() == jButton_search) {

            ArrayList<String> liste = new ArrayList();
            try {
                
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM book WHERE title LIKE '%" + suchetext + "%' ");

                while(rs.next()) {
                    
                    tmpTitle = rs.getString("title");
                    liste.add(tmpTitle);
                }
                
                String comboBoxArray[] = new String[liste.size()+2];
                comboBoxArray[0] = "Bitte wählen ...";
                //Array of String become filled with the content of the ArrayList
                int i = 0;
                while(i < liste.size()) {
                    comboBoxArray[i+1] = liste.get(i).toString();
                    i++;
                }
            //JFrame, JPanel, JLabel created        
                frame = new JFrame();
                frame.setSize(250,150);
                JPanel panel = new JPanel();
                JLabel labelRange = new JLabel("You have " + liste.size() + " books to range");
                panel.add(labelRange);
            
            //JCombobox created
                JComboBox titleRangeBox = new JComboBox(comboBoxArray);
                titleRangeBox.addItemListener(new ItemListener() {

                    int i = 0;
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        
                        int state = e.getStateChange();
                        
                        if(state == ItemEvent.SELECTED) {
                            
                            tmpTitle = e.getItem().toString();    
                        }else {
                            System.out.println("Deselected: " +e.getItem().toString());
                        }
                        
                    }
                });
                panel.add(titleRangeBox);
            
            //JButton
                JButton jbutton_OK = new JButton("OK");
                jbutton_OK.setSize(200, 50);
                jbutton_OK.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                       
                        try {
                            Statement st = conn.createStatement();
                            ResultSet rs = st.executeQuery("SELECT * FROM book WHERE title = '" +tmpTitle+"'");
                            
                            System.out.println(tmpTitle);
                            
                            while(rs.next()) {
                             
                                combogenre = comboGenre(rs.getString("genre"));
                                comboagerating = comboAgerating(rs.getInt("ageRating"));
                                System.out.println(comboagerating);
                                combopricecat = comboPricecat(rs.getString("price"));
                                combolanguage = comboLanguage(rs.getString("language"));
                                combolanguage2 = comboLanguage(rs.getString("language2"));
                                
                                jTextField_title.setText(rs.getString("title"));
                                jTextField_imgLink.setText(rs.getString("picture"));
                                jTextField_PDFLink.setText(rs.getString("pdflink"));
                                jText_Author.setText(rs.getString("author"));
                                jText_description.setText(rs.getString("description"));
                                jText_releaseYear.setText(String.valueOf(rs.getInt("releaseYear")));
                                
                                jCombo_genre.setSelectedIndex(combogenre);
                                jCombo_ageRating.setSelectedIndex(comboagerating);
                                jCombo_language1.setSelectedIndex(combolanguage);
                                jCombo_language2.setSelectedIndex(combolanguage2);
                                jCombo_priceCategory.setSelectedIndex(combopricecat);
                                
                                //get id from respective book
                                bookid = String.valueOf(rs.getInt("mid"));
                                
                                frame.setVisible(false);
                            
                            }
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(ChangeBook.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                panel.add(jbutton_OK);
                        
           //Adding components into frame     
                frame.add(panel);
                frame.setLocationRelativeTo(this);
                frame.setResizable(false);
                
                frame.setVisible(true);
              
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No match found");
            }
        }//if(evt.getSource() == jButton_search) closing

    }//GEN-LAST:event_jButton_searchActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        
        if (evt.getSource() == jButton_delete) {
            if (!(bookid.equals(""))) {
                try {
                    if (JOptionPane.showConfirmDialog(null, "Do you really want to delete this book?") == 0) {
                        stmtDelete = conn.createStatement();
                        stmtDelete.executeUpdate("DELETE FROM book  WHERE mid = '" + bookid + "'");
                        JOptionPane.showMessageDialog(null, "Book was succesfully deleted.");
                        this.releaseArea();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ChangeBook.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jCombo_priceCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_priceCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_priceCategoryActionPerformed

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
            java.util.logging.Logger.getLogger(ChangeBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangeBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangeBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangeBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                new ChangeBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_change;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_return;
    private javax.swing.JButton jButton_search;
    private javax.swing.JComboBox jCombo_ageRating;
    private javax.swing.JComboBox jCombo_genre;
    private javax.swing.JComboBox jCombo_language1;
    private javax.swing.JComboBox jCombo_language2;
    private javax.swing.JComboBox jCombo_priceCategory;
    private javax.swing.JLabel jLabelMovieid;
    private javax.swing.JLabel jLabel_ageRating;
    private javax.swing.JLabel jLabel_author;
    private javax.swing.JLabel jLabel_changeBook;
    private javax.swing.JLabel jLabel_description;
    private javax.swing.JLabel jLabel_genre;
    private javax.swing.JLabel jLabel_img;
    private javax.swing.JLabel jLabel_language;
    private javax.swing.JLabel jLabel_pdfLink;
    private javax.swing.JLabel jLabel_priceCat;
    private javax.swing.JLabel jLabel_releaseYear;
    private javax.swing.JLabel jLabel_search;
    private javax.swing.JLabel jLabel_title;
    private javax.swing.JScrollPane jScrollPane_discription;
    private javax.swing.JTextField jTextField_PDFLink;
    private javax.swing.JTextField jTextField_imgLink;
    private javax.swing.JTextField jTextField_search;
    private javax.swing.JTextField jTextField_title;
    private javax.swing.JTextField jText_Author;
    private javax.swing.JTextArea jText_description;
    private javax.swing.JTextField jText_releaseYear;
    // End of variables declaration//GEN-END:variables

 
    
  
       
    
    
    
    
    
}
