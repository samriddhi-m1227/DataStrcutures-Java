import java.util.Arrays;

public class CustomStack {
	
	protected int [] data;
	private static final int DEFAULT_SIZE= 10;
	
	int ptr=-1;
	
	
	 public CustomStack(int size) {
		 this.data= new int [size]; //if user passes a value for the length
		
	}
	 
	 public CustomStack() {
		 this(DEFAULT_SIZE); //when nothing is passed use this default size.
	 }
	 
	 
	 public boolean push(int item) {
		 
		 if(isFull()) {
			 System.out.println("Stack is full!");
			 return false;	 
		 }
		 
		 //increase index by 1 
		 ptr++;
		 
		 //and insert item at that index.
		 data[ptr]=item;
		 
		 return true;
		 
	 }
	 
	 public int pop() throws Exception {
		 
		 if(isEmpty()) {
			throw new Exception ("Cannot pop from an Empty Stack!!");	
		 }
		 
		 int removed= data[ptr];  //removed item 
		 ptr--;                   //pointer decreases by 1
		 return removed;          //return value
	 }
	 
	 
public int peek() throws Exception { //returns topmost value, but wont remove it 
		 
		 if(isEmpty()) {
			throw new Exception ("Cannot peek from an Empty Stack!!");	
		 }
		 
		 int peeked= data[ptr];  //removed item                   
		 return peeked;         //return value
	 }
	

	public boolean isFull() {
		
		return ptr==data.length-1; //returns true if pointer is at last index.
		
	}
	
public boolean isEmpty() {
		
		return ptr==-1; //if the pointer is at -1, then stack in empty
		
	}

	public static void main(String[] args) throws Exception {
		CustomStack stack= new CustomStack(3);
		
		stack.push(5);
		stack.push(11);
		stack.push(20);
		
		
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
		//this will throw an exception:
		System.out.println(stack.pop());
	}

}
