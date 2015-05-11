//********************************************************************
//  CardStack.java      
//  Author: Jared Kwok      ID no. 1382534
//********************************************************************
package solitaire;

public class CardStack{
    public Stack<Card> cards;
    
    public CardStack(){
        cards = new Stack<Card>();
    }
    
    public boolean add(Card c){
        // Pre: Checks for list size 0 or matching suits 
        //      and face value higher by 1
        // Post: Adds card to one of the top 4 stacks
        if(cards.size() == 0){
            if(c.getFace() == Card.Face.Ace)
                cards.push(c);
                return true;
        } else {
            if (c.getSuit() == cards.getFirst().getSuit()){
                if(c.getFace().ordinal() == cards.getFirst().getFace().ordinal() + 1)
                    cards.push(c);
                    return true;
            }
        }
        return false;
    }
    
    public String toString(){
        String result = "";
        if(cards.size() == 0){
            result += "Empty";
        } else {
            result += cards.getFirst();
        }
        
        return result;
    }
}
