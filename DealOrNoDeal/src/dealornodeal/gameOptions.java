//********************************************************************
//  Project 50
//  Author: Jared Kwok(1382534) and Mike Jiang(1240129)    
//********************************************************************
package dealornodeal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class gameOptions implements ActionListener {

    private GameView  gui;

    public gameOptions(GameView  thegui) {       
        this.gui = thegui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == gui.quitGame) {
                JOptionPane.showMessageDialog(null, "Thank You for Playing \nDeal or No Deal",
                        "Deal or No Deal? (GUI)", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } else if (e.getSource() == gui.helpOption) {
                helper();
            }
        } catch (Exception ex) {
        }
    }
        private void helper() {
        JOptionPane.showMessageDialog(null, "Welcome to Deal or No Deal? (GUI)"
                + "\nInstructions: \n1.Select a case (buttton) to be yours"
                + "\n2.Select cases (butttons) to reveal values" + "\n3.Decide if you want to keep playing or take the offer"
                + "\n4.If all cases run out the case you select at the beginning is what you won",
                "Deal or No Deal? (GUI)", JOptionPane.INFORMATION_MESSAGE);


    }
}
