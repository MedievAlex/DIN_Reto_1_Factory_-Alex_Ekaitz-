package main;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Alex Irazola, Ekaitz Campo
 */
public class Main extends Application {

    /**
     * Crea el controlador y llama al método para abrir la ventana de login.
     *
     * @param stage
     * @exception Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Controller controller = new Controller();
        controller.showWindow(stage);
    }

    /**
     * Main.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
