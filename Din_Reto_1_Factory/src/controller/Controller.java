package controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DBImplementation;
import model.FichImplementation;
import model.ModelDAO;
import model.User;
import view.FXMLWindowLoginController;

/**
 * @author Alex Irazola & Ekaitz Campo
 */
public class Controller {

    private ModelDAO dao;

    /**
     * Constructor del Controller
     */
    public Controller() {
        dao = new DBImplementation();
        saveUsers();
    }

    /**
     * Selecciona la implementación a usar: base de datos o fichero.
     *
     * @param isDb true si se quiere usar DB, false si se quiere usar fichero
     */
    public void setDao(boolean isDb) {
        dao = isDb ? new DBImplementation() : new FichImplementation();
    }

    /**
     * Crea y muestra la ventana de login.
     *
     * @param stage
     * @throws IOException
     */
    public void showWindow(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WindowLogin.fxml"));
        Parent root = loader.load();

        FXMLWindowLoginController loginController = loader.getController();
        loginController.setController(this);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Comprueba si el usuario existe en la fuente de datos.
     *
     * @param username
     * @return true si existe, false si no
     */
    public boolean verifyUserExists(String username) {
        return dao.verifyUserExists(username);
    }

    /**
     * Comprueba si la contraseña del usuario es correcta.
     *
     * @param username
     * @param password
     * @return true si coincide, false si no
     */
    public boolean verifyUserPassword(String username, String password) {
        return dao.verifyUserPassword(username, password);
    }

    /**
     * Obtiene el usuario completo con todos sus datos.
     *
     * @param username
     * @return User
     */
    public User getUser(String username) {
        return dao.getUser(username);
    }

    /**
     * Obtiene todos los usuarios desde la base de datos y los guarda en un fichero.
     */
    public void saveUsers() {
        ArrayList<User> usuarios = new DBImplementation().getUsers();
        new FichImplementation().saveUsers(usuarios);
    }
}
