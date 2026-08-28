package org.camilomartin.system.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import org.camilomartin.system.Main;

public class ViewFactory {

    private final String PATH_VIEWS = "/org/camilomartin/system/view/";

    public Scene loadFileFXML(String nameFile, int width, int height) {
        String pathOfFile = PATH_VIEWS + nameFile;
        try {
            FXMLLoader loadFXML = new FXMLLoader();

            URL urlFile = Main.class.getResource(pathOfFile);
            loadFXML.setBuilderFactory(new JavaFXBuilderFactory());
            loadFXML.setLocation(urlFile);
            return new javafx.scene.Scene(loadFXML.load(), width, height);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }

    public void loadScene(String nameFile) {
    Scene scene = null;
    try {
        switch (nameFile) {
            case "login" ->
                scene = loadFileFXML("LoginView.fxml", 800, 600);
            case "register" -> {
                SceneManager.getInstanciaSceneManager()
                        .getMainStage().setTitle("REGISTRO DE USUARIO");
                SceneManager.getInstanciaSceneManager()
                        .getMainStage().setResizable(true);
                scene = loadFileFXML("RegisterView.fxml", 800, 616);
            }
            default ->
                scene = loadFileFXML("LoginView.fxml", 800, 600);
        }
        SceneManager.getInstanciaSceneManager().changeScene(scene);
    } catch (Exception e) {
        System.err.println("Error al cargar la vista '" + nameFile + "': " + e);
        e.printStackTrace();
    }
}
    
    public void viewRegister(){
    loadScene("register");
    }

    public void viewLogin() {
    loadScene("login");
    
    
    }
}
