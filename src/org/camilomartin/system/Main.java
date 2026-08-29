package org.camilomartin.system;

import javafx.application.Application;
import javafx.stage.Stage;
import org.camilomartin.system.utils.SceneManager;
import org.camilomartin.system.utils.ViewFactory;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stageRoot) {
        SceneManager.getInstanciaSceneManager().setMainStage(stageRoot);
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();

    }
}
