package application;

import java.io.*;
import java.util.ArrayList;

public class DataHandler {

    private static final String CUSTOMER_CSV = "customers.csv";

    // ---------- SAVE ----------
    public static void saveCustomers(ArrayList<Customer> customers) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CUSTOMER_CSV))) {
            pw.println("id,name,phone,address");

            for (Customer c : customers) {
                pw.println(
                    escape(c.getId()) + "," +
                    escape(c.getName()) + "," +
                    escape(c.getPhone()) + "," +
                    escape(c.getAddress())
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving customers.csv: " + e.getMessage());
        }
    }

    // ---------- LOAD ----------
    public static ArrayList<Customer> loadCustomers() {
        File f = new File(CUSTOMER_CSV);
        ArrayList<Customer> list = new ArrayList<>();

        if (!f.exists()) {
            return list;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_CSV))) {
            String line = br.readLine(); // header

            while ((line = br.readLine()) != null) {
                String[] parts = splitCsvLine(line);

                if (parts.length >= 4) {
                    String id = unescape(parts[0]);
                    String name = unescape(parts[1]);
                    String phone = unescape(parts[2]);
                    String address = unescape(parts[3]);

                    list.add(new Customer(id, name, phone, address));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading customers.csv: " + e.getMessage());
        }

        return list;
    }

    // ---------- helpers ----------
    private static String escape(String s) {
        if (s == null) return "";
        if (s.contains(",") || s.contains("\"")) {
            s = s.replace("\"", "\"\"");
            return "\"" + s + "\"";
        }
        return s;
    }

    private static String unescape(String s) {
        s = s.trim();
        if (s.startsWith("\"") && s.endsWith("\"")) {
            s = s.substring(1, s.length() - 1);
            s = s.replace("\"\"", "\"");
        }
        return s;
    }

    private static String[] splitCsvLine(String line) {
        ArrayList<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);

            if (ch == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (ch == ',' && !inQuotes) {
                parts.add(current.toString());
                current.setLength(0);
            } else {
                current.append(ch);
            }
        }
        parts.add(current.toString());

        return parts.toArray(new String[0]);
    }
}
