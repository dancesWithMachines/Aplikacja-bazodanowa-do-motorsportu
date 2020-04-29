/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import frontend.regularPanel;
import javax.swing.UIManager;
/**
 *
 * @author Timax
 */
public class start {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        }
        regularPanel rp = new regularPanel();
        rp.setTitle("R-Master");
        rp.setVisible(true);
    }
    
}
