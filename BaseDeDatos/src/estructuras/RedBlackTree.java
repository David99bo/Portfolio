package estructuras;

import java.awt.Color;
import java.util.ArrayList;

public class RedBlackTree<T extends Comparable,V>{
	
	public static final boolean RED = true;
	public static final boolean BLACK = false;
	
	private RedBlackTreeNode<T,V> root;
	private RedBlackTreeNode<T,V> centry;
	
	public RedBlackTree()
	{
		root = null;
		centry = new RedBlackTreeNode<>(BLACK);
	}
	public void insert(T newItem, V newDato)
	{
		
		if(root != null)
		{
		 	RedBlackTreeNode<T, V> y = centry;
			RedBlackTreeNode<T, V> x = root;
			RedBlackTreeNode<T, V> z = new RedBlackTreeNode<>(RED);
			z.setValue(newItem);
			z.setDato(newDato);
			while (x != centry) 
			{
				y = x;
				if(x.compareTo(z.getValue()) > 0)
				{
					x = x.getLeft();
				}
				else
				{
					x = x.getRight();
				}
			}
			z.setParent(y);
			if(y == centry)
			{
				root = z;
			}
			else
			{
				if(y.compareTo(z.getValue()) > 0)
				{
					y.setLeft(z);
				}
				else
				{
					y.setRight(z);
				}
			}
			z.setLeft(centry);
			z.setRight(centry);
			z.setColor(RED);
			RBInsertFixUp(z);
		}
		else
		{
			root = new RedBlackTreeNode<>(BLACK);
			root.setValue(newItem);
			root.setDato(newDato);
			root.setParent(centry);
			root.setLeft(centry);
			root.setRight(centry);
		}
	}
	public void RBInsertFixUp(RedBlackTreeNode<T,V> z)
	{
		while (z!=root && z.getParent().isColor() == RED) 
		{
			if(z.getParent() == z.getParent().getParent().getLeft())
			{
				RedBlackTreeNode<T,V> y = z.getParent().getParent().getRight();
				if(y.isColor() == RED)
				{
					z.getParent().setColor(BLACK);
					y.setColor(BLACK);
					z.getParent().getParent().setColor(RedBlackTree.RED);
					z = z.getParent().getParent();
				}
				else
				{
					if(z == z.getParent().getRight())
					{
						z = z.getParent();
						leftRotate(z);
					}
					z.getParent().setColor(BLACK);
					z.getParent().getParent().setColor(RED);
					rigthRotate(z.getParent().getParent());
				}
			}
			else{
				RedBlackTreeNode<T,V> y = z.getParent().getParent().getLeft();
				if(y.isColor() == RED)
				{
					z.getParent().setColor(BLACK);
					y.setColor(BLACK);
					z.getParent().getParent().setColor(RedBlackTree.RED);
					z = z.getParent().getParent();
				}
				else
				{
					if(z == z.getParent().getLeft())
					{
						z = z.getParent();
						rigthRotate(z);
					}
					z.getParent().setColor(BLACK);
					z.getParent().getParent().setColor(RED);
					leftRotate(z.getParent().getParent());
				}
			}
		}
		root.setColor(BLACK);
	}
	public void deleteItem(T searchKey)
	{
		RedBlackTreeNode<T, V> z = search(searchKey);
		RedBlackTreeNode<T, V> x = centry;
		RedBlackTreeNode<T, V> y = centry;
		if(z.getLeft() == centry || z.getRight() == centry)
		{
			y = z;
		}
		else
		{
			y = z.getRight().minimum();
		}
		if(y.getLeft() != centry)
		{
			x = y.getLeft();
		}
		else
		{
			x = y.getRight();
		}
		x.setParent(y.getParent());
		if(y.getParent() == centry)
		{
			root = x;
		}
		else if(y.getParent().getLeft() != centry && y.getParent().getLeft() == y)
		{
			y.getParent().setLeft(x);
		}
		else if(y.getParent().getRight() != centry && y.getParent().getRight() == y)
		{
			y.getParent().setRight(x);
		}
		if(y != z)
		{
			z.setValue(y.getValue());
		}
		if(y.isColor() == BLACK)
		{
			RBDeleteFixUp(x);
		}
	}
	public void RBDeleteFixUp(RedBlackTreeNode<T,V> x)
	{
		while (x != getRoot() && x.isColor() == BLACK) 
		{
			if(x == x.getParent().getLeft())
			{
				RedBlackTreeNode<T,V> w = x.getParent().getRight();
				if(w.isColor() == RED)
				{
					w.setColor(BLACK);
					x.getParent().setColor(RED);
					leftRotate(x.getParent());
					w = x.getParent().getRight();
				}
				if(w.getLeft().isColor() == BLACK && w.getRight().isColor() == BLACK)
				{
					w.setColor(RED);
					x = x.getParent();
				}
				else
				{
					if(w.getRight().isColor() == BLACK)
					{
						w.getLeft().setColor(BLACK);
						w.setColor(RED);
						rigthRotate(w);
						w = x.getParent().getRight();
					}
					w.setColor(x.getParent().isColor());
					x.getParent().setColor(BLACK);
					w.getRight().setColor(BLACK);
					leftRotate(x.getParent());
					x = getRoot();
				}
			}
			else
			{
				if(x == x.getParent().getRight())
				{
					RedBlackTreeNode<T,V> w = x.getParent().getLeft();
					if(w.isColor() == RED)
					{
						w.setColor(BLACK);
						x.getParent().setColor(RED);
						rigthRotate(x.getParent());
						w = x.getParent().getLeft();
					}
					if(w.getRight().isColor() == BLACK && w.getLeft().isColor() == BLACK)
					{
						w.setColor(RED);
						x = x.getParent();
					}
					else
					{
						if(w.getLeft().isColor() == BLACK)
						{
							w.getRight().setColor(BLACK);
							w.setColor(RED);
							leftRotate(w);
							w = x.getParent().getLeft();
						}
						w.setColor(x.getParent().isColor());
						x.getParent().setColor(BLACK);
						w.getLeft().setColor(BLACK);
						rigthRotate(x.getParent());
						x = getRoot();
					}
				}
			}
		}
		x.setColor(BLACK);
	}
	public RedBlackTreeNode<T, V> search(T searchKey)
	{
       RedBlackTreeNode<T, V> actual = root;
       while (actual != centry)
       {
    	   if(actual.compareTo(searchKey) == 0)
    	   {
    		   return actual;
    	   }
    	   else if(actual.compareTo(searchKey) < 0)
    	   {
    		   actual = actual.getRight();
    	   }
    	   else
    	   {
    		   actual = actual.getLeft();
    	   }
       }
       System.out.println("No encontrado");
       return null;
	}
	public RedBlackTreeNode<T,V> getRoot() {
		return root;
	}
	public void leftRotate(RedBlackTreeNode<T,V> x)
	{
		RedBlackTreeNode<T,V> y = (RedBlackTreeNode<T,V>) x.getRight();
		x.setRight(y.getLeft());
		y.getLeft().setParent(x);
		if(y.getLeft() != centry)
		{
			y.getLeft().setParent(x);
		}
		y.setParent(x.getParent());
		if(x.getParent() == centry)
		{
			this.setRoot(y);
		}
		else if(x == x.getParent().getLeft())
		{
			x.getParent().setLeft(y);
		}
		else
		{
			x.getParent().setRight(y);
		}
		y.setLeft(x);
		x.setParent(y);
	}
	public void rigthRotate(RedBlackTreeNode<T,V> y)
	{
		RedBlackTreeNode<T,V> x = (RedBlackTreeNode<T,V>) y.getLeft();
		y.setLeft(x.getRight());
		if(x.getRight() != centry)
		{
			x.getRight().setParent(y);
		}
		x.setParent(y.getParent());
		if(y.getParent() == centry)
		{
			this.setRoot(x);
		}
		else if(y == y.getParent().getRight())
		{
			y.getParent().setRight(x);
		}
		else
		{
			y.getParent().setLeft(x);
		}
		x.setRight(y);
		y.setParent(x);
	}
	public ArrayList<T> inorden()
	{
		ArrayList<T> collection = new ArrayList<>();
		if(root != null)
		{
			root.inorden(collection);
		}
		return collection;
	}
	public void setRoot(RedBlackTreeNode<T,V> root) {
		this.root = root;
	}
	
}
