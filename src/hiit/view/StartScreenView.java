package hiit.view;

import java.util.Observable;
import java.util.Observer;

import hiit.controller.SetupScreenController;
import hiit.controller.StartScreenController;
import hiit.model.Model;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartScreenView implements Observer{
	private Model model;
	private StartScreenController controller;
	private Scene scene;
	private Stage primaryStage;
	
	public StartScreenView(Stage primaryStage, Model model, StartScreenController controller) {
		this.model = model;
		this.controller = controller;
		this.primaryStage = primaryStage;
		
		final Button buttonGoToSetupScreen = new Button("SetupScreen");
		final Label startScreenTitleLabel = new Label("Title");
		final Label startScreenGuide = new Label("Guide");
		final Button buttonEnd = new Button("End");
		buttonEnd.setOnAction(e -> controller.onClickExit(e));		
		buttonGoToSetupScreen.setOnAction(e -> controller.onClickSetup(e));		
		final GridPane startPane = new GridPane();
		startPane.setGridLinesVisible(true);

		
		for (int i = 0; i < 3; i++) {
	        ColumnConstraints column = new ColumnConstraints(startPane.getMaxWidth()/3, startPane.getMaxWidth()/3, Double.MAX_VALUE);
	        column.setHgrow(Priority.ALWAYS);
	        column.setHalignment(HPos.CENTER);
	        startPane.getColumnConstraints().add(column);
	    }
		
		RowConstraints startPaneRow1 = new RowConstraints(100, 100, Double.MAX_VALUE);
		RowConstraints startPaneRow2 = new RowConstraints(600, 100, Double.MAX_VALUE);
		RowConstraints startPaneRow3 = new RowConstraints(100, 100, Double.MAX_VALUE);
		startPaneRow1.setVgrow(Priority.ALWAYS);
		startPaneRow1.setValignment(VPos.CENTER);
		startPaneRow2.setVgrow(Priority.ALWAYS);
		startPaneRow2.setValignment(VPos.CENTER);
		startPaneRow3.setVgrow(Priority.ALWAYS);
		startPaneRow3.setValignment(VPos.CENTER);
	    startPane.getRowConstraints().addAll(startPaneRow1, startPaneRow2, startPaneRow3);
		startPane.add(startScreenTitleLabel, 0, 0, 3, 1);
		startPane.add(startScreenGuide, 0, 1, 3, 1);
		startPane.add(buttonEnd, 0, 2);
		startPane.add(buttonGoToSetupScreen, 2, 2);
		
		VBox layoutStart = new VBox();
		layoutStart.getChildren().addAll(startPane);
		scene = new Scene(layoutStart, 600, 800);
		this.primaryStage.setScene(scene);
	}
	
	public void show()
	{
		this.primaryStage.show();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
}
