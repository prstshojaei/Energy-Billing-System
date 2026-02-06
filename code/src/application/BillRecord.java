package application;

public class BillRecord {

    private String customerId;
    private int days;
    private double electricityBill;
    private double gasBill;
    private double total;
    private String status; // Paid / Unpaid

    public BillRecord(String customerId, int days, double electricityBill, double gasBill) {
        this.customerId = customerId;
        this.days = days;
        this.electricityBill = electricityBill;
        this.gasBill = gasBill;
        this.total = electricityBill + gasBill;
        this.status = "Unpaid";
    }

    public String getCustomerId() { return customerId; }
    public int getDays() { return days; }
    public double getElectricityBill() { return electricityBill; }
    public double getGasBill() { return gasBill; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
