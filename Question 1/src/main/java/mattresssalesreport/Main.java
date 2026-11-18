package mattresssalesreport;

import products.Product;
import products.PrintProduct;

public class Main {
    public static void main(String[] args) {
        Product product = new Product();
        PrintProduct report = new PrintProduct(product);
        report.PrintSalesReport();
    }
}
