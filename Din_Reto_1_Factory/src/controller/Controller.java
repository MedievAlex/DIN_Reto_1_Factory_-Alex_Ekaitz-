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

    private ModelDAO dao = new DBImplementation();

    /**
     * Creates one or another Implementation depending the use. If the data will be used from files or database.
     *
     * @param isDb
     */
    public void setDao(boolean isDb) {
        dao = isDb ? new DBImplementation() : new FichImplementation();
    }

    /**
     * Creates and starts the windows.
     *
     * @exception
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
     * Verifies if the user exists and if it does it copies all the attributes to the object to return it.
     *
     * @param user
     * @return user
     */
    public User verifyUser(User user) {
        return dao.verifyUser(user);
    }

    /**
     * Verifies that the password matches returning a boolean. TRUE if they match, FALSE if not.
     *
     * @param user
     * @return user
     */
    public boolean verifyUserPassword(User user) {
        return dao.verifyUserPassword(user);
    }

    /**
     * Verifies the user's type to see if its an Admin. TRUE if its an admin, FALSE if not.
     *
     * @param user
     * @return user
     */
    public boolean verifyUserType(User user) {
        return dao.verifyUserType(user);
    }

    /**
     * Obtains all the users and saves them in a file
     *
     */
    public void exportUsers() {
        ArrayList<User> usuarios = new DBImplementation().getUsers();
        new FichImplementation().saveUsers(usuarios);
    }
}
