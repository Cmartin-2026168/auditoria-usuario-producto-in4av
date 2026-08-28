/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.camilomartin.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.camilomartin.system.service.UserService;
import org.camilomartin.system.service.UserStatus;
import org.camilomartin.system.utils.AlertInformation;
import org.camilomartin.system.utils.Validations;
import org.camilomartin.system.utils.ViewFactory;

public class RegisterController implements Initializable {

    /**
     *
     * @author informatica
     */
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private PasswordField PwdConfirmPassword;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnCancelRegister;
    private Validations validate = new Validations();
    private AlertInformation alertInfo = new AlertInformation();
    private UserService userService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void onCancelRegister(MouseEvent event) {
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }

    @FXML
    public void onRegisterUser(MouseEvent event) {
        // 1. Validate Email
        String email = txtEmail.getText().trim();
        boolean validEmail = validate.validateEmail(email);
        if (!validEmail) {
            alertInfo.viewAlert("Formato de correo inválido", "Error de Campo", "Corrija el correo electrónico", "error");
            return; // Stop execution if email is invalid
        }

        // 2. Retrieve other fields
        String user = txtUserName.getText().trim();
        String name = txtName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String password = pwdPassword.getText().trim();
        String confirmPassword = PwdConfirmPassword.getText().trim();

        // 3. Validate that no fields are empty
        if (validate.validateTextFieldEmpty(user)
                || validate.validateTextFieldEmpty(name)
                || validate.validateTextFieldEmpty(lastName)
                || validate.validateTextFieldEmpty(password)
                || validate.validateTextFieldEmpty(confirmPassword)) {

            alertInfo.viewAlert("Campos incompletos", "Error de Campo", "Por favor, rellene todos los campos", "error");
            return; // Stop execution if any field is empty
        }

        // 4. Validate that passwords match
        if (!password.equals(confirmPassword)) {
            alertInfo.viewAlert("Contraseñas distintas", "Error de Campo", "Las contraseñas no coinciden", "error");
            return;
        }

        String msgField = "";
        if (validate.validateTextLength(user, 25) == false) {
            msgField = "El campo USUARIO es mayor a 25 letras";
        }
        if (validate.validateTextLength(name, 50) == false) {
            msgField = "El campo NOMBRES es mayor a 50 letras";
        }
        if (validate.validateTextLength(lastName, 50) == false) {
            msgField = "El campo APELLIDOS es mayor a 50 letras";
        }
        if (validate.validateTextLength(email, 50) == false) {
            msgField = "El campo CORREO es mayor a 50 letras";
        }
        if (validate.validateTextLength(password, 35) == false) {
            msgField = "El campo CONTRASEÑA es mayor a 35 letras";
        }
        if (validate.validateTextLength(confirmPassword, 35) == false) {
            msgField = "El campo CONFIRMAR CONTRASEÑA es mayor a 35 letras";
        }

        if (msgField.isEmpty() == false) {
            alertInfo.viewAlert("ERROR", "ERROR DE CAMPO", "ERROR LONGITUD DE CAMPO",
                    msgField);
            return;
        }

        if (validate.equalsText(password, confirmPassword) == false) {
            alertInfo.viewAlert("ERROR", "ERROR DE contraseña", "no coinciden las contraseñas",
                    msgField);
            return;
        }

        UserStatus status = userService.createUser(user, name, lastName, email, password);

        switch (status) {
            case USER_CREATED -> {
                alertInfo.viewAlert("Registro completado", "Éxito", "El usuario ha sido registrado correctamente", "info");
                txtName.clear();
                txtLastName.clear();
                txtUserName.clear();
                txtEmail.clear();
                pwdPassword.clear();
                PwdConfirmPassword.clear();
            }
            case ERROR_USER_CREATED ->
                alertInfo.viewAlert("Error al registrar", "Error", "Ocurrió un problema al guardar el usuario", "error");
            case EMPTY_FIELDS ->
                alertInfo.viewAlert("Campos incompletos", "Error de Campo", "Por favor, rellene todos los campos", "error");
            default -> {
            }
        }

        // 7. Clear the text fields for the next registration
        txtName.clear();
        txtLastName.clear();
        txtUserName.clear();
        txtEmail.clear();
        pwdPassword.clear();
        PwdConfirmPassword.clear();

    }

}
