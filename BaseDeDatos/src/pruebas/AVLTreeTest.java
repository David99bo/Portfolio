package pruebas;

import org.junit.Test;

import estructuras.AVLTree;
import junit.framework.TestCase;

public class AVLTreeTest extends TestCase{
		
	private AVLTree<Integer,Integer> tree;
	
	public void setUpEscenario1(){
		tree = new AVLTree<>();
	}
	
	public void setUpEscenario2(){
		tree = new AVLTree<>();
		tree.insert(2, 5);
		tree.insert(5, 5);
		tree.insert(7, 5);
		tree.insert(10, 5);
		tree.insert(15, 5);
	}
	
	@Test
	public void testInsert() {
		setUpEscenario1();
		tree.insert(2, 5);
		tree.insert(5, 5);
		tree.insert(7, 5);
		tree.insert(10, 5);
		tree.insert(15, 5);
		assertEquals(tree.balanceado(), "[2, 5, 7, 10, 15]-Balanceado");
		
		setUpEscenario2();
		tree.insert(16, 5);
		assertEquals(tree.balanceado(), "[2, 5, 7, 10, 15, 16]-Balanceado");
		
		setUpEscenario2();
		tree.insert(14, 5);
		assertEquals(tree.balanceado(), "[2, 5, 7, 10, 14, 15]-Balanceado");
		
		setUpEscenario2();
		tree.insert(15, 4);
		tree.insert(15, 3);
		assertEquals(tree.balanceado(), "[2, 5, 7, 10, 15, 15 15 ]-Balanceado");
		
	}
	
	@Test
	public void testEliminar() {
		setUpEscenario2();
		tree.delete(10);
		assertEquals(tree.balanceado(), "[2, 5, 7, 15]-Balanceado");
		
		setUpEscenario2();
		tree.insert(20, 10);
		tree.delete(15);
		try{
			assertEquals(tree.balanceado(), "[2, 5, 7, 10, 20]-Balanceado");
			tree.search(15);
			fail();
		}catch(NullPointerException e) {
			
		}
		
		setUpEscenario2();
		tree.delete(10);
		tree.delete(2);
		tree.delete(5);
		tree.delete(7);
		tree.delete(15);
		assertEquals(tree.getRoot(), null);
		
		
	}
	
	public void testBuscar() {
		setUpEscenario2();
		assertEquals(tree.search(10)+"", 5+"");
		
		setUpEscenario2();
		try {
			assertEquals(tree.search(16)+"", 4+"");
			fail();
		}catch(NullPointerException e) {
			
		}
		
		setUpEscenario1();
		assertEquals(tree.search(16), null);
		
	}
	
}
