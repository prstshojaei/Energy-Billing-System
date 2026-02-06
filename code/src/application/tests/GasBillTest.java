package application.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import application.GasBill;

public class GasBillTest {

    @Test
    void shouldMatchSampleStatement_gas() {
        int days = 33;

        GasBill g = new GasBill(
                10091.5, 10127.6,
                24.87,  // standing charge (p/day)
                3.797,  // unit price (p)
                days
        );

        double pence = g.calculateBill();
        double pounds = pence / 100.0;

        assertEquals(54.22, pounds, 0.0001);
    }
}
