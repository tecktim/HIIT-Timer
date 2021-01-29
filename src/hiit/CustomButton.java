package hiit;

import javafx.scene.control.Button;

//Custom Button Class helping us resize the buttons globally
public class CustomButton extends Button {

	private final double MAX_WIDTH = 200;
	private final double MAX_HEIGHT = 60;

	public CustomButton(String label) {
		super(label);
		this.setMaxSize(MAX_WIDTH, MAX_HEIGHT);
	}

	public CustomButton() {
		super();
		this.setMaxSize(MAX_WIDTH, MAX_HEIGHT);
	}

}
