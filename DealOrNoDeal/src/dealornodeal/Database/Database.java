//********************************************************************
//  Project 50
//  Author: Jared Kwok(1382534) and Mike Jiang(1240129)    
//********************************************************************
package dealornodeal.database;

import dealornodeal.Banker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author phm1858
 */
public class Database {


    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String url = "jdbc:derby:dond_db;create=true;"; 
    String user = "mike";  
    String pass = "mike";   
    String player;
    Connection connection = null;
    Statement stmt = null;  

    float playerScore;

    public void connectDB(String playerName, float offer) throws Exception {       
        System.out.print(playerName);
        this.player = playerName;
        this.playerScore = offer;
        try {          
            System.out.println("connecting..........");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("yay! connected!");

            stmt = connection.createStatement();
            
            String addValues = "INSERT INTO MIKE.HIGHSCORES Values ('" + player + "', " + playerScore + ")";
            stmt.execute(addValues);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public String getHighScores() throws Exception {     
        String highscore = "Highscores\n\n";
        try {
  
            System.out.println("connecting..........");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("yay! connected!");

            stmt = connection.createStatement();
            
            String addValues = "SELECT * FROM MIKE.HIGHSCORES order by score desc";
            ResultSet result = stmt.executeQuery(addValues);
            int i = 1;
            
            while (result.next()) {
                String playerName = result.getString("Player");
                String score = result.getString("Score");
             
                highscore += i + ": " + playerName + ": " + score + "\n";
                
                if (i == 10) {
                    break;
                }
                
                i++;
            }
   
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return highscore;
    }
}