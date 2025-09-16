/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public interface ModelDAO {

    public boolean verifyUser(User user);

    public boolean verifyUserPassword(User user);

    public boolean verifyUserType(User user);

    public User getUser(User user);

    public boolean registerUser(User user);
}
