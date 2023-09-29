package ass1;

import java.util.Random;

public class WarGame {
    private SQueue<Card> player1Hand = new SQueue<>(53);
    private SQueue<Card> player2Hand = new SQueue<>(53);
    private SQueue<Card> player1Discard = new SQueue<>(53);
    private SQueue<Card> player2Discard = new SQueue<>(53);
    public int i;
    public WarGame() {
        SQueue<Card> deck = new SQueue<>(53);
        for (Card.Suits suit : Card.Suits.values()) {
            for (Card.Ranks rank : Card.Ranks.values()) {
                deck.enqueue(new Card(suit, rank));
            }
        }
        shuffle(deck);

        // Distributing cards to both players
        while (!deck.isEmpty()) {
            player1Hand.enqueue(deck.dequeue());
            if (!deck.isEmpty()) {
                player2Hand.enqueue(deck.dequeue());
            }
        }
    }

    public void shuffle(SQueue<Card> deck) {
        for (int i = 0; i < 3; i++) {
            deck.shuffle();
        }
    }

    public void playGame(int maxRounds) {
        System.out.println("Starting the game of WAR!");
        System.out.println("Max number of rounds = " + maxRounds);
    
        for (i = 0; i < maxRounds; i++) {
            if (player1Hand.isEmpty() && player1Discard.isEmpty()) {
                break;
            } else if (player2Hand.isEmpty() && player2Discard.isEmpty()) {
                break;
            }
    
            ensureCardsInHand();
    
            Card player1Card = player1Hand.dequeue();
            Card player2Card = player2Hand.dequeue();
    
            System.out.print("Round " + i + " ");
    
            int comparison = player1Card.compareTo(player2Card);
            if (comparison > 0) {
                player1Discard.enqueue(player1Card);
                player1Discard.enqueue(player2Card);
                System.out.println("Player 1 Wins: " + player1Card + " beats " + player2Card);
            } else if (comparison < 0) {
                player2Discard.enqueue(player1Card);
                player2Discard.enqueue(player2Card);
                System.out.println("Player 2 Wins: " + player1Card + " loses to " + player2Card);
            } else {
                System.out.println("WAR: " + player1Card + " ties " + player2Card);
                i += handleWar(player1Card, player2Card);  // Adjust the counter based on the number of war rounds
            }
        }
    }
    
    public int handleWar(Card player1Card, Card player2Card) {
        SQueue<Card> warPile = new SQueue<>(53);
    
        warPile.enqueue(player1Card);
        warPile.enqueue(player2Card);
    
        int roundsConsumed = 0;
    
        while (true) {
            roundsConsumed++;
    
            ensureCardsInHand();
    
            if (player1Hand.isEmpty() || player2Hand.isEmpty()) {
                break;
            }
    
            Card player1FaceDown = player1Hand.dequeue();
            Card player2FaceDown = player2Hand.dequeue();
    
            warPile.enqueue(player1FaceDown);
            warPile.enqueue(player2FaceDown);
    
            System.out.println("Face down");
    
            ensureCardsInHand();
    
            if (player1Hand.isEmpty() || player2Hand.isEmpty()) {
                break;
            }
    
            Card player1FaceUp = player1Hand.dequeue();
            Card player2FaceUp = player2Hand.dequeue();
    
            int comparison = player1FaceUp.compareTo(player2FaceUp);
            if (comparison > 0) {
                while (!warPile.isEmpty()) {
                    player1Discard.enqueue(warPile.dequeue());
                }
                i++;
                System.out.println("Round " + i + " Player 1 Wins WAR: " + player1FaceUp + " beats " + player2FaceUp);
                break;
            } else if (comparison < 0) {
                while (!warPile.isEmpty()) {
                    player2Discard.enqueue(warPile.dequeue());
                }
                i++;
                System.out.println("Round " + i + " Player 2 Wins WAR: " + player1FaceUp + " loses to " + player2FaceUp);
                break;
            }
        }
    
        return roundsConsumed;
    }
    

    public void ensureCardsInHand() {
        if (player1Hand.isEmpty()) {
            while (!player1Discard.isEmpty()) {
                player1Hand.enqueue(player1Discard.dequeue());
            }
            player1Hand.shuffle();
            System.out.println("Getting and shuffling the pile for Player 1");
        }

        if (player2Hand.isEmpty()) {
            while (!player2Discard.isEmpty()) {
                player2Hand.enqueue(player2Discard.dequeue());
            }
            player2Hand.shuffle();
            System.out.println("Getting and shuffling the pile for Player 2");
        }
    }

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
