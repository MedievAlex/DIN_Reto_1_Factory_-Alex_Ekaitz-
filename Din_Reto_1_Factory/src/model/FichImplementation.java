package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author Alex Irazola & Ekaitz Campo
 */
public class FichImplementation implements ModelDAO {
    File fichUsuarios = new File("Usuarios.obj");

    
    /**
     * Verifies if the user exists and if it does it copies all the attributes
     * to the object to return it.
     *
     * @param user
     * @return user
     */   
    @Override
    public User verifyUser(User user) { // Verifies that the user exists and copies the information
        ObjectInputStream ois = null; // Lectura
        boolean encontrado = false;
        boolean finArchivo = false;

        if (fichUsuarios.exists()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(fichUsuarios)); // Lectura
                while (!finArchivo) {
                    try {
                        User userDB = (User) ois.readObject();
                        if (userDB.getU_username().equals(user.getU_username()) && userDB.getU_password().equals(userDB.getU_password())) {
                            user.setU_username(user.getU_username());
                            user.setU_name(user.getU_name());
                            user.setU_password(user.getU_password());
                            user.setU_type(user.getU_type());
                        }
                    } catch (EOFException e) { // Fin del archivo alcanzado
                        finArchivo = true;
                    }
                }
                ois.close(); // Lectura (CERRAR)	 
            } catch (FileNotFoundException e) { // Excepcion no se ha encontrado el Fichero
                e.printStackTrace();
            } catch (ClassNotFoundException e) { // Excepcion no es de la misma clase o no se ha encontrado
                e.printStackTrace();
            } catch (IOException e) { // Excepcion error al acceder al fichero
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("\n[FATAL ERROR]");
            }
        } else {
            System.err.println("\n[ERROR] Fichero no encontrado.");
        }
        return user;
    }

    @Override
    public boolean verifyUserPassword(User user) { // Verifies that the password matches
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifyUserType(User user) { // Verify the user type
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
