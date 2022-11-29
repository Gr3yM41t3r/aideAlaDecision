module com.example.projetaidedecision {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires com.almasb.fxgl.all;

    opens com.example.projetaidedecision to javafx.fxml;
    opens com.example.projetaidedecision.Controllers to javafx.fxml;
    opens com.example.projetaidedecision.Models to javafx.fxml;
    exports com.example.projetaidedecision;
    exports com.example.projetaidedecision.Models;
}