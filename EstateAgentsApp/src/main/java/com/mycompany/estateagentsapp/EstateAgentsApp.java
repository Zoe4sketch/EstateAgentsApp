package com.mycompany.estateagentsapp;

public class EstateAgentsApp {

    public static void main(String[] args) {
        // Q.1.1: 2D array storing property sales for Jan, Feb, Mar
        String[] agents = {"Joe Bloggs", "Jane Doe"};
        String[] months = {"JAN", "FEB", "MAR"};
        
        double[][] propertySales = {
            {800000.0, 1500000.0, 2000000.0}, // Joe Bloggs
            {700000.0, 1200000.0, 1600000.0}  // Jane Doe
        };

        EstateAgent estateAgent = new EstateAgent();
        double[] totalSales = new double[agents.length];
        double[] totalCommissions = new double[agents.length];

        // Print Report Header
        System.out.println("ESTATE AGENTS SALES REPORT");
        System.out.printf("%-18s %-15s %-15s %-15s%n", "", months[0], months[1], months[2]);

        // Print Monthly Sales & Calculate Totals/Commissions
        for (int i = 0; i < agents.length; i++) {
            System.out.printf("%-18s", agents[i]);
            for (int j = 0; j < months.length; j++) {
                System.out.printf("R %-13.1f ", propertySales[i][j]);
            }
            System.out.println();

            // Q.1.2 & Q.1.3 Calculations
            totalSales[i] = estateAgent.EstateAgentSales(propertySales[i]);
            totalCommissions[i] = estateAgent.EstateAgentCommission(totalSales[i]);
        }

        System.out.println();

        // Print Total Property Sales (formatted with commas as in screenshot)
        for (int i = 0; i < agents.length; i++) {
            System.out.printf("Total property sales for %s = R %,.0f%n", agents[i], totalSales[i]);
        }

        System.out.println();

        // Print Total 2% Commission
        for (int i = 0; i < agents.length; i++) {
            System.out.printf("Sales Commission for %s = R %,.0f%n", agents[i], totalCommissions[i]);
        }

        System.out.println();

        // Print Top Performing Estate Agent
        int topAgentIndex = estateAgent.TopEstateAgent(totalSales);
        System.out.println("Top performing estate agent: " + agents[topAgentIndex]);
    }
}