package lab3;

import java.util.List;

public class DotsAndBoxes {
	
	int[] score;
	int rows;
	int columns;
	Graph g;
	DFSGraphTraversal dfs;
	
	public DotsAndBoxes(int rows, int columns) {
		dfs = new DFSGraphTraversal();
		score = new int[2];
		g = new Graph((rows + 1) * (columns + 1));
		this.rows = rows + 1;
		this.columns = columns + 1;
		
		for(int y = 1; y < rows; y++) {
			for(int x = 1; x < columns; x++) {
				int index = x + this.rows * y;
				g.addEdge(index, index - 1, 0);
				g.addEdge(index, index + 1, 0);
				g.addEdge(index,  index - this.rows,  0);
				g.addEdge(index,  index + this.rows,  0);
				System.out.println(y + " " + x);
			}
		}
	}
	
	public int drawLine(int player, int x1, int y1, int x2, int y2) {
		int vertex1 = x1 + rows * y1;
		int vertex2 = x2 + rows * y2;
		
		int biggestX = (vertex1 > vertex2) ? x1 : x2;
		int biggestY = (vertex1 > vertex2) ? y1 : y2;
		int index = biggestX + rows * biggestY;
		
		if(!g.isEdge(index, index + rows) && !g.isEdge(index, index + 1) && !isValidEdge(x1, x2, y1, y2))
			return -1;

		int pointsScored = 0;
		if(isVerticalEdge(x1, x2)) {
			g.removeEdge(index, index + 1);
			if(x1 > 0) {
				if(g.degree(index) == 0)	
					pointsScored++;
			}
			
			if(g.degree(index + 1) == 0)
				pointsScored++;
		}
		else if(isHorizontalEdge(y1, y2)) {
			g.removeEdge(index, index + rows);
			if(y1 > 0) {
				if(g.degree(index) == 0)
					pointsScored++;
			}
			
			if(g.degree(index + rows) == 0)
					pointsScored++;
		}
		
		score[player] += pointsScored;
		return pointsScored;
	}
	
	private boolean isValidEdge(int x1, int x2, int y1, int y2) {
		boolean edge = false;
		double part1 = Math.pow((x2 - x1), 2);
		double part2 = Math.pow((y2 - y1), 2);
		if(Math.sqrt(part1 + part2) == 1)
			edge = true;
		
		return edge;
	}
	
	private boolean isVerticalEdge(int x1, int x2) {
		boolean horizontal = false;
		if(x1 == x2)
			horizontal = true;
		
		return horizontal;
	}
	
	private boolean isHorizontalEdge(int y1, int y2) {
		boolean vertical = false;
		if(y1 == y2)
			vertical = true;
		
		return vertical;
	}
	
	public int score(int player) {
		return score[player];
	}
	
	public boolean anyMovesLeft() {
		boolean isFull = false;
		
		if(dfs.traverse(g).size() == 1)
			isFull = true;
		
		return isFull;
	}
	
	public int countDoubleCrosses() {
		dfs.changeMark(2);
		List<List<Integer>> forest =  dfs.traverse(g);
		int doubleCrosses = 0;
		for(int i = 0; i < forest.size(); i++) {
			if(forest.get(i).size() == 2)
				doubleCrosses++;
		}
		return doubleCrosses;
	}
	
	public int countCycles() {
		dfs.changeMark(8);
		List<List<Integer>> forest = dfs.traverse(g);
		int cycles = 0;
		for(int i = 0; i < forest.size(); i++) {
			List<Integer> tree = forest.get(i);
			if(tree.size() > 2 && tree.size() % 2 == 0) {
				int size = tree.size();
				for(int j = 0; j < tree.size(); j++) {
					if(g.degree(tree.get(j)) == 2) {
						size--;
					}
				}
				if(size == 0 && g.isEdge(tree.get(0), tree.get(tree.size() - 1)))
					cycles++;
			}
		}
		return cycles;
	}
	
	public int countClosedChains() {
		dfs.changeMark(3);
		List<List<Integer>> forest = dfs.traverse(g);
		int chains = 0;
		for(int i = 0; i < forest.size(); i++) {
			List<Integer> tree = forest.get(i);
			if(tree.size() >= 3) {
				int size = tree.size();
				int start = -1;
				int end = -1;
				int startEndCount = 0;
				for(int j = 0; j < tree.size(); j++) {
					if(g.degree(tree.get(j)) == 2)
						size--;
					else if(startEndCount < 2 && g.degree(tree.get(j)) == 1) {
						size--;
						startEndCount++;
						if(start == -1)
							start = tree.get(j);
						else
							end = tree.get(j);
					}
					
				}
				if(start != -1 && end != -1) {
					if(size == 0 && !g.isEdge(start, end) && !isRimEdge(start) && !isRimEdge(end))
						chains++;
				}
			}
		}
		return chains;
	}
	
	public int countOpenChains() {
		dfs.changeMark(9);
		List<List<Integer>> forest = dfs.traverse(g);
		int chains = 0;
		for(int i = 0; i < forest.size(); i++) {
			List<Integer> tree = forest.get(i);
			if(tree.size() >= 3) {
				int size = tree.size();
				int start = -1;
				int end = -1;
				int startEndCount = 0;
				for(int j = 0; j < tree.size(); j++) {
					if(g.degree(tree.get(j)) == 2)
						size--;
					else if(startEndCount < 2 && g.degree(tree.get(j)) >= 1) {
						if((g.degree(tree.get(j)) == 1 && isRimEdge(tree.get(j)) || g.degree(tree.get(j)) > 1)) {
							size--;
							startEndCount++;
							if(start == -1)
								start = tree.get(j);
							else
								end = tree.get(j);
						}
					}
					
				}
				if(start != -1 && end != -1) {
					if(size == 0 && !g.isEdge(start, end))
						chains++;
				}
			}
		}
		return chains;
	}
	
//	public int countHalfOpenChains() {
//		dfs.changeMark(6);
//		List<List<Integer>> forest = dfs.traverse(g);
//		int chains = 0;
//		for(int i = 0; i < forest.size(); i++) {
//			List<Integer> tree = forest.get(i);
//			if(tree.size() >= 3) {
//				int size = tree.size();
//				int start = -1;
//				int end = -1;
//				int startEndCount = 0;
//				for(int j = 0; j < tree.size(); j++) {
//					if(g.degree(tree.get(j)) == 2)
//						size--;
//					else if(startEndCount < 2 && g.degree(tree.get(j)) >= 1) {
//						if((g.degree(tree.get(j)) == 1 && isRimEdge(tree.get(j)) || g.degree(tree.get(j)) > 1)) {
//							size--;
//							startEndCount++;
//							if(start == -1)
//								start = tree.get(j);
//							else
//								end = tree.get(j);
//						}
//					}
//					
//				}
//				if(start != -1 && end != -1 && ((g.degree(start) == 1 && g.degree(end) > 1) || (g.degree(end) == 1 && g.degree(start) > 1))) {
//					if(size == 0 && !g.isEdge(start, end))
//						chains++;
//				}
//			}
//		}
//		return chains;
//	}
	
	private boolean isRimEdge(int index) {
		boolean isRimEdge = false;
		for(int x = 0; x < rows; x++) {
			if(index == x * rows || index == x || index == x * rows + rows - 1 || index == x + rows * (rows - 1))
				isRimEdge = true;
		}
		
		return isRimEdge;
	}
	
	public void displayBoard() {
		System.out.println();
		for(int y = 1; y < rows; y++) {
			String verticalEdges = "";
			for(int x = 1; x < columns; x++) {
				System.out.print("0");
					int index = x + this.rows * y;
					if(!g.isEdge(index, index - rows) && x != columns - 1)
						System.out.print("--");
					else
						System.out.print("  ");
						
					if(!g.isEdge(index, index - 1))
						verticalEdges += "|  ";
					else
						verticalEdges += "   ";
			}
			System.out.println();
			if(y != rows - 1)
				System.out.println(verticalEdges);
		}
		System.out.println();
	}
}