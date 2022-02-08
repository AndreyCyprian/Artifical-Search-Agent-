package cs221.test;


import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;


import cs211.puz.Frontier;
import cs211.puz.PuzState;
import cs211.puz.StackFrontier;
import cs211.puz.TreeNode;

public class StackFrontierTest {
	
	PuzState psA;  
	PuzState psB;
	PuzState psC; 
	
	TreeNode tnA;
	TreeNode tnB;
	TreeNode tnC;
	
	
	@Before
	public void setUpStates () {
		psA = new PuzState("23615*478");
		psB =  new PuzState("12345678*");
		psC = new PuzState("*12345678");
		
		tnA = new TreeNode(psA);
		tnB = new TreeNode(psB);
		tnC = new TreeNode(psC);
	}

	
	/* ================================================================
	 * is Empty
	 */
	
	@Test
	public void test_StackFrontier_isEmpty_whenEmpty() {
		Frontier fringe = new StackFrontier();
		assertTrue(fringe.isEmpty());
	}

	@Test
	public void test_StackFrontier_isEmpty_whenNotEmpty() {
		Frontier fringe = new StackFrontier();
		assertTrue(fringe.isEmpty());
		
		fringe.addNode(tnA);   // add a node
		assertFalse(fringe.isEmpty());
		
		fringe.addNode(tnA);   // add a node
		assertFalse(fringe.isEmpty());

	}
	
	/* ================================================================
	 * remove Node - if frontier is not empty, should return the last
	 * node in.   Otherwise, should throw an exception.
	 */
	
	
	@Test
	public void test_StackFrontier_removeNode_whenNotEmpty() {
		Frontier fringe = new StackFrontier();
		
		fringe.addNode(tnA);
		fringe.addNode(tnB);
		fringe.addNode(tnC);   // last one in should be the first one out
		
		assertSame(tnC, fringe.removeNode());
	}

	
	@Test(expected=NoSuchElementException.class)
	public void test_StackFrontier_removeNode_whenEmpty() {
		Frontier fringe = new StackFrontier();
		
		assertTrue(fringe.isEmpty());
		
		assertNull( fringe.removeNode());
	}

	
	
	/* ================================================================
	 * findState
	 */
	
	@Test
	public void test_StackFrontier_findNode_whenPresent() {
		Frontier fringe = new StackFrontier();
		
		fringe.addNode(tnA);
		fringe.addNode(tnB);
		fringe.addNode(tnC);
		
		assertNotNull(fringe.findNode(tnA.getState()));
		assertSame(tnA, fringe.findNode(tnA.getState()));
		
	}
	
	
	@Test
	public void test_StackFrontier_findNode_whenAbsent() {
		Frontier fringe = new StackFrontier();
		
		// fringe.addNode(tnA);
		fringe.addNode(tnB);
		fringe.addNode(tnC);
		
		assertNull(fringe.findNode(tnA.getState()));  // tnA state not present, should be null
		
	}
	
	
	
	/* ================================================================
	 * constainsState
	 */
	
	@Test
	public void test_StackFrontier_constainsState_whenPresent() {
		Frontier fringe = new StackFrontier();
		
		fringe.addNode(tnA);
		fringe.addNode(tnB);
		fringe.addNode(tnC);
		
		assertTrue(fringe.containsState(psA));
	}
	
	
	
	@Test
	public void test_StackFrontier_constainsState_whenAbsent() {
		Frontier fringe = new StackFrontier();
		
		// fringe.addNode(tnA);
		fringe.addNode(tnB);
		fringe.addNode(tnC);
		
		assertFalse(fringe.containsState(psA));
	}
	


}
