import gui.ScreenManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SimApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setScene(ScreenManager.getInstance().getLoginScene());
		primaryStage.setTitle("Sistema Integral Médico");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("/gui/img/appIcon.png"));
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
