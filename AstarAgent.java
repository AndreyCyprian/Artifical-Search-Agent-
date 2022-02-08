package cs211.puz;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

public class AstarAgent extends Agent {
	
	Hashtable<TreeNode, PuzState> hash = new Hashtable<TreeNode, PuzState>();//hash table that will be used to store the visited states

	
	/**This comparator method will take in two nodes and get the cost of and the cost of the other
	 * while also comparing the distance traveled to make sure that we are preforming a proper Astar search
	 * and not something like a greedy best first search 
	 */
    Comparator<TreeNode> comparator = new Comparator<>() {
        @Override
        public int compare(TreeNode tree1, TreeNode tree2) {

            if((tree1).getCost() + distanceTraveled < (tree2).getCost() + distanceTraveled) {   
                return -1;
            }
            else if((tree2).getCost() + distanceTraveled < ( tree1).getCost() + distanceTraveled) {    
                return 1;
            }
            return 0;
        }
    };


    PqFrontier prioQueueFrontier = new PqFrontier(comparator);//priority queue using to store the node and will be taken out bases on shortest distance 

    TreeNode curr = new TreeNode(start);//initializing my current node 
    double distanceTraveled = 0;//initializing my distance traveled variable that i will use in the comparator 
    PuzState puzzle;//puz state handle for generating new state from action 

    @Override
    public List<String> makePlan() throws InvalidActionException{
        List<TreeNode> nodeChildList = new ArrayList<TreeNode>();//array list to hold onto the children of each node 
        Point point = new Point(goal.blankCol(), goal.blankRow());//point variable that will be for the manhatttan distance calculation 
        curr.setState(start);// setting current state to the start state
        String action;// action string variable using for for loop and store which action each puz
		// state is from

        while(!curr.getState().equals(goal)) {// keep going until we reach goal
            puzzle = curr.getState();// setting the puz state equal to the current state to use in next state
            distanceTraveled += curr.getCost();//adding on the distance traveled for each node that we go to pasted the start state 
            for (int i = 0; i < Action.ALL.length; i++) {// for loop that will try all 4 actions available from each state
                action = Action.ALL[i];//sets action string to the current action in Action.All

                try {
                    TreeNode n = new TreeNode(new PuzState(curr.getState().toString()).nextStateFromAction(action));//new tree node that will be a new puzzstate from nextstate of current 
                    Point currPoint = new Point(n.getState().blankCol(), n.getState().blankRow());//creating new point for manhattan distance 
                    n.setCost(Math.abs(currPoint.x - point.x)+ Math.abs(currPoint.y - point.y));
                    if(!hash.contains(n.getState())) {//if the hash table does not contain it means its not visited so add to queue and put in hash
                    	hash.put(n, n.getState());
                        prioQueueFrontier.addNode(n);//adding to frontier so we can remove the next one to try from
                    }
                    n.setParent(curr);//setting the parent of the tree node to current so that will i trace back for path from everything works just fine 
                    n.setAction(action);//setting the action to a string for path form method 
                    nodeChildList.add(n);//adding into the children list of all the moves possible from this one state 
                } catch (InvalidActionException e) {

                }

            }
            curr.setChildren(new ArrayList<TreeNode>(nodeChildList));//setting the current nodes children to those that are already in the list 
            nodeChildList.clear();//clearing the list so that the next node wont have the children from the previous node 
            curr = prioQueueFrontier.removeNode();//takes the node with the lowest manhattan distance out of the queue 

        }

        return this.pathFrom(curr);
    }





}