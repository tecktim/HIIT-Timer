package hiit;

import javafx.scene.control.TextField;

//Custom Text Field Class helping us to globally resize the TextFields
public class CustomTextField extends TextField {

	private final double MAX_HEIGHT = 60;

	public CustomTextField() {
		super();
		this.setMaxHeight(MAX_HEIGHT);
		this.setMinHeight(MAX_HEIGHT);
		this.setPrefHeight(MAX_HEIGHT);
	}

	public CustomTextField(String text) {
		super(text);
		this.setMaxHeight(MAX_HEIGHT);
		this.setMinHeight(MAX_HEIGHT);
		this.setPrefHeight(MAX_HEIGHT);
	}

}
