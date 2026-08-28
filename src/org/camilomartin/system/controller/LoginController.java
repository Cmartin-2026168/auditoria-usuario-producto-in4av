package org.camilomartin.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.camilomartin.system.service.LoginStatus;
import org.camilomartin.system.service.UserService;
import org.camilomartin.system.utils.AlertInformation;
import org.camilomartin.system.utils.ViewFactory;

public class LoginController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField pwdPassword;

    private UserService userService = new UserService();
    private AlertInformation alertInfo = new AlertInformation();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void onRegister(ActionEvent e) {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.viewRegister();
    }

    @FXML
    public void onLogin(ActionEvent e) {
        String user = txtUser.getText().trim();
        String password = pwdPassword.getText().trim();

        LoginStatus status = userService.login(user, password);

        switch (status) {
            case LOGIN_SUCCESS -> {
                alertInfo.viewAlert("Bienvenido " + user, "Acceso concedido", "Inicio de sesión exitoso", "info");
                txtUser.clear();
                pwdPassword.clear();
            }
            case USER_NOT_FOUND ->
                alertInfo.viewAlert("Usuario no encontrado", "Error de acceso", "El usuario ingresado no existe", "error");
            case WRONG_PASSWORD ->
                alertInfo.viewAlert("Contraseña incorrecta", "Error de acceso", "La contraseña ingresada no es correcta", "error");
            case EMPTY_FIELDS ->
                alertInfo.viewAlert("Campos incompletos", "Error de Campo", "Por favor, ingrese usuario y contraseña", "error");
            case LOGIN_ERROR ->
                alertInfo.viewAlert("Error de conexión", "Error", "No fue posible conectarse a la base de datos", "error");
            default -> {
            }
        }
    }

}