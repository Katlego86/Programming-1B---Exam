# Mattress Sales Report - Katlego Mohlaka

A Java console application that reports mattress sales for three products over three months.


## Project structure
- `interfaces/IProduct.java`: declares the required product-report methods.
- `products/Product.java`: stores product names in a single-dimensional array and monthly sales in a two-dimensional array; performs the calculations.
- `products/PrintProduct.java`: prints the sales report.
- `mattresssalesreport/Main.java`: application entry point.
- `tests/ProductTest.java`: JUnit 5 tests for total and average sales.

## Sample data Used With The Results Shown On the Submission File
| Product | Month 1 | Month 2 | Month 3 |
|---|---:|---:|---:|
| Cloud Nine Mattress | 120 | 150 | 180 |
| Restonic Mattress | 100 | 130 | 140 |
| Sealy Mattress | 160 | 170 | 190 |

Expected results:
- Total products sold: 1,340
- Average products sold per product: 446.67
- Most popular product: Sealy Mattress

