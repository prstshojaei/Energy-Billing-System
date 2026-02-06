package application;

public class ElectricityBill {

    private double openingDay;
    private double closingDay;
    private double openingNight;
    private double closingNight;

    // inputs are pence
    private double standingCharge; // pence per day
    private double unitPrice;      // pence per kWh
    private int days;

    public ElectricityBill(double od, double cd, double on, double cn,
                           double sc, double up, int d) {
        openingDay = od;
        closingDay = cd;
        openingNight = on;
        closingNight = cn;
        standingCharge = sc;
        unitPrice = up;
        days = d;
    }

    private double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public double calculateUsageDay() {
        return closingDay - openingDay;
    }

    public double calculateUsageNight() {
        return closingNight - openingNight;
    }

    // returns pence
    public double calculateBill() {

        double dayUnits = calculateUsageDay();
        double nightUnits = calculateUsageNight();

        // pence
        double usagePence = (dayUnits * unitPrice) + (nightUnits * unitPrice);
        double standingPence = standingCharge * days;

        // IMPORTANT: round like the statement (in pounds at each step)
        double usagePounds = round2(usagePence / 100.0);
        double standingPounds = round2(standingPence / 100.0);

        double supplyPounds = round2(usagePounds + standingPounds);
        double vatPounds = round2(supplyPounds * 0.05);
        double totalPounds = round2(supplyPounds + vatPounds);

        return totalPounds * 100.0; // back to pence
    }
}
