package hiit.controller;

import java.util.Observable;
import java.util.Observer;

import hiit.model.Model;
import hiit.view.StartScreenView;
import hiit.view.WorkoutScreenView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class SetupScreenController implements Observer {

	private Stage primaryStage;
	private Model model;

	public SetupScreenController(Stage primaryStage, Model model) {
		this.model = model;
		this.primaryStage = primaryStage;
	}

	public void onClickStart(ActionEvent e) {
		StartScreenController startScreenController = new StartScreenController(this.primaryStage, this.model);
		StartScreenView startScreenView = new StartScreenView(this.primaryStage, this.model, startScreenController);

		startScreenView.show();
	}

	public void onClickWorkout(ActionEvent e, ComboBox<Integer> pauseCombo) {
		if (pauseCombo.getSelectionModel().getSelectedItem() == null) {
			pauseCombo.getStyleClass().remove("custom-combo-box");
			pauseCombo.getStyleClass().add("pause-combo-error");
			return;
		} else {
			this.model.setPauseTime(pauseCombo.getSelectionModel().getSelectedItem());
		}

		WorkoutScreenController workoutScreenController = new WorkoutScreenController(this.primaryStage, this.model);
		WorkoutScreenView workoutScreenView = new WorkoutScreenView(this.primaryStage, this.model,
				workoutScreenController);

		workoutScreenView.show();
	}

	public void onClickAddExercise(ActionEvent e, String name, String sets, String reps) {
		this.model.addEntry(name, sets, reps);
	}

	// ExerciseRemoveButton.setOnAction(e -> controller.onClickRemoveExercise(e,
	// selectionModel.getSelectedIndex()));
	public void onClickRemoveExercise(ActionEvent e, int index) {
		if (index > -1) {
			this.model.removeEntry(index);
		}
	}

//	ExerciseRemoveButton.setOnAction(e -> controller.onClickRemoveExercise(e, selectionModel.getSelectedIndex()));
	public void onPauseDurationSelected(ActionEvent e) {
		@SuppressWarnings("unchecked")
		ComboBox<Integer> comboBox = ((ComboBox<Integer>) e.getSource());
		comboBox.getStyleClass().remove("pause-combo-error");
		comboBox.getStyleClass().add("custom-combo-box");
	}

	// https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
	public ChangeListener<String> valideInputOnChange(TextField textField) {
		return new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					textField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		};
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
