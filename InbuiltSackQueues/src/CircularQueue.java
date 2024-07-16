
public class CircularQueue {
	

	protected int [] data;
	private static final int DEFAULT_SIZE= 10;
	
	protected int end=0; 
	protected int front=0;
	private int size=0;
	
	
	 public CircularQueue(int size) {
		 this.data= new int [size]; //if user passes a value for the length
		
	}
	 
	 public CircularQueue() {
		 this(DEFAULT_SIZE); //when nothing is passed use this default size.
	 }
	 
	
	 public boolean isFull() {
			
			return size==data.length; //returns true if pointer is at last index.
			
		}
		
	public boolean isEmpty() {
			
			return size==0; //if the pointer is at -1, then stack in empty
			
		}
	
public boolean insert(int item) {
		
		if(isFull()) {
			return false;
		}
		
		data[end]=item;	//inserts number at end 
		end++; 			//then increments up
		end= end% data.length;
		size++;
		return true;
	}
	
public int remove() throws Exception {
	if (isEmpty()) {
		throw new Exception ("Queue is Empty!!");
		
	}
	int removed= data[front];  //removes first item 
	front++;
	front=front%data.length;
	size--;
	return removed;
	
}
public int front() throws Exception{
	
	if (isEmpty()) {
		throw new Exception ("Queue is Empty!!");
		
	}
	return data[front];
}

public void display() {
	
	if (isEmpty()) {
		System.out.println("Empty");
	}
	int i=front;
	
	do {
		System.out.print(data[i]+ " ");
		i++;
		i%= data.length;
	}
	while(i!=end); {
		System.out.println("END");
	}
	}


	public static void main(String[] args) throws Exception {
		CircularQueue cq= new CircularQueue(5);
		
		cq.insert(4);
		cq.insert(10);
		cq.insert(6);
		cq.insert(22);
		cq.insert(87);
		
		cq.display();
		
		System.out.println("Item removed: "+cq.remove()); //first item should be removed 
		cq.insert(133);
		cq.display(); // updated queue
		
	}

}
