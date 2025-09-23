package controller;

import model.DBImplementation;
import model.FichImplementation;
import model.ModelDAO;
import model.User;

/**
 * @author Alex Irazola & Ekaitz Campo
 */
public class Controller {

    ModelDAO dao;

    /**
     * Creates one or another Implementation depending the use. If the data will
     * be used from files or database.
     *
     * @param isDb
     */
    public Controller(boolean isDb) {
        if (isDb) {
            dao = new DBImplementation();
        } else {
            dao = new FichImplementation();
        }
    }

    /**
     * Creates and starts the windows.
     *
     * @exception
     */
    public void showWindow() throws Exception {
        /*
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
         */
    }

    /**
     * Verifies if the user exists and if it does it copies all the attributes
     * to the object to return it.
     *
     * @param user
     * @return user
     */
    public User verifyUser(User user) {
        return dao.verifyUser(user);
    }

    /**
     * Verifies that the password matches returning a boolean. TRUE if they
     * match, FALSE if not.
     *
     * @param user
     * @return user
     */
    public boolean verifyUserPassword(User user) {
        return dao.verifyUserPassword(user);
    }

    /**
     * Verifies the user's type to see if its an Admin. TRUE if its an admin,
     * FALSE if not.
     *
     * @param user
     * @return user
     */
    public boolean verifyUserType(User user) {
        return dao.verifyUserType(user);
    }

}
