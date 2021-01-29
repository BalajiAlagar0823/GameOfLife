package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadPattern {
	
	public int[][] inputBoard;
	public int width;
	public int height;
	public File input;
	
	public ReadPattern(File input) {
		this.input = input;
	}
	
	public int[][] readFile(){
		Scanner scan;
		try {
			
			scan = new Scanner(input);
			//Finding height
			while(scan.hasNextLine()){
				height++;
				scan.nextLine();
			}
			scan.close();
				
			//Finding width
			scan = new Scanner(input);
			String s = scan.nextLine();
			width = s.length();
			inputBoard = new int[width][height];
				
			scan.close();
			scan = new Scanner(input);
				
			for(int i = 0; i < height; i++) {
				String loopStr = scan.nextLine();
				for(int j = 0; j < width; j++) {
					if((loopStr.charAt(j) == 49) || (loopStr.charAt(j) == 79)) inputBoard[j][i] = 1;
					else inputBoard[j][i] = 0;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return inputBoard;
		}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
