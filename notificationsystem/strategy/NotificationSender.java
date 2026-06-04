package notificationsystem.strategy;

import notificationsystem.entities.NotificationMessage;
import notificationsystem.entities.User;

public interface NotificationSender {
    void send(User user, NotificationMessage message);
}
