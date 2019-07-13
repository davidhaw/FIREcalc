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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	Boolean databaseSelection = false;
	
	
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
			Text scenetitle = new Text("Welcome. Please input your data to calculate FIRE data");
			
			//Advanced Mode
			final CheckBox advancedMode = new CheckBox("Advanced Mode");
			setupGrid.add(advancedMode, 1, 10);
			
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			setupGrid.add(scenetitle, 0, 0, 2, 1);

			Label ageLabel = new Label("Age:");
			setupGrid.add(ageLabel, 0, 1);
			TextField ageField = new TextField("0");
			setupGrid.add(ageField, 1, 1);
			
			Label ageStartLabel = new Label("Age To Start FIRE:");
			setupGrid.add(ageStartLabel, 0, 2);
			TextField ageStartField = new TextField("40");
			setupGrid.add(ageStartField, 1, 2);

			Label preSave = new Label("How much have you already saved for FIRE:");
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
			
			Label InterInfL = new Label ("Inflation - Interest Per Year");
			setupGrid.add(InterInfL, 0, 6);
			TextField InterInfF = new TextField("0");
			setupGrid.add(InterInfF, 1, 6);
			InterInfL.setVisible(false);
			InterInfF.setVisible(false);
			
			Label matchL = new Label ("Amount Employer Adds Yearly");
			setupGrid.add(matchL, 0, 7);
			TextField matchF = new TextField("0");
			setupGrid.add(matchF, 1, 7);
			matchL.setVisible(false);
			matchF.setVisible(false);
			
			Button infoBtn = new Button("Show me my data!"); 
			final Text actiontarget = new Text();
	        setupGrid.add(actiontarget, 1, 8);
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().add(infoBtn);
			setupGrid.add(hbBtn, 1, 8);
			
			Button loadDBBtn = new Button("Load Data"); 
			setupGrid.add(loadDBBtn, 0, 8);
			
			final CheckBox saveMode = new CheckBox("Save Info For Further Use");
			setupGrid.add(saveMode, 0, 10);
			
			
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
		
			Button backStart = new Button("Go Back To Data Input");
			dataGrid.add(backStart, 0, 3);
			
			
			Scene setupScene = new Scene(setupGrid, 720, 480);
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
			    			InterInfL.setVisible(true);
			    			InterInfF.setVisible(true);
			    			matchL.setVisible(true);
			    			matchF.setVisible(true);
			    			
			            	
			            } else {
			            	
			    			deathAgeL.setVisible(false);
			    			deathAgeF.setVisible(false);
			    			InterInfL.setVisible(false);
			    			InterInfF.setVisible(false);
			    			matchL.setVisible(false);
			    			matchF.setVisible(false);
			            	
			            }
			            
			        }
			    }
			};
			
			//Save to Database action event
			EventHandler<ActionEvent> saveEvent = new EventHandler<ActionEvent> () {
				
				public void handle(ActionEvent event)
				{
					if (event.getSource() instanceof CheckBox) {
						CheckBox saveMode = (CheckBox) event.getSource();
			            databaseSelection = saveMode.isSelected();
			            System.out.println(databaseSelection);
					}					
				}
			};
			
			//Load to Database action event
			EventHandler<ActionEvent> loadDB = new EventHandler<ActionEvent> () {
				
				public void handle(ActionEvent event)
				{
					
					DBuser dbLoad = new DBuser();
					ArrayList<Object> dbEarnings = new ArrayList<>();
					dbEarnings = dbLoad.getEarnings();
					primaryStage.setScene(dataScene);
			        dataAmntNeedT.setText("Amount Needed to FIRE: $" + dbEarnings.get(1));
			        saveYearlyT.setText("Amount needed to save yearly to FIRE: $" + dbEarnings.get(0));
			        
					
				}
			};
			
			//Go back to data input
			EventHandler<ActionEvent> goBack = new EventHandler<ActionEvent> () {
				
				public void handle(ActionEvent event)
				{
					primaryStage.setScene(setupScene);			
				}
			};
			
			advancedMode.setOnAction(advancedModeEvent);
			saveMode.setOnAction(saveEvent);
			loadDBBtn.setOnAction(loadDB);
			backStart.setOnAction(goBack);
			
			infoBtn.setOnAction(new EventHandler<ActionEvent>() { 
			    @SuppressWarnings("unchecked")
				@Override
			    public void handle(ActionEvent e) {
		        
			    	try {
			    	
			        Calculator calc = new Calculator();
			        
			        int deathAge = 80;
			        int age;
			        double preSaved;
			        double interestInflation;
				    age = Integer.parseInt(ageField.getText());
					deathAge = Integer.parseInt(deathAgeF.getText());
				    interestInflation = Double.parseDouble(InterInfF.getText());
				    preSaved = Double.parseDouble(preSaveField.getText());
			       
				    double employerAmnt = Double.parseDouble(matchF.getText());
			        
			        final double amntNeeded = calc.amntNeeded(preSaved, Double.parseDouble(yearSpendField.getText()), Integer.parseInt(ageStartField.getText()), deathAge);
			        final double saveYearly = calc.saveYearly(amntNeeded, age, Integer.parseInt(ageStartField.getText()), employerAmnt);
			        double advancedCalc = 0;
			        double advancedSaveYearly = 0;
			        if (interestInflation != 0.00) {
				        advancedCalc = calc.amntNeededAdvanced(amntNeeded, Integer.parseInt(ageStartField.getText()), age, interestInflation);
				        advancedSaveYearly = calc.saveYearly(advancedCalc, age, Integer.parseInt(ageStartField.getText()), employerAmnt);
				        System.out.println(advancedCalc);
			        }
			        
			        ArrayList<Object> chartData = calc.chartdata(age, Integer.parseInt(ageStartField.getText()), saveYearly, Double.parseDouble(yearSpendField.getText()), deathAge);
			        
					XYChart.Series FIREcash = new XYChart.Series(); 
					FIREcash.setName("Cash spending for FIRE"); 

					int i = age;
					int x =0;
					while (i <= deathAge) {
						FIREcash.getData().add(new XYChart.Data(i, chartData.get(x))); 
						//System.out.println(chartData.get(x));
						i++;
						x++;
					}
					
					//Defining X axis - betterGraphAge makes the graph look better as the start points aren't all out of graph
					int betterGraphStartAge = age;
					if (age != 0) {
						betterGraphStartAge = age - 1;
					}
					betterGraphStartAge = age - 1;
					int betterGraphDeathAge = deathAge + 1;
					NumberAxis xAxis = new NumberAxis(betterGraphStartAge, betterGraphDeathAge, 1); 
					xAxis.setLabel("Age"); 
					        
					//amntNeededTop just makes the graph a little bit higher than the mac cash so it is easier to read
					double amntNeededTop = amntNeeded + 500;
					
					//Defining y axis 
					NumberAxis yAxis = new NumberAxis(0, amntNeededTop, 50); 
					yAxis.setLabel("FIRE Cash");
					
					LineChart linechart = new LineChart(xAxis, yAxis);
					linechart.getData().add(FIREcash);
					dataGrid.add(linechart, 0, 4);
					

			        if (interestInflation != 0.00) {
			        	dataAmntNeedT.setText("Amount Needed to FIRE: $" + advancedCalc);
				        dataAmntNeedT.setText("Amount Needed to FIRE: $" + advancedSaveYearly);
						dbThreadSave r = new dbThreadSave(advancedCalc, advancedSaveYearly, databaseSelection);
				        Thread thread_object=new Thread(r);
				        thread_object.start();
			        } else {
				        dataAmntNeedT.setText("Amount Needed to FIRE: $" + amntNeeded);
				        saveYearlyT.setText("Amount needed to save yearly to FIRE: $" + saveYearly);
						dbThreadSave nonAr = new dbThreadSave(amntNeeded, saveYearly, databaseSelection);
				        Thread dbNonAdv=new Thread(nonAr);
				        dbNonAdv.start();
			        }
			        primaryStage.setScene(dataScene);
			    	} catch (NumberFormatException ee) {

					    Alert alert = new Alert(AlertType.INFORMATION);
					    alert.setTitle("Empty Fields");
					    alert.setHeaderText(null);
					    alert.setContentText("Please Enter All Fields Before Continuing!!");

					    alert.showAndWait();
					    
			    	}
			    }
			});
			
			
			
			setupScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dataScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			
			//fx setup
			primaryStage.setScene(setupScene);
			primaryStage.setTitle("FIRE Calc");
			primaryStage.show();
			
		} catch(Exception ex ) {

			System.out.println(ex.getMessage());
			
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
