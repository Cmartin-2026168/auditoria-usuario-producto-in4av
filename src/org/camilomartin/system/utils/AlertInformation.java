/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.camilomartin.system.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertInformation {

    /**
     * Public no-argument constructor
     */
    public AlertInformation() {
        // Constructor logic (if any)
    }

    /**
     * Displays a JavaFX Alert based on the provided parameters.
     *
     * @param message The content text of the alert
     * @param title The title of the alert window
     * @param header The header text of the alert
     * @param parameter The string parameter determining the AlertType
     */
    public void viewAlert(String message, String title, String header, String parameter) {

        // Switch expression using arrow-case syntax (lambda-style rules)
        AlertType type = switch (parameter.toLowerCase()) {
            case "info", "information" ->
                AlertType.INFORMATION;
            case "warn", "warning" ->
                AlertType.WARNING;
            case "error", "err" ->
                AlertType.ERROR;
            case "confirm", "confirmation" ->
                AlertType.CONFIRMATION;
//            case "none" ->
//                AlertType.NONE;
            default ->
                AlertType.WARNING;
        };

        // Create and configure the Alert object
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        // Display the alert and wait for user response
        alert.showAndWait();
    }
}
