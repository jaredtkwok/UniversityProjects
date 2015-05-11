//********************************************************************
//  PaintingPanel.java      
//  Author: Jared Kwok      ID no. 1382534
//********************************************************************
package breakout;

import java.awt.Graphics;
import java.util.Vector;
import javax.swing.JPanel;

public class PaintingPanel extends JPanel{
    
    public Vector<Object> contents;
    
    public PaintingPanel(Vector<Object> contents){
        this.contents = contents;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Object obj: contents){
            if(obj instanceof Ball){              
                ((Ball)obj).paintThis(g);
            } else if(obj instanceof Brick){
                ((Brick)obj).paintThis(g);
            } else if(obj instanceof Paddle){
                ((Paddle)obj).paintThis(g);
            }             
        }   
    }
}
