package products;

public class PrintProduct {
    private final Product product;

    public PrintProduct(Product product) {
        this.product = product;
    }

    public void PrintTotalProductSales() {
        System.out.println("Total Products Sold: " + product.GetTotalProductsSold());
    }

    public void PrintAverageProductSales() {
        System.out.printf("Average Products Sold Per Product: %.2f%n",
                product.GetAverageProductSold());
    }

    public void PrintMostPopularProduct() {
        System.out.println("Most Popular Product: " + product.GetMostPopularProduct());
    }

    public void PrintSalesReport() {
        System.out.println("==============================================");
        System.out.println("             MATTRESS SALES REPORT");
        System.out.println("==============================================");

        String[] names = product.GetProductNames();
        int[][] sales = product.GetSales();

        System.out.printf("%-25s %10s %10s %10s%n",
                "Product", "Month 1", "Month 2", "Month 3");
        System.out.println("----------------------------------------------");

        for (int i = 0; i < sales.length; i++) {
            System.out.printf("%-25s", names[i]);
            for (int j = 0; j < sales[i].length; j++) {
                System.out.printf("%10d", sales[i][j]);
            }
            System.out.println();
        }

        System.out.println("----------------------------------------------");
        PrintTotalProductSales();
        PrintAverageProductSales();
        PrintMostPopularProduct();
        System.out.println("==============================================");
    }
}
