package vidmot;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import vinnsla.Floskur;
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
        samtalsDosir += floskur.getFjoldiDosir();
        samtalsPlast += floskur.getFjoldiPlast();
        samtalsGler += floskur.getFjoldiGler();
        samtalsISK += floskur.getSamtalsISK();

        fxSumGreidaInput.setText(String.valueOf(samtalsDosir + samtalsPlast + samtalsGler));
        fxSumGreidaISK.setText(String.valueOf(samtalsISK));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Kvittun");
        alert.setHeaderText("Greiðsla mótekin");
        alert.setContentText("Þú hefur fengið greitt fyrir " +
                floskur.getSamtalsMagn() + " einingar\n" + "Plast: " + floskur.getFjoldiPlast() + "\n" + "Ál: " + floskur.getFjoldiDosir() + "\n" + "Gler: " + floskur.getFjoldiGler() + "\n" + "\n" +
                "Samtals: " + floskur.getSamtalsISK() + " kr.");
        alert.showAndWait();
    }

    /** Handler til að búa til villuboð **/
    @FXML
    private void onStafur(KeyEvent event) {
        TextField source = (TextField) event.getSource();
        try {
            int fjoldi = Integer.parseInt(source.getText());
            if (fjoldi < 0) throw new NumberFormatException("Neikvæð tala");
            source.setStyle(null);
            // uppfæra gildin í Floskur og UI
            if (source == fxDosir) {
                floskur.setFjoldiDosir(fjoldi);
            } else if (source == fxPlast) {
                floskur.setFjoldiFloskur(fjoldi);
            } else if (source == fxGler) {
                floskur.setFjoldiGler(fjoldi);
            }
            uppfaeraUI();
        } catch (NumberFormatException e) {
            source.setStyle("-fx-border-color:red;");
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Opnunartími");
        alert.setHeaderText("Flöskumótttaka Reykjavík");
        alert.setContentText("Mánudaga - Föstudaga: 10:00 - 18:00\nLaugardaga: 12:00 - 16:00\nSunnudaga: Lokað");
        alert.showAndWait();
    }
}
