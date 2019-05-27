package zykovdmitry.taskex.aqua.objects;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;



public class Main extends Application {

	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//
			//Настраиваем основное окно программы, подключаем css для настройки цветов блоков.
			//
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/zykovdmitry/taskex/aqua/fxml/MainStage.fxml"));
			Scene scene = new Scene(root,600,500);
			primaryStage.setMinHeight(535);
			primaryStage.setMinWidth(615);
			primaryStage.setResizable(true);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Аквариум");
			scene.getStylesheets().add("/zykovdmitry/taskex/aqua/css/stacked-bar-chart.css");
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
				
		
		launch(args);
	}
}
