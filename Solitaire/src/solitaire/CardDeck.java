//********************************************************************
//  CardDeck.java      
//  Author: Jared Kwok      ID no. 1382534
//********************************************************************
package solitaire;

import java.util.Random;

public class CardDeck {

    private Card currentCard;
    private SingleLinkedList<Card> cards;

    public CardDeck() {
        int[] indices = new int[52];
        Random ranNum = new Random();
        cards = new SingleLinkedList<Card>();
        for (int i = 0; i < 52; i++) {
            indices[i] = i;
            // Checking if all cards were being shown
            /*try {
             cards.addLast(new Card(i));
             } catch (Exception e) {
             e.getStackTrace();
             }*/
        }
        // Shuffle Method
        try {
            for (int i = 51; i >= 0; i--) {
                int selectIndex = ranNum.nextInt(i + 1);
                cards.addLast(new Card(indices[selectIndex]));
                indices[selectIndex] = indices[selectIndex] - indices[i];
                indices[i] = indices[selectIndex] + indices[i];
                indices[selectIndex] = indices[i] - indices[selectIndex];
            }
            cards.addLast(new Card(indices[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        currentCard = cards.getFirst();
    }
    

    public Card drawCard() {
        // Pre: Checks if there's cards if not returns null
        // Post: If there's move the first card to the end and show the next card
        if (cards.size == 0) {
            return null;
        }

        if (currentCard.isFaceUp() && cards.size > 1) {
            cards.addLast(cards.removeFirst());
            currentCard = cards.getFirst();
        }

        currentCard.setIsFaceUp(true);

        if (currentCard == cards.getLast()) {
            return null;
        }

        return currentCard;
    }
    
    public Card takeCard() {
        // Pre: creates a template of current card and removes it from deck
        // Post: returns the current card
        Card temp = currentCard;
        cards.remove(currentCard);
        if (cards.size != 0) {
            currentCard = cards.getFirst();
        } else {
            currentCard = null;
        }

        return temp;

    }

    public Card getCurrentCard() {
        return currentCard;

    }

    public boolean isEmpty() {
        return cards.size() == 0;
    }

    // Trialing for errors
    public static void main(String[] args) {
        CardDeck deck = new CardDeck();
        int i = 0;
        while (deck.drawCard() != null) {
            System.out.println(i++ + " " + deck.takeCard());
        }
    }
}
