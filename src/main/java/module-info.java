module vidmot.floskur {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens vidmot to javafx.fxml;
    exports vidmot;
}
