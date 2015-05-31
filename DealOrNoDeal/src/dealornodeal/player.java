//********************************************************************
//  Project 50
//  Author: Jared Kwok(1382534) and Mike Jiang(1240129)    
//********************************************************************
package dealornodeal;

// Player profile
public class Player {
    
    String name;
    CaseValue playerCase;
    public Player(String name) {
        if (name.compareTo("") == 0) {
            name = "Player";
        }
        this.name = name;      
    }
    
    public String getName() {
        return name;
    }
    
}
