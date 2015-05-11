//********************************************************************
//  Project 50
//  Author: Jared Kwok(1382534) and Mike Jiang(1240129)    
//********************************************************************
package dealornodeal;

import java.util.Scanner;

public class DealOrNoDeal {

    private boolean gameRunning;
    private int numCasesLeft = 26;
    private int offer, turn;
    private banker bank;
    private caseGenerator caseGen;
    private caseValue[] cases = new caseValue[26];
    private player newPlayer;
    private caseValue playerCase;
    private String playerCaseNum = "X";

    public DealOrNoDeal() {
        caseGen = new caseGenerator();
        bank = new banker();

        for (int i = 0; i < 26; i++) {
            caseValue currentCase = caseGen.pickCase(i);

            caseGen.showCase(i).setIsOpen(false);

            cases[i] = currentCase;
        }

    }

    public void startGame() {
        Scanner scan = new Scanner(System.in);
        turn = 1;
        gameRunning = true;

        // Starting information about how to play
        System.out.println("----------INSTRUCTIONS----------");
        System.out.println("To pick a case type 'pick #', # is a number 1 - 26");
        System.out.println("To quit type 'Quit' ");
        System.out.println("For help during the game type 'Help' ");
        System.out.println("Enter your name: ");
        String input = scan.nextLine();
        if (input.compareToIgnoreCase("QUIT") == 0) {
            gameRunning = false;
        } else {
            newPlayer = new player(input);

            showCurrentCases();
        }
        // Controls when the game is running
        while (gameRunning) {
            if (numCasesLeft == 1) {
                gameRunning = false;
            }
            if (execute(scan.nextLine())) {
                if (gameRunning) {
                    showCurrentCases();
                }
            } else {
                showCurrentCases();
                
            }
        }
        scan.close();
    }

    private boolean execute(String command) {
        // Quits the Game
        if (command.compareToIgnoreCase("QUIT") == 0) {
            gameRunning = false;
            return true;
        } // If the player forgets a command or wants to know how many cases are left
        else if (command.compareToIgnoreCase("HELP") == 0) {
            System.out.println("----------HELP TIPS----------");
            System.out.println("To pick a case type 'pick #' # is a number 1 - 26");
            System.out.println("To quit type 'Quit' ");
            System.out.println("For help type 'Help' ");
            System.out.println("Cases Left: " + numCasesLeft);
            return true;
        } // Pick command. Choosing a case
        else if (command.toLowerCase().contains("pick") && turn != 8
                && turn != 14 && turn != 19 && turn != 23
                && turn != 25 && turn != 27 && turn != 29
                && turn != 31 && turn != 33 && turn != 35
                && turn <= 36) {
            String input = command;
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("----------Command Error----------");
                System.out.println("No Space between pick and number");
                return false;
            } else {
                String part2 = parts[1];
                try {
                    int caseNum = Integer.parseInt(part2) - 1;
                    if (caseNum < 0 || caseNum > 25) {
                        return false;
                    } else if (cases[caseNum].isCasePicked() == true) {
                        return false;
                    } else {
                        if (turn == 1) {
                            playerCaseNum = caseNum+1+"";
                            cases[caseNum].setCasePicked(true);
                            playerCase = cases[caseNum];
                            numCasesLeft--;
                            turn++;
                        } else {
                            cases[caseNum].setCasePicked(true);
                            cases[caseNum].setIsOpen(true);
                            bank.setOffer(turn, cases, bank.getOffer());
                            numCasesLeft--;
                            turn++;
                        }
                        return true;
                    }
                } catch (Exception e) {
                    System.out.println("----------Command Error----------");
                    System.out.println("Non-Integer input value or greater/lesser than max/min cases");
                    return false;
                }
            }
        } // Deal or no deal commands
        else if ((numCasesLeft == 19 && turn == 8) || (numCasesLeft == 14
                && turn == 14) || (numCasesLeft == 10 && turn == 19) || (numCasesLeft == 7
                && turn == 23) || (turn > 23 && turn % 2 == 1) || numCasesLeft == 1) {
            if (command.compareToIgnoreCase("DEAL") == 0) {
                System.out.print("CONGRATULATIONS " + newPlayer.getName()
                        + " YOU HAVE WON: $" + bank.getOffer());
                gameRunning = false;
                return true;
            } else if (command.compareToIgnoreCase("NODEAL") == 0
                    || command.compareToIgnoreCase("NO DEAL") == 0) {
                if (numCasesLeft == 1) {
                    playerCase.setIsOpen(true);
                    System.out.println("CONGRATULATIONS " + newPlayer.getName()
                            + " YOU HAVE WON: " + playerCase);
                }
                turn++;
                return true;
            }
        }
        System.out.println("----------Command Error----------");
        System.out.println("No such command or unable to pick this turn");
        return false;
    }

    // Method showing displaying the game
    private void showCurrentCases() {
        System.out.println("----------Current Cases----------");
        for (int i = 0; i < 11; i++) {
            if (caseGen.showCase(i).isOpen()) {
                System.out.print(cases[i] + "  ");
            } else {
                System.out.print(i + 1 + "  ");
            }
        }
        System.out.println("");
        for (int i = 11; i < 19; i++) {
            if (caseGen.showCase(i).isOpen()) {
                System.out.print(cases[i] + "  ");
            } else {
                System.out.print(i + 1 + "  ");
            }
        }
        System.out.println("");
        for (int i = 19; i < 26; i++) {
            if (caseGen.showCase(i).isOpen()) {
                System.out.print(cases[i] + "  ");
            } else {
                System.out.print(i + 1 + "  ");
            }
        }
        System.out.println("");
        if ((numCasesLeft == 19 && turn == 8) || (numCasesLeft == 14
                && turn == 14) || (numCasesLeft == 10 && turn == 19) || (numCasesLeft == 7
                && turn == 23) || (turn > 23 && turn % 2 == 1) || numCasesLeft == 1) {

            System.out.println("The Bank Offer Is: $" + bank.getOffer());
            System.out.println("Your selected case: " + playerCaseNum);
            offer = bank.getOffer();
            System.out.println("Deal or No Deal? ");

        } else {
            System.out.println("The Bank Offer Was: $" + offer);
            System.out.println("Your selected case: " + playerCaseNum);
            System.out.println("Select a Case: ");
        }
    }

    public static void main(String[] args) {
        DealOrNoDeal game = new DealOrNoDeal();
        game.startGame();
    }
}
