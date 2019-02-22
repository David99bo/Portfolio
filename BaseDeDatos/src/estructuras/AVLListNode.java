package estructuras;

public class AVLListNode {
	
	private AVLListNode next;
	
	private String value;
	private Integer dato;
	
	public AVLListNode(String newItem,Integer newDato){
		this.value=newItem;
		this.dato=newDato;
		next=null;
	}
	
	public void setNext(AVLListNode avlTreeNode){
		next= avlTreeNode;
	}
	
	public AVLListNode getNext(){
		return next;
	}
	
	public String getValue(){
		return value;
	}

	public Integer getDato() {
		return dato;
	}

	public void setDato(Integer dato) {
		this.dato = dato;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	
}
