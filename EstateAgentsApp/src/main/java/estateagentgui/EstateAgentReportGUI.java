package estateagentgui;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EstateAgentReportGUI extends JFrame {

    private JComboBox<String> cmbLocation;
    private JTextField txtAgentName;
    private JTextField txtPropertyPrice;
    private JTextField txtCommissionPercentage;
    private JTextArea txtReportArea;
    private EstateAgent estateAgent;

    public EstateAgentReportGUI() {
        estateAgent = new EstateAgent();
        setTitle("Estate Agent Report");
        setSize(450, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        // Q.2.2: Menu System
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process Report");
        JMenuItem clearItem = new JMenuItem("Clear");
        JMenuItem saveItem = new JMenuItem("Save Report");
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);
        toolsMenu.add(saveItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // Main Form Panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 12));

        // Q.2.1: Combo Box and Text Fields
        inputPanel.add(new JLabel("AGENT LOCATION:"));
        String[] locations = {"Cape Town", "Durban", "Pretoria"};
        cmbLocation = new JComboBox<>(locations);
        inputPanel.add(cmbLocation);

        inputPanel.add(new JLabel("ESTATE AGENT NAME:"));
        txtAgentName = new JTextField();
        inputPanel.add(txtAgentName);

        inputPanel.add(new JLabel("PROPERTY PRICE:"));
        txtPropertyPrice = new JTextField();
        inputPanel.add(txtPropertyPrice);

        inputPanel.add(new JLabel("COMMISSION PERCENTAGE:"));
        txtCommissionPercentage = new JTextField();
        inputPanel.add(txtCommissionPercentage);

        // Report Display Panel
        JPanel reportPanel = new JPanel(new BorderLayout(5, 5));
        reportPanel.add(new JLabel("ESTATE AGENT REPORT:"), BorderLayout.NORTH);
        txtReportArea = new JTextArea(6, 30);
        txtReportArea.setEditable(false);
        reportPanel.add(new JScrollPane(txtReportArea), BorderLayout.CENTER);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(reportPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Q.2.3: Exit Action
        exitItem.addActionListener(e -> System.exit(0));

        // Q.2.4: Process Report Action
        processItem.addActionListener(e -> processReport());

        // Q.2.5: Save Report Action
        saveItem.addActionListener(e -> saveReport());

        // Q.2.6: Clear Action
        clearItem.addActionListener(e -> clearForm());
    }

    private void processReport() {
        String location = (String) cmbLocation.getSelectedItem();
        String name = txtAgentName.getText().trim();
        String priceStr = txtPropertyPrice.getText().trim();
        String commStr = txtCommissionPercentage.getText().trim();

        Data inputData = new Data(location, name, priceStr, commStr);

        if (!estateAgent.ValidateData(inputData)) {
            JOptionPane.showMessageDialog(this, 
                "Please enter valid data. Name cannot be empty, and price/commission must be greater than 0.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price = Double.parseDouble(priceStr);
        double commPercent = Double.parseDouble(commStr);
        double calculatedCommission = estateAgent.CalculateCommission(priceStr, commStr);

        // Format matching Q.2.4 Sample Screenshot
        String report = String.format(
            "AGENT LOCATION: %s%n" +
            "AGENT NAME: %s%n" +
            "PROPERTY PRICE: R %.1f%n" +
            "COMMISSION PERCENTAGE: %.1f%%%n" +
            "CALCULATED COMMISSION: R %.1f",
            location, name, price, commPercent, calculatedCommission
        );

        txtReportArea.setText(report);
    }

    private void saveReport() {
        if (txtReportArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No report to save. Please process a report first.");
            return;
        }

        // Format matching Q.2.5 Sample Screenshot (report.txt)
        try (PrintWriter writer = new PrintWriter(new FileWriter("report.txt"))) {
            writer.println("ESTATE AGENT REPORT");
            writer.println("****************************************");
            writer.println(txtReportArea.getText());
            writer.println("****************************************");
            JOptionPane.showMessageDialog(this, "Report saved successfully to report.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
        }
    }

    private void clearForm() {
        cmbLocation.setSelectedIndex(0);
        txtAgentName.setText("");
        txtPropertyPrice.setText("");
        txtCommissionPercentage.setText("");
        txtReportArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EstateAgentReportGUI().setVisible(true));
    }
}