package hiit;

//Class representing an Exercise, contain a name, sets, repetitions.
public class Exercise {

	private String name;
	private int sets;
	private int reps;

	public Exercise(String name, int sets, int reps) {
		this.name = name;
		this.sets = sets;
		this.reps = reps;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

}
