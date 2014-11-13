/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Asus
 */
public class Login  extends JFrame{
    
    public Login() throws HeadlessException {
        
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Login();
    }
    
}
