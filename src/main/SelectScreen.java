package main;



import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SelectScreen {
	
	HBox oscillationBox;
	private Button button1;
	private Pattern toad;
	
	public SelectScreen() {
		this.toad = new Pattern(10, "Oscillator", "toad");
		this.button1 = new Button(toad.getName());
		button1.setPrefHeight(50);
		this.oscillationBox = new HBox();
		this.oscillationBox.getChildren().add(button1);
		
		
		this.button1.setOnAction( e->{
			ReadPattern read = new ReadPattern(new File (toad.getName()));
			GameOfLifeBoard inputBoard = new GameOfLifeBoard(read.readFile());
		});
		
		
	}
	
	
	
}
