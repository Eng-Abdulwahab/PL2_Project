module com.example.pl2_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires jdk.unsupported.desktop;
    requires javafx.graphics;


    opens com.example.pl2_project to javafx.fxml;
    opens ClassesOfTheProject to javafx.base, javafx.fxml;
    exports com.example.pl2_project;
}