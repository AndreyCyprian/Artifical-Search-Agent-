package cs211.puz;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class BfsAgent extends Agent {

	QueueFrontier qf = new QueueFrontier();

	TreeNode curr = new TreeNode(start);// tree node that will be initialized as my current state

	PuzState ps;// puzstate handle that i'll use to generate the next state from action
	
	Hashtable<TreeNode, PuzState> hash = new Hashtable<TreeNode, PuzState>();
	@Override
	public List<String> makePlan() throws InvalidActionException {
		List<TreeNode> nodeChildList = new ArrayList<TreeNode>();//array list to hold onto the children of each node 
		curr.setState(start);// setting current state to the start state
		String action;// action string variable using for for loop and store which action each puz
						// state is from

		while (!curr.getState().equals(goal)) {// keep going until we reach goal
			ps = curr.getState();// setting the puz state equal to the current state to use in next state
			for (int i = 0; i < Action.ALL.length; i++) {// for loop that will try all 4 actions available from each state
				action = Action.ALL[i];//sets action string to the current action in Action.All

				try {//I used a try catch block to catch whenever an exception is throw that way my code can run effciently 
					TreeNode n = new TreeNode(new PuzState(curr.getState().toString()).nextStateFromAction(action));//new tree node that will be a new puzzstate from nextstate of current 
					n.setParent(curr);//setting the parent of the tree node to current so that will i trace back for path from everything works just fine 
					n.setAction(action);//setting the action to a string for path form method 
					nodeChildList.add(n);//adding into the children list of all the moves possible from this one state 
					qf.addNode(n);//adding to frontier so we can remove the next one to try from
				} catch (InvalidActionException e) {
					//do nothing when exception is caught 
				}

			}

			curr.setChildren(new ArrayList<TreeNode>(nodeChildList));//setting the current nodes children to those that are already in the list 
			nodeChildList.clear();//clearing the list so that the next node wont have the children from the previous node
			curr = qf.removeNode();//takes the node with the lowest manhattan distance out of the queue
		}

		return this.pathFrom(curr);
	}

}
