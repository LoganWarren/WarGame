package ass1;

public class Ass1NewTest {
	public static void main(String [] args) {
		
		// 1. Storage is implemented using a generic array: 
		// just look at the constructor
	
	
		// 2. The array is circular
		System.out.println("=================================");
		System.out.println("Testing circular array");
		System.out.println("=================================");
		
		int capacity = 3; 
		SQueue<Integer> theQ = new SQueue<Integer>(capacity);
		System.out.println("created an empty queue with " + capacity + " slots");
		
		for (int i = 0; i < capacity; i++) {
			Integer newItem = Integer.valueOf(i);
			try {
				theQ.enqueue(newItem);
				System.out.println("Queue after adding element  "+ newItem);
				System.out.println(theQ.toString());
			} catch (FullQueueException fe) {
				System.out.println("ERROR: "+ "Cannot add element "
							+ newItem +" " + fe.getMessage());				
				break;
			} 
		}
		
		for (int i = capacity; i < 2* capacity; i++  ) {
			try {
				Integer oldItem = theQ.dequeue();
				System.out.println("Queue after dequeing element: " + oldItem);
				System.out.println(theQ.toString());
			}
			catch (EmptyQueueException ee) {
				System.out.println("ERROR: "+ ee.getMessage());
			}		Integer newItem = Integer.valueOf(i);
			
			newItem = Integer.valueOf(i);
			try {
				theQ.enqueue(newItem);
				System.out.println("Queue after adding element  "+ newItem);
				System.out.println(theQ.toString());
			} catch (FullQueueException fe) {
				System.out.println("ERROR: "+ "Cannot add element "
							+ newItem +" " + fe.getMessage());				
				break;
			} 
		}
	
		// 3. The array has one empty slot.
		// 4. enqueue works correctly including exceptions
		// 5. isFull implemented correctly
		System.out.println("\n=================================");
		System.out.println("Testing enqueue, exception, and one empty slot");
		System.out.println("=================================");
		Integer item = 6;
		try {
			theQ.enqueue(item);
			System.out.println("Queue after adding element  "+ item);
			System.out.println(theQ.toString());
		} catch (FullQueueException fe) {
			System.out.println("ERROR: "+ "Cannot add element "
						+ item +" " + fe.getMessage());				
		} 
		System.out.println("Is the queue full? " + theQ.isFull());
		
		// 5. dequeue works correctly including exceptions
		// 6. isEmpty is implemented correctly
		System.out.println("\n=================================");
		System.out.println("Testing dequeue and exception. Is empty and isFull");
		System.out.println("=================================");
		for (int i = 0; i <= capacity; i++  ) {
			try {
				Integer oldItem = theQ.dequeue();
				System.out.println("Queue after dequeing element: " + oldItem);
				System.out.println(theQ.toString());
			}
			catch (EmptyQueueException ee) {
				System.out.println("ERROR: "+ ee.getMessage());
			}		Integer newItem = Integer.valueOf(i);
		}
		
		System.out.println("Is the queue full? " + theQ.isFull());
		System.out.println("Is the queue empty? " + theQ.isEmpty());
		
		// 7. clear works properly
		System.out.println("\n=================================");
		System.out.println("Testing clear");
		System.out.println("=================================");
		SQueue<String> theQ2 = new SQueue<String>(3);
		for (int i = 0; i < 3; i++)	{			
			String theItem = new String("Item " + i);
			try {				
				theQ2.enqueue(theItem);								
			} catch (FullQueueException fe) {
				System.out.println("ERROR: "+ fe.getMessage());
				System.out.println("Cannot add element "+ theItem);										
			} 			
		}
		System.out.println("Queue before clear");
		System.out.println(theQ2);
		
		theQ2.clear();
		System.out.println("\nQueue after clear");
		System.out.println(theQ2);
		System.out.println("Is the queue full? " + theQ2.isFull());
		System.out.println("Is the queue empty? " + theQ2.isEmpty());
		
		System.out.println("\n=================================");
		System.out.println("Testing Shufflable Interface (your results dont need to be identical)");
		System.out.println("=================================");
		// This code will test  the Shufflable interface.
		System.out.println("About to test shuffle method...");
		
		SQueue<Integer> newQ = new SQueue<Integer>(10);
		for (int i = 0; i < 10; i++)	{
			try {
				newQ.enqueue(i);				
			} catch (FullQueueException fe) {
				System.out.println("ERROR: "+ fe.getMessage());
				System.out.println("Cannot add element "+ i);	
			} 		
			
		}
		System.out.println(newQ);
		
		System.out.println("Shuffling...");
		newQ.shuffle();
		System.out.println(newQ);
		
		System.out.println("Removing 2 elements and adding 2");
		try {				
			newQ.dequeue();	
			newQ.dequeue();	
		} catch (EmptyQueueException ee) {
			System.out.println("ERROR: "+ ee.getMessage());
			System.out.println(newQ);							
		} 		
		
		try {
			newQ.enqueue(22);
			newQ.enqueue(33);
		} catch (FullQueueException fe) {
			System.out.println("ERROR: "+ fe.getMessage());
			System.out.println(newQ);							
		} 		
		
		System.out.println(newQ);
		
		System.out.println("Shuffling again...");
		newQ.shuffle();
		System.out.println(newQ);
	}

}
