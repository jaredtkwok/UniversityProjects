//********************************************************************
//  Paddle.java      
//  Author: Jared Kwok      ID no. 1382534
//********************************************************************
package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle {
    public int padSize;
    public int padThickness;
    public int centreX, centreY;
       
    public Paddle(int padSize, int padThickness){
        this.padSize = padSize;
        this.padThickness = padThickness;
    }
    // Paints Paddle given values from Breackout class
    public void paintThis(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(centreX-padSize/2, centreY-padThickness/2, padSize, padThickness);
    }
    
    // Bounds to for handleCollisions method in Breakout
    public Rectangle getBounds(){ 
        return new Rectangle(centreX-padSize/2, centreY-padThickness/2, padSize, padThickness); 
    }
}
