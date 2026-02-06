package application;

public class GasBill {

    private double opening;
    private double closing;

    // inputs are pence
    private double standingCharge; // pence per day
    private double unitPrice;      // pence per kWh
    private int days;

    public GasBill(double o, double c, double sc, double up, int d) {
        opening = o;
        closing = c;
        standingCharge = sc;
        unitPrice = up;
        days = d;
    }

    private double round1(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    private double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // returns pence
    public double calculateBill() {

        // 1) units (ft^3 hundreds)
        double units = closing - opening;

        // 2) convert to m3 (then statement shows m3 rounded to 1dp)
        double m3 = units * 2.83;
        double m3Rounded = round1(m3); // like 102.2 in the statement

        // 3) convert to kWh
        double volumeCorrection = 1.02264;
        double calorificValue = 39.4;
        double kwh = (m3Rounded * volumeCorrection * calorificValue) / 3.6;

        // 4) costs (round in pounds like the statement)
        double usagePounds = round2((kwh * unitPrice) / 100.0);          // pence -> pounds then round
        double standingPounds = round2((standingCharge * days) / 100.0); // pence -> pounds then round

        double supplyPounds = round2(usagePounds + standingPounds);
        double vatPounds = round2(supplyPounds * 0.05);
        double totalPounds = round2(supplyPounds + vatPounds);

        return totalPounds * 100.0; // back to pence
    }
}
