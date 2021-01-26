package hiit.model;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import hiit.Exercise;

import hiit.view.SetupScreenView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;

public class Model extends Observable{
	public ObservableList<Integer> pauseOptions = 
		    FXCollections.observableArrayList(
		    	5,
		        20,
		        30,
		        40,
		        60,
		        120,
		        180);
	
	
	private Exercise[] exercises = {};
	private String[] stringExercises = {};
	public ObservableList<Exercise> entries = FXCollections.observableArrayList(exercises);
	public ObservableList<String> stringEntries = FXCollections.observableArrayList(stringExercises);
	public int state = 0;
	private int pauseTime;
	private boolean pauseStop = false;
	
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
	
	public void decrementSets() {
		int i = this.entries.get(0).getSets();
		i--;
		this.stringEntries.set(0, new String(this.entries.get(0).getName() + " Sets: " + i + " Reps: " + this.entries.get(0).getReps()));
		this.entries.get(0).setSets(i);

		if (this.entries.get(0).getSets() == 0) {
			this.stringEntries.remove(0);
			this.entries.remove(0);
		}
	}
	
	public final Task<Integer> timerTask = new Task<Integer>() {
		protected Integer call() throws Exception {
			int i;
			for (i=1;i<(pauseTime*10)+pauseTime;i++) {
				Thread.sleep(100);
				if (isCancelled()) {
					break;
				}
				updateProgress(i, (pauseTime*10));
				System.out.println(i);
			}
			return i;
		}
	};
	
	/*//https://stackoverflow.com/questions/12465127/simple-java-countdown
	public void runTimer(){
        int i = this.getPauseTime();
         while (i>0 && pauseStop == false){
          System.out.println("Remaining: "+i+" seconds");
          try {
            i--;
            Thread.sleep(1000L);    // 1000L = 1000ms = 1 second
           }
           catch (InterruptedException e) {
               //I don't think you need to do anything for your particular problem
           }
         }
    }
	*/
	
	public void setPauseStop(Boolean bool) {
		this.pauseStop = bool;
	}
	
	public void setPauseTime(int pauseTime) {
		this.pauseTime = pauseTime;
	}
	
	public int getPauseTime() {
		return this.pauseTime;
	}
}
