package view;

import controller.Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Alex Irazola & Ekaitz Campo
 */
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
    
    private Controller controller;
    
    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
       // label.setText("Hello World!");
    }
    
    public void openWindowShow(ActionEvent event, Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/WindowShow.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
