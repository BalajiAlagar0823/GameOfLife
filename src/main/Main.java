package main;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import java.awt.Graphics;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.embed.swing.*;
public class Main extends Application{
	
	public static void main(String[] args) {
		launch();
//		GameOfLifeBoard baliBoard = new GameOfLifeBoard(1920,1080);
//		baliBoard.randomBoard(.9);
//		baliBoard.printBoard();
//		PaintBoard paint = new PaintBoard(baliBoard);
//		Scene scene = new Scene(paint);
//		
//		JFrame frame = new JFrame("game of life");
//		final JFXPanel panel = new JFXPanel();
//		Stage stage = new Stage();
//		panel.setScene(scene);
//		
//		JPanel game = new JPanel();
//		game.add(panel);
//		
//		frame.add(game);
//		
//		frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        paint.run();
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		GameOfLifeBoard baliBoard = new GameOfLifeBoard(800 , 800);
		baliBoard.randomBoard(.3);
		PaintBoard paint = new PaintBoard(baliBoard);
		Scene scene = new Scene(paint);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Game Of Life");
		primaryStage.show();
		//baliBoard.printBoard();
		paint.draw();
	}
}
