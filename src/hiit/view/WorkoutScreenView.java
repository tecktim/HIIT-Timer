package hiit.view;

import java.util.Observable;
import java.util.Observer;

import hiit.Exercise;
import hiit.controller.WorkoutScreenController;
import hiit.model.Model;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WorkoutScreenView implements Observer {
	private Model model;
	private WorkoutScreenController controller;
	private Scene scene;
	private Stage primaryStage;
	
	public WorkoutScreenView(Stage primaryStage, Model model, WorkoutScreenController controller) {
		this.model = model;
		this.controller = controller;
		this.primaryStage = primaryStage;
	
		final Label WorkoutScreenTitleLabel = new Label("Complete your Workout");
		final Button ButtonGoToSetupScreen = new Button("SetupScreen");
		final Button ButtonEnd = new Button("Exit");
		final Label WorkoutTimer = new Label("TimerLabelPlaceholder");
		final Button TimerPlay = new Button("Play Timer");
		final Button TimerPause = new Button("Pause Timer");
		
		ButtonEnd.setOnAction(e -> this.controller.onClickExit(e));		
		ButtonGoToSetupScreen.setOnAction(e -> this.controller.onClickSetup(e));		

		
		ListView<String> WorkoutListView = new ListView<String>(model.stringEntries);
		SelectionModel<String> testModel = WorkoutListView.getSelectionModel();
		
		final GridPane WorkoutPane = new GridPane();
		WorkoutPane.setGridLinesVisible(true);
		
		for (int i = 0; i < 5; i++) {
	        ColumnConstraints column = new ColumnConstraints(WorkoutPane.getMaxWidth()/3, WorkoutPane.getMaxWidth()/3, Double.MAX_VALUE);
	        column.setHgrow(Priority.ALWAYS);
	        column.setHalignment(HPos.CENTER);
	        WorkoutPane.getColumnConstraints().add(column);
	    }
		RowConstraints WorkoutPaneRow1 = new RowConstraints(100, 100, Double.MAX_VALUE);
		RowConstraints WorkoutPaneRow2 = new RowConstraints(100, 100, Double.MAX_VALUE);
		RowConstraints WorkoutPaneRow3 = new RowConstraints(100, 100, Double.MAX_VALUE);
		RowConstraints WorkoutPaneRow4 = new RowConstraints(400, 100, Double.MAX_VALUE);
		RowConstraints WorkoutPaneRow5 = new RowConstraints(100, 100, Double.MAX_VALUE);
		WorkoutPane.getRowConstraints().addAll(WorkoutPaneRow1, WorkoutPaneRow2, WorkoutPaneRow3, WorkoutPaneRow4, WorkoutPaneRow5);

		WorkoutPane.add(WorkoutScreenTitleLabel, 2, 0);
		WorkoutPane.add(ButtonGoToSetupScreen, 0, 4);
		WorkoutPane.add(ButtonEnd, 4, 4);
		WorkoutPane.add(WorkoutListView, 0, 2, 4, 2);
		WorkoutPane.add(WorkoutTimer, 1, 1);
		WorkoutPane.add(TimerPlay, 3, 1);
		WorkoutPane.add(TimerPause, 4, 1);
		
		VBox layoutWorkout = new VBox();
		layoutWorkout.getChildren().addAll(WorkoutPane);
		scene = new Scene(layoutWorkout, 600, 800);
		this.primaryStage.setScene(scene);
	}
	
	public void show()
	{
		this.primaryStage.show();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
}
