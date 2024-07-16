import java.util.Stack;

public class InbuiltStack {

	
	
	public static void main(String[] args) {
		Stack <Integer> stack= new Stack<>();
		
		//stack
		stack.push(18);
		stack.push(4);
		stack.push(22);
		stack.push(10);
		
		System.out.println(stack);//print it 
		
		System.out.println(stack.pop());//return the topmost item 
		
		
	}

}
