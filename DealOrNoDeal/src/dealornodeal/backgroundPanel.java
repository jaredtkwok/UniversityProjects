//********************************************************************
//  Project 50
//  Author: Jared Kwok(1382534) and Mike Jiang(1240129)    
//********************************************************************
package dealornodeal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class backgroundPanel extends JPanel{
        Image img;
    public backgroundPanel()
    {     
        this.img = Toolkit.getDefaultToolkit().createImage("src/dealornodeal/Images/dollarBackground.jpg");
    }

    public void paintComponent(Graphics g){
          super.paintComponent(g);      
        g.drawImage(this.img, 0, 0, null);

    }
}
