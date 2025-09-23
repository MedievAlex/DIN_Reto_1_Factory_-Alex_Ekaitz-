package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FXMLWindowLoginController implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private CheckBox cbxBaseDeDatos;
    @FXML
    private CheckBox cbxFichero;
    @FXML
    private TextField txtNombreDeUsuario;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private Text lblNombreDeUsuario;
    @FXML
    private Text lblContraseña;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
       // label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
