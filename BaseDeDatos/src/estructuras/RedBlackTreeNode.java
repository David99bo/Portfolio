package estructuras;

import java.util.ArrayList;

public class RedBlackTreeNode<T extends Comparable,V>{

	private RedBlackTreeNode<T,V> parent;
	private RedBlackTreeNode<T,V> left;
	private RedBlackTreeNode<T,V> right;
	private T value;
	private V dato;
	private boolean color;
	
	public RedBlackTreeNode(boolean color)
	{
		parent = null;
		left = null;
		right = null;
		value = null;
		dato=null;
		this.color = color; 
	}
//	public RedBlackTreeNode<T,V> insert(T newItem, V newDato)
//	{
//		RedBlackTreeNode<T,V> added = null;
//		if(isLeaf())
//		{
//			if(this.compareTo(newItem) <= 0)
//			{
//				this.right = new RedBlackTreeNode<>(RedBlackTree.RED);
//				this.right.setValue(newItem);
//				this.right.setDato(newDato);
//				this.right.setParent(this);
//				added = this.right;
//			}
//			else if(this.compareTo(newItem) > 0)
//			{
//				this.left = new RedBlackTreeNode<>(RedBlackTree.RED);
//				this.left.setValue(newItem);
//				this.left.setDato(newDato);
//				this.left.setParent(this);
//				added = this.left;
//			}
//		}
//		else if(this.compareTo(newItem) <= 0)
//		{
//			if(right == null || right.getValue() == null)
//			{
//				this.right = new RedBlackTreeNode<>(RedBlackTree.RED);
//				this.right.setValue(newItem);
//				this.right.setDato(newDato);
//				this.right.setParent(this);
//				added = this.right;
//			}
//			else 
//			{
//				added = right.insert(newItem,newDato);
//			}
//		}
//		else if(this.compareTo(newItem) > 0)
//		{
//			if(left == null || left.getValue() == null)
//			{
//				this.left = new RedBlackTreeNode<>(RedBlackTree.RED);
//				this.left.setValue(newItem);
//				this.left.setDato(newDato);
//				this.left.setParent(this);
//				added = this.left;
//			}
//			else
//			{
//				added = left.insert(newItem,newDato);
//			}
//		}
//		
//		return added;
//	}
	public RedBlackTreeNode<T,V> delete(T searchKey)
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
            RedBlackTreeNode<T,V> successor = right.minimum();
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
	
	public RedBlackTreeNode<T,V> getLeft() {
		return left;
	}

	public RedBlackTreeNode<T,V> getRight() {
		return right;
	}

	public void setLeft(RedBlackTreeNode<T,V> left) {
		this.left = left;
	}

	public void setRight(RedBlackTreeNode<T,V> right) {
		this.right = right;
	}
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public void setDato(V dato){
		this.dato=dato;
	}
	
	public V getDato(){
		return dato;
	}
	public boolean isLeaf()
	{
		boolean resultado = false;
		if(left == null)
		{
			if(right == null)
			{
				resultado = true;
			}
			else if(right.getValue() == null);
			{
				resultado = true;
			}
		}
		else if(right == null)
		{
			if(left == null)
			{
				resultado = true;
			}
			else if(left.getValue() == null)
			{
				resultado = true;
			}
		}
		else if(left == right)
		{
			resultado = true;
		}
		
		return resultado;
	}
	public RedBlackTreeNode<T,V> getParent() {
		return parent;
	}
	public void setParent(RedBlackTreeNode<T,V> parent) {
		this.parent = parent;
	}
	public RedBlackTreeNode<T,V> minimum()
	{
		return ( left == null ) ? this : left.minimum();
	}
	public RedBlackTreeNode<T,V> maximum()
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
	public RedBlackTreeNode<T,V> search(T searchKey)
    {
        if( this.compareTo(searchKey) == 0 ){
        	System.out.println(searchKey);
            return this;
        }
        else if( this.compareTo(searchKey) > 0 )
            return ( left == null ) ? null : left.search(searchKey);
        else
            return ( right == null ) ? null : right.search(searchKey);
    }
	public void inorden(ArrayList<T> collection)
	{
		if(left != null)
		{
			left.inorden(collection);
		}
		collection.add(this.getValue());
		if(right != null)
		{
			right.inorden(collection);
		}
	}
	public int compareTo(T arg0) 
	{
		return this.value.compareTo(arg0);
	}
	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

}
