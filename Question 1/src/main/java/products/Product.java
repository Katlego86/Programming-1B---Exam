package products;

import interfaces.IProduct;

public class Product implements IProduct {
    private final String[] productNames = {
        "Cloud Nine Mattress",
        "Restonic Mattress",
        "Sealy Mattress"
    };

    // Each row represents a product; each column represents one month.
    private final int[][] sales = {
        {120, 150, 180},
        {100, 130, 140},
        {160, 170, 190}
    };

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int[] productSales : sales) {
            for (int monthlySales : productSales) {
                total += monthlySales;
            }
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        return (double) GetTotalProductsSold() / productNames.length;
    }

    @Override
    public String GetMostPopularProduct() {
        int highestSales = -1;
        int popularIndex = 0;

        for (int i = 0; i < sales.length; i++) {
            int productTotal = 0;
            for (int monthlySales : sales[i]) {
                productTotal += monthlySales;
            }

            if (productTotal > highestSales) {
                highestSales = productTotal;
                popularIndex = i;
            }
        }
        return productNames[popularIndex];
    }

    public String[] GetProductNames() {
        return productNames.clone();
    }

    public int[][] GetSales() {
        int[][] copy = new int[sales.length][];
        for (int i = 0; i < sales.length; i++) {
            copy[i] = sales[i].clone();
        }
        return copy;
    }
}
