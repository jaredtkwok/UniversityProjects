//********************************************************************
//  CardList.java      
//  Author: Jared Kwok      ID no. 1382534
//********************************************************************
package solitaire;

public class CardList{

    private Card tailCard;
    private int openedIndex;
    private SingleLinkedList<Card> cards;
    
    public CardList(Card[] cards){
        this.cards = new SingleLinkedList<>();
        for(Card card: cards){
            this.cards.addFirst(card);
        }
        tailCard = this.cards.getLast();
        openedIndex = this.cards.size - 1;
        tailCard.setIsFaceUp(true);
    }
    
    public SingleLinkedList<Card> cut(int index) {
        // Pre: checks if there's any cards if not throw exception
        // Post: If there is cards cut it at specified point
        if(index<0|| index>= cards.size())
            throw new IndexOutOfBoundsException();
        
        SingleLinkedList<Card> cutList = new SingleLinkedList<Card>();
        int currentIndex = cards.size();
        while(index<currentIndex){
            cutList.addFirst(cards.removeLast());
            currentIndex--;
        }
        
        if(cards.size() != 0){
            cards.getLast().setIsFaceUp(true);
            openedIndex = openedIndex();
            tailCard = cards.getLast();
        } else{
            tailCard = null;
        }     
        return cutList;
    }

    public boolean link(SingleLinkedList<Card> other) {
        // Pre: Checks if card is able to link to another card
        // Post: Links both cards and the stack of cards that followed
        Card card = other.getFirst();
        if(cards.size() == 0){
            if(card.getFace() == Card.Face.King){
                while(other.size() != 0){
                    cards.addLast(other.removeFirst());
                }
                tailCard = cards.getLast();
                openedIndex = this.cards.size()-1;
                return true;
            }
        } else if(card.getColour() != tailCard.getColour()){
            if(card.getFace().ordinal() == tailCard.getFace().ordinal()-1){
                while(other.size() != 0){
                    cards.addLast(other.removeFirst());
                }
                tailCard = cards.getLast();
                openedIndex = openedIndex();
                return true;
            }
        } 
        return false;
    }
    
    private int openedIndex(){
        // Checks for the first face up card
        int index = -1;
        for(int i = 0; i<cards.size()-1; i++){
            index++;
            if(cards.getValueAtIndex(i).isFaceUp()){
            break;
            }
        }
        
        return index;  
    }

    public boolean add(Card c) {
        // Pre: Checks if card is able to link to another card
        // Post: Adds card to the end of list
        if(cards.size() == 0){
            if(c.getFace() == Card.Face.King){
                cards.addLast(c);
                tailCard = cards.getLast();
                openedIndex = this.cards.size-1;
                return true;
            }
        } else if (c.getColour() != tailCard.getColour()){
            if(c.getFace().ordinal()== tailCard.getFace().ordinal()-1){
                cards.addLast(c);
                tailCard = cards.getLast();
                openedIndex = cards.size()+1; 
                return true;
            }
        }
        return false;
    }
    
    public Card moveTail(){
        // Pre: Checks if list is empty or not
        // Post: If the tail card moves a new one is open else it's null
        if(cards.size != 0){
            Card card = cards.removeLast();
            if(cards.size() != 0){
                tailCard = cards.getLast();
                tailCard.setIsFaceUp(true);
                openedIndex = this.cards.size() - 1;
            } else {
                tailCard =null;
            }
            return card;
        }
        return null;
    }
    
    public SingleLinkedList<Card> getCards(){
        return cards;
    }
    
    public String toString(){
        String result = "";
        if (cards.size() == 0){
            result += "Empty";
        } else{
            for(int i = 0; i < cards.size()-1; i++){
                result += cards.getValueAtIndex(i) + "-";
            }
            result += cards.getLast();
        }       
        return result;
    }

}
