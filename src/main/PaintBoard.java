package main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.geometry.Pos;
import java.io.File;
import java.text.DecimalFormat;
import javafx.animation.AnimationTimer;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class PaintBoard extends VBox{
	
	private GameOfLifeBoard lifeBoard;
	private Canvas canvas;
	private Button nextButton;
	private Button playButton;
	private Button stopButton;
	private Slider speedSlider;
	private GraphicsContext gc;
	HBox hbox;
	private HBox bottomHbox;
	private BorderPane border;
	private Label sliderLabel;
	private Label speedLabel;
	private Button openFile;
	private ComboBox<String> moreOptionBox;
	private CheckBox overflowBox;
	private boolean overflowSelected;
	
	public boolean importFileBool;
	
	private AnimationTimer timer = new AnimationTimer() {
		private long lastUpdate = 0;
		
		@Override
		public void handle(long now) {
			if(now - lastUpdate >= (Math.pow(8.8, 9)) * (1/speedSlider.getValue())) {
				lifeBoard.updateBoardState();
				draw(importFileBool);
				lastUpdate = now;
			}
			
		}
	};
	/** Constructor for PaintBoard */
	public PaintBoard(GameOfLifeBoard lifeBoard) {
		this.lifeBoard = lifeBoard;
		this.importFileBool = false;
		this.canvas = new Canvas(lifeBoard.getHeight() * 10, lifeBoard.getWidth() * 10);
		this.nextButton = new Button("Next Step");
		this.playButton = new Button("Play");
		this.stopButton = new Button("Stop");
		this.openFile = new Button("Open File");
		this.gc = this.canvas.getGraphicsContext2D();
		this.hbox = new HBox();
		this.border = new BorderPane();
		this.speedSlider = new Slider();
		this.bottomHbox = new HBox();
		
		this.speedSlider.setMin(0.5);
		this.speedSlider.setMax(4);
		this.speedSlider.setShowTickLabels(true);
		this.speedSlider.setShowTickMarks(false);
		this.speedSlider.setMajorTickUnit(0.5);
		
		this.sliderLabel = new Label(" ");
		this.speedLabel = new Label("Speed");
		
		this.moreOptionBox = new ComboBox<>();
		this.overflowBox = new CheckBox("Overflow");
		
		moreOptionBox.getItems().add("Restart");
		moreOptionBox.getItems().add("Exit");
		moreOptionBox.setValue("More");
		
		this.nextButton.setOnAction( e -> {
			this.lifeBoard.updateBoardState();
			draw(this.importFileBool);
		});
		
		this.playButton.setOnAction( e -> {
			timer.start();
		});
		
		this.stopButton.setOnAction( e ->{
			timer.stop();
		});
		
		this.openFile.setOnAction( e ->{
			this.overflowSelected = this.overflowBox.isSelected();
			System.out.println(overflowSelected);
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("C:\\Users\\Balaji Alagar\\Documents\\GameOfLife\\src\\patterns"));
			fileChooser.setDialogTitle("Open Pattern");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
			fileChooser.setFileFilter(filter);
			int returnVal = fileChooser.showOpenDialog(null);
	        clearCanvas();
	        ReadPattern read = new ReadPattern(fileChooser.getSelectedFile());
	        GameOfLifeBoard inputBoard = new GameOfLifeBoard(read.readFile());
	        inputBoard.setOverflowBorder(this.overflowSelected);
	        this.lifeBoard = inputBoard;
	        this.importFileBool = true;
	        draw(this.importFileBool);
		});
		
		DecimalFormat df = new DecimalFormat("#.00"); 
		this.speedSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(
               ObservableValue<? extends Number> observableValue, 
               Number oldValue, 
               Number newValue) { 
                  sliderLabel.textProperty().setValue(
                       String.valueOf(df.format(newValue.doubleValue())));
              		}	
        		
    		});
		
		
		
		/* Button opens a drop down menu with more options that are less frequently used */
		this.moreOptionBox.setOnAction( e->{
			if(moreOptionBox.getValue().equals("Restart")) {
				clearCanvas();
		        GameOfLifeBoard restartBoard = new GameOfLifeBoard(70 , 70);
		        restartBoard.randomBoard(.3);
		        this.lifeBoard = restartBoard;
		        this.importFileBool = false;
		        draw(this.importFileBool);
			}
			
			if(moreOptionBox.getValue().equals("Exit")) {
				System.exit(0);
			}
		});
		
		border.setBottom(bottomHbox);
		bottomHbox.setSpacing(50);
		bottomHbox.setAlignment(Pos.TOP_CENTER);
		border.setTop(hbox);
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.TOP_CENTER);
		nextButton.setStyle("-fx-font-size: 15");
		nextButton.setPrefSize(100, 20);
		
		playButton.setStyle("-fx-font-size: 15");
		playButton.setPrefSize(100, 20);
		
		stopButton.setStyle("-fx-font-size: 15");
		stopButton.setPrefSize(100, 20);
		
		openFile.setStyle("-fx-font-size: 15");
		openFile.setPrefSize(100, 20);
		
		moreOptionBox.setPrefSize(50, 20);
		
		hbox.getChildren().addAll(this.openFile , this.nextButton , this.playButton , this.stopButton  , this.speedLabel , this.speedSlider , this.sliderLabel , this.moreOptionBox);
		bottomHbox.getChildren().addAll(this.overflowBox);
		this.getChildren().addAll(this.hbox, this.bottomHbox , this.canvas);
	}
	

	
	public void draw(boolean imported) {
		for (int i = 0; i < this.lifeBoard.getHeight(); i++) {
			for (int j = 0; j < this.lifeBoard.getWidth(); j++) {
				if(this.lifeBoard.getBoard()[j][i] == 1) {
					this.gc.setFill(Color.BLACK);
					if(imported) this.gc.fillRect(j*(700/this.lifeBoard.getWidth()), i*(700/this.lifeBoard.getHeight()), (700/this.lifeBoard.getWidth()), (700/this.lifeBoard.getHeight()));
					else this.gc.fillRect(j*10, i*10, 10, 10);
				}
				else {
					this.gc.setFill(Color.WHITE);
					if(imported) this.gc.fillRect(j*(700/this.lifeBoard.getWidth()), i*(700/this.lifeBoard.getHeight()), (700/this.lifeBoard.getWidth()), (700/this.lifeBoard.getHeight()));
					else this.gc.fillRect(j*10, i*10, 10, 10);
				}
			}
		}
	}
	
	public void clearCanvas() {
		for (int i = 0; i < this.lifeBoard.getHeight(); i++) {
			for (int j = 0; j < this.lifeBoard.getWidth(); j++) {
				this.gc.setFill(Color.WHITE);
				this.gc.fillRect(i*10,j*10,10,10);
			}
		}	
	}
	
	
	
}
	


