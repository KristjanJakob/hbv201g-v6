package vidmot;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class HistoryController {

    @FXML
    private ListView<String> fxListi;

    public void setHistory(List<String> history) {
        fxListi.getItems().addAll(history);
    }

    @FXML
    private void onLoka() {
        Stage stage = (Stage) fxListi.getScene().getWindow();
        stage.close();
    }
}

