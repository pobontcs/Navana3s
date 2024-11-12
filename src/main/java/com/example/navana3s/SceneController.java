package com.example.navana3s;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
public class SceneController {
    private Stage stage;
    private final HashMap<String,Parent>screenMap=new HashMap<>();
    public SceneController(Stage stage){
        this.stage=stage;
    }
    public void addScene(String name, String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        screenMap.put(name, root);
    }public void switchTo(String sceneName) {
        Parent root = screenMap.get(sceneName);
        if (root != null) {
            Scene scene = new Scene(root, 1000, 1000);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Scene not found: " + sceneName);
        }
    }



}
