package estateagentgui;

public class EstateAgent implements IEstateAgent {

    @Override
    public double CalculateCommission(String propertyPrice, String agentCommission) {
        double price = Double.parseDouble(propertyPrice);
        double percentage = Double.parseDouble(agentCommission);
        return price * (percentage / 100.0);
    }

    @Override
    public boolean ValidateData(Data dataToValidate) {
        // Check that location and name are not empty
        if (dataToValidate.getAgentLocation() == null || dataToValidate.getAgentLocation().trim().isEmpty()) {
            return false;
        }
        if (dataToValidate.getAgentName() == null || dataToValidate.getAgentName().trim().isEmpty()) {
            return false;
        }

        // Check that property price and commission percentage are valid numbers > 0
        try {
            double price = Double.parseDouble(dataToValidate.getPropertyPrice());
            double commission = Double.parseDouble(dataToValidate.getCommissionPercentage());

            if (price <= 0 || commission <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}