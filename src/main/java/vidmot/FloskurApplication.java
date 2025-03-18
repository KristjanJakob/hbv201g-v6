package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 *Main klasi sem sér um að starta forritið **/
public class FloskurApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FloskurApplication.class.getResource("floskur-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Flöskumóttaka");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

