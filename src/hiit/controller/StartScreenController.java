package hiit.controller;


import hiit.model.Model;
import hiit.view.SetupScreenView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


public class StartScreenController{

	private Model model;
	private Stage primaryStage;

	public StartScreenController(Stage primaryStage, Model model) {
		this.model = model;
		this.primaryStage = primaryStage;
	}

	//End Button
	public void onClickExit(ActionEvent e) {
		Platform.exit();
	}

	//SetupButton
	public void onClickSetup(ActionEvent e) {
		SetupScreenController setupScreenController = new SetupScreenController(this.primaryStage, this.model);
		SetupScreenView setupScreenView = new SetupScreenView(this.primaryStage, this.model, setupScreenController);

		setupScreenView.show();
	}

}
