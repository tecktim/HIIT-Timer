package hiit.view;



import java.util.Observable;
import java.util.Observer;

import hiit.Exercise;
import hiit.controller.SetupScreenController;
import hiit.model.Model;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SetupScreenView implements Observer {
	private Model model;
	private SetupScreenController controller;
	private Scene scene;
	private Stage primaryStage;
	
	public SetupScreenView(Stage primaryStage, Model model, SetupScreenController controller) {
		this.model = model;
		this.controller = controller;
		this.primaryStage = primaryStage;
		
		final Label setupScreenTitleLabel = new Label("Setup yous Workout");
		final TextField exerciseNameInput = new TextField();
		final Label exerciseNameLabel = new Label("Setup yous Exercises");
		final TextField exerciseSetsInput = new TextField();
		final TextField exerciseRepsInput = new TextField();

		final Button exerciseAddButton = new Button("Add");
		final Button buttonGoToStartScreen = new Button("StartScreen");
		final Button buttonGoToWorkoutScreen = new Button("WorkoutScreen");
		final Button exerciseRemoveButton = new Button("Remove");
		final ComboBox<String> pauseCombo = new ComboBox<>(model.pauseOptions);
		pauseCombo.setPromptText("Pause Time");
		
		ListView<String> setupListView = new ListView<>(model.stringEntries);
		SelectionModel<String> selectionModel = setupListView.getSelectionModel();
		
		
		buttonGoToStartScreen.setOnAction(e -> controller.onClickStart(e));
		buttonGoToWorkoutScreen.setOnAction(e -> controller.onClickWorkout(e));
		
		exerciseAddButton.setOnAction(e -> controller.onClickAddExercise(e, exerciseNameInput.getText(), exerciseSetsInput.getText(), exerciseRepsInput.getText()));
		exerciseRemoveButton.setOnAction(e -> controller.onClickRemoveExercise(e, selectionModel.getSelectedIndex()));
		
		BooleanBinding nameValid = exerciseNameInput.textProperty()
				.isNotEmpty()
				.and(exerciseNameInput.textProperty().length().greaterThan(0));

		BooleanBinding setsValid = exerciseSetsInput.textProperty()
				.isNotEmpty()
				.and(exerciseSetsInput.textProperty().length().greaterThan(0));
		
		BooleanBinding repsValid = exerciseRepsInput.textProperty()
				.isNotEmpty()
				.and(exerciseRepsInput.textProperty().length().greaterThan(0));
		
		exerciseAddButton.disableProperty().bind(nameValid.and(repsValid).and(setsValid).not());
		
		exerciseSetsInput.textProperty().addListener(this.controller.valideInputOnChange(exerciseSetsInput));
		exerciseRepsInput.textProperty().addListener(this.controller.valideInputOnChange(exerciseRepsInput));
		
		final GridPane setupPane = new GridPane();
		setupPane.setGridLinesVisible(true);
		
		for (int i = 0; i < 5; i++) {
	        ColumnConstraints column = new ColumnConstraints(setupPane.getMaxWidth()/3, setupPane.getMaxWidth()/3, Double.MAX_VALUE);
	        column.setHgrow(Priority.ALWAYS);
	        column.setHalignment(HPos.CENTER);
	        setupPane.getColumnConstraints().add(column);
	    }
		RowConstraints setupPaneRow1 = new RowConstraints(100, 100, Double.MAX_VALUE);
		RowConstraints setupPaneRow2 = new RowConstraints(100, 100, Double.MAX_VALUE);
		RowConstraints setupPaneRow3 = new RowConstraints(400, 100, Double.MAX_VALUE);
		RowConstraints setupPaneRow4 = new RowConstraints(100, 100, Double.MAX_VALUE);
		RowConstraints setupPaneRow5 = new RowConstraints(100, 100, Double.MAX_VALUE);
		
	    setupPane.getRowConstraints().addAll(setupPaneRow1, setupPaneRow2, setupPaneRow3, setupPaneRow4, setupPaneRow5);
	    setupPane.add(setupScreenTitleLabel, 0, 0, 5, 1);
	    
	    setupPane.add(exerciseNameLabel, 0, 1, 1, 1);
		setupPane.add(exerciseNameInput, 1, 1, 2, 1);
		
		setupPane.add(setupListView, 0, 2, 4, 1);
		setupPane.add(exerciseRemoveButton, 4, 2);
		setupPane.add(exerciseSetsInput, 2, 1);
		setupPane.add(exerciseRepsInput, 3, 1);

	    setupPane.add(exerciseAddButton, 4, 1);
		setupPane.add(pauseCombo, 1, 3, 3, 1);
		setupPane.add(buttonGoToStartScreen, 0, 4);
		setupPane.add(buttonGoToWorkoutScreen, 4, 4);
		
		VBox layoutSetup = new VBox();
		layoutSetup.getChildren().addAll(setupPane);
		scene = new Scene(layoutSetup, 600, 800);
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
