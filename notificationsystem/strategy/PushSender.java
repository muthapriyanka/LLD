package notificationsystem.strategy;

import notificationsystem.entities.NotificationMessage;
import notificationsystem.entities.User;

public class PushSender implements NotificationSender{
    @Override

    public void send(User user, NotificationMessage message) {
        System.out.println("Sending Push to " + user.getName());
        System.out.println("Body: " + message.getMessage());
    }
}
