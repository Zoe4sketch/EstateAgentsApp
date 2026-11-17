package estateagentgui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class EstateAgentTest {

    @Test
    public void CalculateCommission_CalculatedSuccessfully() {
        EstateAgent agent = new EstateAgent();
        double expected = 80000.0;
        double actual = agent.CalculateCommission("800000", "10");
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void CalculateCommission_CalculatedUnSuccessfully() {
        EstateAgent agent = new EstateAgent();
        double incorrectExpected = 95000.0;
        double actual = agent.CalculateCommission("800000", "10");
        assertNotEquals(incorrectExpected, actual, 0.01);
    }

    @Test
    public void ValidationTest() {
        EstateAgent agent = new EstateAgent();
        // Valid data test
        Data validData = new Data("Cape Town", "Joe Bloggs", "800000", "10");
        assertTrue(agent.ValidateData(validData));

        // Invalid data test (empty agent name)
        Data invalidData = new Data("Cape Town", "", "800000", "10");
        assertFalse(agent.ValidateData(invalidData));
    }
}