package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomersController {

    @FXML private TextField tfName;
    @FXML private TextField tfPhone;
    @FXML private TextField tfAddress;
    @FXML private Label lblMsg;

    @FXML private TableView<Customer> tableCustomers;
    @FXML private TableColumn<Customer, String> colId;
    @FXML private TableColumn<Customer, String> colName;
    @FXML private TableColumn<Customer, String> colPhone;
    @FXML private TableColumn<Customer, String> colAddress;

    private ObservableList<Customer> customers = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        tableCustomers.setItems(customers);

        // auto load from CSV
        customers.addAll(DataHandler.loadCustomers());
    }

    @FXML
    private void addCustomer() {
        lblMsg.setText("");

        String name = tfName.getText().trim();
        String phone = tfPhone.getText().trim();
        String address = tfAddress.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            lblMsg.setText("Please fill all fields.");
            return;
        }

        String id = generateCustomerId();

        customers.add(new Customer(id, name, phone, address));

        tfName.clear();
        tfPhone.clear();
        tfAddress.clear();

        lblMsg.setText("Customer added: " + id);
    }

    private String generateCustomerId() {
        int max = 0;
        for (Customer c : customers) {
            try {
                int num = Integer.parseInt(c.getId().substring(1));
                if (num > max) max = num;
            } catch (Exception e) {}
        }
        return String.format("C%03d", max + 1);
    }

    @FXML
    private void deleteSelected() {
        Customer selected = tableCustomers.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblMsg.setText("Select a customer first.");
            return;
        }
        customers.remove(selected);
        lblMsg.setText("Customer deleted.");
    }

    @FXML
    private void saveToFile() {
        DataHandler.saveCustomers(new ArrayList<>(customers));
        lblMsg.setText("Saved to CSV.");
    }
}
