//********************************************************************
//  Card.java      
//  Author: Jared Kwok      ID no. 1382534
//********************************************************************
package solitaire;

public class Card {

    public enum Suit {
        Spade, Heart, Club, Diamond
    }

    public enum Face {
        Ace, Two, Three, Four, Five, Six, Seven,
        Eight, Nine, Ten, Jack, Queen, King
    }

    public enum Colour {

        Red, Black
    }
    private Suit suit;
    private Face face;
    private Colour colour;
    private boolean isFaceUp;
    private int cardIndex;

    public Card(int cardIndex) throws Exception {
        if (cardIndex< 0 || cardIndex>51)
            throw new Exception("Card Index not found");
        this.cardIndex = cardIndex;
        suit = Suit.values()[cardIndex / 13];
        face = Face.values()[cardIndex % 13];
        if(suit == Suit.Heart || suit == Suit.Diamond){
            colour = Colour.Red;
        } else{
            colour = Colour.Black;
        }
        isFaceUp = false;
    }

    public Suit getSuit() {
        return suit;
    }

    public Face getFace() {
        return face;
    }

    public Colour getColour() {
        return colour;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }
    
    // shows if the card is face up or down
    public void setIsFaceUp(boolean isFaceUp) {
        this.isFaceUp = isFaceUp;
    }
    
    // Strings the suit and face name
    public String toString() {
        String cardName = "";
        if (!isFaceUp) {
            cardName += "Back";
        } else {
            cardName += suit.name();
            switch (face) {
                case Ace:
                    cardName += "A";
                    break;
                case Jack:
                    cardName += "J";
                    break;
                case Queen:
                    cardName += "Q";
                    break;
                case King:
                    cardName += "K";
                    break;
                default:
                    cardName += face.ordinal()+1;
            }
        }
        return cardName;
    }
    
    // Trialing for errors
    public static void main(String[] args){
        try{
            Card card = new Card(22);
            Card card2 = new Card(36);
            System.out.println(card);
            card.setIsFaceUp(true);
            card2.setIsFaceUp(true);
            System.out.println(card);
            System.out.println(card2);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}