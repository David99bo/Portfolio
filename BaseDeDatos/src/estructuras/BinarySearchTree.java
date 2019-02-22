package estructuras;

public class BinarySearchTree<T  extends Comparable> {
	
	private TreeNode<T> root;
	
	public BinarySearchTree()
	{
		root = null;
	}
	public TreeNode<T> insert(T newItem)
	{
		TreeNode<T> added = null;
		if(root != null)
		{
			added = root.insert(newItem);
		}
		else
		{
			root = new TreeNode<>();
			root.setValue(newItem);
			added = root;
		}
		return added;
	}
	public void deleteItem(T searchKey)
	{
		if(root != null)
		{
			root = root.delete(searchKey);
		}
	}
	public TreeNode<T> retrieve(T searchKey)
	{
		TreeNode<T> node = null;
		return node;
	}
	public TreeNode<T> getRoot() {
		return root;
	}
	public void setRoot(TreeNode<T> root) {
		this.root = root;
	}
	
}
