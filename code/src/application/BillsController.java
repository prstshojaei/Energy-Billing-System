package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class BillsController {

    @FXML private TableView<BillRecord> tableBills;

    @FXML private TableColumn<BillRecord, String> colCustomerId;
    @FXML private TableColumn<BillRecord, Integer> colDays;
    @FXML private TableColumn<BillRecord, Double> colElec;
    @FXML private TableColumn<BillRecord, Double> colGas;
    @FXML private TableColumn<BillRecord, Double> colTotal;
    @FXML private TableColumn<BillRecord, String> colStatus;

    @FXML private Label lblMsg;

    private ObservableList<BillRecord> bills = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("days"));
        colElec.setCellValueFactory(new PropertyValueFactory<>("electricityBill"));
        colGas.setCellValueFactory(new PropertyValueFactory<>("gasBill"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableBills.setItems(bills);
        refresh();
    }

    @FXML
    private void refresh() {
        bills.clear();
        bills.addAll(BillsDataHandler.loadBills());
        lblMsg.setText("Loaded: " + bills.size());
    }

    @FXML
    private void markPaid() {
        BillRecord selected = tableBills.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblMsg.setText("Select a bill first.");
            return;
        }

        selected.setStatus("Paid");
        BillsDataHandler.saveBills(new ArrayList<>(bills));
        tableBills.refresh();
        lblMsg.setText("Marked as Paid.");
    }

    @FXML
    private void deleteSelected() {
        BillRecord selected = tableBills.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblMsg.setText("Select a bill first.");
            return;
        }

        bills.remove(selected);
        BillsDataHandler.saveBills(new ArrayList<>(bills));
        lblMsg.setText("Deleted.");
    }
}
