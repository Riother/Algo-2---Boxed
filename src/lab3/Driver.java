package lab3;

import java.util.List;

public class Driver {

	public static void main(String[] args) {
		
		Graph g = new Graph(9);
		
		g.addEdge(0, 1, 0);
		g.addEdge(0, 2, 0);
		g.addEdge(0, 3, 0);
		g.addEdge(0, 4, 0);
		g.addEdge(1, 3, 0);
		g.addEdge(1, 5, 0);
		g.addEdge(2, 6, 0);
		g.addEdge(3, 5, 0);
		g.addEdge(4, 6, 0);
		
		g.addEdge(7, 8, 0);
		
		BFSGraphTraversal bfs = new BFSGraphTraversal();
		
		List<List<Integer>> BFSforest = bfs.traverse(g);
		
		for(int i = 0; i < BFSforest.size(); i++) {
			System.out.println("Tree " + i + ": ");
			for(int j = 0; j < BFSforest.get(i).size(); j++) {
				System.out.print(BFSforest.get(i).get(j) + ", ");
			}
			System.out.println();
		}
		
		System.out.println();
		//------------------------------------
		System.out.println();
		
		DFSGraphTraversal dfs = new DFSGraphTraversal();
		
		List<List<Integer>> DFSforest = dfs.traverse(g);
		
		for(int i = 0; i < DFSforest.size(); i++) {
			System.out.println("Tree " + i + ": ");
			for(int j = 0; j < DFSforest.get(i).size(); j++) {
				System.out.print(DFSforest.get(i).get(j) + ", ");
			}
			System.out.println();
		}
		
		//testDoubleCrosses();
		//testCycles();
		testChains();

	}
	
	public static void testDoubleCrosses() {
		DotsAndBoxes game = new DotsAndBoxes(5, 5);
		game.displayBoard();
		
		if(game.drawLine(1, 0, 0, 1, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 0, 2, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 0, 3, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 0, 4, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 0, 0, 1) != -1) 
			game.displayBoard();
		
		if(game.drawLine(1, 0, 1, 0, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 2, 0, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 3, 1, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 3, 2, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 3, 2, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 2, 2, 1) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 1, 2, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 3, 3, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 3, 4, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 4, 3, 4, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 4, 2, 4, 1) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 4, 1, 4, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 1, 1, 1) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 1, 2, 1) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 1, 3, 1) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 1, 4, 1) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 2, 1, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 2, 2, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 2, 3, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 2, 4, 2) != -1)
			game.displayBoard();
		
		System.out.println("Double Crosses: " + game.countDoubleCrosses());
	}

	public static void testCycles() {
		DotsAndBoxes game = new DotsAndBoxes(6, 6);
		game.displayBoard();
		
		if(game.drawLine(1, 0, 0, 1, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 0, 2, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 0, 3, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 0, 4, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 0, 0, 1) != -1) 
			game.displayBoard();
		
		if(game.drawLine(1, 0, 1, 0, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 2, 0, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 3, 1, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 3, 2, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 3, 3, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 3, 4, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 4, 3, 4, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 4, 2, 4, 1) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 4, 1, 4, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 1, 2, 1) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 1, 3, 1) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 2, 2, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 2, 3, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 1, 1, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 1, 3, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 4, 3, 5, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 5, 3, 5, 4) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 5, 4, 5, 5) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 5, 5, 4, 5) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 4, 5, 3, 5) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 5, 3, 4) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 4, 3, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 3, 0, 4) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 4, 0, 5) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 5, 1, 5) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 5, 2, 5) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 5, 3, 5) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 4, 2, 4) != -1)
			game.displayBoard();
		
		System.out.println("Cycles: " + game.countCycles());
		System.out.println("Double Crosses: " + game.countDoubleCrosses());
	}

	public static void testChains() {
		DotsAndBoxes game = new DotsAndBoxes(6, 6);
		game.displayBoard();
		
		if(game.drawLine(1, 0, 0, 1, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 0, 2, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 0, 3, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 0, 4, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 0, 0, 1) != -1) 
			game.displayBoard();
		
		if(game.drawLine(1, 0, 1, 0, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 2, 0, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 3, 1, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 3, 2, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 3, 3, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 3, 4, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 4, 3, 4, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 4, 2, 4, 1) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 4, 1, 4, 0) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 1, 2, 1) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 1, 3, 1) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 2, 2, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 2, 3, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 1, 1, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 1, 3, 2) != -1)
			game.displayBoard();
//		
//		if(game.drawLine(1, 4, 3, 5, 3) != -1)
//			game.displayBoard();
////		
		if(game.drawLine(1, 5, 3, 5, 4) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 5, 4, 5, 5) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 5, 5, 4, 5) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 4, 5, 3, 5) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 5, 3, 4) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 4, 3, 3) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 5, 1, 5) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 5, 2, 5) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 2, 5, 3, 5) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 1, 4, 2, 4) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 3, 3, 3, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 0, 4, 1, 4) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 5, 0, 5, 1) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 5, 1, 5, 2) != -1)
			game.displayBoard();
		
		if(game.drawLine(1, 5, 2, 5, 3) != -1)
			game.displayBoard();
		
//		if(game.drawLine(1, 0, 3, 0, 4) != -1)
//			game.displayBoard();
		
		System.out.println("Closed Chains: " + game.countClosedChains());
		System.out.println("Open Chains: " + game.countOpenChains());
//		System.out.println("Half Open Chains: " + game.countHalfOpenChains());
		
	}

}
