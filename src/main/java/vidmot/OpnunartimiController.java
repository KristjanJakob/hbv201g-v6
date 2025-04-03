package vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OpnunartimiController {

    public void onLoka(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button lokaButton;

    @FXML
    private void onMouseOver() {
        lokaButton.setStyle("-fx-background-color: #d32f2f; " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 5; " +
                "-fx-padding: 8 16; " +
                "-fx-font-weight: bold;");
    }

    @FXML
    private void onMouseExit() {
        lokaButton.setStyle("-fx-background-color: #f44336; " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 5; " +
                "-fx-padding: 8 16; " +
                "-fx-font-weight: bold;");
    }

}


