package estructuras;

public class LinkedList {
	
	private AVLListNode first;
	
	public LinkedList(){
		first=null;
	}

	public boolean isEmpty() {
		return first==null;
	}

	public void insert(AVLListNode avlListNode) {
		// TODO Auto-generated method stub
		AVLListNode nuevo=avlListNode;
		if(isEmpty()){
			first=nuevo;
		}
		else{
			AVLListNode actual = first;
			while(actual.getNext()!=null) {
				actual = actual.getNext();
			}
			actual.setNext(avlListNode);
		}
		
		
	}
	
	public String printList() {
	String cad = "";
	AVLListNode actual = first;
	while(actual!=null) {
	cad+=actual.getValue()+" ";
	actual=actual.getNext();
	}
	return cad;
	}

	public AVLListNode getFirst() {
		return first;
	}

	public void setFirst(AVLListNode first) {
		this.first = first;
	}
	
	
	
}
