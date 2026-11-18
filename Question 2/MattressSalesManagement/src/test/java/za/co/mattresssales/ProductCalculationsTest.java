package za.co.mattresssales;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductCalculationsTest {

    @Test
    void GetVat_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calculations = new ProductCalculations(1000.00);
        assertEquals(150.00, calculations.GetVat(), 0.001);
    }

    @Test
    void GetTotal_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calculations = new ProductCalculations(1000.00);
        assertEquals(1200.00, calculations.GetTotal(), 0.001);
    }
}
