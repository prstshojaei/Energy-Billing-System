package application.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import application.ElectricityBill;

public class ElectricityBillTest {

    @Test
    void shouldMatchSampleStatement_electricity() {
        int days = 33;

        ElectricityBill e = new ElectricityBill(
                40470.637, 40516.687,
                37386.998, 37623.210,
                22.63,   // standing charge (p/day)
                19.349,  // unit price (p)
                days
        );

        double pence = e.calculateBill();
        double pounds = pence / 100.0;

        assertEquals(65.18, pounds, 0.0001);
    }
}
