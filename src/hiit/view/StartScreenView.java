package hiit.view;


import hiit.CustomButton;
import hiit.controller.StartScreenController;
import hiit.model.Model;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class StartScreenView{
	private final double WIDTH = 900;
	private final double HEIGHT = 900;
	private final int COLUMN_COUNT = 9;
	private final int ROW_COUNT = 9;

	private Scene scene;
	private Stage primaryStage;

	public StartScreenView(Stage primaryStage, Model model, StartScreenController controller) {
		this.primaryStage = primaryStage;
		
		final GridPane gridPane = new GridPane();

		final CustomButton buttonGoToSetupScreen = new CustomButton("Setup");
		final Label startScreenTitleLabel = new Label("Getting started");
		final Label startScreenGuide = new Label("Step 1: Read this guide until the end\n\n"
				+ "Step 2: Continue to Setup Screen and start adding exercises to your workout\n\n"
				+ "Step 3: Choose how long you want to pause between every Set\n\n"
				+ "Step 4: Continue to Workout Screen\n\n" + "Step 5: Start working out and complete your first set\n\n"
				+ "Step 6: If you are done with your first set, press 'Set Done' to start the pause timer\n\n"
				+ "Step 7: Continue your next set upon completion of the pause\n\n"
				+ "Step 8: Repeat Sets 5-7 until you finish your workout\n\n" + "Have fun");
		final CustomButton buttonEnd = new CustomButton("Quit");
		
		// Handling end and setup button
		buttonEnd.setOnAction(e -> controller.onClickExit(e));
		buttonGoToSetupScreen.setOnAction(e -> controller.onClickSetup(e));
		
		// Adding css classes
		startScreenTitleLabel.getStyleClass().add("title-label");
		startScreenGuide.getStyleClass().add("guide-label");

		// Adding columns and rows to our gridPane
		double colWidth = WIDTH / COLUMN_COUNT;
		for (int i = 0; i < 9; i++) {
			ColumnConstraints column = new ColumnConstraints(colWidth, colWidth, colWidth);
			column.setHalignment(HPos.CENTER);
			gridPane.getColumnConstraints().add(column);
		}
		double rowHeight = HEIGHT / ROW_COUNT;
		for (int i = 0; i < 9; i++) {
			RowConstraints row = new RowConstraints(rowHeight, rowHeight, rowHeight);
			gridPane.getRowConstraints().add(row);
		}

		// Adding every element to the gridPane
		gridPane.add(startScreenTitleLabel, 0, 0, COLUMN_COUNT, 1);
		gridPane.add(startScreenGuide, 1, 1, COLUMN_COUNT - 2, ROW_COUNT - 2);
		gridPane.add(buttonEnd, 0, ROW_COUNT - 1, 3, 1);
		gridPane.add(buttonGoToSetupScreen, COLUMN_COUNT - 3, ROW_COUNT - 1, 3, 1);
		gridPane.getStyleClass().add("grid-pane");

		
		VBox layoutStart = new VBox();
		layoutStart.getChildren().addAll(gridPane);
		scene = new Scene(layoutStart, WIDTH, HEIGHT);
		scene.getStylesheets().add("css/style.css");
		this.primaryStage.setScene(scene);
	}

	public void show() {
		this.primaryStage.show();
	}

}
