import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application{
	Scene sceneStart, sceneSetup, sceneWorkout;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			
	primaryStage.setTitle("HIIT-Timer");
	
	/*--------------------------------------------------------------------------------------------------*/
	//sceneStart
	final Label StartScreenTitleLabel = new Label("Title");
	final Label StartScreenGuide = new Label("Guide");
	final Button ButtonEnd = new Button("End");
	ButtonEnd.setOnAction(e -> Platform.exit());
	final Button ButtonGoToSetupScreen = new Button("SetupScreen");
	ButtonGoToSetupScreen.setOnAction(e -> primaryStage.setScene(sceneSetup));
	
	final GridPane StartPane = new GridPane();
	StartPane.setGridLinesVisible(true);
	double maxHeight = StartPane.getMaxHeight();
	double maxWidth = StartPane.getMaxWidth();
	for (int i = 0; i < 3; i++) {
        ColumnConstraints column = new ColumnConstraints(maxWidth/3, maxWidth/3, Double.MAX_VALUE);
        column.setHgrow(Priority.ALWAYS);
        column.setHalignment(HPos.CENTER);
        //RowConstraints row = new RowConstraints(800/3, 800/3, Double.MAX_VALUE);
        //row.setVgrow(Priority.ALWAYS);
        //row.setValignment(VPos.CENTER);
        StartPane.getColumnConstraints().add(column);
        //StartPane.getRowConstraints().add(row);
    }
	RowConstraints StartPaneRow1 = new RowConstraints(100, 100, Double.MAX_VALUE);
	RowConstraints StartPaneRow2 = new RowConstraints(600, 100, Double.MAX_VALUE);
	RowConstraints StartPaneRow3 = new RowConstraints(100, 100, Double.MAX_VALUE);
	StartPaneRow1.setVgrow(Priority.ALWAYS);
	StartPaneRow1.setValignment(VPos.CENTER);
	StartPaneRow2.setVgrow(Priority.ALWAYS);
	StartPaneRow2.setValignment(VPos.CENTER);
	StartPaneRow3.setVgrow(Priority.ALWAYS);
	StartPaneRow3.setValignment(VPos.CENTER);
    StartPane.getRowConstraints().addAll(StartPaneRow1, StartPaneRow2, StartPaneRow3);
	//gridPane.add(Node, columnIndex, rowIndex, colspan, rowspan);
	StartPane.add(StartScreenTitleLabel, 0, 0, 3, 1);
	StartPane.add(StartScreenGuide, 0, 1, 3, 1);
	StartPane.add(ButtonEnd, 0, 2);
	StartPane.add(ButtonGoToSetupScreen, 2, 2);
	
	VBox layoutStart = new VBox();
	layoutStart.getChildren().addAll(StartPane);
	sceneStart = new Scene(layoutStart, 600, 800);

	/*--------------------------------------------------------------------------------------------------*/
	//sceneSetup
	final Label SetupScreenTitleLabel = new Label("Setup you Workout");
	final TextField ExerciseNameInput = new TextField("");
	final TextField ExerciseSetsInput = new TextField();
	final Button ExerciseAddButton = new Button("Add");
	final Button ButtonGoToStartScreen = new Button("StartScreen");
	ButtonGoToStartScreen.setOnAction(e -> primaryStage.setScene(sceneStart));
	final Button ButtonGoToWorkoutScreen = new Button("WorkoutScreen");
	ButtonGoToWorkoutScreen.setOnAction(e -> primaryStage.setScene(sceneWorkout));
	final Button ExerciseRemoveButton = new Button("Remove");
	
	String[] workouts = {};
	ObservableList<String> entries = FXCollections.observableArrayList(workouts);
	ListView<String> SetupListView = new ListView<>(entries);
	
	SelectionModel<String> selectionModel = SetupListView.getSelectionModel();
	
	BooleanBinding nameValid = ExerciseNameInput.textProperty()
			.isNotEmpty()
			.and(ExerciseNameInput.textProperty().length().greaterThan(1));
	BooleanBinding setsValid = ExerciseSetsInput.textProperty()
			.isNotEmpty();
	
	
	ExerciseAddButton.setOnAction(e -> {
		String inputString = new String(ExerciseNameInput.getText() + ", " + ExerciseSetsInput.getText() + " Sets");
		entries.add(inputString);
	});
	
	ExerciseRemoveButton.setOnAction(e -> {
		entries.remove(selectionModel.getSelectedItem());
	});

	ExerciseAddButton.disableProperty().bind(nameValid.and(setsValid).not());
	
	/*
	 * 
		System.out.println("Name: " + ExerciseNameInput.textProperty().isEmpty().toString());
		System.out.println("Sets: " + ExerciseSetsInput.textProperty().isEmpty().toString());
	 */
	


	//ExerciseAddButton.disableProperty().bind(ExerciseSetsInput.textProperty().isEmpty()); //buggy fix pls
	
	//ExerciseRemoveButton.disableProperty().bind(Bindings.isNull(selectionModel.selectedItemProperty()));
	
	
		/*
	 * ObservableCollection For Exercise
	 */

	ObservableList<String> TimerOptions = 
		    FXCollections.observableArrayList(
		    		"20 seconds",
			        "30 seconds",
			        "40 seconds",
			        "1 minute",
			        "2 minute",
			        "3 minute");

	ButtonEnd.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			System.exit(0);
		}
	});

	ObservableList<String> PauseOptions = 
		    FXCollections.observableArrayList(
		        "20 seconds",
		        "30 seconds",
		        "40 seconds",
		        "1 minute",
		        "2 minute",
		        "3 minute");
	final ComboBox<String> TimerCombo = new ComboBox<>(TimerOptions);
	final ComboBox<String> PauseCombo = new ComboBox<>(PauseOptions);
	
	final GridPane SetupPane = new GridPane();
	SetupPane.setGridLinesVisible(true);
	
	for (int i = 0; i < 5; i++) {
        ColumnConstraints column = new ColumnConstraints(maxWidth/3, maxWidth/3, Double.MAX_VALUE);
        column.setHgrow(Priority.ALWAYS);
        column.setHalignment(HPos.CENTER);
        //RowConstraints row = new RowConstraints(800/3, 800/3, Double.MAX_VALUE);
        //row.setVgrow(Priority.ALWAYS);
        //row.setValignment(VPos.CENTER);
        SetupPane.getColumnConstraints().add(column);
        //StartPane.getRowConstraints().add(row);
    }
	RowConstraints SetupPaneRow1 = new RowConstraints(100, 100, Double.MAX_VALUE);
	RowConstraints SetupPaneRow2 = new RowConstraints(100, 100, Double.MAX_VALUE);
	RowConstraints SetupPaneRow3 = new RowConstraints(400, 100, Double.MAX_VALUE);
	RowConstraints SetupPaneRow4 = new RowConstraints(100, 100, Double.MAX_VALUE);
	RowConstraints SetupPaneRow5 = new RowConstraints(100, 100, Double.MAX_VALUE);
	
    SetupPane.getRowConstraints().addAll(SetupPaneRow1, SetupPaneRow2, SetupPaneRow3, SetupPaneRow4, SetupPaneRow5);
    SetupPane.add(SetupScreenTitleLabel, 0, 0, 5, 1);
	SetupPane.add(ExerciseNameInput, 0, 1, 3, 1);
	SetupPane.add(SetupListView, 0, 2, 4, 1);
	SetupPane.add(ExerciseRemoveButton, 4, 2);
	SetupPane.add(ExerciseSetsInput, 3, 1);
    SetupPane.add(ExerciseAddButton, 4, 1);
	SetupPane.add(TimerCombo, 1, 3);
	SetupPane.add(PauseCombo, 3, 3);
	SetupPane.add(ButtonGoToStartScreen, 0, 4);
	SetupPane.add(ButtonGoToWorkoutScreen, 4, 4);
	
	VBox layoutSetup = new VBox();
	layoutSetup.getChildren().addAll(SetupPane);
	sceneSetup = new Scene(layoutSetup, 600, 800);
	
	/*--------------------------------------------------------------------------------------------------*/
	//sceneWorkout
	final Label WorkoutScreenTitleLabel = new Label("Complete your Workout");
	Timer timerObj = new Timer("rauchen");
	final Button ButtonGoToSetupScreen2 = new Button("SetupScreen");
	ButtonGoToSetupScreen2.setOnAction(e -> primaryStage.setScene(sceneSetup));
	final Button ButtonExit = new Button("Exit");
	ButtonExit.setOnAction(e -> Platform.exit());
	final Label WorkoutTimer = new Label("TimerLabelPlaceholder");
	final Button TimerPlay = new Button("Play Timer");
	final Button TimerPause = new Button("Pause Timer");
	//property binding liste wie in der übung für die übungen

	ListView<String> WorkoutListView = new ListView<>(entries);
	SelectionModel<String> testModel = WorkoutListView.getSelectionModel();
	TimerPlay.setOnAction(e -> {
		
	});
	
	
	final GridPane WorkoutPane = new GridPane();
	WorkoutPane.setGridLinesVisible(true);
	
	for (int i = 0; i < 5; i++) {
        ColumnConstraints column = new ColumnConstraints(maxWidth/3, maxWidth/3, Double.MAX_VALUE);
        column.setHgrow(Priority.ALWAYS);
        column.setHalignment(HPos.CENTER);
        //RowConstraints row = new RowConstraints(800/3, 800/3, Double.MAX_VALUE);
        //row.setVgrow(Priority.ALWAYS);
        //row.setValignment(VPos.CENTER);
        WorkoutPane.getColumnConstraints().add(column);
        //StartPane.getRowConstraints().add(row);
    }
	RowConstraints WorkoutPaneRow1 = new RowConstraints(100, 100, Double.MAX_VALUE);
	RowConstraints WorkoutPaneRow2 = new RowConstraints(100, 100, Double.MAX_VALUE);
	RowConstraints WorkoutPaneRow3 = new RowConstraints(100, 100, Double.MAX_VALUE);
	RowConstraints WorkoutPaneRow4 = new RowConstraints(400, 100, Double.MAX_VALUE);
	RowConstraints WorkoutPaneRow5 = new RowConstraints(100, 100, Double.MAX_VALUE);
	WorkoutPane.getRowConstraints().addAll(WorkoutPaneRow1, WorkoutPaneRow2, WorkoutPaneRow3, WorkoutPaneRow4, WorkoutPaneRow5);

	WorkoutPane.add(WorkoutScreenTitleLabel, 2, 0);
	WorkoutPane.add(ButtonGoToSetupScreen2, 0, 4);
	WorkoutPane.add(ButtonExit, 4, 4);
	WorkoutPane.add(WorkoutListView, 0, 2, 4, 2);
	WorkoutPane.add(WorkoutTimer, 1, 1);
	WorkoutPane.add(TimerPlay, 3, 1);
	WorkoutPane.add(TimerPause, 4, 1);
	
	VBox layoutWorkout = new VBox();
	layoutWorkout.getChildren().addAll(WorkoutPane);
	sceneWorkout = new Scene(layoutWorkout, 600, 800);
	
	/*--------------------------------------------------------------------------------------------------*/

	primaryStage.setScene(sceneStart);
	primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}

/* Breite und Höhe von GridPane setten
 for (int i = 0; i < 3; i++) {
        ColumnConstraints column = new ColumnConstraints(maxWidth/3, maxWidth/3, Double.MAX_VALUE);
        column.setHgrow(Priority.ALWAYS);
        column.setHalignment(HPos.CENTER);
        //RowConstraints row = new RowConstraints(800/3, 800/3, Double.MAX_VALUE);
        //row.setVgrow(Priority.ALWAYS);
        //row.setValignment(VPos.CENTER);
        StartPane.getColumnConstraints().add(column);
        //StartPane.getRowConstraints().add(row);
    }
*/
