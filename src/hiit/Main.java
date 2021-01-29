package hiit;

import hiit.controller.StartScreenController;
import hiit.model.Model;
import hiit.view.StartScreenView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Icon, title and notResizable setten
		primaryStage.setResizable(false);
		primaryStage.setTitle("HIIT-Workout-Timer");
		
		// <div>Icons made by <a href="https://www.freepik.com"
		// title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/"
		// title="Flaticon">www.flaticon.com</a></div>
		// https://stackoverflow.com/questions/10121991/javafx-application-icon
		primaryStage.getIcons().add(new Image("/assets/dumbbell.png"));

		Model model = new Model();
		StartScreenController startScreenController = new StartScreenController(primaryStage, model);
		StartScreenView startScreenView = new StartScreenView(primaryStage, model, startScreenController);

		startScreenView.show();

	}

	public void update(Stage primaryStage, Model model) {

	}

}
