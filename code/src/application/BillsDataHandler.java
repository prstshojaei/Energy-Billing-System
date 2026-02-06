package application;

import java.io.*;
import java.util.ArrayList;

public class BillsDataHandler {

    private static final String FILE = "bills.csv";

    public static ArrayList<BillRecord> loadBills() {
        ArrayList<BillRecord> list = new ArrayList<>();
        File f = new File(FILE);
        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            br.readLine(); // header
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 6) {
                    BillRecord b = new BillRecord(
                        p[0],                       // customerId
                        Integer.parseInt(p[1]),      // days
                        Double.parseDouble(p[2]),    // elec
                        Double.parseDouble(p[3])     // gas
                    );
                    b.setStatus(p[5]); // status
                    list.add(b);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading bills.csv: " + e.getMessage());
        }

        return list;
    }

    public static void saveBills(ArrayList<BillRecord> bills) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            pw.println("customerId,days,electricity,gas,total,status");

            for (BillRecord b : bills) {
                pw.println(
                    b.getCustomerId() + "," +
                    b.getDays() + "," +
                    b.getElectricityBill() + "," +
                    b.getGasBill() + "," +
                    b.getTotal() + "," +
                    b.getStatus()
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving bills.csv: " + e.getMessage());
        }
    }
}
