 package cs211.puz;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Student uses a priority queue with the appropriate comparator 
 * in order to give priority needed for informed searches.
 * 
 * @author 
 *
 */
public class PqFrontier implements Frontier {
	protected PriorityQueue<TreeNode> pq = new PriorityQueue<TreeNode>();
	
	Comparator comp;
	
	public PqFrontier(Comparator comp) {
		this.comp = comp;
	}
	
	@Override
	public void addNode(TreeNode n) {
		pq.add(n);
	}

	@Override
	public TreeNode removeNode() {
		return pq.remove();
	}

	@Override
	public boolean containsState(PuzState pz) {
		TreeNode[] array = (TreeNode[]) pq.toArray();
		
		for(int i = 0; i <array.length -1; i++) {
			
			if(array[i].getState().equals(pz)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public TreeNode findNode(PuzState state) {
		
		TreeNode[] array = (TreeNode[]) pq.toArray();
		
		for(int i = 0; i <array.length -1; i++) {
			
			if(array[i].getState().equals(state)) {
				return array[i];
			}
		}
			return null;
	}

	@Override
	public boolean isEmpty() {
		
		return pq.size()==0;
	}



}
