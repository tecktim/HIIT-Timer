package hiit;

import hiit.controller.SetupScreenController;
import hiit.controller.StartScreenController;
import hiit.controller.WorkoutScreenController;
import hiit.model.Model;
import hiit.view.SetupScreenView;
import hiit.view.StartScreenView;
import hiit.view.WorkoutScreenView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Model model = new Model();
		
		StartScreenController startScreenController = new StartScreenController(primaryStage, model);
		StartScreenView startScreenView = new StartScreenView(primaryStage, model, startScreenController);
	
		startScreenView.show();
		
	}
	
	public void update(Stage primaryStage, Model model) {
		
	}

}
