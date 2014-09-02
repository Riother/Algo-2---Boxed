package lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSGraphTraversal {
	
	Stack<Integer> stack;
	List<List<Integer>> visitedVertices;
	int mark;
	
	public DFSGraphTraversal() {
		stack = new Stack<Integer>();
		visitedVertices = new ArrayList<List<Integer>>();
		mark = 4;
	}
	
	public List<List<Integer>> traverse(Graph g) {
		stack.clear();
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
		
		stack.add(currentIndex);
		g.setMark(currentIndex, mark);
		
		int connectionValue = -1;
		for(int i = 0; i < g.degree(currentIndex); i++) {
			
			if(i == 0)
				connectionValue = g.getFirst(currentIndex);
			else
				connectionValue = g.getNext(currentIndex, connectionValue);
			
			if(g.getMark(connectionValue) != mark) {
				if(!stack.contains(connectionValue)) {
					connections = traverse(connectionValue, g, connections);
				}
			}
		}

		connections.add(currentIndex);
		return connections;
	}

}
