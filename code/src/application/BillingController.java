package application;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BillingController {

    @FXML private ComboBox<String> cbCustomerId;

    @FXML private TextField tfDays;

    @FXML private TextField tfOD, tfCD, tfON, tfCN;
    @FXML private TextField tfElecStanding, tfElecUnit;

    @FXML private TextField tfGasOpen, tfGasClose, tfGasStanding, tfGasUnit;

    @FXML private Label lblElecBill, lblGasBill, lblTotal, lblMsg;

    private int lastDays = 0;
    private double lastElec = 0;
    private double lastGas = 0;

    @FXML
    public void initialize() {
        ArrayList<Customer> customers = DataHandler.loadCustomers();

        cbCustomerId.getItems().clear();
        for (Customer c : customers) {
            if (c != null && c.getId() != null && !c.getId().isEmpty()) {
                cbCustomerId.getItems().add(c.getId());
            }
        }
    }

    @FXML
    private void calculate() {

        try {
            int days = Integer.parseInt(tfDays.getText());

            ElectricityBill e = new ElectricityBill(
                    Double.parseDouble(tfOD.getText()),
                    Double.parseDouble(tfCD.getText()),
                    Double.parseDouble(tfON.getText()),
                    Double.parseDouble(tfCN.getText()),
                    Double.parseDouble(tfElecStanding.getText()),
                    Double.parseDouble(tfElecUnit.getText()),
                    days
            );

            GasBill g = new GasBill(
                    Double.parseDouble(tfGasOpen.getText()),
                    Double.parseDouble(tfGasClose.getText()),
                    Double.parseDouble(tfGasStanding.getText()),
                    Double.parseDouble(tfGasUnit.getText()),
                    days
            );

            lastDays = days;
            lastElec = e.calculateBill() / 100.0;
            lastGas  = g.calculateBill() / 100.0;

            lblElecBill.setText(String.format("£%.2f", lastElec));
            lblGasBill.setText(String.format("£%.2f", lastGas));
            lblTotal.setText(String.format("£%.2f", (lastElec + lastGas)));

            lblMsg.setText("Calculated");

        } catch (Exception ex) {
            lblMsg.setText("Invalid input");
        }
    }

    @FXML
    private void saveBill() {

        String id = cbCustomerId.getValue();

        if (id == null || id.isEmpty()) {
            lblMsg.setText("Select customer");
            return;
        }

        if (lastDays == 0) {
            lblMsg.setText("Calculate first");
            return;
        }

        BillRecord bill = new BillRecord(id, lastDays, lastElec, lastGas);

        ArrayList<BillRecord> bills = BillsDataHandler.loadBills();
        bills.add(bill);
        BillsDataHandler.saveBills(bills);

        lblMsg.setText("Bill saved");
    }
}
