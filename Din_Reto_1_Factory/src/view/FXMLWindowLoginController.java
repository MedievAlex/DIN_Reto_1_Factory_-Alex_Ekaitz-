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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controlador de la ventana de Login
 */
public class FXMLWindowLoginController implements Initializable {

    private Controller controller;

    @FXML private ToggleGroup Method;
    @FXML private Button btnLogin;
    @FXML private Text lblContraseña;
    @FXML private Text lblErrorMessage;
    @FXML private Text lblNombreDeUsuario;
    @FXML private Text lblTitle;
    @FXML private RadioButton rbBaseDeDatos;
    @FXML private RadioButton rbFichero;
    @FXML private PasswordField txtContraseña;
    @FXML private TextField txtNombreDeUsuario;

    /**
     * Asigna el controlador principal
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Método llamado al pulsar el botón LOGIN
     */
    @FXML
    private void openWindowShow(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WindowShow.fxml"));
            Parent root = loader.load();

            FXMLWindowShowController showController = loader.getController();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            lblErrorMessage.setText("Error al abrir la ventana.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblErrorMessage.setText("");
    }
}