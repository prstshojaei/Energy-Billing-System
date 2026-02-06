package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class MainLayoutController {

    @FXML
    private StackPane contentArea;

    @FXML
    public void initialize() {
        loadPage("PageHome.fxml");
    }

    @FXML
    private void showHome() {
        loadPage("PageHome.fxml");
    }

    @FXML
    private void showCustomers() {
        loadPage("PageCustomers.fxml");
    }

    @FXML
    private void showBilling() {
        loadPage("PageBilling.fxml");
    }

    @FXML
    private void showBills() {
        loadPage("PageBills.fxml");
    }

    @FXML
    private void exitApp() {
        System.exit(0);
    }

    private void loadPage(String fxmlName) {
        try {
            contentArea.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + fxmlName));
            contentArea.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
