package hiit.controller;


import hiit.CustomButton;
import hiit.model.Model;
import hiit.view.SetupScreenView;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

public class WorkoutScreenController{

	private Model model;
	private Stage primaryStage;

	private Thread thread;

	public WorkoutScreenController(Stage primaryStage, Model model) {
		this.model = model;
		this.primaryStage = primaryStage;
	}

	// Exit Button
	public void onClickExit(ActionEvent e) {
		Platform.exit();
	}

	// Setup Button
	public void onClickSetup(ActionEvent e) {

		SetupScreenController setupScreenController = new SetupScreenController(this.primaryStage, this.model);
		SetupScreenView setupScreenView = new SetupScreenView(this.primaryStage, this.model, setupScreenController);

		setupScreenView.show();
	}

	// Button to pause or resume the "pause timer"
	public void onClickPauseTimerAction(ActionEvent e) {
		CustomButton button = (CustomButton) e.getSource();
		boolean newValue = !this.model.pauseStop;
		this.model.setPauseStop(newValue);
		button.setText(button.getText().equals("Pause") ? "Resume" : "Pause");
	}

	// Button to initially start the pause timer, every time a set is done
	public void onClickStartTimerAction(ActionEvent e, ProgressIndicator pauseIndicator, Button timerPlay,
			Button timerPause) {
		Task<Integer> timerTask = this.model.timerTask();

		timerPause.setDisable(false);
		this.model.workoutDoneProperty().set(false);
		this.model.setPauseStop(false);
		
		// Disable SetDone Button when timerTask is running
		timerTask.setOnRunning((WorkerStateEvent event) -> {
			timerPlay.disableProperty().bind(timerTask.progressProperty().greaterThanOrEqualTo(100).not());
		});
		pauseIndicator.progressProperty().bind(timerTask.progressProperty());
		thread = new Thread(timerTask);
		thread.start();
		
		// To Do on timerTask done
		timerTask.setOnSucceeded((WorkerStateEvent event) -> {
			timerPlay.disableProperty().bind(this.model.workoutCreatedProperty().not());
			pauseIndicator.setVisible(false);
			timerPause.setDisable(true);
			this.model.workoutDoneProperty().set(this.model.entries.size() == 0);
			try {
				thread.join();
			} catch (Exception ex) {

			}

		});

		pauseIndicator.setVisible(true);

		this.model.decrementSets();
	}

}
