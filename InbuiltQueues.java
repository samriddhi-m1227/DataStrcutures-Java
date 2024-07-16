import java.util.LinkedList;
import java.util.Queue;

public class InbuiltQueues {

	public static void main(String[] args) {
		 Queue<Integer> q= new LinkedList<>();
		 
		 q.add(3);
		 q.add(6);
		 q.add(5);
		 q.add(19);
		 
		 System.out.println(q);
		 System.out.println(q.peek()); //returns value of the head queue/out removing

		 
		 
		 q.remove();
		 q.remove();
		 q.remove();
	
		 System.out.println(q);
	}

}
