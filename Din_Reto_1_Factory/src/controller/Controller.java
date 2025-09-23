/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.DBImplementation;
import model.FichImplementation;
import model.ModelDAO;
import model.User;

public class Controller {
        ModelDAO dao;
    	
        public Controller(boolean isDb) {
            if (isDb) {
                dao = new DBImplementation();
            } else {
                dao = new FichImplementation();
            }
        }

	// Creates and starts the windows
	public void showWindow() throws Exception {
            /*
            Parent root = FXMLLoader.load(getClass().getResource("/view/WindowLogin.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            */
	}

	/**[USERS]**/
	public boolean verifyUser(User user) { // Verifies that the user exists and copies the information
		return dao.verifyUser(user);
	}

	public boolean verifyUserPassword(User user) { // Verifies that the password matches
		return dao.verifyUserPassword(user);
	}

	public boolean verifyUserType(User user) { // Verify the user type
		return dao.verifyUserType(user);
	}
	
}
