//********************************************************************
//  Project 50
//  Author: Jared Kwok(1382534) and Mike Jiang(1240129)    
//********************************************************************
package dealornodeal;

import dealornodeal.GUI.GameView;
import dealornodeal.database.Database;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CaseOpener implements ActionListener {

    private GameView  gui;
        private Database db;
    private String player;
    public CaseOpener(GameView  thegui) {
        this.gui = thegui;
        db = new Database();
              
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int action = Integer.parseInt(e.getActionCommand());
        if (gui.casesLeft == 26) {
            gui.playerCase = action - 1;
            gui.caseButton[action - 1].setText(null);
            gui.mgPanel.remove(gui.caseButton[action - 1]);
            
            gui.mgPanel.setLayout(new GridLayout(5, 5));
            gui.playerCaseDis.setText("| You case is: " + action);
            gui.casesLeft--;
            gui.casesLeftDis.setText("| Cases Left: " + gui.casesLeft);
            gui.offerMultiplier++;
        } else {
            try {
                openCase(action);
            } catch (Exception ex) {
                Logger.getLogger(CaseOpener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void openCase(int i) throws Exception {
        int n = i - 1;
        gui.caseButton[n].setText(null);
        gui.mgPanel.remove(gui.caseButton[n]);
        
        if (n > gui.playerCase) {
            gui.mgPanel.add(new JLabel(gui.cases[n].getValue()), null, n - 1);
            gui.cases[n].setIsOpen(true);
        } else {
            gui.mgPanel.add(new JLabel(gui.cases[n].getValue()), null, n);
            gui.cases[n].setIsOpen(true);
        }
        gui.casesLeft--;
        gui.offerMultiplier++;
        gui.casesLeftDis.setText("| Cases Left: " + gui.casesLeft);
        gui.bank.setOffer(gui.offerMultiplier, gui.cases, gui.bank.getOffer());
        if (gui.casesLeft == 19 || gui.casesLeft == 14 || gui.casesLeft == 10 || gui.casesLeft == 7 || gui.casesLeft <= 5) {
            if (gui.casesLeft == 0) {
                db.connectDB(gui.playerName  , gui.bank.getOffer());
                JOptionPane.showMessageDialog(null, "Congratulations "
                        + gui.playerName + "\nYou Have won\n " + gui.cases[gui.playerCase].getValue());
                JOptionPane.showMessageDialog(null, db.getHighScores());
                System.exit(0);
            }
            int selected = JOptionPane.showOptionDialog(null, "Your offer is: $"
                    + gui.bank.getOffer() + "\nDeal or No Deal?", "Deal or No Deal? (GUI)",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, gui.options, "Deal");
                   
            if (selected == 0) {
                db.connectDB(gui.playerName, gui.bank.getOffer());
                JOptionPane.showMessageDialog(null, "Congratulations "
                        + gui.playerName + "\nYou Have won " + "\n $" + gui.bank.getOffer());
                JOptionPane.showMessageDialog(null, db.getHighScores());
                System.exit(0);
                
            }

            gui.offerDisplay.setText("| Your offer was: $" + gui.bank.getOffer());
        }
    }
}
