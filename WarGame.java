package ass1;

import java.util.Random;

public class WarGame {
    private SQueue<Card> player1Hand;
    private SQueue<Card> player1Discard;
    private SQueue<Card> player2Hand;
    private SQueue<Card> player2Discard;
    private SQueue<Card> deck;
    private int maxRounds = 1000;
    
    public WarGame() {
        System.out.println("Welcome to the Game of War!\n");
        // Initialize the players' hands and discard piles
        player1Hand = new SQueue<>(52);
        player1Discard = new SQueue<>(52);
        player2Hand = new SQueue<>(52);
        player2Discard = new SQueue<>(52);
        
        // Create and shuffle the deck
        createDeck();
        deck.shuffle();
        
        // Deal the cards to the players
        dealCards();
        System.out.println("\nNow dealing cards to the players\n");
        System.out.println("Player 1's Deck:");
        System.out.println(player1Hand);
        System.out.println("\nPlayer 2's Deck:");
        System.out.println(player2Hand);
        System.out.println("\nStarting the game of WAR!");
        System.out.println("Max number of rounds = " + maxRounds);
    }
    
    private void createDeck() {
        deck = new SQueue<>(52);
        for (Card.Suits suit : Card.Suits.values()) {
            for (Card.Ranks rank : Card.Ranks.values()) {
                deck.enqueue(new Card(suit, rank));
            }
        }
    }
    
    private void dealCards() {
        boolean turn = true; // True for player1, False for player2
        while (!deck.isEmpty()) {
            if (turn) {
                player1Hand.enqueue(deck.dequeue());
            } else {
                player2Hand.enqueue(deck.dequeue());
            }
            turn = !turn; // Alternate players
        }
    }
    
public void playGame(int maxRounds) {
        int rounds = 0;
        while (rounds < maxRounds && !player1Hand.isEmpty() && !player2Hand.isEmpty()) {
            System.out.println("\nRound " + rounds + " ");
            Card card1 = player1Hand.dequeue();
            Card card2 = player2Hand.dequeue();
            
            int comparison = card1.compareTo(card2);
            if (comparison > 0) {
                // Player 1 wins the round
                player1Discard.enqueue(card1);
                player1Discard.enqueue(card2);
                System.out.println("Player 1 Wins: " + card1 + " beats " + card2);
            } else if (comparison < 0) {
                // Player 2 wins the round
                player2Discard.enqueue(card1);
                player2Discard.enqueue(card2);
                System.out.println("Player 2 Wins: " + card1 + " loses to " + card2);
            } else {
                // WAR round
                SQueue<Card> warPile = new SQueue<>(52);  // Temporary pile for cards in play during WAR
                warPile.enqueue(card1);
                warPile.enqueue(card2);
                System.out.println("WAR: " + card1 + " ties " + card2);
                playWarRound(warPile);
            }
            
            rounds++;
        }
        
        // ... End game logic to be added later ...
    }
    
    private void playWarRound(SQueue<Card> warPile) {
        while (!player1Hand.isEmpty() && !player2Hand.isEmpty()) {
            Card card1FaceDown = player1Hand.dequeue();
            Card card2FaceDown = player2Hand.dequeue();
            Card card1FaceUp = player1Hand.dequeue();
            Card card2FaceUp = player2Hand.dequeue();

            warPile.enqueue(card1FaceDown);
            warPile.enqueue(card2FaceDown);
            warPile.enqueue(card1FaceUp);
            warPile.enqueue(card2FaceUp);
            System.out.println("Face down");
            
            int comparison = card1FaceUp.compareTo(card2FaceUp);
            if (comparison > 0) {
                // Player 1 wins the WAR
                while (!warPile.isEmpty()) {
                    player1Discard.enqueue(warPile.dequeue());
                }
                System.out.println("Player 1 Wins WAR: " + card1FaceUp + " beats " + card2FaceUp);
                }
            else {
                // Player 2 wins the WAR
                while (!warPile.isEmpty()) {
                    player2Discard.enqueue(warPile.dequeue());
                }
                System.out.println("Player 2 Wins WAR: " + card1FaceUp + " loses to " + card2FaceUp);
                }
                break;
            }  
            }
            // If tie, the loop continues and another WAR round begins
    
    public void endGame() {
        if (player1Hand.isEmpty() && player1Discard.isEmpty()) {
            System.out.println("\nPlayer 1 is out of cards!\nPlayer 2 Wins!!");
        } else if (player2Hand.isEmpty() && player2Discard.isEmpty()) {
            System.out.println("\nPlayer 2 is out of cards!\nPlayer 1 Wins!!");
        } else if (player1Hand.getSize() + player1Discard.getSize() > player2Hand.getSize() + player2Discard.getSize()) {
            System.out.println("Player 1 wins with more cards!");
        } else if (player1Hand.getSize() + player1Discard.getSize() < player2Hand.getSize() + player2Discard.getSize()) {
            System.out.println("Player 2 wins with more cards!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public static void main(String[] args) {
        int maxRounds = 1000;  // Default number of rounds
        if (args.length > 0) {
            try {
                maxRounds = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number of rounds provided. Using default.");
            }
        }
        
        WarGame game = new WarGame();
        game.playGame(maxRounds);
        game.endGame();
    }
}