
public class DynamicStack extends CustomStack {
	
	public DynamicStack() {
		super(); //calls CustomStack
	}
	
	public DynamicStack(int size) {
		super(size); //calls CustomStack(int size)
	}
	
	
	@Override 
	public boolean push(int item) {
		 //this takes care of it being full
		
		 if(isFull()) {
			 
			//double the array size 
			int [] temp= new int[data.length*2];
			
			//copy all previous items into new data 
			for (int i=0; i<data.length; i++) {
				temp[i]=data[i];
			}
		 data=temp;
	 }
	 //takes care of pushing new element since array is NOT full
		 return super.push(item);
	
	}
	
}
