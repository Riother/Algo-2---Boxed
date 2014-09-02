package lab3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BFSGraphTraversal {
	
	Queue<Integer> queue;
	List<List<Integer>> visitedVertices;
	int mark;
	
	public BFSGraphTraversal() {
		queue = new LinkedList<Integer>();
		visitedVertices = new ArrayList<List<Integer>>();
		mark = 5;
	}
	
	public List<List<Integer>> traverse(Graph g) {
		queue.clear();
		visitedVertices.clear();
		
		for(int i = 0; i < g.vertexCount(); i++) {
			if(g.getMark(i) != mark) {
				List<Integer> connections = new ArrayList<Integer>();
				visitedVertices.add(traverse(i, g, connections));
			}
		}
		
		return visitedVertices;
	}
	
	public void changeMark(int newMark) {
		mark = newMark;
	}
	
	private List<Integer> traverse(int currentIndex, Graph g, List<Integer> connections) {
		
		g.setMark(currentIndex, mark);
		connections.add(currentIndex);
		
		int connectionValue = -1;
		for(int i = 0; i < g.degree(currentIndex); i++) {
		
			connectionValue = g.getNext(currentIndex, connectionValue);
			
			if(g.getMark(connectionValue) != mark) {
				if(!queue.contains(connectionValue))
					queue.add(connectionValue);
			}
		}
	
		if(!queue.isEmpty()) {
			int nextValue = queue.poll();
			g.setMark(nextValue, mark);
			return traverse(nextValue, g, connections);
		}
		
		return connections;
	}
	
}
