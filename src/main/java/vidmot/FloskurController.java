package vidmot;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import vinnsla.Floskur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller klasi sem að höndlar notendaviðmót fyrir flöskumóttökuna.
 * Klasinn sér um að taka inn inntak frá notendanum og vinnur svo úr þeim.
 * **/
public class FloskurController {
    private final Floskur floskur = new Floskur();

    private int samtalsDosir = 0;
    private int samtalsPlast = 0;

    private int samtalsGler = 0;
    private int samtalsISK = 0;

    @FXML
    private Label fxISKDosir, fxISKPlast, fxISKGler, fxSumISK, fxSumInput, fxSumGreidaInput, fxSumGreidaISK;

    @FXML
    private TextField fxDosir, fxPlast, fxGler;

    @FXML
    private ListView<String> fxHistoryList;

    /** Handler fyrir að setja inn fjölda plast flaska **/
    @FXML
    protected void onPlast(ActionEvent actionEvent) {
        updatePlast();
    }

    /** Handler fyrir að setja inn fjölda dósa **/
    @FXML
    protected void onDosir(ActionEvent actionEvent) {
        updateDosir();
    }

    /** Handler fyrir að setja inn fjölda glers **/
    @FXML
    protected void onGler(ActionEvent actionEvent) {
        updateGler();
    }

    /** Handler fyrir að hreinsa tölur úr dósa og flöskusviðum **/
    @FXML
    protected void onHreinsa(ActionEvent actionEvent) {
        resetFields();
    }

    /** Handler fyrir að greiða fyrir flöskur og dósir **/
    @FXML
    protected void onGreida(ActionEvent actionEvent) {
        // Athuga hvort allir reitir eru tómir
        if (fxDosir.getText().isEmpty() && fxPlast.getText().isEmpty() && fxGler.getText().isEmpty()) {
            sýnaVilluboð("Vinsamlegast fylltu inn að minnsta kosti eina tölu áður en þú greiðir.");
            return;
        }

        // Reikna út og athuga hvort einhver gildi eru ógild
        try {
            int dosir = fxDosir.getText().isEmpty() ? 0 : Integer.parseInt(fxDosir.getText());
            int plast = fxPlast.getText().isEmpty() ? 0 : Integer.parseInt(fxPlast.getText());
            int gler = fxGler.getText().isEmpty() ? 0 : Integer.parseInt(fxGler.getText());

            if (dosir < 0 || plast < 0 || gler < 0) {
                sýnaVilluboð("Tölurnar mega ekki vera neikvæðar.");
                return;
            }

            floskur.setFjoldiDosir(dosir);
            floskur.setFjoldiFloskur(plast);
            floskur.setFjoldiGler(gler);
            uppfaeraUI();

        } catch (NumberFormatException e) {
            sýnaVilluboð("Vinsamlegast fylltu í reitina með réttum gildum.");
            return;
        }

        // Uppfæra samtals
        samtalsDosir += floskur.getFjoldiDosir();
        samtalsPlast += floskur.getFjoldiPlast();
        samtalsGler += floskur.getFjoldiGler();
        samtalsISK += floskur.getSamtalsISK();

        fxSumGreidaInput.setText(String.valueOf(samtalsDosir + samtalsPlast + samtalsGler));
        fxSumGreidaISK.setText(String.valueOf(samtalsISK));

        // Birta kvittun
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vidmot/kvittun-view.fxml"));
            Parent root = loader.load();

            KvittunController controller = loader.getController();
            controller.setjaGildi(
                    floskur.getFjoldiDosir(),
                    floskur.getFjoldiPlast(),
                    floskur.getFjoldiGler(),
                    floskur.getSamtalsISK()
            );

            Stage kvittunarGluggi = new Stage();
            kvittunarGluggi.setTitle("Kvittun");
            kvittunarGluggi.setScene(new Scene(root));
            kvittunarGluggi.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String kvittunText = String.format(
                "Ál: %d | Plast: %d | Gler: %d → Samtals: %d kr.",
                floskur.getFjoldiDosir(),
                floskur.getFjoldiPlast(),
                floskur.getFjoldiGler(),
                floskur.getSamtalsISK()
        );

        if (fxHistoryList != null) {
            fxHistoryList.getItems().add(0, kvittunText);
            if (fxHistoryList.getItems().size() > 10) {
                fxHistoryList.getItems().remove(10);
            }
        }
    }

    // Hjálparaðferð til að sýna villuboð
    private void sýnaVilluboð(String skilaboð) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Villa");
        alert.setHeaderText(null);
        alert.setContentText(skilaboð);
        alert.showAndWait();
    }

    @FXML
    private void onLoka() {
        System.exit(0);
    }

    /** Handlerar til að tékka hvort input séu valid **/
    private boolean erInntakValid() {
        return erValid(fxDosir.getText()) && erValid(fxPlast.getText()) && erValid(fxGler.getText());
    }

    private boolean erValid(String text) {
        if (text == null || text.trim().isEmpty()) return false;
        try {
            int value = Integer.parseInt(text.trim());
            return value >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    /** Handler til að búa til villuboð **/
    @FXML
    private void onStafur(KeyEvent event) {
        TextField source = (TextField) event.getSource();
        String input = source.getText();

        if (input.isEmpty()) {
            source.setStyle(null);
            return;
        }

        try {
            int fjoldi = Integer.parseInt(input);
            if (fjoldi < 0) throw new NumberFormatException("Neikvæð tala");

            source.setStyle(null);

            if (source == fxDosir) {
                floskur.setFjoldiDosir(fjoldi);
            } else if (source == fxPlast) {
                floskur.setFjoldiFloskur(fjoldi);
            } else if (source == fxGler) {
                floskur.setFjoldiGler(fjoldi);
            }

            uppfaeraUI();
        } catch (NumberFormatException e) {
            source.setStyle("-fx-border-color: red;");
        }
    }


    /** Uppfærir fjölda dósir og UI **/
    private void updateDosir() {
        try {
            int fjoldi = Integer.parseInt(fxDosir.getText());
            if (fjoldi < 0) throw new NumberFormatException("Neikvæð tala");
            floskur.setFjoldiDosir(fjoldi);
            fxDosir.setStyle(null);
            uppfaeraUI();
        } catch (NumberFormatException e) {
            /** Villuskilaboð **/
        }
    }

    /** Uppfærir fjölda flaska og UI **/
    private void updatePlast() {
        try {
            int fjoldi = Integer.parseInt(fxPlast.getText());
            if (fjoldi < 0) throw new NumberFormatException("Neikvæð tala");
            floskur.setFjoldiFloskur(fjoldi);
            fxPlast.setStyle(null);
            uppfaeraUI();
        } catch (NumberFormatException e) {
            /** Villuskilaboð **/
        }
    }

    /** Uppfærir fjölda glers og UI **/
    private void updateGler() {
        try {
            int fjoldi = Integer.parseInt(fxGler.getText());
            if (fjoldi < 0) throw new NumberFormatException("Neikvæð tala");
            floskur.setFjoldiGler(fjoldi);
            fxGler.setStyle(null);
            uppfaeraUI();
        } catch (NumberFormatException e) {
            /** Villuskilaboð **/
        }
    }

    /** Uppfærir viðmót **/
    private void resetFields() {
        fxDosir.clear();
        fxPlast.clear();
        fxGler.clear();
        floskur.hreinsa();
        fxSumGreidaISK.setText("0");
        fxSumGreidaInput.setText("0");
        uppfaeraUI();
    }

    /** Uppfærir UI með nýjum gildum **/
    private void uppfaeraUI() {
        fxISKDosir.setText(String.valueOf(floskur.getISKDosir()));
        fxISKPlast.setText(String.valueOf(floskur.getISKFloskur()));
        fxISKGler.setText(String.valueOf(floskur.getISKGler()));
        fxSumInput.setText(String.valueOf(floskur.getSamtalsMagn()));
        fxSumISK.setText(String.valueOf(floskur.getSamtalsISK()));
    }

    /** Sýnir opnunartíma **/
    @FXML
    private void onOpnunartimi(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("opnunartimi-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Opnunartími");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Sýnir síðustu greiðslur **/
    @FXML
    private void onSidustuGreidslur(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("history-view.fxml"));
            Parent root = loader.load();

            HistoryController controller = loader.getController();

            controller.setHistory(fxHistoryList.getItems());

            Stage stage = new Stage();
            stage.setTitle("Síðustu greiðslur");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
