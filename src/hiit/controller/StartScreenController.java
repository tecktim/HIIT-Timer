package hiit.controller;
import java.util.Observable;
import java.util.Observer;

import hiit.model.Model;
import hiit.view.SetupScreenView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class StartScreenController implements Observer {

	private Model model;
	private Stage primaryStage; 
	
	public StartScreenController(Stage primaryStage, Model model) {
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
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
