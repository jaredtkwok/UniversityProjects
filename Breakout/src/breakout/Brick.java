//********************************************************************
//  Brick.java      
//  Author: Jared Kwok      ID no. 1382534
//********************************************************************
package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Brick{
    
    public final int topLeftX, topLeftY, width, height;
    public final Color colour;
    
    public Brick (int topLeftX, int topLeftY, int width,
            int height,Color colour){
            
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.width = width;
        this.height = height;
        this.colour = colour;   
    }
    
    // Paints Brick given values from Breackout class
    public void paintThis(Graphics g){
        g.setColor(colour);
        g.fillRect(topLeftX, topLeftY, width, height);
    }
    
    // Bounds to for handleCollisions method in Breakout
    public Rectangle getBounds(){
        return new Rectangle(topLeftX, topLeftY, width, height);
    }
}
