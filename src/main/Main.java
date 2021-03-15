package main;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application{
	public static void main(String[] args) {
		launch();
	}


	@Override
	public void start(Stage primaryStage) {
		
		GameOfLifeBoard gameBoard = new GameOfLifeBoard(70,70);
		gameBoard.randomBoard(.3);
		PaintBoard paint = new PaintBoard(gameBoard);
		paint.moreOptionBox.getItems().add("Create");
		DrawPattern drawPat = new DrawPattern(70);
		Scene drawScene = new Scene(drawPat);
		Scene paintScene = new Scene(paint);
		primaryStage.setScene(paintScene);
		primaryStage.setTitle("Game Of Life");
		primaryStage.show();
		primaryStage.getIcons().add(new Image("windowIcon.png"));
		
		paint.moreOptionBox.setOnAction( e->{
			if(paint.moreOptionBox.getValue().equals("Create")) {
				primaryStage.setScene(drawScene);
				primaryStage.show();
			}
		});
		
		drawPat.moreOptionBox.setOnAction(e->{
			if(drawPat.moreOptionBox.getValue().equals("Play")) {
				primaryStage.setScene(paintScene);
				paint.draw(paint.importFileBool);
				primaryStage.show();
			}
		});
			
	}
}
