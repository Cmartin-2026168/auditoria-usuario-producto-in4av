package org.camilomartin.system.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Region;

public class AlertInformation {

    public AlertInformation() {
    }

    public void viewAlert(String message, String title, String header, String parameter) {
        AlertType type = switch (parameter.toLowerCase()) {
            case "info", "information" ->
                AlertType.INFORMATION;
            case "warn", "warning" ->
                AlertType.WARNING;
            case "error", "err" ->
                AlertType.ERROR;
            case "confirm", "confirmation" ->
                AlertType.CONFIRMATION;
            default ->
                AlertType.WARNING;
        };

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        DialogPane pane = alert.getDialogPane();
        pane.getStylesheets().add(getClass().getResource("/org/camilomartin/system/resources/styles/AlertInfoStyles.css").toExternalForm());
        pane.getStyleClass().addAll("mi-alert", type.name().toLowerCase());
        pane.setPrefWidth(360);
pane.setMaxWidth(300);
        Region icon = new Region();
        icon.getStyleClass().add("alert-icon");
        pane.setGraphic(icon);

        alert.showAndWait();
    }
}