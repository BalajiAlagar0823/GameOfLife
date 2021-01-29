package main;

public class GameOfLifeBoard {
	private int width;
	private int height;
	private int[][] board;
	private boolean overflowBorder;
	
	/* Constructor for GameOfLifeBoard */
	public GameOfLifeBoard(int width, int height) {
		this.width = width;
		this.height = height;
		this.board = new int[width][height];
		this.overflowBorder = true;
	}
	
	public GameOfLifeBoard(int[][] inputBoard) {
		this.board = inputBoard;
		this.width = inputBoard.length;
		this.height = inputBoard[1].length;
	}
	
	/** Getter for Instance Variable Width */
	public int getWidth() {
		return width;
	}
	
	/** Getter for Instance Variable Height */
	public int getHeight() {
		return height;
	}
	
	/** Getter for the Instance Variable Board */
	public int[][] getBoard() {
		
		return board;
	}
	
	/** Randomizes the 2-D Array, Board */
	public void randomBoard(double prob) {
		for(int i  = 0; i < getWidth(); i++) {
			for(int j = 0; j < getHeight(); j++) {
				if(prob > Math.random()) board[i][j] = 1;
				else board[i][j] = 0;
			}
		}
	}
	/** Calculates the number of alive neighbors for a given cell */
	public int numNeighborAlive(int i, int j, boolean overflow){
		int numAliveNeighbors = 0;
		int i2 = i-1; //9
		int i3 = i+1; //11
		int j2 = j-1; //9
		int j3 = j+1; //11
		
		
		//Algorithm for overflow border
		if(overflowBorder) {
			boolean i2Out = false, j2Out = false, i3Out = false, j3Out = false;
			int i1j3=0, i1j2=0, i2j3=0, i2j2=0, i2j1=0, i3j3=0, i3j2=0, i3j1= 0;
		
			if((i2 >= board.length) || (i2 < 0)) i2Out = true;
			if((j2 >= board[i].length) || (j2 < 0)) j2Out = true;
			if((i3 >= board.length) || (i3 < 0)) i3Out = true;
			if((j3 >= board[i].length) || (j3 < 0)) j3Out = true;
			
			if(i2Out || j3Out) {
				i2j3 = 0;
			} else {
				i2j3 = board[i2][j3];
			}
			
			if(i2Out || j2Out) {
				i2j2 = 0;
			} else {
				i2j2 = board[i2][j2];
			}
			
			if(i3Out || j2Out) {
				i3j2 = 0;
			} else {
				i3j2 = board[i3][j2];
			}
			
			if(i3Out || j3Out) {
				i3j3 = 0;
			} else {
				i3j3 = board[i3][j3];
			}
			
			if(i3Out) {
				i3j1 = 0;
			} else {
				i3j1 = board[i3][j];
			}
			
			if(i2Out) {
				i2j1 = 0;
			} else {
				i2j1 = board[i2][j];
			}
			
			if(j3Out) {
				i1j3 = 0;
			} else {
				i1j3 = board[i][j3];
			}
			
			if(j2Out) {
				i1j2 = 0;
			} else {
				i1j2 = board[i][j2];
			}
			numAliveNeighbors = i1j2 + i1j3 + i2j1 + i2j2 + i2j3 + i3j1 + i3j2 + i3j3;
			return numAliveNeighbors;
		}
		//Normal Board
		else {
			if(i2 == -1){
				i2 = board.length-1;
			}
			if(i3 == board.length){
				i3 = 0;
			}
			if(j2 == -1) {
				j2 = board[i].length-1;
			}
			if(j3 == board[i].length) {
				j3 = 0;
			}
			numAliveNeighbors = board[i2][j3] + board[i2][j] + board[i2][j2] + board[i][j3] + board[i][j2] + board[i3][j3] + board[i3][j] + board[i3][j2];
			return numAliveNeighbors;
		}
	}
	
	
	
	public void updateBoardState() {
		int[][] newBoard = new int[board.length][board[1].length];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				numNeighborAlive(i,j, overflowBorder);
				if(board[i][j] == 1) {
					if(numNeighborAlive(i,j, overflowBorder) == 0  || (numNeighborAlive(i,j, overflowBorder) == 1)) {
						//Dead due to under-population
						newBoard[i][j] = 0;
					}
					if((numNeighborAlive(i,j, overflowBorder) == 2) || (numNeighborAlive(i,j, overflowBorder) == 3)) {
						//Alive Still...
						newBoard[i][j] = 1;
					}
					if(numNeighborAlive(i,j, overflowBorder) > 3) {
						//Dead due to over-population
						newBoard[i][j] = 0;
					}
				}
				if(board[i][j] == 0) {
					if(numNeighborAlive(i,j, overflowBorder) == 3) {
						//Dead cell becomes alive due to reproduction
						newBoard[i][j] = 1;
					}
				}
				
			}
		}
		board = newBoard;
	}
	
	
	/** Prints the board to the console */
	public void printBoard() {
		for(int i = 0; i < getHeight(); i++) {
			for(int j = 0; j < getWidth(); j++) {
				if(board[j][i] == 0) System.out.print(0);
				if(board[j][i] == 1) System.out.print(1);
			
			} 	System.out.println();
		}
	}

	
	
	
	public boolean isOverflowBorder() {
		return overflowBorder;
	}

	public void setOverflowBorder(boolean overflowBorder) {
		this.overflowBorder = overflowBorder;
	}
}
		
