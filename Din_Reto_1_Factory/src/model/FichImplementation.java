package model;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Alex Irazola & Ekaitz Campo
 */
public class FichImplementation implements ModelDAO {

    private final File fichUsuarios = new File("Usuarios.obj");

    @Override
    public boolean verifyUserExists(String username) {
        boolean exists = false;
        if (fichUsuarios.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichUsuarios))) {
                while (true) {
                    try {
                        User userDB = (User) ois.readObject();
                        if (userDB.getU_username().equalsIgnoreCase(username)) {
                            exists = true;
                            break;
                        }
                    } catch (EOFException eof) {
                        break; // Fin del archivo
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("[ERROR] Fichero no encontrado.");
        }
        return exists;
    }

    @Override
    public boolean verifyUserPassword(String username, String password) {
        boolean valid = false;
        if (fichUsuarios.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichUsuarios))) {
                while (true) {
                    try {
                        User userDB = (User) ois.readObject();
                        if (userDB.getU_username().equalsIgnoreCase(username) &&
                            userDB.getU_password().equals(password)) {
                            valid = true;
                            break;
                        }
                    } catch (EOFException eof) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("[ERROR] Fichero no encontrado.");
        }
        return valid;
    }

    /**
     *
     * @param username
     * @return User
     */
    @Override
    public User getUser(String username) {
        User user = null;
        if (fichUsuarios.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichUsuarios))) {
                while (true) {
                    try {
                        User userDB = (User) ois.readObject();
                        if (userDB.getU_username().equalsIgnoreCase(username)) {
                            user = userDB;
                            break;
                        }
                    } catch (EOFException eof) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("[ERROR] Fichero no encontrado.");
        }
        return user;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        if (fichUsuarios.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichUsuarios))) {
                while (true) {
                    try {
                        User userDB = (User) ois.readObject();
                        users.add(userDB);
                    } catch (EOFException eof) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("[ERROR] Fichero no encontrado.");
        }
        return users;
    }

    public void saveUsers(ArrayList<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichUsuarios))) {
            for (User user : users) {
                oos.writeObject(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}