//********************************************************************
//  Breakout.java      
//  Author: Jared Kwok      ID no. 1382534
//
//  Extensions:
//  Score System with score multiplier (does not keep track of highscore)
//  Paddle Size & Speed Increase
//  Ball Speed Increase as more bricks are destroyed
//********************************************************************
package breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Breakout extends TimerTask{

    public Vector<Brick> bricks;
    public Ball ball;
    public Paddle paddle;
    public PaintingPanel panel;
    public boolean gameRunning = false;
    public int score, paddSpeed, paddSize, paddThickness;
    public double speed;
    public JLabel cScore;
    
    public Breakout(){
        Vector<Object> vecObj = new Vector<>();
        
        // Game window size 800x560
        int gameWidth = 800;
        int gameHeight = 560;       
        
        // Displays score
        cScore = new JLabel("Score: " + score);
        cScore.setForeground(Color.yellow);
        
        panel = new PaintingPanel(vecObj);              
        panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(gameWidth,gameHeight));
        panel.add(cScore);       
        
        // Create new paddle and centres it with initial size & speed
        paddSize = 100;
        paddThickness = 10;
        paddle = new Paddle(paddSize,paddThickness);
        paddSpeed = 25;
        paddle.centreX = gameWidth/2;
        paddle.centreY = gameHeight-5;
        vecObj.add(paddle);
        
        // Create new array of bricks
        bricks = new Vector<Brick>();
        // i = number of columns  im = i multiplier
        // j = number of rows     jm = j multiplier
        // bw = brick width       db = distance from top left & right border/side
        int im = 78; int jm = 30; int bw = 68; int db = 14;
        for (int i = 0; i<10; i++){
            for(int j = 0; j<8; j++){
               if(j<4){
                   // Coloured bricks see below for what each brick does
                   if(i == 2 && j == 1 || i == 7 && j == 1){
                     Brick brick = new Brick(i*im+db,j*jm+db,bw,20,Color.WHITE);
                     bricks.add(brick);
                     vecObj.add(brick);
                   }
                   else if(i == 3 && j == 2 || i == 6 && j == 2){
                     Brick brick = new Brick(i*im+db,j*jm+db,bw,20,Color.RED);
                     bricks.add(brick);
                     vecObj.add(brick);
                   }else{                     
                     Brick brick = new Brick(i*im+db,j*jm+db,bw,20,Color.BLUE);
                     bricks.add(brick);
                     vecObj.add(brick);}
                   }else if(i == 1 && j == 4 || i == 8 && j == 4){                      
                     Brick brick = new Brick(i*im+db,j*jm+db,bw,20,Color.YELLOW);
                     bricks.add(brick);
                     vecObj.add(brick);
                   }else if(i == 3 && j == 6 || i == 6 && j == 6){                      
                     Brick brick = new Brick(i*im+db,j*jm+db,bw,20,Color.WHITE);
                     bricks.add(brick);
                     vecObj.add(brick);
                   }else{
                     Brick brick = new Brick(i*im+db,j*jm+db,bw,20,Color.GREEN);
                     bricks.add(brick);
                     vecObj.add(brick);
               }
            }
        }
        
        // Create new ball with inital speed
        speed = 4;
        ball = new Ball(gameWidth/2,gameHeight-17,7,Color.RED,speed);
        vecObj.add(ball);
               
        showGUI(panel);
    }
    
    // Starts the game by making boolean true
    public void beginGame(){
        gameRunning = true;
    }
    
    @Override
    public void run() {
        if(gameRunning){
            ball.move(speed);
            handleCollisions();
            Vector<Object> vecObj = new Vector<Object>();
            vecObj.add(ball);
            vecObj.add(paddle);
            for(Brick brick:bricks)
                vecObj.add(brick);
            panel.contents = vecObj;
            panel.repaint();
            
        }   
    }
    // Stops the game by making boolean false
    public void stopGame(){
        gameRunning = false;
    }
    
    public void handleCollisions(){
        if(bricks.isEmpty() == true){
            stopGame();
        }
        
        // Ensures the paddle does not go off the screen
        if(paddle.centreX < 0)
            paddle.centreX = 10;
        if(paddle.centreX > 800)
            paddle.centreX = 790;
        
        if(ball.y > 560){
            stopGame();
        }
        if(ball.x < 0 || ball.x > 800)
            ball.reflect(true, false);
        if(ball.y < 0)
            ball.reflect(false, true);
        if(ball.getBounds().intersects(paddle.getBounds()))
                ball.reflect(false, true);     
        
        for(Brick brick: bricks){
            if(ball.getBounds().intersects(brick.getBounds())){
                // Scoring for hitting bricks with score update
                if(brick.colour == Color.BLUE){
                    score += 10;
                    cScore.setText("Score: " + score);
                }else if(brick.colour == Color.RED){
                    // Score Multiplier Brick
                    score *= 3;
                    cScore.setText("Score: " + score);
                }else if(brick.colour == Color.YELLOW){   
                    // Score Multiplier Brick and speed up brick
                    score *= 2;
                    paddSpeed += 10;
                    cScore.setText("Score: " + score);
                }else if(brick.colour == Color.WHITE){   
                    // Score Brick and size increase
                    paddle.padSize +=10;
                    paddle.padThickness +=1;
                    score += 10;
                    cScore.setText("Score: " + score);    
                } else {
                    score += 5;
                    cScore.setText("Score: " + score);
                }
                // Speed up as you destroy more bricks
                speed += 0.15;
                ball.reflect(false, true);
                bricks.remove(brick);     
                break;
            }
        }
    }
       
    public static void main(String[] args) { 
        Timer t = new Timer();
        t.schedule(new Breakout(), 0, 40);
    }
    
    // static wasn't used because it says "You MAY use the following description 
    //                                     of classes and interfaces as guideline."
    // Standard GUI method
    public void showGUI(PaintingPanel panel){
        
        JFrame frame = new JFrame("Breakout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.addKeyListener(new Breakout.KeyListen());
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);   
    } 
    
    // KeyListener for paddle
    private class KeyListen extends KeyAdapter implements KeyListener{     
        @Override
        public void keyPressed(KeyEvent ke) {
            switch(ke.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    paddle.centreX -= paddSpeed;
                    break;
                case KeyEvent.VK_RIGHT:                  
                    paddle.centreX += paddSpeed;
                    break;
                case KeyEvent.VK_SPACE:
                    beginGame();
                    break;
            }
        }
    } 
}
