package main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

import java.util.concurrent.TimeUnit;
import javafx.scene.control.ToggleButton;
import javafx.animation.AnimationTimer;

import javax.swing.*;
public class PaintBoard extends VBox{
	
	private GameOfLifeBoard lifeBoard;
	private Canvas canvas;
	private Button nextButton;
	private Button playButton;
	private Button stopButton;
	private GraphicsContext gc;
	private HBox hbox;
	private BorderPane border;
	
	private AnimationTimer timer = new AnimationTimer() {
		private long lastUpdate = 0;
		
		@Override
		public void handle(long now) {
			if(now - 1000 >= lastUpdate) {
				lifeBoard.updateBoardState();
				draw();
				lastUpdate = now;
			}
			
		}
	};
	/** Constructor for PaintBoard */
	public PaintBoard(GameOfLifeBoard lifeBoard) {
		this.lifeBoard = lifeBoard;
		this.canvas = new Canvas(lifeBoard.width, lifeBoard.height);
		this.nextButton = new Button("Next Step");
		this.playButton = new Button("Play");
		this.stopButton = new Button("Stop");
		this.gc = this.canvas.getGraphicsContext2D();
		this.hbox = new HBox();
		this.border = new BorderPane();
		
		
		
		this.nextButton.setOnAction( e -> {
			this.lifeBoard.updateBoardState();
			draw();
		});
		
		this.playButton.setOnAction( e -> {
			timer.start();
		});
		
		this.stopButton.setOnAction( e ->{
			timer.stop();
		});
		border.setTop(hbox);
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.TOP_CENTER);
		nextButton.setStyle("-fx-font-size: 15");
		nextButton.setPrefSize(100, 20);
		
		playButton.setStyle("-fx-font-size: 15");
		playButton.setPrefSize(100, 20);
		
		stopButton.setStyle("-fx-font-size: 15");
		stopButton.setPrefSize(100, 20);
		
		hbox.getChildren().addAll(this.nextButton , this.playButton , this.stopButton);
		
		this.getChildren().addAll(this.hbox , this.canvas);
	}
	

	
	public void draw() {
		for (int i = 0; i < this.lifeBoard.width; i++) {
			for (int j = 0; j < this.lifeBoard.height; j++) {
				if(this.lifeBoard.board[i][j] == 1) {
					this.gc.setFill(Color.BLACK);
					this.gc.fillRect(i*20,j*20,20,20);
				}
				else {
					this.gc.setFill(Color.WHITE);
					this.gc.fillRect(i*20,j*20,20,20);
				}
			}
		}
	}
}
	


