package view;

import controller.Controller;
import java.io.IOException;
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

    private Controller controller;
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
    @FXML
    private Text lblTitle;
    @FXML
    private Text lblErrorMessage;

    /**
     * Sets the controller.
     *
     * @param controller
     */    
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * ...
     *
     * @param user
     */
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
       // label.setText("Hello World!");
    }

    /**
     * Opens the next window where the information will be shown.
     *
     * @param event
     * @param stage
     * @exception IOException
     */    
    public void openWindowShow(ActionEvent event, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WindowShow.fxml"));
        Parent root = loader.load();

        FXMLWindowLoginController loginController = loader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * ...
     *
     * @param url
     * @param rb
     */     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openWindowShow(ActionEvent event) {
    }
    
}
