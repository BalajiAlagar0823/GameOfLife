package main;
import javafx.application.Application;
import javafx.scene.Scene;
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
		Scene scene = new Scene(paint);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Game Of Life");
		primaryStage.show();
		primaryStage.getIcons().add(new Image("windowIcon.png"));
		paint.draw(paint.importFileBool);
		primaryStage.show();
	}
}
