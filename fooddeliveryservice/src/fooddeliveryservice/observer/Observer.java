

package fooddeliveryservice.observer;

import fooddeliveryservice.entities.Order;

public interface Observer {
    void update(Order order);
}
