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
import model.User;

/**
 * @author Alex Irazola, Ekaitz Campo
 */
public class FXMLWindowLoginController implements Initializable {

    private Controller controller;

    @FXML
    private Text lblErrorMessage;
    @FXML
    private RadioButton rbBaseDeDatos;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private TextField txtNombreDeUsuario;

    /**
     * Asigna el controlador principal.
     * @param controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Método llamado al pulsar el botón LOGIN.
     */
    @FXML
    private void openWindowShow(ActionEvent event) {
        String username = txtNombreDeUsuario.getText().trim();
        String password = txtContraseña.getText().trim();
        boolean useDb = rbBaseDeDatos.isSelected();

        controller.setDao(useDb);

        if (username.isEmpty() || password.isEmpty()) {
            lblErrorMessage.setText("Rellena todos los campos.");
            return;
        }

        if (!controller.verifyUserExists(username)) {
            lblErrorMessage.setText("Usuario no encontrado.");
            return;
        }

        if (!controller.verifyUserPassword(username, password)) {
            lblErrorMessage.setText("Contraseña incorrecta.");
            return;
        }

        User user = controller.getUser(username);
        if (user == null) {
            lblErrorMessage.setText("Error al cargar datos del usuario.");
            return;
        }

        try {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WindowShow.fxml"));
            Parent root = loader.load();   
            
            view.FXMLWindowShowController showController = loader.getController();
            showController.setUser(user);

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

    }
}
