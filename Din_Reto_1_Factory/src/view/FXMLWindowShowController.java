package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.User;

/**
 * @author Alex Irazola & Ekaitz Campo
 */
public class FXMLWindowShowController implements Initializable {

    private User user;
    private Label label;
    @FXML private Text lblNombreDeUsuario;
    @FXML private Text lblContraseña;
    @FXML private Text lblTituloNombre;
    @FXML private Button btnBack;
    @FXML private Text lblTituloApellido;
    @FXML private Text lblTituloContraseña;
    @FXML private Text lblNombre;
    @FXML private Text lblApellido;

    public void setUser(User user) {
        this.user = user;
    }

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
public void initialize(URL url, ResourceBundle rb) {
    if (user != null) {
        lblTituloNombre.setText(user.getU_name());
        lblNombreDeUsuario.setText(user.getU_username());
        lblNombre.setText(user.getU_name());
        lblApellido.setText(user.getU_lastname());
        lblContraseña.setText(user.getU_password());
        lblTituloContraseña.setText("Contraseña:");
        lblTituloApellido.setText("Apellido:");
    }
}

}