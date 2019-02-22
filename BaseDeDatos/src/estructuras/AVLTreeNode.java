package estructuras;

import java.util.ArrayList;

public class AVLTreeNode <T extends Comparable,V> {

	private int balanceFactor;
	private AVLTreeNode<T,V> parent;
	private AVLTreeNode<T,V> left;
	private AVLTreeNode<T,V> right;
	private T value;
	private V dato;
	private LinkedList list;
	
	public AVLTreeNode()
	{
		
		parent = null;
		left = null;
		right = null;
		value = null;
		dato=null;
		balanceFactor = 0;
		list=null;
		
	}
	
	
	
	public int getBalanceFactor() {
		return balanceFactor;
	}



	public void setBalanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
	}



	public AVLTreeNode<T,V> insert(T newItem,V newDato){
		AVLTreeNode<T,V> added = null;
		if(isLeaf())
		{
			if(this.compareTo(newItem) < 0)
			{
				this.right = new AVLTreeNode<>();
				this.right.setValue(newItem);
				this.right.setDato(newDato);
				this.right.setParent(this);
				added = this.right;
			}
			else if(this.compareTo(newItem) > 0)
			{
				this.left = new AVLTreeNode<>();
				this.left.setValue(newItem);
				this.left.setDato(newDato);
				this.left.setParent(this);
				added = this.left;
			}else {
				AVLListNode a = new AVLListNode(String.valueOf(newItem), (Integer) newDato);
				if(this.list==null) {
					list = new LinkedList();
					list.insert(a);
				}else {
					list.insert(a);
				}
			}
		}
		else if(this.compareTo(newItem) < 0)
		{
			
			if(right == null)
			{
				this.right = new AVLTreeNode<>();
				this.right.setValue(newItem);
				this.right.setParent(this);
				added = this.right;
			}
			else
			{
				added = right.insert(newItem,newDato);
			}
		}
		else if(this.compareTo(newItem) > 0)
		{
			if(left == null)
			{
				this.left = new AVLTreeNode<>();
				this.left.setValue(newItem);
				this.left.setParent(this);
				added = this.left;
			}
			else
			{
				added = left.insert(newItem,newDato);
			}
		}else {
			AVLListNode a = new AVLListNode((String) newItem, (Integer) newDato);
			if(this.list==null) {
				list = new LinkedList();
				list.insert(a);
			}else {
				list.insert(a);
			}
		}
		
		return added;
	}
	
	public AVLTreeNode<T,V> delete(T searchKey)
	{
		if(isLeaf()) 
		{
			
			return null;
		}
        if( this.compareTo(searchKey) == 0)
        {
            if( left == null )
            {
                return right;
            }
            if( right == null )
            {
                return left;
            }
            // Localiza el menor contacto del subárbol derecho
            AVLTreeNode<T,V> successor = right.minimum();
            // Elimina del subárbol derecho el elemento que acaba de localizar
            right = right.delete( successor.getValue());
            // Deja el elemento localizado en la raíz del árbol de respuesta
            successor.left = left;
            successor.right = right;
            return successor;
        }
        else if( this.compareTo(searchKey) > 0 )
        {
            left = left.delete(searchKey);
        }
        else
        {
            right = right.delete(searchKey);
        }
        return this;
	}
	
	public int balanceFactor() {
		if(this.getRight()!= null && this.getLeft()!= null){
			
			balanceFactor = this.getRight().height()-this.getLeft().height();
		}else if(this.getRight()!=null){
			balanceFactor = this.getRight().height();
			
		}else if(this.getLeft()!=null){
			balanceFactor= -this.getLeft().height();
		}else{
			balanceFactor=0;
		}
		
		return balanceFactor;
	}
	
	public void preOrder(ArrayList<String> acum) {
		if(left!=null) {
			
			left.preOrder(acum);
		}
		acum.add(String.valueOf(value));
		if(list!=null) {
			acum.add(list.printList());
		}
		if(right!=null) {
			
			right.preOrder(acum);
		}
			
		
	}
	
	public void preOrderBal(String bal) {
		if(left!=null) {
			if(balanceFactor()<-1 && balanceFactor()>=1) bal = "No balanceado";
			left.preOrderBal(bal);
		}
		
		if(right!=null) {
			if(balanceFactor()<-1 && balanceFactor()>=1) bal = "No balanceado";
			right.preOrderBal(bal);
		}
	}
	
	public AVLTreeNode<T,V> getLeft() {
		return left;
	}

	public AVLTreeNode<T,V> getRight() {
		return right;
	}

	public void setLeft(AVLTreeNode<T,V> left) {
		this.left = left;
	}

	public void setRight(AVLTreeNode<T,V> right) {
		this.right = right;
	}
	
	public T getValue() {
		return value;
	}

	public V getDato(){
		return dato;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public void setDato(V dato){
		this.dato=dato;
	}
	public boolean isLeaf()
	{
		return this.left == null && this.right == null;
	}
	public AVLTreeNode<T,V> getParent() {
		return parent;
	}
	public void setParent(AVLTreeNode<T,V> parent) {
		this.parent = parent;
	}
	public AVLTreeNode<T,V> minimum()
	{
		return ( left == null ) ? this : left.minimum();
	}
	public AVLTreeNode<T,V> maximum()
	{
		 return ( right == null ) ? this : right.maximum();
	}
	public int weight( )
    {
        int p1 = ( left == null ) ? 0 : left.weight( );
        int p2 = ( right == null ) ? 0 : right.weight( );
        return 1 + p1 + p2;
    }
	public int height()
	{
		if(isLeaf())
            return 1;
        else
        {
            int a1 = ( left == null ) ? 0 : left.height( );
            int a2 = ( right == null ) ? 0 : right.height( );
            return 1 + Math.max( a1, a2 );
        }
	}
	public AVLTreeNode<T,V> search(T searchKey)
    {
        if( this.compareTo(searchKey) == 0 )
            return this;
        else if( this.compareTo(searchKey) > 0 )
            return ( left == null ) ? null : left.search(searchKey);
        else
            return ( right == null ) ? null : right.search(searchKey);
    }
	public int compareTo(T arg0) 
	{
		return this.value.compareTo(arg0);
	}



	public LinkedList getList() {
		return list;
	}



	public void setList(LinkedList list) {
		this.list = list;
	}
	
	
	
}
