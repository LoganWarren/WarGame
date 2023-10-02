# WarGame - CS 0445  Algorithms and Data Structures 1

WarGame is a Java-based implementation of the classic card game "War". The game is designed with a focus on object-oriented principles and showcases the use of interfaces, exceptions, and data structures like queues.

## Files Overview:

WarGame.java: The main class that drives the game. It contains the game logic and flow.

Card.java: Represents a playing card with attributes like rank and suit.

Shufflable.java: An interface that provides a method for shuffling.

QueueInterface.java: An interface that defines the basic operations for a queue data structure.

SQueue.java: A concrete implementation of the QueueInterface, representing a static queue.

EmptyQueueException.java: Custom exception thrown when trying to dequeue from an empty queue.

FullQueueException.java: Custom exception thrown when trying to enqueue into a full queue.

Ass1NewTest.java & Ass1Test.java: Test classes to validate the functionality of the game and its components.

CardQueueTest.java: Test class specifically for validating the queue operations related to cards.

## How to Play:
    1. Compile all the Java files.
    2. Run the WarGame.java file to start the game.
    3. Follow the on-screen instructions to play.