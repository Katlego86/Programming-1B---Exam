# Mattress Sales Management

A Java Swing desktop application for processing mattress sales.

## Requirements
- JDK 17 or later
- Maven 3.8 or later (for running the unit tests)

## Run the application
From the project folder, compile and run:

```bash
mvn clean package
java -cp target/classes za.co.mattresssales.MattressSalesApp
```

Alternatively, open the folder as a Maven project in NetBeans, IntelliJ IDEA, or Eclipse and run `MattressSalesApp`.

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
Run:

```bash
mvn test
```

The tests cover `GetVat_WithValidData_ReturnsCorrectValue()` and `GetTotal_WithValidData_ReturnsCorrectValue()`.

## Calculation example
For a product price of R1,000.00:
- VAT: R150.00
- Commission: R50.00
- Total sale: R1,200.00
