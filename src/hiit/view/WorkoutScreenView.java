package hiit.view;

import java.util.Observable;
import java.util.Observer;

import hiit.CustomButton;
import hiit.controller.WorkoutScreenController;
import hiit.model.Model;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class WorkoutScreenView implements Observer {
	private final double WIDTH = 900;
	private final double HEIGHT = 900;
	private final int COLUMN_COUNT = 9;
	private final int ROW_COUNT = 9;

	private Model model;
	private WorkoutScreenController controller;
	private Scene scene;
	private Stage primaryStage;

	public WorkoutScreenView(Stage primaryStage, Model model, WorkoutScreenController controller) {
		this.model = model;
		this.controller = controller;
		this.primaryStage = primaryStage;

		final GridPane gridPane = new GridPane();

		final Label workoutScreenTitleLabel = new Label("Complete your workout");
		final CustomButton buttonGoToSetupScreen = new CustomButton("Setup");
		final CustomButton buttonEnd = new CustomButton("Quit");
		final CustomButton timerPlay = new CustomButton("Set done");
		final CustomButton timerPause = new CustomButton("Pause");
		final ProgressIndicator pauseIndicator = new ProgressIndicator();
		pauseIndicator.setMaxHeight(120);
		pauseIndicator.setMaxWidth(120);
		pauseIndicator.setMinHeight(120);
		pauseIndicator.setMinWidth(120);
		pauseIndicator.setPrefHeight(120);
		pauseIndicator.setPrefWidth(120);

		pauseIndicator.setVisible(false);
		timerPause.setDisable(true);

		workoutScreenTitleLabel.getStyleClass().add("title-label");

		// Handling exit and setup button
		buttonEnd.setOnAction(e -> this.controller.onClickExit(e));
		buttonGoToSetupScreen.setOnAction(e -> this.controller.onClickSetup(e));
		
		// Disabling setup button when the workout has been started but not finished yet
		buttonGoToSetupScreen.disableProperty().bind(this.model.workoutDoneProperty().not());

		// Handling "Set Done" button, starting the timerTask and disabling the button
		timerPlay.setOnAction(e -> this.controller.onClickStartTimerAction((e), pauseIndicator, timerPlay, timerPause));
		
		// Handling "Pause" and "Resume" button to elongate the pause if wanted
		timerPause.setOnAction(e -> this.controller.onClickPauseTimerAction(e));
		
		ListView<String> workoutListView = new ListView<String>(model.stringEntries);
		
		// Adding css controllers
		workoutListView.getStyleClass().add("custom-list-view");
		gridPane.getStyleClass().add("grid-pane");
		
		// Adding columns and rows to the gridPane
		double colWidth = WIDTH / 9;
		for (int i = 0; i < 9; i++) {
			ColumnConstraints column = new ColumnConstraints(colWidth, colWidth, colWidth);
			column.setHalignment(HPos.CENTER);
			gridPane.getColumnConstraints().add(column);
		}
		double rowHeight = HEIGHT / 9;
		for (int i = 0; i < 9; i++) {
			RowConstraints row = new RowConstraints(rowHeight, rowHeight, rowHeight);
			gridPane.getRowConstraints().add(row);
		}

		// Adding every element to the gridPane
		gridPane.add(workoutScreenTitleLabel, 0, 0, 9, 1);
		gridPane.add(workoutListView, 0, 2, COLUMN_COUNT - 2, 5);
		gridPane.add(pauseIndicator, COLUMN_COUNT - 2, 5, 2, 2);
		gridPane.add(timerPlay, COLUMN_COUNT - 2, 2, 2, 1);
		gridPane.add(timerPause, COLUMN_COUNT - 2, 3, 2, 1);
		gridPane.add(buttonGoToSetupScreen, 0, ROW_COUNT - 1, 3, 1);
		gridPane.add(buttonEnd, COLUMN_COUNT - 3, ROW_COUNT - 1, 3, 1);

		VBox layoutWorkout = new VBox();
		layoutWorkout.getChildren().addAll(gridPane);
		scene = new Scene(layoutWorkout, WIDTH, HEIGHT);
		scene.getStylesheets().add("css/style.css");
		this.primaryStage.setScene(scene);

	}

	public void show() {
		this.primaryStage.show();
	}

	@Override
	public void update(Observable o, Object arg) {

	}
}
