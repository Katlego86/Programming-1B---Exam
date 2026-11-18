# Mattress Sales Management

A Java Swing desktop application for processing mattress sales.


## Features
- Customer name, mattress product selection, and product price input.
- VAT calculated at 15% of product price.
- Commission calculated at 5% of product price.
- Total sale calculated as price + VAT + commission.
- Process button and Tools > Process use the same processing method.
- Clear button and Tools > Clear reset all fields and select the first product.
- File > Exit closes the application.
- Each processed sale is appended to `data.txt` in the application's working directory.

## Unit tests

```bash
mvn test
```

The tests cover `GetVat_WithValidData_ReturnsCorrectValue()` and `GetTotal_WithValidData_ReturnsCorrectValue()`.

## Calculation example
For a product price of R1,000.00:
- VAT: R150.00
- Commission: R50.00
- Total sale: R1,200.00
