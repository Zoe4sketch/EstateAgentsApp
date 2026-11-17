package com.mycompany.estateagentsapp;

public class EstateAgent implements IEstateAgent {

    @Override
    public double EstateAgentSales(double[] propertySales) {
        double total = 0;
        for (double sale : propertySales) {
            total += sale;
        }
        return total;
    }

    @Override
    public double EstateAgentCommission(double propertySales) {
        return propertySales * 0.02; // 2% commission
    }

    @Override
    public int TopEstateAgent(double[] totalSales) {
        int topIndex = 0;
        for (int i = 1; i < totalSales.length; i++) {
            if (totalSales[i] > totalSales[topIndex]) {
                topIndex = i;
            }
        }
        return topIndex;
    }
}