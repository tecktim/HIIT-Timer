package hiit.model;
import java.util.ArrayList;
import java.util.Observable;

import hiit.Exercise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model extends Observable {

	private int pauseTimer;
	public ObservableList<String> pauseOptions = 
		    FXCollections.observableArrayList(
		        "20 seconds",
		        "30 seconds",
		        "40 seconds",
		        "1 minute",
		        "2 minute",
		        "3 minute");
	
	private Exercise[] exercises = {};
	private String[] stringExercises = {};
	public ObservableList<Exercise> entries = FXCollections.observableArrayList(exercises);
	public ObservableList<String> stringEntries = FXCollections.observableArrayList(stringExercises);
	public int state = 0;
	
	
	
	public Model() {
		pauseTimer = 30;
		
		
	}
	
	public void addEntry(String name, String sets, String reps)
	{
		Exercise exercise = new Exercise(name, Integer.parseInt(sets), Integer.parseInt(reps));
		this.stringEntries.add(name + " Sets: " + sets + " Reps: " + reps);
		this.entries.add(exercise);
	}
	
	public void removeEntry(int index)
	{
		this.entries.remove(index);
		this.stringEntries.remove(index);
	}
	
	public void tick() {
		if (pauseTimer > 0) {
			pauseTimer--;
		}
	}
}
