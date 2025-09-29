package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.User;

/**
 * @author Alex Irazola, Ekaitz Campo
 */
public class FXMLWindowShowController implements Initializable {

    private User user;

    private Label label;
    @FXML
    private Text lblNombreDeUsuario;
    @FXML
    private Text lblContraseña;
    @FXML
    private Text lblTituloNombre;
    @FXML
    private Text lblTituloApellido;
    @FXML
    private Text lblTituloContraseña;
    @FXML
    private Text lblNombre;
    @FXML
    private Text lblApellido;
    @FXML
    private Text lblErrorMessage;

    
    /**
     * Asigna el usuario.
     * @param user
     */
    public void setUser(User user) {
        this.user = user;

        lblNombreDeUsuario.setText(user.getU_username());

        lblNombre.setText(user.getU_name());
        lblApellido.setText(user.getU_lastname());
        
        switch (user.getU_type()) {
            case ADMIN:
                lblTituloContraseña.setVisible(true);
                lblContraseña.setVisible(true);
                lblContraseña.setText(user.getU_password());
                break;
            case CLIENT:
                lblTituloContraseña.setVisible(false);
                lblContraseña.setVisible(false);
                break;
        }       
    }
    
    /**
     * Añade los datos al inicializar la ventana.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
