package estructuras;

import java.util.ArrayList;

public class AVLTree <T extends Comparable,V>{

	private AVLTreeNode<T,V> root;
	
	public AVLTree(){
		root = null;
	}
	
	public V search(T searchKey){
		if(root!=null){
			return root.search(searchKey).getDato();
		}
		return null;
	}
	
	public String balanceado() {
		   ArrayList<String> a = new ArrayList<>();
		   String bal = "Balanceado";
		   if(root!=null) {
			   root.preOrder(a);
			   root.preOrderBal(bal);
		   }
		   String ret = a.toString()+"-"+bal;
		   
		   return ret;
		 }
	

	
	public void insert(T newItem,V newDato) {
		
		if(root!=null) {
			AVLTreeNode<T,V> x = root.insert(newItem, newDato);
			if(x!=null) {
			avlRebalance(x);
			}
		}else {
			root= new AVLTreeNode<>();
			root.setValue(newItem);
			root.setDato(newDato);
		}
		
	}
	
	public void avlRebalance(AVLTreeNode<T,V> x) {
		
		do{
		if(x.balanceFactor()==2) {
			if(x.getRight().balanceFactor()==1) { //caso A
				leftRotate(x);
			}else if(x.getRight().balanceFactor()==0) { //caso B
				leftRotate(x);
			}else if(x.getRight().balanceFactor()==-1){
				AVLTreeNode<T,V> p = x;
				rigthRotate(x.getRight());
				leftRotate(p);
			}
		}else if(x.balanceFactor()==-2){
			if(x.getLeft().balanceFactor()==1) { //caso C
				rigthRotate(x);
			}else if(x.getLeft().balanceFactor()==0) { //caso E
				rigthRotate(x);
			}else if(x.getLeft().balanceFactor()==-1){ //caso F
				AVLTreeNode<T,V> p = x;
				leftRotate(x.getLeft());
				rigthRotate(p);
			}
		}

		x=x.getParent();
		
		}while(x!=null);
		
		}
	
	public void delete(T searchKey) {
		if(root!=null) {
			if(root.isLeaf() && root.getValue().equals(searchKey)) {
				root=null;
			}else {
			AVLTreeNode<T,V> x = root.delete(searchKey);
			if(x!=null) {
				avlRebalance(x);
			
			}
			}
		}
	}
	
	public AVLTreeNode<T,V> getRoot() {
		return root;
	}

	public void setRoot(AVLTreeNode<T,V> root) {
		this.root = root;
	}

	public void leftRotate(AVLTreeNode<T,V> x)
	{
		
		AVLTreeNode<T,V> y = (AVLTreeNode<T,V>) x.getRight();
		if(y!=null){
		x.setRight(y.getLeft());
		if(y.getLeft()!=null) y.getLeft().setParent(x);
		y.setParent(x.getParent());
		if(x.getParent() == null)
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
		
	}
	public void rigthRotate(AVLTreeNode<T,V> x)
	{
		AVLTreeNode<T,V> y = (AVLTreeNode<T,V>) x.getLeft();
		if(y!=null){
		x.setLeft(y.getRight());
		if(y.getRight()!=null)y.getRight().setParent(x);
		
		y.setParent(x.getParent());
		if(x.getParent() == null){
			this.setRoot(y);
		}
		else if(x == x.getParent().getRight()){
			x.getParent().setRight(y);
		}
		else{
			x.getParent().setLeft(y);
		}
		y.setRight(x);
		x.setParent(y);
		}
	}
	
	
}
