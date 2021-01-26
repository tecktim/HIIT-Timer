package hiit.controller;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import hiit.model.Model;
import hiit.view.SetupScreenView;
import hiit.view.StartScreenView;
import hiit.view.WorkoutScreenView;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class WorkoutScreenController implements Observer {

	private Model model;
	private Stage primaryStage;
	
	private Thread thread;

	public WorkoutScreenController(Stage primaryStage, Model model) {
		this.model = model;
		this.primaryStage = primaryStage;
	}

	public void onClickExit(ActionEvent e) {
		Platform.exit();
	}

	public void onClickSetup(ActionEvent e) {
		SetupScreenController setupScreenController = new SetupScreenController(this.primaryStage, this.model);
		SetupScreenView setupScreenView = new SetupScreenView(this.primaryStage, this.model, setupScreenController);

		setupScreenView.show();
	}

	public void onClickStartTimerAction(ActionEvent e, ProgressIndicator pauseIndicator, Button timerPlay) {
		if (this.model.timerTask.isRunning()) {
			return;
		} else {
			this.model.timerTask.setOnRunning((WorkerStateEvent event) -> {
				timerPlay.disableProperty().bind(this.model.timerTask.progressProperty().greaterThanOrEqualTo(100).not());
			});
			pauseIndicator.progressProperty().bind(this.model.timerTask.progressProperty());
			thread = new Thread(this.model.timerTask);
			thread.start();
			
			this.model.timerTask.setOnSucceeded((WorkerStateEvent event) -> {
				timerPlay.disableProperty().bind(this.model.timerTask.progressProperty().greaterThan(100));
				pauseIndicator.setVisible(false);
				thread.stop();
			});
			pauseIndicator.setVisible(true);
		}
		this.model.decrementSets();
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
