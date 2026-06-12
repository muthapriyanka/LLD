

package fooddeliveryservice.strategy;

import java.util.List;
import java.util.Optional;

import fooddeliveryservice.entities.DeliveryAgent;
import fooddeliveryservice.entities.Order;

public interface DeliveryAgentStrategy {
    Optional<DeliveryAgent> findAgent(Order order, List<DeliveryAgent> agents);
}
