package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SavePattern {
	public int[][] inputBoard;
	public File save;
	public BufferedWriter writer;
	public StringBuilder builder;
	
	public SavePattern(int[][] input) {
		this.inputBoard = input;
	}
	
	public void saveFile(String fileName){
		builder = new StringBuilder();
		for(int i = 0; i < inputBoard.length; i++) {
			for(int j = 0; j < inputBoard.length; j++) {
				builder.append(inputBoard[j][i]);
			}
			builder.append("\n");
		}
		
		try {
			writer = new BufferedWriter(new FileWriter("C:\\Users\\Balaji Alagar\\Documents\\GameOfLife\\src\\patterns\\" + fileName + ".txt"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			writer.write(builder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
