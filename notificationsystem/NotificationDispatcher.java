package notificationsystem;

import notificationsystem.entities.Channel;
import notificationsystem.entities.NotificationMessage;
import notificationsystem.entities.NotificationSender;
import notificationsystem.entities.NotificationSenderFactory;
import notificationsystem.entities.User;

public class NotificationDispatcher {
    
    NotificationSenderFactory senderFactory;
    public NotificationDispatcher() {
        this.senderFactory = new NotificationSenderFactory();
    }

    public void send(User user, NotificationMessage msg, Channel channel) {
        if (msg == null || channel == null) {
            System.out.println("Invalid notification");
            return;
        }

        NotificationSender notificationSender = senderFactory.getMessageSender(channel);
        notificationSender.send(user, msg);
    }
}
