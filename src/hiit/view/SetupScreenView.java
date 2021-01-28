package hiit.view;

import java.util.Observable;
import java.util.Observer;

import hiit.CustomButton;
import hiit.CustomTextField;
import hiit.controller.SetupScreenController;
import hiit.model.Model;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class SetupScreenView implements Observer {
	private final double WIDTH = 900;
	private final double HEIGHT = 900;
	private final int COLUMN_COUNT = 9;
	private final int ROW_COUNT = 9;

	private Model model;
	private SetupScreenController controller;
	private Scene scene;
	private Stage primaryStage;

	public SetupScreenView(Stage primaryStage, Model model, SetupScreenController controller) {
		this.model = model;
		this.controller = controller;
		this.primaryStage = primaryStage;

		// this.model.addEntry("asd", "12", "12");

		final Label setupScreenTitleLabel = new Label("Setup your workout");
		final CustomTextField exerciseNameInput = new CustomTextField();
		final Label exerciseNameLabel = new Label("Name");
		final Label exerciseSetsLabel = new Label("Sets");
		final Label exerciseRepsLabel = new Label("Reps");
		final CustomTextField exerciseSetsInput = new CustomTextField();
		final CustomTextField exerciseRepsInput = new CustomTextField();

		final CustomButton exerciseAddButton = new CustomButton("Add");
		final CustomButton buttonGoToStartScreen = new CustomButton("Start");
		final CustomButton buttonGoToWorkoutScreen = new CustomButton("Workout");
		final CustomButton exerciseRemoveButton = new CustomButton("Remove");
		final ComboBox<Integer> pauseCombo = new ComboBox<>(model.pauseOptions);
		pauseCombo.getStyleClass().add("custom-combo-box");

		setupScreenTitleLabel.getStyleClass().add("title-label");
		exerciseNameLabel.getStyleClass().add("field-label");
		exerciseSetsLabel.getStyleClass().add("field-label");
		exerciseRepsLabel.getStyleClass().add("field-label");

		pauseCombo.setPromptText("Pause (in seconds)");

		ListView<String> setupListView = new ListView<>(model.stringEntries);
		SelectionModel<String> selectionModel = setupListView.getSelectionModel();
		setupListView.getStyleClass().add("custom-list-view");

		buttonGoToStartScreen.setOnAction(e -> controller.onClickStart(e));
		buttonGoToWorkoutScreen.setOnAction(e -> controller.onClickWorkout((e), pauseCombo));

		exerciseAddButton.setOnAction(e -> controller.onClickAddExercise(e, exerciseNameInput.getText(),
				exerciseSetsInput.getText(), exerciseRepsInput.getText()));
		exerciseRemoveButton.setOnAction(e -> controller.onClickRemoveExercise(e, selectionModel.getSelectedIndex()));

		pauseCombo.setOnAction(e -> controller.onPauseDurationSelected(e));

		BooleanBinding nameValid = exerciseNameInput.textProperty().isNotEmpty()
				.and(exerciseNameInput.textProperty().length().greaterThan(0));

		BooleanBinding setsValid = exerciseSetsInput.textProperty().isNotEmpty()
				.and(exerciseSetsInput.textProperty().length().greaterThan(0));

		BooleanBinding repsValid = exerciseRepsInput.textProperty().isNotEmpty()
				.and(exerciseRepsInput.textProperty().length().greaterThan(0));

		exerciseAddButton.disableProperty().bind(nameValid.and(repsValid).and(setsValid).not());
		exerciseSetsInput.textProperty().addListener(this.controller.valideInputOnChange(exerciseSetsInput));
		exerciseRepsInput.textProperty().addListener(this.controller.valideInputOnChange(exerciseRepsInput));

		buttonGoToWorkoutScreen.disableProperty().bind(this.model.workoutCreatedProperty().not());

		final GridPane gridPane = new GridPane();

		double colWidth = WIDTH / COLUMN_COUNT;
		for (int i = 0; i < 9; i++) {
			ColumnConstraints column = new ColumnConstraints(colWidth, colWidth, colWidth);
			column.setHalignment(HPos.CENTER);
			gridPane.getColumnConstraints().add(column);
		}

		double rowHeight = HEIGHT / ROW_COUNT;
		for (int i = 0; i < 9; i++) {
			RowConstraints row = new RowConstraints(rowHeight, rowHeight, rowHeight);
			gridPane.getRowConstraints().add(row);
		}
		gridPane.add(setupScreenTitleLabel, 0, 0, 9, 1);

		gridPane.add(exerciseNameLabel, 0, 1, 1, 1);
		gridPane.add(exerciseNameInput, 1, 1, 2, 1);

		gridPane.add(exerciseSetsLabel, 3, 1, 1, 1);
		gridPane.add(exerciseSetsInput, 4, 1, 1, 1);
		gridPane.add(exerciseRepsLabel, 5, 1, 1, 1);
		gridPane.add(exerciseRepsInput, 6, 1, 1, 1);

		gridPane.add(setupListView, 0, 2, COLUMN_COUNT - 2, 5);
		gridPane.add(exerciseRemoveButton, COLUMN_COUNT - 2, (int) ROW_COUNT / 2, 2, 1);
		gridPane.getStyleClass().add("grid-pane");

		gridPane.add(exerciseAddButton, 7, 1, 2, 1);
		gridPane.add(pauseCombo, 3, ROW_COUNT - 2, 3, 1);

		gridPane.add(buttonGoToStartScreen, 0, ROW_COUNT - 1, 3, 1);
		gridPane.add(buttonGoToWorkoutScreen, COLUMN_COUNT - 3, ROW_COUNT - 1, 3, 1);
		VBox layoutSetup = new VBox();
		layoutSetup.getChildren().addAll(gridPane);
		scene = new Scene(layoutSetup, WIDTH, HEIGHT);
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
