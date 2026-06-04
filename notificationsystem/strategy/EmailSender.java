package notificationsystem.strategy;

import notificationsystem.entities.NotificationMessage;
import notificationsystem.entities.User;

public class EmailSender implements NotificationSender{
     @Override

    public void send(User user, NotificationMessage message) {
        System.out.println("Sending EMAIL to " + user.getEmail());
        System.out.println("Body: " + message.getMessage());
    }
}
