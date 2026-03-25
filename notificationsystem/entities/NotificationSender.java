package notificationsystem.entities;

public interface NotificationSender {
    void send(User user, NotificationMessage message);
}
