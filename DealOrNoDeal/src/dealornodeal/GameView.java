//********************************************************************
//  Project 50
//  Author: Jared Kwok(1382534) and Mike Jiang(1240129)    
//********************************************************************
package dealornodeal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GameView extends JPanel {

    public JPanel game, mgPanel,ePanel,wPanel;   //mg = main game
    public JLabel mainLabel, nameDisplay, offerDisplay, playerCaseDis, casesLeftDis;
    public caseValue[] cases = new caseValue[26];
    private caseValue[] valueDisplay = new caseValue[26];
    private JLabel[] valueDisplayLabel = new JLabel[26];
    public caseGenerator caseGen;
    public JButton quitGame, helpOption;
    public JButton[] caseButton = new JButton[26];
    public banker bank;
    public int casesLeft = 26;
    public int playerCase, offerMultiplier;
    public String playerName;
    public String[] options = {"Deal", "No Deal"};
    

    public GameView () {
        caseGen = new caseGenerator();
        bank = new banker();
        
        // Main Menu Panel
        try {
            playerName = JOptionPane.showInputDialog(null, "Enter You Name",
                    "Deal or No Deal? (GUI)", JOptionPane.QUESTION_MESSAGE);
            if (playerName.length() < 1) {
                playerName = "New Player";
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "You have quit the game",
                    "Deal or No Deal? (GUI)", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        // Game Screen Panel

        game = new backgroundPanel();  
        game.setLayout(new BorderLayout());
        //North components
        mainLabel = new JLabel(new ImageIcon("src/dealornodeal/Images/dealornodeal.jpg"));

        //West components
        wPanel = new JPanel(new GridLayout(13, 1));
        for (int i = 0; i < 13; i++) {
            sidePanels("WEST",i);
        }

        //Center components
        mgPanel = new JPanel();
        mgPanel.setLayout(new GridLayout(6, 5));
        for (int i = 0; i < 26; i++) {
            caseValue currentCase = caseGen.pickCase(i);
            caseGen.showCase(i).setIsOpen(false);
            cases[i] = currentCase;
            caseButton[i] = new JButton(i + 1 + "");
            caseButton[i].addActionListener(new caseOpener(this));   
            mgPanel.add(caseButton[i]);
        }
        
        //East components
        ePanel = new JPanel(new GridLayout(13, 1));
        for (int i = 13; i < 26; i++) {
            sidePanels("EAST",i);
        }

        //South components
        JPanel sPanel = new JPanel(new GridLayout(3, 2));
        nameDisplay = new JLabel("| Name: " + playerName);
        playerCaseDis = new JLabel("| Your case is: " + playerCase);
        offerDisplay = new JLabel("| Your Offer was: $" + bank.getOffer());
        casesLeftDis = new JLabel("| Cases Left: " + casesLeft);
        helpOption = new JButton("Help");
        quitGame = new JButton("Quit");   
        helpOption.addActionListener(new gameOptions(this));
        quitGame.addActionListener(new gameOptions(this));  

        sPanel.add(nameDisplay);
        sPanel.add(playerCaseDis);
        sPanel.add(offerDisplay);
        sPanel.add(casesLeftDis);
        sPanel.add(helpOption);
        sPanel.add(quitGame);
        
        
        
        //Adding components to panel
        game.add(mainLabel, BorderLayout.NORTH);
        game.add(wPanel, BorderLayout.WEST);
        game.add(mgPanel, BorderLayout.CENTER);
        game.add(ePanel, BorderLayout.EAST);
        game.add(sPanel, BorderLayout.SOUTH);
        add(game);       
    }
    private void sidePanels(String side,int i){
        try {
                valueDisplay[i] = new caseValue(i);
            } catch (Exception ex) {
                System.out.println("Error");
            }
            valueDisplayLabel[i] = new JLabel(valueDisplay[i].getValue() + "       ");
            caseDisplayColour(i);
            if(side.compareToIgnoreCase("WEST")==0)
            this.wPanel.add(valueDisplayLabel[i]);
            else if(side.compareToIgnoreCase("EAST")==0)
            this.ePanel.add(valueDisplayLabel[i]);
    }
    
    private void caseDisplayColour(int i) {
        if (i < 13) {
            Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
            valueDisplayLabel[i].setBorder(border);
        } else if (i > 21) {
            Border border = BorderFactory.createLineBorder(Color.GREEN, 1);
            valueDisplayLabel[i].setBorder(border);
        } else {
            Border border = BorderFactory.createLineBorder(Color.RED, 1);
            valueDisplayLabel[i].setBorder(border);
        }
    }
    
}
