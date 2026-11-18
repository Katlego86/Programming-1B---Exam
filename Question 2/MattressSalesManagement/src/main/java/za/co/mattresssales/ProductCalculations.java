package za.co.mattresssales;

/**
 *sales calculations using the supplied product price.
 */
public class ProductCalculations implements IProductCalculations {
    private final double productPrice;

    public ProductCalculations(double productPrice) {
        if (!Double.isFinite(productPrice) || productPrice < 0) {
            throw new IllegalArgumentException("Product price must be a valid, non-negative amount.");
        }
        this.productPrice = productPrice;
    }

    @Override
    public double GetVat() {
        return roundToCents(productPrice * 0.15);
    }

    @Override
    public double GetCommission() {
        return roundToCents(productPrice * 0.05);
    }

    @Override
    public double GetTotal() {
        return roundToCents(productPrice + GetVat() + GetCommission());
    }

    private double roundToCents(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }
}
