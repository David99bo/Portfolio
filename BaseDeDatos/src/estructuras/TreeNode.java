package estructuras;


public class TreeNode<T extends Comparable>{

	private TreeNode<T> parent;
	private TreeNode<T> left;
	private TreeNode<T> right;
	private T value;
	
	public TreeNode()
	{
		parent = null;
		left = null;
		right = null;
		value = null;
	}
	
	public TreeNode<T> insert(T newItem)
	{
		TreeNode<T> added = null;
		if(isLeaf())
		{
			if(this.compareTo(newItem) <= 0)
			{
				this.right = new TreeNode<>();
				this.right.setValue(newItem);
				this.right.setParent(this);
				added = this.right;
			}
			else if(this.compareTo(newItem) > 0)
			{
				this.left = new TreeNode<>();
				this.left.setValue(newItem);
				this.left.setParent(this);
				added = this.left;
			}
		}
		else if(this.compareTo(newItem) <= 0)
		{
			 added = right.insert(newItem);
		}
		else if(this.compareTo(newItem) > 0)
		{
			 added = left.insert(newItem);
		}
		return added;
	}
	public TreeNode<T> delete(T searchKey)
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
            TreeNode<T> successor = right.minimum();
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
	public TreeNode<T> getLeft() {
		return left;
	}

	public TreeNode<T> getRight() {
		return right;
	}

	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	public void setRight(TreeNode<T> right) {
		this.right = right;
	}
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	public boolean isLeaf()
	{
		return this.left == null && this.right == null;
	}
	public TreeNode<T> getParent() {
		return parent;
	}
	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}
	public TreeNode<T> minimum()
	{
		return ( left == null ) ? this : left.minimum();
	}
	public TreeNode<T> maximum()
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
	public TreeNode<T> search(T searchKey)
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
}
