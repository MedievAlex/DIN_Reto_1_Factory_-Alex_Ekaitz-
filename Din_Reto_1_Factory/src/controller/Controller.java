/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DBImplementation;
import model.ModelDAO;
import model.User;

public class Controler {
    	/**[DATABASE]**/

	// Prepare the DB Implementation
	ModelDAO dao = new DBImplementation();

	// Creates and starts the windows
	public void showWindow() {
		//MainWindow frame = new MainWindow(this);
		//frame.setVisible(true);
	}

	/**[USERS]**/
	public boolean verifyUser(User user) {
		return dao.verifyUser(user);
	}

	public boolean verifyUserPassword(User user) {
		return dao.verifyUserPassword(user);
	}

	public boolean verifyUserType(User user) {
		return dao.verifyUserType(user);
	}
	
	public User getUser(User user){
		return dao.getUser(user);
	}

	public boolean registerUser(User user) {
		return dao.registerUser(user);
	}
}
