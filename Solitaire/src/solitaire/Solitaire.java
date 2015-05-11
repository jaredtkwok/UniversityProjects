//********************************************************************
//  Solitare.java      
//  Author: Jared Kwok      ID no. 1382534
//********************************************************************
package solitaire;

import java.util.Scanner;

public class Solitaire {

    private CardDeck deck;
    private CardStack[] stacks;
    private CardList[] lists;
    private boolean isRunning;

    public Solitaire() {
        deck = new CardDeck();
        stacks = new CardStack[4];
        stacks[0] = new CardStack();
        stacks[1] = new CardStack();
        stacks[2] = new CardStack();
        stacks[3] = new CardStack();
        lists = new CardList[7];
        lists[0] = createCardList(1);
        lists[1] = createCardList(2);
        lists[2] = createCardList(3);
        lists[3] = createCardList(4);
        lists[4] = createCardList(5);
        lists[5] = createCardList(6);
        lists[6] = createCardList(7);
        isRunning = false;
    }
    
    // Create Lists for the starting board
    private CardList createCardList(int size) {
        Card[] card = new Card[size];
        for (int i = 0; i < size; i++) {
            if (deck.drawCard() != null) {
                card[i] = deck.takeCard();
                card[i].setIsFaceUp(false);
            }
        }
        return new CardList(card);
    }
    
    // Starts a new game and creates a scanner for inputs
    public void newGame() {
        Scanner scan = new Scanner(System.in);
        isRunning = true;
        deck.drawCard();
        showCurrentCards();
        while (isRunning) {
            if (execute(scan.nextLine())) {
                if (isRunning) {
                    showCurrentCards();
                }
            } else {
                showCurrentCards();
                System.out.print("Unable to execute move, next move:");
            }
        }
        scan.close();
    }
    
    // Shows the current cards that can be moved
    private void showCurrentCards() {
        System.out.println("----------Current Cards----------");
        if(deck.isEmpty()){
            System.out.println("Deck: No Cards");
        } else {
            System.out.print("Deck: Has Cards");
            System.out.println("        Top Card: " + deck.getCurrentCard());
        }
        System.out.print("Stacks: ");
        for(CardStack cardStack: stacks){
            System.out.print(cardStack + " ");       
        }
        System.out.println("");
        System.out.println("Card Lists:");
        int count = 1;
        for (CardList list: lists){
            System.out.println(count++ + ": "+ list);
        }
        System.out.print("Next move: ");
    }
    
    // executes commands typed by user
    private boolean execute(String exeCommand) {
        // Quitting the game
        if (exeCommand.compareToIgnoreCase("Quit") == 0) {
            isRunning = false;
            return true;
        } 
        // Draw a new card
        else if (exeCommand.compareToIgnoreCase("DrawCard") == 0
                || exeCommand.compareToIgnoreCase("Draw") == 0) {
            if (deck.drawCard() != null) {
                return true;
            } else {
                return false;
            }
        } 
        // Taking a card from the deck
        else if (exeCommand.toLowerCase().contains("deckto")){
            String input = exeCommand;
            String[] parts = input.split(" ");
            if(parts.length<2){
                return false;
            }
            String part2 = parts[1];
            
            int deckNum = Integer.parseInt(part2);                      
            
            if(deckNum < 1 || deckNum > 7)
                return false;
            
            if(lists[deckNum-1].add(deck.getCurrentCard())){
                deck.takeCard();
                deck.drawCard();
                return true;
            }
            return false;
            
        } 
        // Links two stacks of cards
        else if (exeCommand.toLowerCase().contains("link")){
            // Not working
            String input = exeCommand;
            String[] parts = input.split(" ");
            if(parts.length<2){
                return false;
            }
            String part2 = parts[1];
            String part3 = parts[2];
            
            int deckNum = Integer.parseInt(part3);
         
            if(deckNum < 1 || deckNum > 7)
                return false;
            
            
            Card cL = lists[deckNum-1].getCards().getLast();
            int cListIndex = 0;
            for(int i = 0; i<lists.length-1; i++){
                int cardIndex = 0;
                Card c = lists[i].getCards().getLast();
                for(int j = 0; j<lists[i].getCards().size()-1; j++){
                    if(c.toString().compareToIgnoreCase(part2)==0 
                            && c.isFaceUp()){
                        if(cListIndex == deckNum-1){
                            return false;
                        } else if (c.getColour() != cL.getColour()
                                && c.getFace().ordinal()+1 == cL.getFace().ordinal()){
                            return lists[deckNum-1].link(lists[cListIndex].cut(cardIndex));              
                        }                       
                    }
                    cardIndex++;
                }
                cListIndex++;
            }
                       
        } 
        // Sends the cards to one of the 4 stacks on top
        else if (exeCommand.toLowerCase().contains("send")){
            String input = exeCommand;
            String[] parts = input.split(" ");
            if(parts.length<2){
                return false;
            }
            String cardName = parts[1];
            
            Card card = null;
            boolean isAdded = false;
            boolean isFromDeck = false;
            int listIndex = 0;
                        
            if(cardName.compareToIgnoreCase(deck.getCurrentCard().toString())==0){
                card = deck.getCurrentCard();
                isFromDeck = true;
            }
                  
            for(int i = 0; i<lists.length-1; i++){
                if(cardName.compareToIgnoreCase(lists[i].getCards().getLast().toString())==0){
                    card = lists[i].getCards().getLast();
                    break;
                }
                listIndex++;
            }
            
            if(card == null){
                return false;
            }
            for(int i = 0; i<stacks.length-1; i++){
                isAdded = stacks[i].add(card);
                if(isAdded)
                    break;
            }
            
            if(isAdded && isFromDeck){
                deck.takeCard();
                deck.drawCard();
                return true;
            } else if (isAdded){
                lists[listIndex].moveTail();
                return true;
            }
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        Solitaire game = new Solitaire();
        game.newGame();
    }
}
