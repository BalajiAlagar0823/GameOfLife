package main;
import java.util.*;

import java.io.*;

public class GameOfLifeBoard {
	int width;
	int height;
	int[][] board;
	
	/* Constructor for GameOfLifeBoard */
	public GameOfLifeBoard(int width, int height) {
		this.width = width;
		this.height = height;
		this.board = new int[width][height];
	}
	
	public GameOfLifeBoard(String filename) {
		board = new int[width][height];
		this.width = board[1].length;
		this.height = board.length;
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
	public int numNeighborAlive(int i, int j){
		int numAliveNeighbors = 0;
		int i2 = i-1;
		int i3 = i+1;
		int j2 = j-1;
		int j3 = j+1;
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
	
	
	
	public void updateBoardState() {
		int[][] newBoard = new int[board.length][board[1].length];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				numNeighborAlive(i,j);
				if(board[i][j] == 1) {
					if(numNeighborAlive(i,j) == 0  || (numNeighborAlive(i,j) == 1)) {
						//Dead due to under-population
						newBoard[i][j] = 0;
					}
					if((numNeighborAlive(i,j) == 2) || (numNeighborAlive(i,j) == 3)) {
						//Alive Still...
						newBoard[i][j] = 1;
					}
					if(numNeighborAlive(i,j) > 3) {
						//Dead due to over-population
						newBoard[i][j] = 0;
					}
				}
				if(board[i][j] == 0) {
					if(numNeighborAlive(i,j) == 3) {
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
		for(int i = 0; i < getWidth(); i++) {
			for(int j = 0; j < getHeight(); j++) {
				if(board[i][j] == 0) System.out.print(" ");
				if(board[i][j] == 1) System.out.print(1);
			
			} 	System.out.println();
		}
	}
}
		
