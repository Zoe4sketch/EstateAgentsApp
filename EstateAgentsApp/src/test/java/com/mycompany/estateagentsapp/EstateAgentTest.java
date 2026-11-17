package com.mycompany.estateagentsapp;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class EstateAgentTest {

    @Test
    public void CalculateTotalSales_ReturnsTotalSales() {
        EstateAgent agent = new EstateAgent();
        double[] sales = {800000.0, 1500000.0, 2000000.0};
        double expected = 4300000.0;
        double actual = agent.EstateAgentSales(sales);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void CalculateTotalCommission_ReturnsCommission() {
        EstateAgent agent = new EstateAgent();
        double totalSales = 4300000.0;
        double expected = 86000.0;
        double actual = agent.EstateAgentCommission(totalSales);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void TopAgent_ReturnsTopPosition() {
        EstateAgent agent = new EstateAgent();
        double[] totalSales = {4300000.0, 3500000.0};
        int expected = 0; // Index 0 (Joe Bloggs) has the highest sales
        int actual = agent.TopEstateAgent(totalSales);
        assertEquals(expected, actual);
    }
}