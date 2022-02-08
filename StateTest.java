package cs221.test;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import cs211.puz.Action;
import cs211.puz.AstarAgent;
import cs211.puz.BfsAgent;
import cs211.puz.InvalidActionException;
import cs211.puz.PuzState;

public class StateTest {

	@Test
	public void testCreate() {
		
		PuzState s = new PuzState("12345678*");
		assertNotNull(s);
		assertEquals("12345678*", s.toString());
	}

	
	@Test
	public void testStateEquals_whenIdenticalTiles() {
		PuzState s1 = new PuzState("1234*5678");
		PuzState s2 = new PuzState("1234*5678");
		
		assertTrue(s1.equals(s2));
		
	}
	
	@Test
	public void testStateEquals_whenTilesDifferent() {
		PuzState s1 = new PuzState("1234*5678");
		PuzState s2 = new PuzState("12348567*");
		
		assertFalse(s1.equals(s2));
		
	}
	
	
	/*
	 * Make sure our PuzState object can work in Java Collections
	 * Framework
	 */
	@Test
	public void testState_whenUsingHashSet() {
		
		PuzState s0 = new PuzState("123*45678");
		PuzState s1 = new PuzState("1234*5678");
		PuzState s2 = new PuzState("12345*678");
		PuzState s3 = new PuzState("123456*78");

		HashSet<PuzState> set = new HashSet<>();
		set.add(s0);
		set.add(s1);
		set.add(s2);
		// s3 not added on purpose
		
		
		assertTrue(set.contains(s0));   // should be there
		
		assertFalse(set.contains(s3));  // should not be there
		
	}
	
	
	
	
	@Test
	public void testDisplayString() {
		
		PuzState s = new PuzState("12345678*");
		System.err.println(s.displayString());
		assertEquals(
				"+---+\n" + 
				"|123|\n" + 
				"|456|\n" + 
				"|78*|\n" + 
				"+---+\n"
				, s.displayString());
		
	}
	
	
	@Test
	public void testBlankRow_whenBlankOnBottom() {
		
		PuzState s = new PuzState("12345678*");
		assertEquals(2,s.blankRow());

		PuzState s1 = new PuzState("1234567*8");
		assertEquals(2,s1.blankRow());

		PuzState s2 = new PuzState("123456*78");
		assertEquals(2,s1.blankRow());

	}

	@Test
	public void testBlankRow_whenBlankOnTop() {
		
		PuzState s = new PuzState("*12345678");
		assertEquals(0,s.blankRow());

		PuzState s1 = new PuzState("1*2345678");
		assertEquals(0,s1.blankRow());

		PuzState s2 = new PuzState("12*345678");
		assertEquals(0,s1.blankRow());

	}

	
	@Test
	public void testBlankRow_whenBlankInMiddle() {
		
		PuzState s = new PuzState("123*45678");
		assertEquals(1,s.blankRow());

		PuzState s1 = new PuzState("1234*5678");
		assertEquals(1,s1.blankRow());

		PuzState s2 = new PuzState("12345*678");
		assertEquals(1,s1.blankRow());

	}
	
	
	/* ============================================================================
	 * Add more junit tests here to fully test your nextStateFromAction algorithm.
	 * 
	 */
	
	@Test
	public void testNextStateFromAction_whenActionIsUpOnTopRow() throws Exception {
		PuzState s = new PuzState("1234*5678");
		
		PuzState ns = s.nextStateFromAction(Action.UP);
		
		// 2 moves down, blank moves to top row
		assertEquals("1*3425678", ns.toString());
		
	}
	
	@Test 
	public void testNextStateFromAction_whenActionMovesToBottomRow() throws Exception {
		PuzState s = new PuzState("1234*5678");
		
		PuzState ns = s.nextStateFromAction(Action.DOWN);
		
		//7 moves up, blank moves to bottom row
		assertEquals("1234756*8", ns.toString());
		
	}

	@Test 
	public void testNextStateFromAction_whenActionMovesToRightColumn() throws Exception {
		PuzState s = new PuzState("1234*5678");
		
		PuzState ns = s.nextStateFromAction(Action.RIGHT);
		
		//5 moves left, blank moves to right column
		assertEquals("12345*678", ns.toString());
		
	}
	
	@Test 
	public void testNextStateFromAction_whenActionMovesToLeftColumn() throws Exception {
		PuzState s = new PuzState("1234*5678");
		
		PuzState ns = s.nextStateFromAction(Action.LEFT);
		
		//4 moves right, blank moves to the left column
		assertEquals("123*45678", ns.toString());
		
	} 
	
	@Test
	public void BfsAgentTest() throws InvalidActionException {
		PuzState s = new PuzState("1234*5678");
		PuzState goal = new PuzState("12345*678");

		BfsAgent bf = new BfsAgent();
		bf.setStartState(s);
		bf.setGoalState(goal);
		bf.makePlan();
		
		
	}
	
	@Test 
	public void AstarAgentTest() throws InvalidActionException{
		PuzState s = new PuzState("5432*6789");
		PuzState g = new PuzState("45368*792");
		
		AstarAgent star = new AstarAgent();
		star.setStartState(s);
		star.setGoalState(g);
		star.makePlan();
		
	}
	
	
	
}
