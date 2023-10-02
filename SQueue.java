package ass1;

import java.util.Random;

public class SQueue<T> implements QueueInterface<T>, Shufflable {
    private T [] theArray;  
    private int read; 
    private int write;
    private int capacity;
    private int size;

    public SQueue(int capacity)	{		
        this.capacity = capacity + 1;			
        @SuppressWarnings("unchecked")	
        T [] temp = (T []) new Object[this.capacity];
        theArray = temp;
        this.read = 0;
        this.write = 0;
        this.size = 0;
    }	

    // Returns the current size of the queue
    public int getSize() {
        return this.size;
    }

    // Adds a new element to the end of the queue
    public void enqueue(T newEntry) {
        if (isFull()) {
            throw new FullQueueException();
        }
        theArray[write] = newEntry;
        write = (write + 1) % capacity; // Circular behavior
        size++;
    }

    // Removes and returns the front element of the queue
   public T dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        T front = theArray[read];
        theArray[read] = null;  // Clear the slot
        read = (read + 1) % capacity; // Circular behavior
        size--;
        return front;
    }

    public T getFront() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return theArray[read];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity - 1;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            theArray[i] = null;
        }
        read = 0;
        write = 0;
        size = 0;
    }
    public void shuffle() {
        Random rand = new Random();
        for (int i = 0; i < size - 1; i++) {
            int currentIndex = (read + i) % capacity;
            int randomIndex = (read + rand.nextInt(size - i)) % capacity;
            
            // Swaps the current element with the random element
            T temp = theArray[currentIndex];
            theArray[currentIndex] = theArray[randomIndex];
            theArray[randomIndex] = temp;
        }
    }
    public void shuffle(long seed) {
        Random rand = new Random(seed);
        
    for (int i = 0, pos = read; i < size; pos++, i++) {
            int currentIndex = (pos) % capacity;
            int r = rand.nextInt(size - i);
               int randomIndex = (read + r) % capacity;	        
                    
    T temp = theArray[currentIndex];
               theArray[currentIndex] = theArray[randomIndex];
               theArray[randomIndex] = temp;
        }
    }
    
    public String toString() {
        StringBuilder result = new StringBuilder("SQueue: the array [" + theArray[0] );
        for (int i = 1; i < capacity; i++) {
            result.append(", ");
            result.append(theArray[i]);
        }
        result.append("] ");

        result.append("Capacity:" + this.capacity);
        result.append(" Size:" + this.size);
        result.append(" Read:" + this.read);
        result.append(" Write:" + this.write);
        return result.toString();
    }
}