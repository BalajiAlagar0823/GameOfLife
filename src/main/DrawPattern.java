package main;

import java.awt.MouseInfo;
import java.awt.Point;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DrawPattern extends VBox {
	
	public Scene drawScene;
	private int[][] drawBoard;
	private Canvas drawCanvas;
	public GraphicsContext gc;
	private Button savePatternButton;
	HBox hbox;
	private BorderPane border;
	private SavePattern save;
	public ComboBox<String> moreOptionBox;
	private Boolean isDrawGridOn = false;
	
	
	public DrawPattern(int patternSize) {
		this.border = new BorderPane();
		this.drawCanvas = new Canvas(patternSize*10, patternSize*10);
		this.drawBoard = new int[patternSize][patternSize];
		this.gc = this.drawCanvas.getGraphicsContext2D();
		this.hbox = new HBox();
		
		this.moreOptionBox = new ComboBox<String>();
		this.moreOptionBox.getItems().add("Play");
		
		drawCanvas.setOnMousePressed(new EventHandler<MouseEvent>() {
	        @Override	        
	        public void handle(MouseEvent event) {
	        	if(event.getButton() == MouseButton.PRIMARY) {
	        		gc.setFill(Color.BLACK);
	        		draw(Color.BLACK);
	        		if(isDrawGridOn) {
	        			drawGrid();
	        		}
	        		//printBoard();
	        	}
	        	
	        	if(event.getButton() == MouseButton.SECONDARY) {
	        		gc.setFill(Color.WHITESMOKE);
	        		draw(Color.WHITESMOKE);
	        		if(isDrawGridOn) {
	        			drawGrid();
	        		}
	        	}
 	            
	        }
	    });
		
		drawCanvas.setOnScroll(new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				zoom();
			}
		});
		
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			 @Override
	        public void handle(KeyEvent event) {
				 if (event.getCode() == KeyCode.F1) {
					if(!isDrawGridOn) {
						drawGrid();
						isDrawGridOn = true;
					} 
					else {
						drawOffGrid();
						isDrawGridOn = false;
					}
	             }
			 }
		});
		
		this.savePatternButton = new Button("Save Pattern");
		savePatternButton.setStyle("-fx-font-size: 15");
		savePatternButton.setPrefSize(120, 20);
		
		moreOptionBox.setStyle("-fx-font-size: 15");
		moreOptionBox.setPrefSize(100, 20);
		
		this.setCursor(Cursor.HAND);
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.TOP_CENTER);
		this.setAlignment(Pos.TOP_CENTER);
		hbox.getChildren().addAll(this.savePatternButton, this.moreOptionBox);
		border.setTop(hbox);
		
		this.getChildren().addAll(this.border, this.drawCanvas);
		this.save = new SavePattern(this.drawBoard);
		
		this.savePatternButton.setOnAction( e -> {
			this.save.saveFile("patternCreated");
		});
		
	}
	
	public void draw(Color color) {
		drawCell(getMousePos().x  , getMousePos().y , color);
	}
	
	private void drawCell(double xCord, double yCord , Color color) {
		int roundXCord = Math.abs((int) (Math.round(xCord/10) * 10));
		int roundYCord = Math.abs((int)(Math.round(yCord/10) * 10));
		//System.out.println("X: " + roundXCord + " Y: " + roundYCord);
		this.gc.setFill(color);
		this.gc.fillRect(roundXCord, roundYCord, 10, 10);
		
		if(color == Color.BLACK) this.drawBoard[roundXCord/10][roundYCord/10] = 1;
		else this.drawBoard[roundXCord/10][roundYCord/10] = 0;
	}
	
	
	private Point getMousePos() {
		Point mosPos = MouseInfo.getPointerInfo().getLocation();
		Bounds bounds = this.drawCanvas.getLayoutBounds();
		Point2D windowPos = this.drawCanvas.localToScreen(bounds.getMinX() , bounds.getMinY());
		Point relativeMosPos = new Point();
		relativeMosPos.x = (int) (mosPos.x - windowPos.getX() - 5);
		relativeMosPos.y = (int) (mosPos.y - windowPos.getY());
		System.out.println("X: " + relativeMosPos.x + " Y: " + relativeMosPos.y);
		return relativeMosPos;
		
	}
	
	public void printBoard() {
		for(int i = 0; i < drawBoard.length-1; i++) {
			for(int j = 0; j < drawBoard.length-1; j++) {
				System.out.print(drawBoard[i][j]);
			}
			System.out.println();
		}
	}
	
	public void drawGrid() {
		for(int i = 0; i <= drawBoard.length-1; i++) {
			for(int j = 0; j <= drawBoard.length-1; j++) {
				if(i % 1 == 0) {
					gc.setFill(Color.BLUE);
					gc.fillRect(i*10,j*10 ,2,10);
				}
				if(j%1 == 0) {
					gc.setFill(Color.BLUE);
					gc.fillRect(i*10,j*10,10,2);
				}
				
			}
		}
	}
	
	public void drawOffGrid() {
		for(int i = 0; i <= drawBoard.length-1; i++) {
			for(int j = 0; j <= drawBoard.length-1; j++) {
				if(i % 1 == 0) {
					if(drawBoard[i][j] == 1) {
						gc.setFill(Color.BLACK);
						gc.fillRect(i*10,j*10 ,2,10);
					}
					else {
						gc.setFill(Color.WHITESMOKE);
						gc.fillRect(i*10,j*10,2,10);
					}
					
				}
				if(j%1 == 0) {
					if(drawBoard[i][j] == 1) {
						gc.setFill(Color.BLACK);
						gc.fillRect(i*10,j*10 ,10,2);
					}
					else {
						gc.setFill(Color.WHITESMOKE);
						gc.fillRect(i*10,j*10,10,2);
					}
				}
				
			}
		}
	}
	
	public void zoom() {
		//get mouse position and zoom in on mouse
		//calculate mouse relative to center
		//use this calculation to then create smaller squares 'centered' around the mouse but stretch to fill up the original canvas size
		int size = 100;
		Point newSquareCorner = new Point();
		newSquareCorner.x = mouseRelativeToCenter().x + (size / 2);
		newSquareCorner.y = mouseRelativeToCenter().y + (size / 2);
	}
	
	public Point mouseRelativeToCenter() {
		Point mosPos = MouseInfo.getPointerInfo().getLocation();
		Bounds bounds = this.drawCanvas.getLayoutBounds();
		Point2D windowPos = this.drawCanvas.localToScreen(bounds.getMaxX() / 2 , bounds.getMaxY() / 2);
		Point relativeMosPos = new Point();
		relativeMosPos.x = (int) (mosPos.x - windowPos.getX());
		relativeMosPos.y = (int) (mosPos.y - windowPos.getY());
		System.out.println("Center Relative X: " + relativeMosPos.x + " Center Relative Y: " + relativeMosPos.y);
		return relativeMosPos;
	}
	
}
