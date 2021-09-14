

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class TextEditor extends Application {
	
	Button openButton;
	Button saveButton;
	Button runButton;
	Button fontPlusButton;
	Button fontMinusButton;
	
	TextArea textArea;
	
	HBox menu;
	VBox root;
	
	Scene scene;
	
	File fileSelected;
	BufferedReader is;
	BufferedWriter os;
	
	FileChooser fileChooser;
	
	Stage stage;
	
	@Override
	public void init() {
		
		openButton = new Button("Open");
		saveButton = new Button("Save");
		runButton = new Button("Run");
		fontPlusButton = new Button("Font +");
		fontMinusButton = new Button("Font -");
		
		textArea = new TextArea();
		textArea.setPrefWidth(800);
		textArea.setPrefHeight(1000);
		
		menu = new HBox();
		root = new VBox();
		
		menu.setPadding(new Insets(10,0,10,0));
		menu.setSpacing(15);
		root.setPadding(new Insets(40,40,40,40));
		root.setSpacing(15);
		
		menu.getChildren().addAll(openButton,saveButton,runButton,fontPlusButton,fontMinusButton);
		root.getChildren().addAll(menu,textArea);
		
		scene = new Scene(root,1000,1200);
		
		fileChooser = new FileChooser();
		
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		primaryStage.setTitle("Text Editor");
		primaryStage.setScene(scene);
		stage = primaryStage;
		setActions();
		primaryStage.show();
		
		
		

	}
	
	public void setActions() {
		openButton.setOnAction(e -> {
			String line;
			String text = "";
			fileSelected = fileChooser.showOpenDialog(stage);
			try {
				is = new BufferedReader(new FileReader(fileSelected));
				line = is.readLine();
				while(line != null) {
					text = text + line + "\n";
					line = is.readLine();
				}
			} catch (IOException e1) {
				System.out.println("cant read from the file !");
			}
			textArea.setText(text);
			try {
				is.close();
			} catch (IOException e4) {
				System.out.println("cant close the stream");
			}
		});
		
		
		saveButton.setOnAction(e -> {
			String text = textArea.getText();
			try {
				os = new BufferedWriter(new FileWriter(fileSelected));
				os.write(text);
				os.flush();
			} catch (IOException e2) {
				System.out.println("cant write to the file");
			}
			try {
				os.close();
			} catch (IOException e3) {
				System.out.println("cant close the stream");
			}
			
		});
		
		
		runButton.setOnAction(e -> {
			
		});
		
		
		fontPlusButton.setOnAction(e -> {
			textArea.setStyle("-fx-font-size: 20");
		});
		
		fontMinusButton.setOnAction(e -> {
			textArea.setStyle("-fx-font-size: 10");
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

