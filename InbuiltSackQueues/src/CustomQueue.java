
public class CustomQueue {
	
	protected int [] data;
	private static final int DEFAULT_SIZE= 10;
	
	int end=0; //pointer is pointing towards the end
				//we can add item first and then increase 'end'
				//so we can start 'end' at 0
	
	
	 public CustomQueue(int size) {
		 this.data= new int [size]; //if user passes a value for the length
		
	}
	 
	 public CustomQueue() {
		 this(DEFAULT_SIZE); //when nothing is passed use this default size.
	 }
	 
	
	 public boolean isFull() {
			
			return end==data.length; //returns true if pointer is at last index.
			
		}
		
	public boolean isEmpty() {
			
			return end==0; //if the pointer is at -1, then stack in empty
			
		}
	
	public boolean insert(int item) {
		
		if(isFull()) {
			return false;
		}
		
		data[end]=item;	//inserts number at end 
		end++; 			//then increments up
		return true;
	}
	
	public int remove() throws Exception {
		if (isEmpty()) {
			throw new Exception ("Queue is Empty!!");
			
		}
		int removed= data[0];  //removes first item 
		
		//shift elements to the left
		for (int i=1; i< end; i++) {
			data[i-1]=data[i];
		}
		end-- ;//end decreases because one item has been removed 
		
		return removed;
	}
	
	public int front() throws Exception{
		
		if (isEmpty()) {
			throw new Exception ("Queue is Empty!!");
			
		}
		return data[0];
	}
	
	public void display() {
		for(int i=0; i< end; i++) {
			System.out.print(data[i]+ " ");
		}
		System.out.println("END");
	}
	

	public static void main(String[] args) throws Exception {
		
		CustomQueue q= new CustomQueue(5);
		
		q.insert(4);
		q.insert(10);
		q.insert(6);
		q.insert(22);
		q.insert(87);
		
		q.display();
		
		System.out.println("Item removed: "+q.remove()); //first item should be removed 
		q.display(); // new queue
	}

}
