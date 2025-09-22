/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public interface ModelDAO {

    public boolean verifyUser(User user); // Verifies that the user exists and copies the information

    public boolean verifyUserPassword(User user); // Verifies that the password matches

    public boolean verifyUserType(User user); // Verify the user type

}
