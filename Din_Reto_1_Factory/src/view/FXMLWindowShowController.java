package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * @author Alex Irazola & Ekaitz Campo
 */
public class FXMLWindowShowController implements Initializable {
    
    private Label label;
    @FXML
    private Text lblNombreDeUsuario;
    @FXML
    private Text lblContraseña;
    @FXML
    private AnchorPane lblTituloNombre;
    @FXML
    private Button btnBack;
    @FXML
    private Text lblTituloApellido;
    @FXML
    private Text lblTituloContraseña;
    @FXML
    private Text lblNombre;
    @FXML
    private Text lblApellido;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
