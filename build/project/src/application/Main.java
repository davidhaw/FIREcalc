package application;
	
import java.util.ArrayList;

import javafx.application.Application;
//fx event
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//fx geo
import javafx.geometry.Insets;
import javafx.geometry.Pos;
//fx stage
import javafx.stage.Stage;
//fx scene
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
 
public class Main extends Application {
	Boolean advancedSelection;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			//Setup Scene
			//TODO make advanced mode and all the textfields and such
			GridPane setupGrid = new GridPane();
			setupGrid.setAlignment(Pos.CENTER);
			setupGrid.setHgap(10);
			setupGrid.setVgap(10);
			setupGrid.setPadding(new Insets(25, 25, 25, 25));
			Text scenetitle = new Text("Welcome. Please input your data");
			
			//Advanced Mode
			final CheckBox advancedMode = new CheckBox("Advanced Mode");
			setupGrid.add(advancedMode, 0, 10);
			
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			setupGrid.add(scenetitle, 0, 0, 2, 1);

			Label ageLabel = new Label("Age:");
			setupGrid.add(ageLabel, 0, 1);
			TextField ageField = new TextField();
			setupGrid.add(ageField, 1, 1);
			
			Label ageStartLabel = new Label("Age To Start FIRE:");
			setupGrid.add(ageStartLabel, 0, 2);
			TextField ageStartField = new TextField();
			setupGrid.add(ageStartField, 1, 2);

			Label preSave = new Label("How much have you already saved for FIRE?:");
			setupGrid.add(preSave, 0, 3);
			TextField preSaveField = new TextField();
			setupGrid.add(preSaveField, 1, 3);
			
			Label yearSpend = new Label ("How much do you want to spend each year in FIRE?");
			setupGrid.add(yearSpend, 0, 4);
			TextField yearSpendField = new TextField();
			setupGrid.add(yearSpendField, 1, 4);
			
			
			Label deathAgeL = new Label ("Death Age");
			setupGrid.add(deathAgeL, 0, 5);
			TextField deathAgeF = new TextField("80");
			setupGrid.add(deathAgeF, 1, 5);
			deathAgeL.setVisible(false);
			deathAgeF.setVisible(false);
			
			Button infoBtn = new Button("Show me my data!"); 
			final Text actiontarget = new Text();
	        setupGrid.add(actiontarget, 1, 8);
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().add(infoBtn);
			setupGrid.add(hbBtn, 1, 8);
			
			//Saving DB
			//TODO finish saving DB
			final CheckBox saveMode = new CheckBox("Save Info For Further Use");
			setupGrid.add(saveMode, 0, 9);
			
			//TODO finish Data showing scene
			GridPane dataGrid = new GridPane();
			dataGrid.setAlignment(Pos.CENTER);
			dataGrid.setHgap(10);
			dataGrid.setVgap(10);
			dataGrid.setPadding(new Insets(25, 25, 25, 25));
			
			Text dataTitle = new Text("Your FI/RE Data:");
			dataTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			dataTitle.setFill(Color.FIREBRICK);
			dataGrid.add(dataTitle, 0, 0);
			
			Text dataAmntNeedT = new Text();
			dataGrid.add(dataAmntNeedT, 0, 1);
			
			
			Text saveYearlyT = new Text();
			dataGrid.add(saveYearlyT, 0, 2);

			//TODO finish line chart showing all the data

			
			
			Scene dataScene = new Scene(dataGrid, 720, 480);
			
			
			//Action Events
			EventHandler<ActionEvent> advancedModeEvent = new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent event) {
			        if (event.getSource() instanceof CheckBox) {
			            CheckBox advancedMode = (CheckBox) event.getSource();
			            advancedSelection = advancedMode.isSelected();
			            System.out.println(advancedSelection);
			            if (advancedSelection == true) {
			            	
			    			deathAgeL.setVisible(true);
			    			deathAgeF.setVisible(true);
			            	
			            } else {
			            	
			    			deathAgeL.setVisible(false);
			    			deathAgeF.setVisible(false);
			            	
			            }
			            
			        }
			    }
			};
			advancedMode.setOnAction(advancedModeEvent);


			
			infoBtn.setOnAction(new EventHandler<ActionEvent>() { 
			    @SuppressWarnings("unchecked")
				@Override
			    public void handle(ActionEvent e) {
			        //TODO This all needs to be concurrent later on
			    	//TODO Add In all the CONCURRENT calculations and DB saving
			        
			        Calculator calc = new Calculator();
			        
			        int deathAge = 80;
			        int age = Integer.parseInt(ageField.getText());
			        
			        deathAge = Integer.parseInt(deathAgeF.getText());
			        Double amntNeeded = calc.amntNeeded(Double.parseDouble(preSaveField.getText()), Double.parseDouble(yearSpendField.getText()), Integer.parseInt(ageStartField.getText()), deathAge);
			        Double saveYearly = calc.saveYearly(amntNeeded, age, Integer.parseInt(ageStartField.getText()));
			        ArrayList<Object> chartData = calc.chartdata(age, Integer.parseInt(ageStartField.getText()), saveYearly, Double.parseDouble(yearSpendField.getText()), deathAge);
			        
			        //System.out.println(amntNeeded);
			        //System.out.println(saveYearly);
			        //System.out.println(chartData);
			        
					XYChart.Series FIREcash = new XYChart.Series(); 
					FIREcash.setName("Cash spending for FIRE"); 

					int i = age;
					int x = 0;
					while (i < deathAge) {
						FIREcash.getData().add(new XYChart.Data(x, chartData.get(x))); 
						//System.out.println(chartData.get(x));
						i++;
						x++;
					}
					//Defining X axis  
					NumberAxis xAxis = new NumberAxis(0, deathAge, 1); 
					xAxis.setLabel("Age"); 
					        
					//Defining y axis 
					NumberAxis yAxis = new NumberAxis(0, amntNeeded, 50); 
					yAxis.setLabel("FIRE Cash");
					
					LineChart linechart = new LineChart(xAxis, yAxis);
					linechart.getData().add(FIREcash);
					dataGrid.add(linechart, 0, 4);

					
			        primaryStage.setScene(dataScene);
			        dataAmntNeedT.setText("Amount Needed to FIRE: $" + amntNeeded);
			        saveYearlyT.setText("Amount needed to save yearly to FIRE: $" + saveYearly);
			        
			        
			    }
			});
			
			
			Scene setupScene = new Scene(setupGrid, 720, 480);
			//TODO finish CSS
			setupScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dataScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			
			//fx setup
			primaryStage.setScene(setupScene);
			primaryStage.setTitle("FIRE Calc");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}