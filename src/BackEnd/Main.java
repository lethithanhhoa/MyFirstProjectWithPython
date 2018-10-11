/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import Interface.StudyEnglishJFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Hoa Le
 */
public class Main {
    
    public  static Dictionary ev = new Dictionary();
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudyEnglishJFrame().setVisible(true);
            }
        });
    }
}
