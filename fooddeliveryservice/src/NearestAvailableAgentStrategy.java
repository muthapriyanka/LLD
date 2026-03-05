

import java.util.List;
import java.util.Optional;

public class NearestAvailableAgentStrategy implements DeliveryAgentStrategy {
    @Override
    public Optional<DeliveryAgent> findAgent(Order order, List<DeliveryAgent> agents) {
       Address restaurantAddress = order.getRestaurant().getAddress();
       Address userAddress = order.getUser().getAddress();
       DeliveryAgent bestAgent = null;
       double minDistance = Double.MAX_VALUE;

       for (DeliveryAgent agent : agents) {
           if (agent.isAvailable()) {
               double totalDistance = calculateTotalDistance(agent, restaurantAddress, userAddress);
               if (totalDistance < minDistance) {
                   minDistance = totalDistance;
                   bestAgent = agent;
               }
           }
       }
       if (bestAgent != null) {
           bestAgent.setAvailable(false); // Mark the agent as unavailable
       }
       return Optional.ofNullable(bestAgent);
    }

    private double calculateTotalDistance(DeliveryAgent agent, Address restaurantAddress, Address userAddress) {
        double agentToRestaurantDist = agent.getCurrentLocation().distanceTo(restaurantAddress);
        double restaurantToUserDist = restaurantAddress.distanceTo(userAddress);
        return agentToRestaurantDist + restaurantToUserDist;
    }
}
