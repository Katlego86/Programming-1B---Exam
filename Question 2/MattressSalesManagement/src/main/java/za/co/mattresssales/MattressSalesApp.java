package za.co.mattresssales;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MattressSalesApp extends JFrame {
    private final JTextField customerNameField = new JTextField(22);
    private final JComboBox<String> productCombo = new JComboBox<>(new String[]{
            "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"
    });
    private final JTextField priceField = new JTextField(22);
    private final JTextField vatField = outputField();
    private final JTextField commissionField = outputField();
    private final JTextField totalField = outputField();

    public MattressSalesApp() {
        super("Mattress Sales Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());
        buildForm();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private static JTextField outputField() {
        JTextField field = new JTextField(22);
        field.setEditable(false);
        field.setBackground(new Color(240, 240, 240));
        return field;
    }

    private void buildForm() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(22, 28, 22, 28));
        panel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(7, 7, 7, 7);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel heading = new JLabel("MATTRESS SALES");
        heading.setFont(new Font("SansSerif", Font.BOLD, 20));
        heading.setForeground(new Color(35, 75, 115));
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        panel.add(heading, c);
        c.gridwidth = 1;

        addRow(panel, c, 1, "Customer Name:", customerNameField);
        addRow(panel, c, 2, "Product Name:", productCombo);
        addRow(panel, c, 3, "Product Price (R):", priceField);
        addRow(panel, c, 4, "VAT Amount (R):", vatField);
        addRow(panel, c, 5, "Commission Amount (R):", commissionField);
        addRow(panel, c, 6, "Total Sale Amount (R):", totalField);

        JButton processButton = new JButton("Process");
        JButton clearButton = new JButton("Clear");
        processButton.addActionListener(e -> processSale());
        clearButton.addActionListener(e -> clearForm());
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        buttons.setBackground(Color.WHITE);
        buttons.add(processButton);
        buttons.add(clearButton);
        c.gridx = 0; c.gridy = 7; c.gridwidth = 2;
        panel.add(buttons, c);

        setContentPane(panel);
    }

    private void addRow(JPanel panel, GridBagConstraints c, int row, String label, Component input) {
        c.gridy = row; c.gridx = 0; c.weightx = 0;
        panel.add(new JLabel(label), c);
        c.gridx = 1; c.weightx = 1;
        panel.add(input, c);
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");
        processItem.addActionListener(e -> processSale());
        clearItem.addActionListener(e -> clearForm());
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        bar.add(fileMenu);
        bar.add(toolsMenu);
        return bar;
    }

    private void processSale() {
        String customer = customerNameField.getText().trim();
        String product = (String) productCombo.getSelectedItem();
        String rawPrice = priceField.getText().trim();

        if (customer.isEmpty()) {
            showError("Please enter the customer's name.");
            customerNameField.requestFocus();
            return;
        }
        if (rawPrice.isEmpty()) {
            showError("Please enter the product price.");
            priceField.requestFocus();
            return;
        }

        final double price;
        try {
            price = Double.parseDouble(rawPrice.replace(",", ""));
            if (!Double.isFinite(price) || price < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            showError("Enter a valid product price (zero or greater).");
            priceField.requestFocus();
            return;
        }

        ProductCalculations calculations = new ProductCalculations(price);
        double vat = calculations.GetVat();
        double commission = calculations.GetCommission();
        double total = calculations.GetTotal();

        vatField.setText(String.format("%.2f", vat));
        commissionField.setText(String.format("%.2f", commission));
        totalField.setText(String.format("%.2f", total));

        String record = String.format(
                "Date: %s%nCustomer Name: %s%nProduct Name: %s%nProduct Price: R%.2f%nVAT Amount: R%.2f%nCommission Amount: R%.2f%nTotal Sale Amount: R%.2f%n------------------------------%n",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                customer, product, price, vat, commission, total);
        try (BufferedWriter writer = Files.newBufferedWriter(
                Path.of("data.txt"), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(record);
            JOptionPane.showMessageDialog(this, "Sale processed and saved to data.txt.",
                    "Sale Saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "The sale was calculated, but the file could not be saved: " + ex.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        customerNameField.setText("");
        productCombo.setSelectedIndex(0);
        priceField.setText("");
        vatField.setText("");
        commissionField.setText("");
        totalField.setText("");
        customerNameField.requestFocus();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MattressSalesApp().setVisible(true));
    }
}
