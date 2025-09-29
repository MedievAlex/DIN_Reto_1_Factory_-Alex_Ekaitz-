package model;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Alex Irazola & Ekaitz Campo
 */
public class FichImplementation implements ModelDAO {

    private final File fichUsuarios = new File("Usuarios.obj");

    /**
     * Verifica si el usuario dado es correcto.
     * 
     * @param username
     * @return exists
     */
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

    /**
     * Verifica si la contraseña es correcta dado un usuario.
     * 
     * @param username
     * @param password
     * @return valid
     */
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
     * @return user
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

    /**
     * Obtiene todos los usuarios de la base de datos.
     * 
     * @param users
     */
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