package hiit.model;

import java.util.Observable;
import hiit.Exercise;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

@SuppressWarnings("deprecation")
public class Model extends Observable {
	private static boolean pauseStopThread = false;

	// The 5 second timer is left there for testing purposes
	public ObservableList<Integer> pauseOptions = FXCollections.observableArrayList(5, 20, 30, 40, 60, 120, 180);

	private Exercise[] exercises = {};
	private String[] stringExercises = {};
	public ObservableList<Exercise> entries = FXCollections.observableArrayList(exercises);
	public ObservableList<String> stringEntries = FXCollections.observableArrayList(stringExercises);
	public int state = 0;
	private int pauseTime;
	public boolean pauseStop = false;
	private BooleanProperty workoutDone;
	private BooleanProperty workoutCreated;

	public void addEntry(String name, String sets, String reps) {
		Exercise exercise = new Exercise(name, Integer.parseInt(sets), Integer.parseInt(reps));
		this.stringEntries.add(name + " Sets: " + sets + " Reps: " + reps);
		this.entries.add(exercise);
		this.workoutCreated.set(true);
	}

	public void removeEntry(int index) {
		this.entries.remove(index);
		this.stringEntries.remove(index);
		this.workoutCreated.set(this.entries.size() > 0);
	}

	public void decrementSets() {
		int i = this.entries.get(0).getSets();
		i--;
		this.stringEntries.set(0,
				new String(this.entries.get(0).getName() + " Sets: " + i + " Reps: " + this.entries.get(0).getReps()));
		this.entries.get(0).setSets(i);

		if (i == 0) {
			this.removeEntry(0);
		}
	}
	
	// Created with the help of the lecture slides
	// timerTask to handle the pauseTime
	public Task<Integer> timerTask() {
		return new Task<Integer>() {
			protected Integer call() throws Exception {

				int i;
				for (i = 1; i < (pauseTime * 10) + pauseTime; i++) {

					Thread.sleep(100);
					if (pauseStopThread) {
						// Warten, bis pause aufgehoben
						i--;
						continue;
					}
					if (isCancelled()) {
						break;
					}
					updateProgress(i, (pauseTime * 10));
				}
				return i;
			}
		};
	}

	// Boolean to handle pausing and resuming the pauseTimer
	public void setPauseStop(Boolean pauseStop) {
		pauseStopThread = pauseStop;
		this.pauseStop = pauseStopThread;
	}

	public void setPauseTime(int pauseTime) {
		this.pauseTime = pauseTime;
	}

	public int getPauseTime() {
		return this.pauseTime;
	}

	// BooleanProperty helping us to disable the (gobackto-)setupButton when you are working out by using binding
	public final BooleanProperty workoutDoneProperty() {
		if (this.workoutDone == null) {
			this.workoutDone = new SimpleBooleanProperty(true);
		}
		return this.workoutDone;
	}

	// BooleanProperty helping us disabling the (goto-)workoutButton when you havent yet added atleast one exercise by using binding
	public final BooleanProperty workoutCreatedProperty() {
		if (this.workoutCreated == null) {
			this.workoutCreated = new SimpleBooleanProperty(false);
		}
		return this.workoutCreated;
	}
}
