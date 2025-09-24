package main;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Alex Irazola & Ekaitz Campo
 */
public class Main extends Application {

    @Override
public void start(Stage stage) throws Exception {
    Controller controller = new Controller();
    controller.showWindow(stage);
}

    public static void main(String[] args) {     
        launch(args);
    }
}
