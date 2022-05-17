import java.util.Scanner;

public class Game {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Blackjack :)");
        System.out.println("What's your name?");
        String playerName = scan.nextLine();

        System.out.println("Hi " + playerName + "!" + " How much money are you betting today?");
        double playerMoney = scan.nextDouble();

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        Scanner playerInput = new Scanner(System.in);

        while(playerMoney > 0){
            System.out.println("You have $" + playerMoney + ", how much money would you like to bet?");
            double playerBet = playerInput.nextDouble();
            if (playerBet > playerMoney){
                System.out.println("You do not have enough money to bet. Sorry! :( ");
                break;
            }

            boolean gameRound =false;

            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);
        

            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);
            if(playerDeck.cardsValue() == 21|| dealerDeck.cardsValue() == 21){
                System.out.println("Blackjack! :) ");
            }

            while(true){
                System.out.println("-------Your hand------ ");
                System.out.println(playerDeck.toString());
                System.out.println("Your hand value is: " + playerDeck.cardsValue());
                if(playerDeck.cardsValue() == 21){
                    System.out.println("You got Blackjack! :) ");
                }

                System.out.println("------Dealers hand-----");
                System.out.println(dealerDeck.getCard(0).toString());
                System.out.println("[****]");
                System.out.println("Enter (1) to Hit or (2) to Stand ");
                int response = playerInput.nextInt();

                if (response == 1){
                    playerDeck.draw(playingDeck);
                    System.out.println("You drew a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
    
                    if(playerDeck.cardsValue() > 21){
                        System.out.println("Your hand value is: " + playerDeck.cardsValue());
                        System.out.println("Bust! :( dealer wins, you lose");
                        playerMoney-=playerBet;
                        gameRound = true;
                        break;
                    }
                }
                if (response == 2){
                    break;
                }
            }
            System.out.println("------Dealer's hand-----");
            System.out.println(dealerDeck.toString());
            if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && gameRound == false){
                System.out.println("Dealer wins");
                playerMoney -= playerBet;
                gameRound = true;
            }
            while((dealerDeck.cardsValue() < 21) && gameRound == false){
                dealerDeck.draw(playerDeck);
                System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
                System.out.println("Dealer's hand's value: " + dealerDeck.cardsValue());
                    if(dealerDeck.cardsValue() == 21){
                        System.out.println("Dealer has Blackjack :) you do not :( ");
                    }
            } 
            if ((dealerDeck.cardsValue() > 21 ) && gameRound == false){
                System.out.println("Dealer busts. You win!");
                playerMoney += playerBet;
                gameRound = true;
            }
            if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && gameRound == false){
                System.out.println("Push, so no one wins. ");
                gameRound = true;
            }
            if((dealerDeck.cardsValue() < playerDeck.cardsValue()) && gameRound == false){
                System.out.println("You win :) ");
                playerMoney += playerBet;
                gameRound = true;
            }
            else if(gameRound == false){
            System.out.println("You lose :( ");
            playerMoney -= playerBet;
            gameRound = true;
            }
            playerDeck.moveToDeck(playingDeck);
            dealerDeck.moveToDeck(playingDeck);
    }
        System.out.println("Game over. Ran out of yung mulah baby." );
        scan.close();
        playerInput.close();
    }

}