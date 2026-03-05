

import java.util.List;
import java.util.Optional;

public interface DeliveryAgentStrategy {
    Optional<DeliveryAgent> findAgent(Order order, List<DeliveryAgent> agents);
}
