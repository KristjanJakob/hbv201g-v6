package vidmot;

import javafx.fxml.FXML;
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
    private int samtalsFloskur = 0;
    private int samtalsISK = 0;

    @FXML
    private Label fxISKDosir, fxISKFloskur, fxSumISK, fxSumInput, fxSumGreidaInput, fxSumGreidaISK;

    @FXML
    private TextField fxDosir, fxFloskur;

    /** Handler fyrir að setja inn fjölda flaska **/
    @FXML
    protected void onFloskur(ActionEvent actionEvent) {
        updateFloskur();
    }

    /** Handler fyrir að setja inn fjölda dósa **/
    @FXML
    protected void onDosir(ActionEvent actionEvent) {
        updateDosir();
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
        samtalsFloskur += floskur.getFjoldiFloskur();
        samtalsISK += floskur.getSamtalsISK();

        fxSumGreidaInput.setText(String.valueOf(samtalsDosir + samtalsFloskur));
        fxSumGreidaISK.setText(String.valueOf(samtalsISK));
    }

    /** Handler til að búa til villuboð **/
    @FXML
    private void onStafur(KeyEvent event) {
        TextField source = (TextField) event.getSource();
        try {
            int fjoldi = Integer.parseInt(source.getText());
            if (fjoldi < 0) throw new NumberFormatException("Neikvæð tala");
            source.setStyle(null);
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
    private void updateFloskur() {
        try {
            int fjoldi = Integer.parseInt(fxFloskur.getText());
            if (fjoldi < 0) throw new NumberFormatException("Neikvæð tala");
            floskur.setFjoldiFloskur(fjoldi);
            fxFloskur.setStyle(null);
            uppfaeraUI();
        } catch (NumberFormatException e) {
            /** Villuskilaboð **/
        }
    }

    /** Uppfærir viðmót **/
    private void resetFields() {
        fxDosir.clear();
        fxFloskur.clear();
        floskur.hreinsa();
        uppfaeraUI();
    }

    /** Uppfærir UI með nýjum gildum **/
    private void uppfaeraUI() {
        fxISKDosir.setText(String.valueOf(floskur.getISKDosir()));
        fxISKFloskur.setText(String.valueOf(floskur.getISKFloskur()));
        fxSumInput.setText(String.valueOf(floskur.getSamtalsMagn()));
        fxSumISK.setText(String.valueOf(floskur.getSamtalsISK()));
    }
}
