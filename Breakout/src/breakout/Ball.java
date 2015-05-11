//********************************************************************
//  Ball.java      
//  Author: Jared Kwok      ID no. 1382534
//********************************************************************
package breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
    
    public int x,y;
    public Dimension xMax,yMax;
    public final int radius;
    public final Color colour;
    public double dirX, dirY;
    public double speed;
    
    public Ball(int x, int y, int radius, Color colour,double speed){
        
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.colour = colour;
        this.speed = speed;
        
        Random ran = new Random();
        double num = ran.nextDouble()*2;
        dirX = num;
        double dirDiv = Math.sqrt(dirX*dirX + dirY*dirY);
        
        dirX += dirX/dirDiv;
        dirY += (1-dirX)/dirDiv;       
    }
    
    // Paints Ball given values from Breackout class
    public void paintThis(Graphics g){
        g.setColor(colour);
        g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
    }
    
    // Bounds to for handleCollisions method in Breakout
    public Rectangle getBounds(){
        return new Rectangle(x-radius, y-radius, 2*radius, 2*radius);       
    }
    
    // int speed requirement to speed up the ball as the game progresses
    public void move(double speed){
        
            x -= (int) (dirX*speed);
            y -= (int) (dirY*speed);
    }
    
    // Changes Direction of the ball after hitting an object
    public void reflect (boolean vertical, boolean horizontal){
        
        if (vertical) dirX *= -1;
        if (horizontal) dirY *= -1;
    }
}

