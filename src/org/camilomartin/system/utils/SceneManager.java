package org.camilomartin.system.utils;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static SceneManager instanciaSceneManager;
    private Stage mainStage;

    private SceneManager() {
    }

    public static SceneManager getInstanciaSceneManager() {
        if (instanciaSceneManager == null) {
            instanciaSceneManager = new SceneManager();
        }
        return instanciaSceneManager;
    }

    public void changeScene(Scene scene) {
        try {
            mainStage.setScene(scene);
            mainStage.sizeToScene();
            mainStage.show();
        } catch (NullPointerException objetoNulo) {
            //Alert

        }

    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

}
