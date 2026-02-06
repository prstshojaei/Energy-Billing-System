package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;
    @FXML private Label lblMessage;

    @FXML
    private void handleLogin() {
        String username = tfUsername.getText() == null ? "" : tfUsername.getText().trim();
        String password = pfPassword.getText() == null ? "" : pfPassword.getText();

        if (username.equals("admin") && password.equals("1234")) {
            openMainLayout();
        } else {
            lblMessage.setText("Wrong username or password");
        }
    }

    private void openMainLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainLayout.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) tfUsername.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Energy Billing System");

        } catch (Exception e) {
            e.printStackTrace();
            lblMessage.setText("Could not open main page.");
        }
    }
}
