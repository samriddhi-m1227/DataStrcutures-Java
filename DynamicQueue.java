
public class DynamicQueue extends CircularQueue {
	
	
	public DynamicQueue() {
		super();
	}
	
	public DynamicQueue(int size) {
		super(size);
	}
@Override	
public boolean insert(int item) {
	
	 //this takes care of it being full
	
	 if(isFull()) {
		 
		//double the array size 
		int [] temp= new int[data.length*2];
		
		//copy all previous items into new data 
		for (int i=0; i<data.length; i++) {
			temp[i]=data[(front+ i) % data.length];
		}
		front =0;
		end=data.length;
		data=temp;
}
//takes care of pushing new element since array is NOT full
	 return super.insert(item);

}

}